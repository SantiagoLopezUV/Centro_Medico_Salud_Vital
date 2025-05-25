CREATE TABLE Servicio_Adicional (
    codServAdi SERIAL PRIMARY KEY,
    nomServAdi VARCHAR(50),
    valorServAdiActual REAL
);

INSERT INTO Servicio_Adicional (nomServAdi, valorServAdiActual) VALUES
('Rayos X', 50000.0),
('Pruebas de sangre', 30000.0),
('Ortopedia', 70000.0),
('Resonancia Magnética', 250000.0),
('Terapia Física', 80000.0),
('Electrocardiograma', 60000.0),
('Endoscopia', 200000.0),
('Ecografía', 100000.0),
('Laboratorio Clínico', 40000.0),
('Tomografía', 300000.0);