package com.fixaai.controller;

import com.fixaai.domain.condominium.*;
import com.fixaai.domain.condominium.CondominiumRepository;
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
@RequestMapping("condominiums")
public class CondominiumController {

    @Autowired
    private CondominiumRepository condominiumRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CondominiumRegistrationDTO condominiumData, UriComponentsBuilder uriBuilder){
        var condominium = new Condominium(condominiumData);

        condominiumRepository.save(condominium);

        var uri = uriBuilder.path("/condominiums/{id}").buildAndExpand(condominium.getId()).toUri();
        return ResponseEntity.created(uri).body(new CondominiumDetailingDTO(condominium));
    }

    @GetMapping
    public ResponseEntity<Page<CondominiumListingDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = condominiumRepository.findAllByActiveTrue(pagination).map(CondominiumListingDTO::new);
        return ResponseEntity.ok(page);
    }

    @PatchMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid CondominiumUpdateDTO condominiumUpdateData) {
        var condominium = condominiumRepository.getReferenceById(condominiumUpdateData.id());
        condominium.updateInformation(condominiumUpdateData);

        return ResponseEntity.ok(new CondominiumDetailingDTO(condominium));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var condominium = condominiumRepository.getReferenceById(id);
        condominium.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        var condominium = condominiumRepository.getReferenceById(id);
        return ResponseEntity.ok(new CondominiumDetailingDTO(condominium));
    }

}
