package com.fixaai.repository;

import com.fixaai.domain.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
