CREATE TABLE Registro_Factura_ServAdicionales (
    codServAdi INT,
    refFactura BIGINT,
    valorServAdiFac REAL,
    PRIMARY KEY (codServAdi, refFactura),
    FOREIGN KEY (codServAdi) REFERENCES Servicio_Adicional(codServAdi),
    FOREIGN KEY (refFactura) REFERENCES Factura(refFactura)
);

INSERT INTO Registro_Factura_ServAdicionales (codServAdi, refFactura, valorServAdiFac) VALUES
(1, 1, 50000.0), (2, 1, 30000.0),
(3, 2, 70000.0), (4, 2, 250000.0),
(5, 3, 80000.0), (6, 3, 60000.0),
(7, 4, 200000.0), (8, 4, 100000.0),
(9, 5, 40000.0), (10, 5, 300000.0),
(1, 6, 50000.0), (3, 6, 70000.0),
(5, 7, 80000.0), (7, 7, 200000.0),
(9, 8, 40000.0), (2, 8, 30000.0),
(4, 9, 250000.0), (6, 9, 60000.0),
(8, 10, 100000.0), (10, 10, 300000.0),
(1, 11, 50000.0), (2, 11, 30000.0),
(3, 12, 70000.0), (4, 12, 250000.0),
(5, 13, 80000.0), (6, 13, 60000.0),
(7, 14, 200000.0), (8, 14, 100000.0),
(9, 15, 40000.0), (10, 15, 300000.0),
(1, 16, 50000.0), (3, 16, 70000.0),
(5, 17, 80000.0), (7, 17, 200000.0),
(9, 18, 40000.0), (2, 18, 30000.0),
(4, 19, 250000.0), (6, 19, 60000.0),
(8, 20, 100000.0), (10, 20, 300000.0);
