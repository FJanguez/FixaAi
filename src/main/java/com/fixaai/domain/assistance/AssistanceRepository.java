package com.fixaai.domain.assistance;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AssistanceRepository extends JpaRepository<Assistance, Long> {

    @Query(value = """
            select a from Assistance a
            where
            a.status = 'PENDENTE'
            and a.status = 'EM_PROGRESSO'
            """)
    Page<Assistance> findOpenAssistances(Pageable pagination);

    @Query(value = """
            select a from Assistance a
            join a.professional p
            where p.id = :id
            and a.status not in ('CONCLUIDO', 'CANCELADO')
            """)
    List<Assistance> findOpenAssistancesByProfessionalId(Long id);

    @Query(value = """
            select a from Assistance a
            join a.professional p
            where p.id = :id
            and a.status not in ('EM_PROGRESSO', 'PENDENTE')
            """)
    List<Assistance> findClosedAssistancesByProfessionalId(Long id);

    @Query(value = """
            select a from Assistance a
            join a.professional p
            where p.id = :id
            and a.status not in ('CONCLUIDO', 'CANCELADO')
            """)
    Boolean verifiyProfessionalWithScheduledAssistance(Long idProfessional, @Valid LocalDateTime localDateTime);
}