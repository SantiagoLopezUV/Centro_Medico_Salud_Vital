CREATE TABLE Especialidad (
    codEspecialidad SERIAL PRIMARY KEY,
    titulo VARCHAR(50)
);

INSERT INTO Especialidad (titulo) VALUES
('Medicina General'),
('Pediatría'),
('Cardiología'),
('Dermatología'),
('Ortopedia'),
('Neurología'),
('Ginecología');