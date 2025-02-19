package com.fixaai.domain.professional;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    @Query(value = """
            select p from Professional p
                        where
                        p.active = true
                        and
                        p.specialty = :specialty
                        and
                        p.id not in(
                            select a.professional.id from Assistance a
                            where
                            a.assistanceDate = :assistanceDate
                    and
                            a.cancelled = false
                        )
                        order by rand()
                        limit 1
            """, nativeQuery = true)
    Professional chooseRandomAvailableProfessionalOnDate(@NotNull @Param("specialty")Specialty specialty, @NotNull @Future @Param("assistanceDate") LocalDateTime assistanceDate);

    Page<Professional> findAllByActiveTrue(Pageable pagination);

    @Query("""
            select p.active
            from Professional p
            where
            p.id = :id
            """)
    Optional<Boolean> findActiveById(@Param("id") Long professionalId);

}