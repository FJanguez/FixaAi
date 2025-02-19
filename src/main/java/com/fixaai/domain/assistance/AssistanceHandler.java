package com.fixaai.domain.assistance;

import com.fixaai.domain.assistance.validations.cancel.AssistanceCancellationValidator;
import com.fixaai.domain.assistance.validations.request.AssistanceRequestValidator;
import com.fixaai.domain.condominium.CondominiumRepository;
import com.fixaai.domain.exception.AssistanceValidationException;
import com.fixaai.domain.professional.Professional;
import com.fixaai.domain.professional.ProfessionalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistanceHandler {

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private List<AssistanceRequestValidator> requestValidators;

    @Autowired
    private List<AssistanceCancellationValidator> cancellationValidators;

    public AssistanceScheduleDTO schedule(@Valid AssistanceScheduleDTO assistanceScheduleData) {

        requestValidators.forEach(v -> v.validate(assistanceScheduleData));

        var professional = chooseProfessional(assistanceScheduleData);
        if (professional == null) {
            throw new AssistanceValidationException("No professionals available on that date!");
        }

        var condominium = condominiumRepository.getReferenceById(assistanceScheduleData.idCondominium());
        var assistance = new Assistance(null, condominium, professional, assistanceScheduleData.description(), assistanceScheduleData.assistanceDate(), assistanceScheduleData.specialty());

        assistanceRepository.save(assistance);

        return new AssistanceScheduleDTO(assistance);
    }

    private Professional chooseProfessional(AssistanceScheduleDTO assistanceScheduleData) {
        if (assistanceScheduleData.idProfessional() != null) {
            return professionalRepository.getReferenceById(assistanceScheduleData.idProfessional());
        }

        return professionalRepository.chooseRandomAvailableProfessionalOnDate(assistanceScheduleData.specialty(), assistanceScheduleData.assistanceDate());

    }


    public void cancel(AssistanceCancelDTO cancellingData) {
        cancellationValidators.forEach(v -> v.validate(cancellingData));

        var assistance = assistanceRepository.getReferenceById(cancellingData.idAssistance());
        assistance.cancel(cancellingData.cancellationReason());
    }
}