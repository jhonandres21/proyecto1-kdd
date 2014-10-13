DROP SCHEMA colmovil;

CREATE SCHEMA colmovil;

SET SCHEMA 'colmovil';

DROP TABLE IF EXISTS colmovil.cliente CASCADE;
DROP TABLE IF EXISTS colmovil.localizacion CASCADE;
DROP TABLE IF EXISTS colmovil.oficina CASCADE;
DROP TABLE IF EXISTS colmovil.operador_roaming CASCADE;
DROP TABLE IF EXISTS colmovil.equipo_celular CASCADE;
DROP TABLE IF EXISTS colmovil.plan_voz CASCADE;
DROP TABLE IF EXISTS colmovil.recarga CASCADE;
DROP TABLE IF EXISTS colmovil.retiro CASCADE;
DROP TABLE IF EXISTS colmovil.llamada CASCADE;
DROP TABLE IF EXISTS colmovil.plan_datos CASCADE;
DROP TABLE IF EXISTS colmovil.sim_card CASCADE;
DROP TABLE IF EXISTS colmovil.contrato CASCADE;
DROP TABLE IF EXISTS colmovil.operador CASCADE;
-- -----------------------------------------------------
-- Table cliente
-- -----------------------------------------------------
CREATE  TABLE colmovil.cliente (
  idcliente BIGINT NOT NULL ,
  tipo_identificacion VARCHAR(45) NOT NULL ,
  numero_identificacion INTEGER NOT NULL ,
  nombre VARCHAR(45) NULL ,
  apellido VARCHAR(45) NULL ,
  direccion_residencia VARCHAR(200) NULL ,
  estrato INTEGER  NOT NULL ,
  email VARCHAR(45) NULL ,
  fecha_nacimiento DATE NOT NULL ,
  genero VARCHAR(45) NOT NULL ,
  estado_civil VARCHAR(45) NULL ,
  PRIMARY KEY (idcliente) );


-- -----------------------------------------------------
-- Table localizacion
-- -----------------------------------------------------
CREATE  TABLE colmovil.localizacion (
  idlocalizacion BIGINT NOT NULL ,
  departamento VARCHAR(45) NOT NULL ,
  ciudad VARCHAR(45) NOT NULL ,
  PRIMARY KEY (idlocalizacion) );


-- -----------------------------------------------------
-- Table oficina
-- -----------------------------------------------------
CREATE  TABLE colmovil.oficina (
  id_oficina BIGINT NOT NULL ,
  id_localizacion BIGINT NOT NULL ,
  direccion VARCHAR(80) NULL ,
  numero_empleados INTEGER NULL ,
  PRIMARY KEY (id_oficina),
  CONSTRAINT fk_oficina_1
    FOREIGN KEY (id_localizacion )
    REFERENCES colmovil.localizacion (idlocalizacion ));


-- -----------------------------------------------------
-- Table operador_roaming
-- -----------------------------------------------------
CREATE  TABLE colmovil.operador_roaming (
  id_operador_roaming BIGINT NOT NULL ,
  nombre_operador VARCHAR(45) NULL ,
  pais_donde_opera VARCHAR(45) NOT NULL ,
  tarifa_voz_por_minuto FLOAT NULL ,
  tarifa_datos_por_kilobyte FLOAT NULL ,
  PRIMARY KEY (id_operador_roaming) );


-- -----------------------------------------------------
-- Table equipo
-- -----------------------------------------------------

CREATE  TABLE colmovil.equipo_celular (
  id_equipo BIGINT NOT NULL ,
  marca VARCHAR(45) NOT NULL ,
  modelo VARCHAR(45) NOT NULL ,
  precio INTEGER NULL ,
  sistema_operativo VARCHAR(45) NULL ,
  pantalla VARCHAR(45) NULL ,
  procesador VARCHAR(45) NULL ,
  memoria VARCHAR(45) NULL ,
  almacenamiento_interno VARCHAR(45) NULL ,
  RED2G VARCHAR(100) NULL ,
  RED3G VARCHAR(100) NULL ,
  conectividad VARCHAR(100) NULL ,
  camara VARCHAR(100) NULL ,
  GPS VARCHAR(45) NULL ,
  radio VARCHAR(45) NULL ,
  bateria VARCHAR(100) NULL,
  PRIMARY KEY (id_equipo) );


-- -----------------------------------------------------
-- Table plan_voz
-- -----------------------------------------------------
CREATE  TABLE colmovil.plan_voz(
  id_plan_voz BIGINT NOT NULL ,
  nombre VARCHAR(45) NULL ,
  total_minutos INTEGER  NULL ,
  costo_otros_operadores FLOAT NULL ,
  costo_minuto_adicional FLOAT NULL ,
  sms_incluidos INTEGER NULL ,
  precio INTEGER NULL ,
  PRIMARY KEY (id_plan_voz));


-- -----------------------------------------------------
-- Table plan_datos
-- -----------------------------------------------------
CREATE  TABLE colmovil.plan_datos (
  id_plan_datos BIGINT NOT NULL ,
  nombre VARCHAR(45) NULL ,
  tipo_tarifa VARCHAR(45) NULL ,
  valor_tarifa FLOAT NULL ,
  valor_kb_adicional INTEGER  NULL ,
  PRIMARY KEY (id_plan_datos));



-- -----------------------------------------------------
-- Table sim_card
-- -----------------------------------------------------
CREATE  TABLE colmovil.sim_card (
  id_sim_card SERIAL,
  numero_serie VARCHAR(20) NULL,
  numero_telefono VARCHAR(45) NOT NULL,
  pin VARCHAR(4)  NULL,
  puk VARCHAR(8)  NULL,
  capacidad_memoria_kb INTEGER NULL,
  PRIMARY KEY (id_sim_card));


-- -----------------------------------------------------
-- Table contrato
-- -----------------------------------------------------

CREATE  TABLE colmovil.contrato (
  id_contrato BIGINT NOT NULL ,
  id_sim_card BIGINT NOT NULL ,
  tipo_plan VARCHAR(45) NULL ,
  id_cliente BIGINT NOT NULL ,
  id_oficina BIGINT NOT NULL ,
  id_plan_voz BIGINT NOT NULL ,
  id_plan_datos BIGINT NOT NULL ,
  id_equipo_celular BIGINT  NULL ,
  fecha_contrato DATE NOT NULL ,
  PRIMARY KEY (id_contrato),
    CONSTRAINT fk_contrato_1
    FOREIGN KEY (id_sim_card )
    REFERENCES colmovil.sim_card (id_sim_card )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_contrato_2
    FOREIGN KEY (id_cliente )
    REFERENCES colmovil.cliente (idcliente )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_contrato_3
    FOREIGN KEY (id_oficina )
    REFERENCES colmovil.oficina (id_oficina )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_contrato_4
    FOREIGN KEY (id_plan_voz )
    REFERENCES colmovil.plan_voz (id_plan_voz )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_contrato_5
    FOREIGN KEY (id_plan_datos )
    REFERENCES colmovil.plan_datos (id_plan_datos )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_contrato_6
    FOREIGN KEY (id_equipo_celular )
    REFERENCES colmovil.equipo_celular (id_equipo )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

/*
CREATE TABLE aleatorios AS SELECT id,floor(random()*10000) AS venta FROM (SELECT * FROM generate_series(1,10000) AS id) AS x;
*/


-- -----------------------------------------------------
-- Table operador
-- -----------------------------------------------------
CREATE  TABLE colmovil.operador (
  id_operador BIGINT NOT NULL ,
  nombre VARCHAR(45) NULL ,
  indicativos VARCHAR(45) NULL ,
  PRIMARY KEY (id_operador));


-- -----------------------------------------------------
-- Table llamada 
-- -----------------------------------------------------
CREATE  TABLE colmovil.llamada (
  fecha_inicio TIMESTAMP NULL ,
  fecha_finalizacion TIMESTAMP NULL ,
  id_contrato BIGINT NOT NULL,
  id_operador_destino BIGINT NOT NULL ,
  numero_destino VARCHAR(45) NOT NULL ,
  pais_destino VARCHAR(45) NOT NULL ,
  utilizo_roaming VARCHAR(45)  NULL ,
  operador_roaming BIGINT NOT NULL,
  CONSTRAINT fk_llamada_1
    FOREIGN KEY (id_contrato)
    REFERENCES colmovil.contrato (id_contrato)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_llamada_2
    FOREIGN KEY (id_operador_destino )
    REFERENCES colmovil.operador (id_operador )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_llamada_3
    FOREIGN KEY (operador_roaming )
    REFERENCES colmovil.operador_roaming (id_operador_roaming )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table retiro      OJO MODIFICADA NUMERO_TELEFONO
-- -----------------------------------------------------
CREATE  TABLE colmovil.retiro (
  id_contrato BIGINT NOT NULL ,
  fecha DATE NULL ,
  causa VARCHAR(45) NULL ,
  CONSTRAINT fk_retiro_1
    FOREIGN KEY (id_contrato)
    REFERENCES colmovil.contrato (id_contrato)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table recarga 
-- -----------------------------------------------------
CREATE TABLE colmovil.recarga (
  id_recarga INTEGER NOT NULL,
  id_contrato BIGINT NOT NULL ,
  fecha_recarga DATE NOT NULL,
  valor_recarga INTEGER DEFAULT NULL,
  medio_recarga VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_recarga),
  CONSTRAINT fk_recarga_1
    FOREIGN KEY (id_contrato)
    REFERENCES  colmovil.contrato (id_contrato)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    

copy cliente FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\clientes.csv' DELIMITER ',' CSV;

COPY localizacion FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\localizacion.csv' DELIMITER ',' CSV;

COPY oficina FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\oficinas.csv' DELIMITER ',' CSV;

COPY operador_roaming FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\operador_roaming.csv' DELIMITER ',' CSV;

COPY equipo_celular FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\equipo_celular.csv' DELIMITER ',' CSV;

COPY plan_voz FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\plan_voz.csv' DELIMITER ',' CSV;

COPY plan_datos FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\plan_datos.csv' DELIMITER ',' CSV;

COPY sim_card FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\sim_card.csv' DELIMITER ',' CSV;

COPY contrato FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\contratos_sim_carddef.csv' DELIMITER ';' CSV;

COPY operador FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\operador.csv' DELIMITER ',' CSV;

COPY llamada FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\llamadas_sim_card.csv' DELIMITER ',' CSV;

COPY retiro FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\retiros_sim_card.csv' DELIMITER ',' CSV;

COPY recarga FROM 'E:\GitHub\proyecto1-kdd\DatosColmovil\recargas_sim_card.csv' DELIMITER ',' CSV;
