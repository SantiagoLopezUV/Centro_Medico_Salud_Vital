CREATE TABLE Medico (
    docIdentidad VARCHAR(15) PRIMARY KEY,
    tarjetaProfesional VARCHAR(15),
    codEspecialidad INTEGER,
    FOREIGN KEY (docIdentidad) REFERENCES Persona(docIdentidad),
    FOREIGN KEY (codEspecialidad) REFERENCES Especialidad(codEspecialidad)
);

INSERT INTO Medico (docIdentidad, tarjetaProfesional, codEspecialidad) VALUES
('1076543210', 'TP1234567', 1),
('1076543211', 'TP2345678', 2),
('1076543212', 'TP3456789', 3),
('1076543213', 'TP4567890', 4),
('1076543214', 'TP5678901', 5),
('1076543215', 'TP6789012', 6),
('1076543216', 'TP7890123', 7),
('1076543217', 'TP8901234', 1),
('1076543218', 'TP9012345', 2),
('1076543219', 'TP0123456', 3);