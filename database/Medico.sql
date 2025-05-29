CREATE TABLE Medico (
    docIdentidad VARCHAR(15) PRIMARY KEY,
    tarjetaProfesional VARCHAR(15),
    codEspecialidad INTEGER,
    FOREIGN KEY (docIdentidad) REFERENCES Persona(docIdentidad),
    FOREIGN KEY (codEspecialidad) REFERENCES Especialidad(codEspecialidad)
);

INSERT INTO Medico (docIdentidad, tarjetaProfesional, codEspecialidad) VALUES
('1066890123', 'TP1234567', 1),
('1077901234', 'TP2345678', 2),
('1088012345', 'TP3456789', 3),
('1099123456', 'TP4567890', 4),
('1010234567', 'TP5678901', 5),
('1021345678', 'TP6789012', 6),
('1032456789', 'TP7890123', 7),
('1043567890', 'TP8901234', 1),
('1054678901', 'TP9012345', 2),
('1065789012', 'TP0123456', 3);