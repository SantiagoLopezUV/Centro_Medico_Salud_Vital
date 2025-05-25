CREATE TABLE Tipo_Consulta (
    codTipoCons SERIAL PRIMARY KEY,
    nomTipoCons VARCHAR(50),
    costoConsulta REAL
);

INSERT INTO Tipo_Consulta (nomTipoCons, costoConsulta) VALUES
('General', 60000.0),
('Urgencias', 90000.0),
('Odontología', 75000.0),
('Especializada', 120000.0);