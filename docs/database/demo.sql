-- Demo SQL actualizado para el proyecto SistemaEventos-Servicio
-- Crea la base de datos `casobd` y las tablas: Evento, Local, Participante, Proveedor
-- Uso: mysql -u root -p < demo.sql

DROP DATABASE IF EXISTS casobd;
CREATE DATABASE casobd CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE casobd;

-- Tabla: Evento
CREATE TABLE Evento (
  idEvento INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  descripcion TEXT,
  fechaEvento DATE,
  ubicacion VARCHAR(255),
  estado VARCHAR(50),
  organizador VARCHAR(150),
  creado_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: Local
CREATE TABLE Local (
  idLocal INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  direccion VARCHAR(255),
  capacidad INT DEFAULT 0,
  disponibilidad VARCHAR(50),
  creado_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: Participante
CREATE TABLE Participante (
  idParticipante INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  email VARCHAR(200),
  telefono VARCHAR(50),
  idEvento INT NOT NULL,
  creado_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_participante_evento FOREIGN KEY (idEvento) REFERENCES Evento(idEvento) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla: Proveedor
CREATE TABLE Proveedor (
  idProveedor INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  tipoServicio VARCHAR(150),
  contacto VARCHAR(200),
  idEvento INT NOT NULL,
  creado_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_proveedor_evento FOREIGN KEY (idEvento) REFERENCES Evento(idEvento) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Índices útiles
CREATE INDEX idx_evento_nombre ON Evento(nombre);
CREATE INDEX idx_local_nombre ON Local(nombre);
CREATE INDEX idx_participante_email ON Participante(email);
CREATE INDEX idx_proveedor_nombre ON Proveedor(nombre);

-- Datos de ejemplo para Evento
INSERT INTO Evento (nombre, descripcion, fechaEvento, ubicacion, estado, organizador) VALUES
('Conferencia Tech 2025', 'Evento anual sobre tecnologías emergentes', '2025-11-10', 'Centro de Convenciones', 'Activo', 'TechCorp'),
('Feria de Emprendimiento', 'Feria para emprendedores locales', '2025-08-22', 'Plaza Central', 'Planeado', 'Muni');

-- Datos de ejemplo para Local
INSERT INTO Local (nombre, direccion, capacidad, disponibilidad) VALUES
('Sala A', 'Av. Principal 123', 200, 'Disponible'),
('Auditorio Norte', 'Calle Secundaria 45', 500, 'Reservado');

-- Datos de ejemplo para Participante
INSERT INTO Participante (nombre, email, telefono, idEvento) VALUES
('Luis Ramírez', 'luis.ramirez@example.com', '999111222', 1),
('Carla Mendoza', 'carla.m@example.com', '988222333', 1),
('Diego Pérez', 'diego.p@example.com', '977333444', 2);

-- Datos de ejemplo para Proveedor
INSERT INTO Proveedor (nombre, tipoServicio, contacto, idEvento) VALUES
('Catering Express', 'Catering', 'contacto@catering.example.com', 1),
('AudioMaster', 'Sonido e iluminación', 'info@audiomaster.example.com', 1),
('Stands & Co', 'Montaje de stands', 'ventas@stands.example.com', 2);

-- Mensaje final
SELECT 'Demo database casobd created with tables Evento, Local, Participante, Proveedor. Use ConexionBDMySql.obtenerConexion("casobd") to connect.' AS info;
