package com.fixaai.domain.assistance.validations.request;

import com.fixaai.domain.assistance.AssistanceScheduleDTO;
import com.fixaai.domain.exception.AssistanceValidationException;
import com.fixaai.domain.professional.ProfessionalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ActiveProfessionalValidator")
public class ActiveProfessional implements AssistanceRequestValidator {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Override
    public void validate(AssistanceScheduleDTO assistanceData) {

        var professionalIsActive = professionalRepository.findActiveById(assistanceData.idProfessional());

        if (professionalIsActive.isEmpty()) {
            throw new EntityNotFoundException("Professional with ID " + assistanceData.idProfessional() + " not found");
        }

        if (!professionalIsActive.get()) {
            throw new AssistanceValidationException("Assistance cannot be requested with an inactive professional");
        }

    }
}