CREATE TABLE condominiums (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    zip_code CHAR(8) NOT NULL CHECK (zip_code REGEXP '^[0-9]{8}$'),
    number SMALLINT NOT NULL,
    street VARCHAR(150) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state ENUM('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA',
                   'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN',
                   'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO') NOT NULL,
    address_complement VARCHAR(100),
    reference_point VARCHAR(100),
    active tinyint,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;