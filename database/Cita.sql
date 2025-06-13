
CREATE TABLE Cita (
    codCita BIGSERIAL PRIMARY KEY,
    estado VARCHAR(30),
    pacienteId BIGINT,
    fechaCita DATE,
    horaCita TIME,
    codTipoCons INT,
    costoConsReg REAL,
    refFactura BIGINT,
    codConvRegistrado INT,
    convTasaAplicada REAL,
    medicoId BIGINT,
    FOREIGN KEY (pacienteId) REFERENCES Paciente(docIdentidad),
    FOREIGN KEY (codTipoCons) REFERENCES Tipo_Consulta(codTipoCons),
    FOREIGN KEY (refFactura) REFERENCES Factura(refFactura),
    FOREIGN KEY (codConvRegistrado) REFERENCES Convenio(codConvenio),
    FOREIGN KEY (medicoId) REFERENCES Medico(docIdentidad)
);

INSERT INTO Cita (estado, pacienteId, fechaCita, horaCita, codTipoCons, costoConsReg, refFactura, codConvRegistrado, convTasaAplicada, medicoId) VALUES
('Pagada', 1023456789, '2025-05-15', '10:30', 1, 70000, 1, 1, 0.20, 1066890123),
('Anulado', 1045678910, '2025-05-18', '09:00', 2, 85000, NULL, 2, 0.15, 1077901234),
('Pendiente por pago', 1056789123, '2025-05-20', '14:00', 3, 60000, 2, 3, 0.10, 1088012345),
('Anulado', 1067891234, '2025-05-22', '11:15', 4, 90000, NULL, 4, 0.25, 1099123456),
('Pagada', 1078912345, '2025-05-25', '13:45', 1, 70000, 3, 5, 0.30, 1010234567),
('Anulado', 1089123457, '2025-05-27', '15:30', 2, 85000, NULL, 6, 0.35, 1021345678),
('Pendiente por pago', 1091234567, '2025-06-01', '08:00', 3, 60000, 4, 7, 0.12, 1032456789),
('Anulado', 1012345678, '2025-06-03', '12:00', 4, 90000, NULL, 1, 0.22, 1043567890),
('Pagada', 1024567891, '2025-06-06', '10:00', 1, 70000, 5, 2, 0.18, 1054678901),
('Anulado', 1035678912, '2025-06-08', '16:15', 2, 85000, NULL, 3, 0.28, 1065789012),
('Pendiente por pago', 1046789123, '2025-06-10', '09:45', 3, 60000, 6, 4, 0.14, 1066890123),
('Anulado', 1057891234, '2025-06-12', '11:30', 4, 90000, NULL, 5, 0.32, 1077901234),
('Pagada', 1068912345, '2025-06-15', '13:00', 1, 70000, 7, 6, 0.19, 1088012345),
('Anulado', 1079123456, '2025-06-18', '15:00', 2, 85000, NULL, 7, 0.27, 1099123456),
('Pendiente por pago', 1080234567, '2025-06-20', '08:30', 3, 60000, 8, 1, 0.13, 1010234567),
('Anulado', 1091345678, '2025-06-22', '12:30', 4, 90000, NULL, 2, 0.21, 1021345678),
('Pagada', 1012456789, '2025-06-25', '10:45', 1, 70000, 9, 3, 0.16, 1032456789),
('Anulado', 1023567890, '2025-06-28', '14:30', 2, 85000, NULL, 4, 0.29, 1043567890),
('Pendiente por pago', 1034678901, '2025-07-01', '09:15', 3, 60000, 10, 5, 0.11, 1054678901),
('Anulado', 1045789012, '2025-07-03', '11:45', 4, 90000, NULL, 6, 0.24, 1065789012);














