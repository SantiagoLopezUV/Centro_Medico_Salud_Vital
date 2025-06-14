CREATE TABLE Registro_Factura_ServAdicionales (
    codServAdi INT,
    refFactura BIGINT,
    valorServAdiFac REAL,
    PRIMARY KEY (codServAdi, refFactura),
    FOREIGN KEY (codServAdi) REFERENCES Servicio_Adicional(codServAdi),
    FOREIGN KEY (refFactura) REFERENCES Factura(refFactura)
);

INSERT INTO Registro_Factura_ServAdicionales (codServAdi, refFactura, valorServAdiFac) VALUES
(1, 1, 50000),
(1, 2, 50000),
(1, 3, 50000),
(1, 8, 50000),
(1, 9, 50000),

(2, 1, 30000),
(2, 3, 30000),
(2, 2, 30000),
(2, 7, 30000),
(2, 6, 30000),
(2, 10, 30000),

(3, 2, 70000),
(3, 3, 70000),
(3, 10, 70000),
(3, 9, 70000),
(3, 8, 70000),
(3, 7, 70000),
(3, 6, 70000),
(3, 5, 70000),

(4, 1, 250000),
(4, 2, 250000),

(5, 3, 80000),

(6, 1, 60000),
(6, 2, 60000),
(6, 6, 60000),
(6, 7, 60000),
(6, 8, 60000),
(6, 9, 60000),

(7, 2, 200000),
(7, 3, 200000),
(7, 8, 200000),
(7, 9, 200000),

(8, 1, 100000),
(8, 10, 100000),

(9, 1, 40000),
(9, 3, 40000),

(10, 2, 300000),
(10, 3, 300000);