CREATE TABLE assistances (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    condominium_id BIGINT NOT NULL,
    professional_id BIGINT NULL,
    specialty VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    scheduled_date DATETIME NULL,
    estimated_time INTEGER NULL,
    displacement_time INTEGER NULL,
    completion_date DATETIME NULL,
    status VARCHAR(50) NOT NULL,
    cancellation_reason TEXT NULL,
    cancellation_date DATETIME NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_assistances_condominium FOREIGN KEY (condominium_id) REFERENCES condominiums(id) ON DELETE CASCADE,
    CONSTRAINT fk_assistances_professional FOREIGN KEY (professional_id) REFERENCES professionals(id) ON DELETE SET NULL
);