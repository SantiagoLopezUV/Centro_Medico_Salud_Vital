
CREATE TABLE Factura (
    refFactura BIGSERIAL PRIMARY KEY,
    fechaFactura DATE,
    horafactura TIME,
    valorTotal REAL
);

INSERT INTO Factura (fechaFactura, horafactura, valorTotal) VALUES
('2025-05-15', '10:45', 100000.0), 
('2025-05-20', '14:15', 75000.0),  
('2025-05-25', '14:00', 70000.0),  
('2025-06-01', '08:15', 55000.0),  
('2025-06-06', '10:15', 45000.0),  
('2025-06-10', '10:00', 55000.0),  
('2025-06-15', '13:15', 70000.0),  
('2025-06-20', '08:45', 30000.0),  
('2025-06-25', '11:00', 75000.0),  
('2025-07-01', '09:30', 70000.0);  














