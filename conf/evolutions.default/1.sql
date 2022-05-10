-- pago table

-- !Ups

CREATE TABLE pago (
 id SERIAL,
 placa VARCHAR(6) NOT NULL,
 tipo_vehiculo INT(10) NOT NULL,
 valor_pago FLOAT NOT NULL,
 fecha_pago DATE NOT NULL,
 PRIMARY KEY (id)
);