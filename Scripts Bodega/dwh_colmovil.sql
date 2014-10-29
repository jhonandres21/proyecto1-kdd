﻿
/*==============================================================*/
/* Eliminación y Creación: Schema		*/
/*==============================================================*/

DROP SCHEMA IF EXISTS bodega CASCADE;
CREATE SCHEMA bodega;
SET SCHEMA 'bodega';

/*==============================================================*/
/* Creación: Dominios 						*/
/*==============================================================*/

CREATE DOMAIN dominio_tipo_id CHAR (5) CHECK ( VALUE IN ( 'C.C.', 'C.E.' ) );
CREATE DOMAIN dominio_flag CHAR (5) CHECK ( VALUE IN ( 'SI', 'NO' ) );

/*==============================================================*/
/* Creación: Dimensiones					*/
/*==============================================================*/

/*==============================================================*/
/* Dimensión: Cliente 						*/
/*==============================================================*/

CREATE SEQUENCE seq_cliente INCREMENT BY 1 START WITH 1;
CREATE TABLE cliente
(
SK_cliente BIGINT NOT NULL DEFAULT nextval('seq_cliente'::regclass),
numero_id INTEGER NOT NULL ,
tipo_id dominio_tipo_id NOT NULL,
nombres VARCHAR(50) NOT NULL,
apellidos VARCHAR(50) NOT NULL,
fecha_nacimiento DATE NOT NULL,
email VARCHAR(50) NOT NULL,
PRIMARY KEY (SK_cliente)
);

/*==============================================================*/
/* Dimensión: Demografia */
/*==============================================================*/

CREATE SEQUENCE seq_demografia INCREMENT BY 1 START WITH 1;

CREATE TABLE DEMOGRAFIA
(
SK_demografia BIGINT NOT NULL DEFAULT nextval('seq_demografia'::regclass),
estado_civil VARCHAR(45) NOT NULL DEFAULT 'N/A',
estrato INT NOT NULL DEFAULT 0,
genero VARCHAR(45) DEFAULT 'N/A',
PRIMARY KEY (SK_demografia)
);

/*==============================================================*/
/* Dimensión: Plan Voz */
/*==============================================================*/
CREATE SEQUENCE seq_plan_voz INCREMENT BY 1 START WITH 1;
CREATE TABLE plan_voz
(
SK_plan_voz BIGINT NOT NULL DEFAULT nextval('seq_plan_voz'::regclass),
id_plan_voz bigint NOT NULL,
tipo_plan_voz VARCHAR(100) NOT NULL,
nombre_plan_voz VARCHAR(100) NOT NULL,
es_corporativo dominio_flag NOT NULL,
PRIMARY KEY (SK_plan_voz)
);
/*==============================================================*/
/* Dimensión: Plan Datos */
/*==============================================================*/

CREATE SEQUENCE seq_plan_datos INCREMENT BY 1 START WITH 1;
CREATE TABLE plan_datos
(
SK_plan_datos BIGINT NOT NULL DEFAULT nextval('seq_plan_datos'::regclass),
id_plan_datos bigint NOT NULL,
nombre_plan_datos VARCHAR(100) NOT NULL,
es_corporativo dominio_flag NOT NULL,
PRIMARY KEY (SK_plan_datos)
);

/*==============================================================*/
/* Dimensión: Equipo */
/*==============================================================*/

CREATE SEQUENCE seq_equipo INCREMENT BY 1 START WITH 1;
CREATE TABLE equipo
(
SK_equipo BIGINT NOT NULL DEFAULT nextval('seq_equipo'::regclass),
id_equipo BIGINT NOT NULL,
marca VARCHAR(50) NOT NULL,
modelo VARCHAR(50) NOT NULL,
precio INTEGER NOT NULL,
PRIMARY KEY (SK_equipo)
);

/*==============================================================*/
/* Dimensión: Sim Card */
/*==============================================================*/

CREATE SEQUENCE seq_sim_card INCREMENT BY 1 START WITH 1;
CREATE TABLE sim_card
(
SK_sim_card BIGINT NOT NULL DEFAULT nextval('seq_sim_card'::regclass),
id_sim_card bigint NOT NULL,
numero_serie varchar(50) NOT NULL,
numero_telefono varchar(50) NOT NULL,
PRIMARY KEY (SK_sim_card)
);

/*==============================================================*/
/* Dimensión: Oficina */
/*==============================================================*/
CREATE SEQUENCE seq_oficina INCREMENT BY 1 START WITH 1;
CREATE TABLE oficina
(
SK_oficina BIGINT NOT NULL DEFAULT nextval('seq_oficina'::regclass),
id_oficina bigint NOT NULL,
direccion VARCHAR(200) NOT NULL,
numero_empleados INTEGER NULL,
PRIMARY KEY (SK_oficina)
);
/*==============================================================*/
/* Dimensión: Tiempo */
/*==============================================================*/

CREATE SEQUENCE seq_tiempo INCREMENT BY 1 START WITH 1;

CREATE TABLE TIEMPO 
AS (
SELECT TO_CHAR(minute, 'hh24:mi') AS Tiempo,
EXTRACT(hour FROM minute) AS hora,
TO_CHAR(minute - (EXTRACT(minute FROM minute)::INTEGER % 15 || 'minutes')::INTERVAL, 'hh24:mi') || ' – ' || TO_CHAR(minute - (EXTRACT(minute FROM minute)::INTEGER % 15 || 'minutes')::INTERVAL + '14 minutes'::INTERVAL, 'hh24:mi') 
AS cuarto_de_hora,
EXTRACT(hour FROM minute)*60 + EXTRACT(minute FROM minute) AS minuto,
CASE WHEN TO_CHAR(minute, 'hh24:mi') BETWEEN '06:00' AND '08:29'
THEN 'Morning'
WHEN TO_CHAR(minute, 'hh24:mi') BETWEEN '08:30' AND '11:59'
THEN 'AM'
WHEN TO_CHAR(minute, 'hh24:mi') BETWEEN '12:00' AND '17:59'
THEN 'PM'
WHEN TO_CHAR(minute, 'hh24:mi') BETWEEN '18:00' AND '22:29'
THEN 'Evening'
ELSE 'Night'
END AS periodo_del_dia,
CASE WHEN TO_CHAR(minute, 'hh24:mi') BETWEEN '07:00' AND '19:59' THEN 'Day'
ELSE 'Night'
END AS dia_noche
FROM (SELECT '0:00'::time + (sequence.minute || ' minutes')::INTERVAL AS minute
FROM generate_series(0,1439) AS sequence(minute)
GROUP BY sequence.minute
) DQ
ORDER BY 1
);

ALTER TABLE TIEMPO ADD SK_tiempo BIGINT DEFAULT NEXTVAL('seq_tiempo'::regclass) PRIMARY KEY;
ALTER TABLE TIEMPO ALTER COLUMN tiempo SET NOT NULL;
ALTER TABLE TIEMPO ALTER COLUMN hora SET NOT NULL;
ALTER TABLE TIEMPO ALTER COLUMN cuarto_de_hora SET NOT NULL;
ALTER TABLE TIEMPO ALTER COLUMN minuto SET NOT NULL;
ALTER TABLE TIEMPO ALTER COLUMN periodo_del_dia SET NOT NULL;
ALTER TABLE TIEMPO ALTER COLUMN dia_noche SET NOT NULL;

/*==============================================================*/
/* Dimensión: Fecha */
/*==============================================================*/

CREATE SEQUENCE seq_fecha INCREMENT BY 1 START WITH 1;

CREATE TABLE FECHA
AS (
SELECT
datum AS Fecha,
EXTRACT(year FROM datum) AS Anio,
EXTRACT(month FROM datum) AS Mes,
TO_CHAR(datum, 'TMMonth') AS Nombre_Mes,
EXTRACT(day FROM datum) AS Dia,
EXTRACT(doy FROM datum) AS Dia_De_Anio,
TO_CHAR(datum, 'TMDay') AS Nombre_Dia,
TO_CHAR(datum, 'yyyy/mm') AS Mes_Del_Anio,
TO_CHAR(datum, 'iyyy/IW') AS Semana_Del_Anio,
-- Fin de Semana
CASE WHEN EXTRACT(isodow FROM datum) IN (6, 7) THEN 'Fin de Semana' ELSE 'Día de la
Semana' END AS Fin_De_Semana,
CASE WHEN TO_CHAR(datum, 'MMDD') IN ('0101', '0106', '0324', '0417', '0418', '0501', '0602',
'0623', '0630', '0720', '0807', '0818', '1013', '1103', '1117', '1208', '1225')
THEN 'Festivo' ELSE 'No Festivo' END
AS Festivos_Colombia,
CASE WHEN TO_CHAR(datum, 'MMDD') BETWEEN '0413' AND '0419' THEN 'Semana Santa'
WHEN TO_CHAR(datum, 'MMDD') BETWEEN '1216' AND '1225' THEN 'Navidad'
ELSE 'Normal' END
AS Periodo,
datum + (1 - EXTRACT(day FROM datum))::INTEGER AS Inicio_De_Mes,
(datum + (1 - EXTRACT(day FROM datum))::INTEGER + '1 month'::INTERVAL)::date - '1 day'::INTERVAL
AS Fin_De_Mes
FROM (
SELECT '2000-01-01'::DATE + sequence.day AS datum
FROM generate_series(0,5475) AS sequence(day)
GROUP BY sequence.day
) DQ
ORDER BY 1
);

ALTER TABLE FECHA ADD SK_fecha BIGINT DEFAULT NEXTVAL('seq_fecha'::regclass) PRIMARY KEY;
ALTER TABLE FECHA ALTER COLUMN fecha SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN anio SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN mes SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN nombre_mes SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN dia SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN dia_de_anio SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN nombre_dia SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN mes_del_anio SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN semana_del_anio SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN fin_de_semana SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN festivos_colombia SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN periodo SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN inicio_de_mes SET NOT NULL;
ALTER TABLE FECHA ALTER COLUMN fin_de_mes SET NOT NULL;


/*==============================================================*/
/* Dimensión: Localización					*/
/*==============================================================*/

CREATE SEQUENCE seq_localizacion INCREMENT BY 1 START WITH 1;

CREATE TABLE LOCALIZACION
(
SK_localizacion BIGINT NOT NULL DEFAULT nextval('seq_localizacion'::regclass),
departamento VARCHAR(45) NOT NULL,
ciudad VARCHAR(45) NOT NULL,
PRIMARY KEY (SK_localizacion)
); 

/*==============================================================*/
/* Creación: Hechos						*/
/*==============================================================*/

/*==============================================================*/
/* Hecho: Retiros						*/
/*==============================================================*/

CREATE TABLE retiro
(
fecha BIGINT references fecha (SK_fecha),
cliente BIGINT references cliente (SK_cliente),
demografia BIGINT references demografia (SK_demografia),
plan_voz BIGINT references plan_voz (SK_plan_voz),
plan_datos BIGINT references plan_datos (SK_plan_datos)
);

/*==============================================================*/
/* Hecho: Recargas */
/*==============================================================*/

CREATE TABLE recarga
(	
fecha BIGINT references fecha (SK_fecha),
id_sim_card BIGINT references sim_card (SK_sim_card),
valor_recarga BIGINT NOT NULL,
tipo_recarga VARCHAR(45) NOT NULL /*Hace referencia al campo medio_recarga*/
);

/*==============================================================*/
/* Hecho: Ventas */
/*==============================================================*/

CREATE TABLE venta
(
fecha BIGINT references fecha (SK_fecha),
localizacion BIGINT REFERENCES localizacion (SK_localizacion),
cliente BIGINT references cliente (SK_cliente),
demografia BIGINT references demografia (SK_demografia),
plan_voz BIGINT references plan_voz (SK_plan_voz),
plan_datos BIGINT references plan_datos (SK_plan_datos),
equipo BIGINT references equipo (SK_equipo),
oficina BIGINT references oficina (SK_oficina),
sim_card BIGINT references sim_card (SK_sim_card),
precio_plan INTEGER NOT NULL 
/*EN cuanto al precio:
Si es voz, entonces se coloca el precio
Si es datos FIJOS, entonces se coloca el valor de la tarifa
Si es datos POR VOLUMEN, entonces se coloca -1 pues no hay manera de calcular el valor a pagar, porque no existen datos de consumo*/
);

/*==============================================================*/
/* Hecho: Llamadas */
/*==============================================================*/

CREATE TABLE llamada
(
fecha BIGINT references fecha (SK_fecha),
tiempo BIGINT references tiempo (SK_tiempo), /*Hace referencia a la hora de inicio*/
cliente BIGINT references cliente (SK_cliente),
demografia BIGINT references demografia (SK_demografia),
sim_card BIGINT references sim_card (SK_sim_card),
plan_voz BIGINT references plan_voz (SK_plan_voz),
nombre_operador VARCHAR (30) NOT NULL,
duracion_llamada TIME NOT NULL, /*Asumiendo duracion en minutos y segundos*/
flag_roaming VARCHAR (25) NOT NULL
);
