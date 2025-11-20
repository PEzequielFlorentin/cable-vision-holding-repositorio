
-- Crear base de datos
CREATE DATABASE IF NOT EXISTS cablevision_holding CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cablevision_holding;

-- =============================================================================
-- TABLAS PRINCIPALES
-- =============================================================================

-- Tabla de países
CREATE TABLE pais (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    capital VARCHAR(255) NOT NULL,
    numero_habitantes BIGINT NOT NULL,
    pib FLOAT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de áreas de negocio
CREATE TABLE area (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de empresas (subsidiarias)
CREATE TABLE empresa (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    facturacion_anual FLOAT NOT NULL,
    fecha_entrada DATE NOT NULL,
    numero_vendedores INT NOT NULL,
    pais_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (pais_id) REFERENCES pais(id)
);

-- Tabla de relación empresa-área
CREATE TABLE empresa_area (
    empresa_id BIGINT NOT NULL,
    area_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (empresa_id, area_id),
    FOREIGN KEY (empresa_id) REFERENCES empresa(id),
    FOREIGN KEY (area_id) REFERENCES area(id)
);

-- Tabla de asesores
CREATE TABLE asesor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo_asesor VARCHAR(255) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    titulacion VARCHAR(255) NOT NULL,
    fecha_inicio DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de relación asesor-áreas cubiertas
CREATE TABLE asesor_areas_cubiertas (
    asesores_id BIGINT NOT NULL,
    areas_cubiertas_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (asesores_id, areas_cubiertas_id),
    FOREIGN KEY (asesores_id) REFERENCES asesor(id),
    FOREIGN KEY (areas_cubiertas_id) REFERENCES area(id)
);

-- Tabla de relación asesor-empresas asesoradas
CREATE TABLE asesor_empresas_asesoradas (
    asesor_id BIGINT NOT NULL,
    empresas_asesoradas_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (asesor_id, empresas_asesoradas_id),
    FOREIGN KEY (asesor_id) REFERENCES asesor(id),
    FOREIGN KEY (empresas_asesoradas_id) REFERENCES empresa(id)
);

-- Tabla de vendedores
CREATE TABLE vendedor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    codigo_vendedor VARCHAR(255) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    fecha_captacion DATE,
    captador_id BIGINT,
    empresa_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (captador_id) REFERENCES vendedor(id),
    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

-- Tabla de cuentas
CREATE TABLE cuenta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    numero_cuenta VARCHAR(255) UNIQUE,
    saldo FLOAT,
    activa BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabla de transacciones
CREATE TABLE transaccion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(255),
    monto FLOAT,
    fecha DATETIME(6),
    exitosa BOOLEAN,
    cuenta_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

-- Tabla de usuarios del sistema
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    rol ENUM('ADMIN', 'ASESOR', 'VENDEDOR'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =============================================================================
-- ÍNDICES PARA OPTIMIZACIÓN
-- =============================================================================

-- Índices para país
CREATE INDEX idx_pais_nombre ON pais(nombre);

-- Índices para empresa
CREATE INDEX idx_empresa_nombre ON empresa(nombre);
CREATE INDEX idx_empresa_pais ON empresa(pais_id);

-- Índices para asesor
CREATE INDEX idx_asesor_codigo ON asesor(codigo_asesor);
CREATE INDEX idx_asesor_nombre ON asesor(nombre);

-- Índices para vendedor
CREATE INDEX idx_vendedor_codigo ON vendedor(codigo_vendedor);
CREATE INDEX idx_vendedor_empresa ON vendedor(empresa_id);
CREATE INDEX idx_vendedor_captador ON vendedor(captador_id);

-- Índices para cuenta
CREATE INDEX idx_cuenta_numero ON cuenta(numero_cuenta);
CREATE INDEX idx_cuenta_activa ON cuenta(activa);

-- Índices para transaccion
CREATE INDEX idx_transaccion_fecha ON transaccion(fecha);
CREATE INDEX idx_transaccion_cuenta ON transaccion(cuenta_id);
CREATE INDEX idx_transaccion_exitosa ON transaccion(exitosa);

-- Índices para usuario
CREATE INDEX idx_usuario_username ON usuario(username);
CREATE INDEX idx_usuario_rol ON usuario(rol);

-- =============================================================================
-- DATOS INICIALES
-- =============================================================================

-- Insertar países de operación
INSERT INTO pais (nombre, capital, numero_habitantes, pib) VALUES
('Argentina', 'Buenos Aires', 45195774, 445445.00),
('Chile', 'Santiago', 19116201, 282318.00),
('Perú', 'Lima', 32971846, 228989.00),
('Colombia', 'Bogotá', 50882884, 323803.00),
('México', 'Ciudad de México', 128932753, 1274448.00);

-- Insertar áreas de negocio
INSERT INTO area (nombre, descripcion) VALUES
('TV Cable', 'Servicios de televisión por cable'),
('Internet', 'Servicios de conectividad a internet'),
('Telefonía IP', 'Servicios de voz sobre IP'),
('Servicios Digitales', 'Plataformas y contenidos digitales'),
('Atención al Cliente', 'Soporte y servicio al cliente'),
('Ventas y Marketing', 'Gestión comercial y marketing');

-- Insertar empresas subsidiarias
INSERT INTO empresa (nombre, facturacion_anual, fecha_entrada, numero_vendedores, pais_id) VALUES
('CableVision Argentina', 150000000.00, '2010-05-15', 45, 1),
('CableVision Chile', 95000000.00, '2012-08-22', 28, 2),
('CableVision Perú', 75000000.00, '2014-03-10', 22, 3),
('CableVision Colombia', 120000000.00, '2011-11-05', 35, 4),
('CableVision México', 250000000.00, '2008-07-30', 65, 5);

-- Asignar áreas a empresas
INSERT INTO empresa_area (empresa_id, area_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
(2, 1), (2, 2), (2, 3), (2, 5), (2, 6),
(3, 1), (3, 2), (3, 5), (3, 6),
(4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6),
(5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6);

-- Insertar asesores
INSERT INTO asesor (codigo_asesor, nombre, direccion, titulacion, fecha_inicio) VALUES
('AS-001', 'María González', 'Av. Libertador 1234, Buenos Aires', 'Ingeniería en Telecomunicaciones', '2018-03-15'),
('AS-002', 'Carlos Rodríguez', 'Calle 45 #67-89, Santiago', 'MBA en Gestión Empresarial', '2019-07-22'),
('AS-003', 'Ana Martínez', 'Jr. Lima 234, Lima', 'Administración de Empresas', '2020-01-10'),
('AS-004', 'Pedro López', 'Carrera 15 #23-45, Bogotá', 'Ingeniería de Sistemas', '2017-11-05'),
('AS-005', 'Laura Hernández', 'Paseo Reforma 789, CDMX', 'Marketing Digital', '2021-05-30');

-- Asignar áreas a asesores
INSERT INTO asesor_areas_cubiertas (asesores_id, areas_cubiertas_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 6),
(3, 2), (3, 4),
(4, 1), (4, 2), (4, 3), (4, 4),
(5, 4), (5, 6);

-- Asignar empresas a asesores
INSERT INTO asesor_empresas_asesoradas (asesor_id, empresas_asesoradas_id) VALUES
(1, 1), (1, 2),
(2, 2), (2, 3),
(3, 3), (3, 4),
(4, 4), (4, 5),
(5, 1), (5, 5);

-- Insertar usuarios del sistema
INSERT INTO usuario (username, password, rol) VALUES
('admin', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'ADMIN'), -- password: admin123
('asesor1', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'ASESOR'),
('vendedor1', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'VENDEDOR');

-- =============================================================================
-- VISTAS PARA REPORTING
-- =============================================================================

-- Vista de resumen por empresa
CREATE VIEW vista_resumen_empresas AS
SELECT 
    e.nombre as empresa,
    p.nombre as pais,
    p.capital,
    e.facturacion_anual,
    e.numero_vendedores,
    COUNT(DISTINCT a.id) as cantidad_asesores,
    COUNT(DISTINCT v.id) as cantidad_vendedores
FROM empresa e
JOIN pais p ON e.pais_id = p.id
LEFT JOIN asesor_empresas_asesoradas aea ON e.id = aea.empresas_asesoradas_id
LEFT JOIN asesor a ON aea.asesor_id = a.id
LEFT JOIN vendedor v ON e.id = v.empresa_id
GROUP BY e.id, e.nombre, p.nombre, p.capital, e.facturacion_anual, e.numero_vendedores;

-- Vista de asesores con áreas cubiertas
CREATE VIEW vista_asesores_areas AS
SELECT 
    a.codigo_asesor,
    a.nombre as asesor,
    a.titulacion,
    GROUP_CONCAT(DISTINCT ar.nombre SEPARATOR ', ') as areas_cubiertas,
    COUNT(DISTINCT e.id) as empresas_asesoradas
FROM asesor a
LEFT JOIN asesor_areas_cubiertas aac ON a.id = aac.asesores_id
LEFT JOIN area ar ON aac.areas_cubiertas_id = ar.id
LEFT JOIN asesor_empresas_asesoradas aea ON a.id = aea.asesor_id
LEFT JOIN empresa e ON aea.empresas_asesoradas_id = e.id
GROUP BY a.id, a.codigo_asesor, a.nombre, a.titulacion;

-- =============================================================================
-- PROCEDIMIENTOS ALMACENADOS
-- =============================================================================

-- Procedimiento para calcular estadísticas por país
DELIMITER //
CREATE PROCEDURE sp_estadisticas_por_pais()
BEGIN
    SELECT 
        p.nombre as pais,
        COUNT(DISTINCT e.id) as cantidad_empresas,
        SUM(e.facturacion_anual) as facturacion_total,
        AVG(e.numero_vendedores) as promedio_vendedores,
        COUNT(DISTINCT a.id) as cantidad_asesores
    FROM pais p
    LEFT JOIN empresa e ON p.id = e.pais_id
    LEFT JOIN asesor_empresas_asesoradas aea ON e.id = aea.empresas_asesoradas_id
    LEFT JOIN asesor a ON aea.asesor_id = a.id
    GROUP BY p.id, p.nombre
    ORDER BY facturacion_total DESC;
END //
DELIMITER ;

-- =============================================================================
-- TRIGGERS PARA AUDITORÍA
-- =============================================================================

-- Trigger para auditoría de cambios en empresas
DELIMITER //
CREATE TRIGGER tr_empresa_audit_update
BEFORE UPDATE ON empresa
FOR EACH ROW
BEGIN
    IF OLD.facturacion_anual != NEW.facturacion_anual THEN
        INSERT INTO auditoria_empresa (empresa_id, campo, valor_anterior, valor_nuevo, fecha_cambio)
        VALUES (NEW.id, 'facturacion_anual', OLD.facturacion_anual, NEW.facturacion_anual, NOW());
    END IF;
    
    IF OLD.numero_vendedores != NEW.numero_vendedores THEN
        INSERT INTO auditoria_empresa (empresa_id, campo, valor_anterior, valor_nuevo, fecha_cambio)
        VALUES (NEW.id, 'numero_vendedores', OLD.numero_vendedores, NEW.numero_vendedores, NOW());
    END IF;
END //
DELIMITER ;

-- =============================================================================
-- TABLA DE AUDITORÍA (crear si no existe)
-- =============================================================================

CREATE TABLE IF NOT EXISTS auditoria_empresa (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    empresa_id BIGINT NOT NULL,
    campo VARCHAR(100) NOT NULL,
    valor_anterior VARCHAR(500),
    valor_nuevo VARCHAR(500),
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

-- =============================================================================
-- FIN DEL SCRIPT DE MIGRACIÓN
-- =============================================================================

SELECT 'Migración completada exitosamente' as resultado;