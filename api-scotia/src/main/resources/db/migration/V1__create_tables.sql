
/*Security tables*/
CREATE TABLE IF NOT EXISTS tb_authorities(
    authority_id VARCHAR(6) NOT NULL,
    authority VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (authority_id)
);

CREATE TABLE IF NOT EXISTS tb_roles(
    role_id VARCHAR(6) NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (role_id)
);

INSERT INTO tb_authorities(authority_id, authority) VALUES
('AUT001', 'READ'),
('AUT002', 'CREATE'),
('AUT003', 'UPDATE'),
('AUT004', 'DELETE'),
('AUT005', 'REFACTOR');

INSERT INTO tb_roles (role_id, role_name) VALUES
('RLE001', 'ADMIN'),
('RLE002', 'USER');
/*END SECURITY TABLES*/

/*BUSINESS TABLES*/
CREATE TABLE IF NOT EXISTS tb_client (
    client_id VARCHAR (6) NOT NULL,
    name VARCHAR (100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR (11) NOT NULL,
    address VARCHAR (100) NOT NULL,
    email VARCHAR (50) NOT NULL UNIQUE,
    password VARCHAR (100) NOT NULL,
    PRIMARY KEY (client_id)
);

CREATE TABLE IF NOT EXISTS tb_customer_financial_info (
    info_id VARCHAR(6) NOT NULL,
    client_id VARCHAR(6) NOT NULL,
    job_type VARCHAR(30) NOT NULL,
    monthly_income DECIMAL(10, 2) NOT NULL,
    fixed_expenses DECIMAL(10, 2) NOT NULL,
    excess_payment DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (info_id),
    CONSTRAINT fk_info_client FOREIGN KEY (client_id) REFERENCES tb_client(client_id)
);

CREATE TABLE IF NOT EXISTS tb_loan (
    loan_id VARCHAR(6) NOT NULL,
    client_id VARCHAR(6) NOT NULL,
    request_type VARCHAR(20) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    request_status VARCHAR(50) NOT NULL,
    request_date DATETIME NOT NULL,
    installments INT NOT NULL,
    PRIMARY KEY (loan_id),
    CONSTRAINT fk_loan_client FOREIGN KEY (client_id) REFERENCES tb_client(client_id)
);

CREATE TABLE IF NOT EXISTS tb_client_payment (
    payment_id VARCHAR(6) NOT NULL,
    payment_date DATE,
    payment_status INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (payment_id)
);

CREATE TABLE IF NOT EXISTS tb_credit_cards(
    card_id VARCHAR(6) NOT NULL,
    client_id VARCHAR(6) NOT NULL,
    card_number BIGINT(16) NOT NULL UNIQUE,
    card_limit DECIMAL(10, 2) NOT NULL,
    expiration_date DATETIME NOT NULL,
    card_type VARCHAR(20) NOT NULL,
    PRIMARY KEY (card_id),
    CONSTRAINT fk_card_client FOREIGN KEY (client_id) REFERENCES tb_client(client_id)
);

CREATE TABLE IF NOT EXISTS tb_card_type (
    type_id VARCHAR(6) NOT NULL,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (type_id)
);

INSERT INTO tb_card_type(type_id, name) VALUES
('CRT001', 'Interbank'),
('CRT002', 'BCP'),
('CRT003', 'BBVA'),
('CRT004', 'Scotiabank'),
('CRT005', 'OH'),
('CRT006', 'CMR'),
('CRT007', 'Ripley');

CREATE TABLE IF NOT EXISTS tb_transaction(
    transaction_id VARCHAR(10) NOT NULL,
    card_id VARCHAR(6) NOT NULL,
    transaction_date DATETIME NOT NULL,
    amount DECIMAL(10, 2),
    PRIMARY KEY (transaction_id),
    CONSTRAINT fk_transaction_card FOREIGN KEY (card_id) REFERENCES tb_credit_cards(card_id)
);

CREATE TABLE IF NOT EXISTS tb_liquidity_transaction (
    liquidity_id VARCHAR(6) NOT NULL,
    card_id VARCHAR(6) NOT NULL,
    transaction_date DATETIME NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (liquidity_id),
    CONSTRAINT fk_liquidity_card FOREIGN KEY (card_id) REFERENCES tb_credit_cards(card_id)
);

/*INTERMEDIATES TABLES*/
/*Intermediates security tables*/
CREATE TABLE IF NOT EXISTS role_authority(
    role_id VARCHAR(6) NOT NULL,
    authority_id VARCHAR(6) NOT NULL,
    PRIMARY KEY (role_id, authority_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES tb_roles(role_id),
    CONSTRAINT fk_authority FOREIGN KEY (authority_id) REFERENCES tb_authorities(authority_id)
);

/*INSERT ROWS IN TABLE role_authority*/
INSERT INTO role_authority (role_id, authority_id) VALUES
('RLE001', 'AUT001'),  -- Rol ADMIN has authority READ
('RLE001', 'AUT002'),  -- Rol ADMIN has authority CREATE
('RLE001', 'AUT003'),  -- Rol ADMIN has authority UPDATE
('RLE001', 'AUT004'),  -- Rol ADMIN has authority DELETE
('RLE002', 'AUT001'),  -- Rol USER has authority READ
('RLE002', 'AUT002'),  -- Rol USER has authority CREATE
('RLE002', 'AUT003');  -- Rol USER has authority UPDATE

CREATE TABLE IF NOT EXISTS customer_role(
    client_id VARCHAR(6) NOT NULL,
    role_id VARCHAR(6) NOT NULL,
    PRIMARY KEY (client_id, role_id),
    CONSTRAINT fk_client_role_client_id FOREIGN KEY (client_id) REFERENCES tb_client(client_id),
    CONSTRAINT fk_client_role_role_id FOREIGN KEY (role_id) REFERENCES tb_roles(role_id)
);
/*End intermediates security tables*/

CREATE TABLE IF NOT EXISTS card_type(
    card_id VARCHAR(6) NOT NULL,
    type_id VARCHAR(6) NOT NULL,
    PRIMARY KEY (card_id, type_id),
    CONSTRAINT fk_card_type_card_id FOREIGN KEY (card_id) REFERENCES tb_credit_cards(card_id),
    CONSTRAINT fK_card_type_type_id FOREIGN KEY (type_id) REFERENCES tb_card_type(type_id)
);

CREATE TABLE IF NOT EXISTS loan_payment (
    loan_id VARCHAR(6) NOT NULL,
    payment_id VARCHAR(6) NOT NULL,
    PRIMARY KEY (loan_id, payment_id),
    CONSTRAINT fk_loan_payment_loan_id FOREIGN KEY (loan_id) REFERENCES tb_loan(loan_id),
    CONSTRAINT fk_loan_payment_payment_id FOREIGN KEY (payment_id) REFERENCES tb_client_payment(payment_id)
);
