package com.fixaai.domain.condominium;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CondominiumRepository extends JpaRepository<Condominium, Long> {
    Page<Condominium> findAllByActiveTrue(Pageable pagination);

    @Query("""
            select c.active
            from Condominium c
            where
            c.id = :id
            """)
    Optional<Boolean> findActiveById(@Param("id") Long condominiumId);

}