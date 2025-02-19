package com.fixaai.domain.assistance.validations.request;

import com.fixaai.domain.assistance.AssistanceScheduleDTO;
import com.fixaai.domain.exception.AssistanceValidationException;
import com.fixaai.domain.condominium.CondominiumRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ActiveCondominiumValidator")
public class ActiveCondominium implements AssistanceRequestValidator{

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Override
    public void validate(AssistanceScheduleDTO assistanceData) {

        var condominiumIsActive = condominiumRepository.findActiveById(assistanceData.idCondominium());

        if(condominiumIsActive.isEmpty()){
            throw new EntityNotFoundException("Condominium with ID " + assistanceData.idCondominium() + " not found");
        }

        if(!condominiumIsActive.get()){
            throw new AssistanceValidationException("Assistance cannot be requested with an inactive condominium");
        }

    }
}