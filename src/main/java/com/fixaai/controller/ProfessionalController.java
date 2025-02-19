package com.fixaai.controller;

import com.fixaai.domain.assistance.Assistance;
import com.fixaai.domain.assistance.AssistanceListingDTO;
import com.fixaai.domain.assistance.AssistanceRepository;
import com.fixaai.domain.professional.*;
import com.fixaai.domain.professional.ProfessionalRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private AssistanceRepository assistanceRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid ProfessionalRegistrationDTO professionalData, UriComponentsBuilder uriBuilder){
        var professional = new Professional(professionalData);

        professionalRepository.save(professional);

        var uri = uriBuilder.path("/professionals/{id}").buildAndExpand(professional.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProfessionalDetailingDTO(professional));
    }

    @GetMapping
    public ResponseEntity<Page<ProfessionalListingDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = professionalRepository.findAllByActiveTrue(pagination).map(ProfessionalListingDTO::new);
        return ResponseEntity.ok(page);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid ProfessionalUpdateDTO professionalUpdateData) {
        var professional = professionalRepository.getReferenceById(professionalUpdateData.id());
        professional.updateInformation(professionalUpdateData);

        return ResponseEntity.ok(new ProfessionalDetailingDTO(professional));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var professional = professionalRepository.getReferenceById(id);
        professional.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var professional = professionalRepository.getReferenceById(id);
        return ResponseEntity.ok(new ProfessionalDetailingDTO(professional));
    }

    @GetMapping("/{id}/open-assistances")
    public ResponseEntity getOpenAssistances(@PathVariable Long id) {
        var assistances = assistanceRepository.findOpenAssistancesByProfessionalId(id);
        var assistancesDTO = assistances.
                stream().map(AssistanceListingDTO::new).toList();
        return ResponseEntity.ok(assistancesDTO);
    }

    @GetMapping("/{id}/closed-assistances")
    public ResponseEntity getClosedAssistances(@PathVariable Long id) {
        var assistances = assistanceRepository.findClosedAssistancesByProfessionalId(id);
        var assistancesDTO = assistances.
                stream().map(AssistanceListingDTO::new).toList();
        return ResponseEntity.ok(assistancesDTO);
    }

}