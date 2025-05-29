CREATE TABLE Medico (
    docIdentidad BIGINT PRIMARY KEY,
    tarjetaProfesional BIGINT,
    codEspecialidad INTEGER,
    FOREIGN KEY (docIdentidad) REFERENCES Persona(docIdentidad),
    FOREIGN KEY (codEspecialidad) REFERENCES Especialidad(codEspecialidad)
);

INSERT INTO Medico (docIdentidad, tarjetaProfesional, codEspecialidad) VALUES
(1066890123, 1234567, 1),
(1077901234, 2345678, 2),
(1088012345, 3456789, 3),
(1099123456, 4567890, 4),
(1010234567, 5678901, 5),
(1021345678, 6789012, 6),
(1032456789, 7890123, 7),
(1043567890, 8901234, 1),
(1054678901, 9012345, 2),
(1065789012, 0123456, 3);