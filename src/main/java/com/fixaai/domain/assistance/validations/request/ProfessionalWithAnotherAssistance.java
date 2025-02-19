package com.fixaai.domain.assistance.validations.request;

import com.fixaai.domain.assistance.AssistanceRepository;
import com.fixaai.domain.assistance.AssistanceScheduleDTO;
import com.fixaai.domain.exception.AssistanceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ProfessionalWithAnotherAssistanceValidator")
public class ProfessionalWithAnotherAssistance implements AssistanceRequestValidator{

    @Autowired
    private AssistanceRepository assistanceRepository;

    Integer DISPLACEMENT = 30; //minutes

    public void validate(AssistanceScheduleDTO assistanceData) {
        var professionalHasAnotherAssistanceAtTheSameTime = assistanceRepository.verifiyProfessionalWithScheduledAssistance(assistanceData.idProfessional(), assistanceData.assistanceDate());

        if (professionalHasAnotherAssistanceAtTheSameTime) {
            throw new AssistanceValidationException("The professional already has another appointment scheduled for the same time!");
        }

    }
}
