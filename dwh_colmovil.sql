
DROP SCHEMA 'colmovil-dwh'﻿ CASCADE;
CREATE SCHEMA 'colmovil-dwh'﻿;
SET SCHEMA 'colmovil-dwh';
/*Nombre del esquema: colmovil-dwh*/

CREATE DOMAIN dominio_tipo_id CHAR (5) CHECK ( VALUE IN ( 'C.C.', 'C.E.' ) );
CREATE DOMAIN dominio_flag CHAR (5) CHECK ( VALUE IN ( 'True', 'False' ) );
CREATE DOMAIN dominio_genero CHAR (9) CHECK ( VALUE IN ( 'Masculino', 'Femenino' ) );
CREATE DOMAIN dominio_dia_noche CHAR (5) CHECK ( VALUE IN ( 'dia', 'noche' ) );
CREATE DOMAIN dominio_periodo_dia CHAR (20) CHECK ( VALUE IN ( 'mañana', 'mediodia', 'tarde', 'noche' ));
CREATE DOMAIN dominio_periodo_año CHAR (30) CHECK ( VALUE IN ( 'Vacaciones_Verano', 'Vacaciones_Invierno', 'Semana_Santa', 'Navidad' ) );
CREATE DOMAIN dominio_estado_civil CHAR (9) CHECK ( VALUE IN ( 'Soltero', 'Divorciado', 'Unión Libre', 'Casado', 'Viudo' ) );
CREATE DOMAIN dominio_recarga CHAR (30) CHECK ( VALUE IN ( 'Adelanto de saldo', 'Recarga Electronica', 'Tarjeta', 'Online') );

/*==============================================================*/
/* Dimensión: Cliente                                           */
/*==============================================================*/

CREATE SEQUENCE seq_cliente INCREMENT BY 1 START WITH 1;

create table cliente 
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
/* Dimensión: Demografía                                        */
/*==============================================================*/

CREATE SEQUENCE seq_demografia INCREMENT BY 1 START WITH 1;

create table demografia 
(
	SK_demografia BIGINT NOT NULL DEFAULT nextval('seq_demografia'::regclass),
  	estado_civil dominio_estado_civil NOT NULL,
  	estrato integer NOT NULL CHECK estrato BETWEEN 1 AND 6,
  	genero dominio_genero NOT NULL,
  	PRIMARY KEY (SK_demografia) 
);

/*==============================================================*/
/* Dimensión: Plan Voz                                          */
/*==============================================================*/

CREATE SEQUENCE seq_plan_voz INCREMENT BY 1 START WITH 1;

create table plan_voz 
(
	SK_plan_voz BIGINT NOT NULL DEFAULT nextval('seq_plan_voz'::regclass),
  	tipo_plan_voz VARCHAR(100) NOT NULL,
  	nombre_plan_voz VARCHAR(100) NOT NULL,
  	es_corporativo dominio_flag  NOT NULL,
  	PRIMARY KEY (SK_plan_voz) 
);

/*==============================================================*/
/* Dimensión: Plan Datos                                        */
/*==============================================================*/

CREATE SEQUENCE seq_plan_datos INCREMENT BY 1 START WITH 1;

create table plan_datos 
(
	SK_plan_datos BIGINT NOT NULL DEFAULT nextval('seq_plan_datos'::regclass),
  	nombre_plan_datos VARCHAR(100) NOT NULL,
  	es_corporativo dominio_flag  NOT NULL,
  	PRIMARY KEY (SK_plan_datos) 
);


/*==============================================================*/
/* Dimensión: Equipo						*/
/*==============================================================*/

CREATE SEQUENCE seq_equipo INCREMENT BY 1 START WITH 1;

create table equipo 
(
	SK_equipo BIGINT NOT NULL DEFAULT nextval('seq_equipo'::regclass),
	id_equipo BIGINT NOT NULL,
	marca VARCHAR(50) NOT NULL,
	modelo VARCHAR(50) NOT NULL,
	precio DOUBLE NOT NULL,
  	PRIMARY KEY (SK_equipo) 
);

/*==============================================================*/
/* Dimensión: Sim Card						*/
/*==============================================================*/

CREATE SEQUENCE seq_sim_card INCREMENT BY 1 START WITH 1;

create table sim_card 
(
	SK_sim_card BIGINT NOT NULL DEFAULT nextval('seq_sim_card'::regclass),
	numero_serie BIGINT NOT NULL,
	numero_telefono BIGINT NOT NULL,
  	PRIMARY KEY (SK_sim_card) 
);

/*==============================================================*/
/* Dimensión: Oficina						*/
/*==============================================================*/

CREATE SEQUENCE seq_oficina INCREMENT BY 1 START WITH 1;

create table oficina 
(
	SK_oficina BIGINT NOT NULL DEFAULT nextval('seq_oficina'::regclass),
  	direccion VARCHAR(200) NOT NULL,
  	numero_empleados INTEGER NULL,
  	PRIMARY KEY (SK_oficina) 
);

/*==============================================================*/
/* Dimensión: Tiempo						*/
/*==============================================================*/

CREATE SEQUENCE seq_tiempo INCREMENT BY 1 START WITH 1;

create table tiempo
(
	SK_tiempo BIGINT NOT NULL DEFAULT nextval('seq_tiempo'::regclass),
  	tiempo TIME WITHOUT TIME ZONE NOT NULL,
  	hora TIME WITHOUT TIME ZONE NOT NULL,
	cuarto_de_hora INTEGER NOT NULL,
	minuto INTEGER NOT NULL,
	periodo_del_dia dominio_periodo_dia NOT NULL,
	dia_noche_flag dominio_dia_noche NOT NULL,
  	PRIMARY KEY (SK_tiempo)
);

/*==============================================================*/
/* Dimensión: Fecha                                             */
/*==============================================================*/

CREATE SEQUENCE seq_fecha INCREMENT BY 1 START WITH 1;

create table fecha 
(
	SK_fecha BIGINT NOT NULL DEFAULT nextval('seq_fecha'::regclass),
	fecha DATE NOT NULL,
	anio INTEGER NOT NULL,
	mes INTEGER NOT NULL,
	nombre_mes VARCHAR (20) NOT NULL,
	dia INTEGER NOT NULL,
	dia_del_anio INTEGER NOT NULL,
	nombre_dia VARCHAR (10),
	numero_semana INTEGER NOT NULL,
	fecha_formateada DATE NOT NULL,
	trimestre INTEGER NOT NULL,
	mes_del_anio INTEGER NOT NULL,
	fin_de_semestre dominio_flag,
	dia_festivo dominio_flag NOT NULL,
	periodo dominio_periodo_anio NOT NULL,
	fecha_comienzo_semana dominio_flag NOT NULL,
	fecha_fin_semana dominio_flag NOT NULL,
	fecha_comienzo_mes dominio_flag NOT NULL,
  	PRIMARY KEY (SK_fecha)
);

/*==============================================================*/
/* Dimensión: Localización                                       */
/*==============================================================*/

CREATE SEQUENCE seq_localizacion INCREMENT BY 1 START WITH 1;

create table localizacion 
(
	SK_localizacion BIGINT NOT NULL DEFAULT nextval('seq_localizacion'::regclass),
  	departamento VARCHAR (30) NOT NULL,
  	ciudad VARCHAR (30) NOT NULL,
  	pais VARCHAR (30) NOT NULL,
  	PRIMARY KEY (SK_localizacion) 
);

/*============================================================================================================================*/
/*=========================================================== HECHOS  =========================================================*/
/*============================================================================================================================*/


/*==============================================================*/
/* Hecho: Retiros												*/
/*==============================================================*/

create table retiro
(
	fecha BIGINT references fecha (SK_fecha),
  	cliente BIGINT references cliente (SK_cliente),
  	demografia BIGINT references demografia (SK_demografia),
	plan_voz BIGINT references plan_voz (SK_plan_voz),
	plan_datos BIGINT references plan_datos (SK_plan_datos),
  	PRIMARY KEY (SK_retiro)
);

/*==============================================================*/
/* Hecho: Recargas												*/
/*==============================================================*/

create table recarga
(	
	fecha BIGINT references fecha (SK_fecha),
  	id_sim_card BIGINT references sim_card (SK_sim_card),
  	valor_recarga BIGINT NOT NULL,
  	tipo_recarga BIGINT NOT NULL
);

/*==============================================================*/
/* Hecho: Ventas                                                */
/*==============================================================*/

create table venta 
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
  	sub_total DOUBLE NOT NULL, 
  	iva DOUBLE NOT NULL, 
  	total DOUBLE NOT NULL
);

/*==============================================================*/
/* Hecho: Llamadas                                              */
/*==============================================================*/

create table llamada 
(
	fecha BIGINT references fecha (SK_fecha),
	localizacion BIGINT references localizacion (SK_localizacion),
	tiempo BIGINT references tiempo (SK_tiempo),
  	cliente BIGINT references cliente (SK_cliente),
  	demografia BIGINT references demografia (SK_demografia),
  	sim_card BIGINT references sim_card (SK_sim_card),
	plan_voz BIGINT references plan_voz (SK_plan_voz),
	nombre_operador VARCHAR (30) NOT NULL,
  	duracion_llamada INTEGER NOT NULL, /*asumiendo duracion en minutos*/
  	flag_roaming dominio_flag NOT NULL
);

