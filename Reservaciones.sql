-- Crear la base de datos
DROP DATABASE IF EXISTS Reservaciones;
CREATE DATABASE Reservaciones;
USE Reservaciones;

-- Crear la tabla 'empleados' con información del personal
CREATE TABLE roles (
    idRol INT AUTO_INCREMENT PRIMARY KEY, -- Clave primaria
    nombreRol VARCHAR(50) NOT NULL -- El nombre del rol
);

-- Insertar datos de roles
INSERT INTO roles (nombreRol) VALUES 
('Recepcionista'),
('Gerente'),
('Camarero');

-- Crear la tabla 'empleados' con una referencia a roles
CREATE TABLE empleados (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY, -- Clave primaria con autoincremento
    nombre VARCHAR(100), 
    puesto VARCHAR(50), 
    salario DECIMAL(10, 2),
    rol_id INT,
	password_hash VARCHAR(256),
    correo VARCHAR(100),
    FOREIGN KEY (rol_id) REFERENCES roles(idRol) -- Relación con la tabla roles
);

-- Crear la tabla 'habitaciones' para almacenar información de las habitaciones
CREATE TABLE habitaciones (
    idHabitacion INT AUTO_INCREMENT PRIMARY KEY, -- Clave primaria con autoincremento
    tipo VARCHAR(50), 
    precio DECIMAL(10, 2), 
    capacidad INT
);

-- Crear la tabla 'huespedes' para almacenar la información de los huéspedes
CREATE TABLE huespedes (
    idHuesped INT AUTO_INCREMENT PRIMARY KEY, -- Clave primaria con autoincremento
    nombre VARCHAR(100), 
    direccion VARCHAR(255), 
    telefono VARCHAR(15) 
);

-- Crear la tabla 'reservas' con claves foráneas y relaciones
CREATE TABLE reservas (
    idReserva INT AUTO_INCREMENT PRIMARY KEY, -- Clave primaria con autoincremento
    idHuesped INT, -- Clave foránea que hace referencia a la tabla huespedes
    idHabitacion INT, -- Clave foránea que hace referencia a la tabla habitaciones
    fechaReserva DATE, 
    fechaIngreso DATE, 
    fechaSalida DATE, 
    FOREIGN KEY (idHuesped) REFERENCES huespedes(idHuesped) ON DELETE CASCADE, -- Relación con huespedes
    FOREIGN KEY (idHabitacion) REFERENCES habitaciones(idHabitacion) ON DELETE SET NULL -- Relación con habitaciones
);

-- Crear la tabla 'pagos' con clave foránea a la tabla reservas
CREATE TABLE pagos (
    idPago INT AUTO_INCREMENT PRIMARY KEY, -- Clave primaria con autoincremento
    idReserva INT, -- Clave foránea que hace referencia a la tabla reservas
    monto DECIMAL(10, 2),
    fechaPago DATE, 
    FOREIGN KEY (idReserva) REFERENCES reservas(idReserva) -- Relación con reservas
);

-- Crear índices para mejorar la velocidad de búsqueda en columnas frecuentemente consultadas
CREATE INDEX idx_fechaReserva ON reservas(fechaReserva); -- Índice en la columna fechaReserva
CREATE INDEX idx_idHuesped ON reservas(idHuesped); -- Índice en la clave foránea idHuesped

-- Insertar empleados
INSERT INTO empleados (nombre, puesto, salario, rol_id) 
VALUES 
('Juan Pérez', 'Recepcionista', 1500.00, 1), -- rol_id 1 es Recepcionista
('María Gómez', 'Camarera', 1200.00, 3), -- rol_id 3 es Camarera
('Carlos Rodríguez', 'Gerente', 2500.00, 2); -- rol_id 2 es Gerente

-- Insertar habitaciones
INSERT INTO habitaciones (tipo, precio, capacidad) 
VALUES 
('Sencilla', 50.00, 1),
('Doble', 80.00, 2),
('Suite', 150.00, 4);

-- Insertar huéspedes
INSERT INTO huespedes (nombre, direccion, telefono) 
VALUES 
('Luis Martínez', 'Calle Falsa 123', '555-1234'),
('Ana López', 'Avenida Siempre Viva 456', '555-5678'),
('Pedro Sánchez', 'Calle Real 789', '555-8765');

-- Insertar reservas
INSERT INTO reservas (idHuesped, idHabitacion, fechaReserva, fechaIngreso, fechaSalida) 
VALUES 
(1, 2, '2025-02-10', '2025-02-15', '2025-02-18'),
(2, 1, '2025-02-11', '2025-02-12', '2025-02-14'),
(3, 3, '2025-02-12', '2025-02-13', '2025-02-16');

-- Insertar pagos
INSERT INTO pagos (idReserva, monto, fechaPago) 
VALUES 
(1, 240.00, '2025-02-10'),
(2, 150.00, '2025-02-11'),
(3, 450.00, '2025-02-12');

-- JOIN para obtener reservas con información del huésped:
SELECT r.idReserva, h.nombre AS huesped, r.fechaReserva, r.fechaIngreso, r.fechaSalida
FROM reservas r
JOIN huespedes h ON r.idHuesped = h.idHuesped;

-- GROUP BY y HAVING para cantidad de reservas por huésped:
SELECT h.nombre, COUNT(r.idReserva) AS cantidadReservas
FROM reservas r
JOIN huespedes h ON r.idHuesped = h.idHuesped
GROUP BY h.nombre
HAVING cantidadReservas > 2;

-- SUBQUERY para encontrar la habitación más cara:
SELECT tipo, precio
FROM habitaciones
WHERE precio = (SELECT MAX(precio) FROM habitaciones);

-- CTE para obtener reservas activas:
WITH ReservasActivas AS (
    SELECT * FROM reservas
    WHERE fechaSalida >= CURDATE()
)
SELECT * FROM ReservasActivas;

-- TRANSACTION para registrar una reserva con pago:
START TRANSACTION;
INSERT INTO reservas (idHuesped, idHabitacion, fechaReserva, fechaIngreso, fechaSalida)
VALUES (1, 3, '2025-03-01', '2025-03-05', '2025-03-10');

INSERT INTO pagos (idReserva, monto, fechaPago)
VALUES (LAST_INSERT_ID(), 500.00, '2025-03-01');
COMMIT;

-- BETWEEN para filtrar reservas en un rango de fechas:
SELECT * FROM reservas
WHERE fechaReserva BETWEEN '2025-01-01' AND '2025-12-31';

-- LIKE para buscar huéspedes por nombre:
SELECT * FROM huespedes
WHERE nombre LIKE '%Juan%';

-- UNION para combinar huéspedes y empleados:
SELECT nombre FROM huespedes
UNION
SELECT nombre FROM empleados;

-- NOT IN para mostrar habitaciones no reservadas:
SELECT * FROM habitaciones
WHERE idHabitacion NOT IN (SELECT idHabitacion FROM reservas);

-- EXPLAIN para analizar el rendimiento de una consulta:
EXPLAIN SELECT * FROM reservas
WHERE fechaReserva BETWEEN '2025-01-01' AND '2025-12-31';
