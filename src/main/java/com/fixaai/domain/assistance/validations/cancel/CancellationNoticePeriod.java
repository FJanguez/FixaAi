package com.fixaai.domain.assistance.validations.cancel;

import com.fixaai.domain.assistance.AssistanceCancelDTO;
import com.fixaai.domain.assistance.AssistanceRepository;
import com.fixaai.domain.exception.AssistanceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("CancellationNoticePeriodValidator")
public class CancellationNoticePeriod implements AssistanceCancellationValidator {

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Override
    public void validate(AssistanceCancelDTO assistanceData) {
        var assistance = assistanceRepository.getReferenceById(assistanceData.idAssistance());
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, assistance.getScheduledDate()).toMinutes();

        if (differenceInMinutes < 120) {
            throw new AssistanceValidationException("Assistance can only be cancelled with at least 2 hours notice!");
        }
    }
}
