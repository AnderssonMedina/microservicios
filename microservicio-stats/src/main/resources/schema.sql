CREATE SCHEMA IF NOT EXISTS dna_analyzer;

CREATE TABLE IF NOT EXISTS dna_analyzer.tbl_ResultMutant (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dna VARCHAR(200),
    is_mutant BOOLEAN,
    date_create TIMESTAMP
    );