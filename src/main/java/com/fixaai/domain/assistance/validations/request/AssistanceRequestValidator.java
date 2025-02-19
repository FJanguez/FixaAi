package com.fixaai.domain.assistance.validations.request;

import com.fixaai.domain.assistance.AssistanceScheduleDTO;

public interface AssistanceRequestValidator {

    void validate(AssistanceScheduleDTO data);

}