CREATE TABLE Convenio (
    codConvenio SERIAL PRIMARY KEY,
    nomEmpresa VARCHAR(50),
    porcentaje REAL,
    vigente BOOLEAN
);

INSERT INTO Convenio (nomEmpresa, porcentaje, vigente) VALUES
('SaludTotal EPS', 0.35, TRUE),
('Nueva EPS', 0.30, TRUE),
('Coomeva EPS', 0.25, TRUE),
('Sura EPS', 0.20, TRUE),
('Sanitas EPS', 0.15, TRUE),
('Famisanar EPS', 0.10, TRUE),
('Compensar EPS', 0.05, TRUE);