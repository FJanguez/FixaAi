package com.fixaai.controller;

import com.fixaai.domain.assistance.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("assistances")
public class AssistanceController {

    @Autowired
    private AssistanceHandler assistanceService;

    @Autowired
    private AssistanceRepository assistanceRepository;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AssistanceScheduleDTO scheduleData) {
        var dto = assistanceService.schedule(scheduleData);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AssistanceCancelDTO cancellingData) {
        assistanceService.cancel(cancellingData);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid AssistanceUpdateDTO assistanceUpdateData) {
        var assistance = assistanceRepository.getReferenceById(assistanceUpdateData.id());
        assistance.updateInformation(assistanceUpdateData);

        return ResponseEntity.ok(new AssistanceDetailingDTO(assistance));
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var assistance = assistanceRepository.getReferenceById(id);
        return ResponseEntity.ok(new AssistanceDetailingDTO(assistance));
    }

    @GetMapping
    public ResponseEntity<Page<AssistanceListingDTO>> listOpenAssistances(@PageableDefault(size = 10) Pageable pagination) {
        var page = assistanceRepository.findOpenAssistances(pagination).map(AssistanceListingDTO::new);
        return ResponseEntity.ok(page);
    }

}