
/*Nombre de la base de datos: colmovildwt*/

CREATE DOMAIN dominio_flag CHAR (5) CHECK ( VALUE IN ( 'true', 'false' ) );
CREATE DOMAIN dominio_genero CHAR (9) CHECK ( VALUE IN ( 'masculino', 'femenino' ) );
CREATE DOMAIN dominio_estado_civil CHAR (9) CHECK ( VALUE IN ( 'soltero', 'divorciado', 'union libre', 'casado', 'viudo' ) );

/*==============================================================*/
/* Dimensión: Cliente                                           */
/*==============================================================*/

CREATE SEQUENCE seq_cliente INCREMENT BY 1 START WITH 1;

create table cliente 
(
	id_cliente bigint NOT NULL DEFAULT nextval('seq_cliente'::regclass),
  	numero_identificacion INTEGER NOT NULL ,
  	nombres VARCHAR(50) NOT NULL,
  	apellidos VARCHAR(50) NOT NULL,
  	direccion VARCHAR(200) NOT NULL,
  	fecha_nacimiento DATE NOT NULL,
  	flag_empresa dominio_flag NOT NULL,
  	PRIMARY KEY (id_cliente) 
);

/*==============================================================*/
/* Dimensión: Demografía                                        */
/*==============================================================*/

CREATE SEQUENCE seq_demografia INCREMENT BY 1 START WITH 1;

create table demografia 
(
	id_demografia bigint NOT NULL DEFAULT nextval('seq_demografia'::regclass),
  	estado_civil dominio_estado_civil NOT NULL,
  	estrato integer NOT NULL,
  	genero dominio_genero NOT NULL,
  	ciudad VARCHAR(100) NOT NULL,
  	departamento VARCHAR(100) NOT NULL,
  	PRIMARY KEY (id_demografia) 
);

/*==============================================================*/
/* Dimensión: Plan                                              */
/*==============================================================*/

CREATE SEQUENCE seq_plan INCREMENT BY 1 START WITH 1;

create table plan 
(
	id_plan bigint NOT NULL DEFAULT nextval('seq_plan'::regclass),
  	tipo_plan_datos VARCHAR(100) NOT NULL,
  	tipo_plan_voz VARCHAR(100) NOT NULL,
  	PRIMARY KEY (id_plan) 
);

/*==============================================================*/
/* Dimensión: Teléfono                                          */
/*==============================================================*/

CREATE SEQUENCE seq_telefono INCREMENT BY 1 START WITH 1;

create table telefono 
(
	id_telefono bigint NOT NULL DEFAULT nextval('seq_telefono'::regclass),
  	numero_serie VARCHAR(50) NOT NULL,
  	numero_telefonico VARCHAR(50) NOT NULL,
  	PRIMARY KEY (id_telefono) 
);

/*==============================================================*/
/* Dimensión: Sucursal                                          */
/*==============================================================*/

CREATE SEQUENCE seq_sucursal INCREMENT BY 1 START WITH 1;

create table sucursal 
(
	id_sucursal bigint NOT NULL DEFAULT nextval('seq_sucursal'::regclass),
  	direccion VARCHAR(200) NOT NULL,
  	ciudad VARCHAR(100) NOT NULL,
  	departamento VARCHAR(100) NOT NULL,
  	numero_empleados integer NULL,
  	PRIMARY KEY (id_sucursal) 
);

/*==============================================================*/
/* Dimensión: Operador                                          */
/*==============================================================*/

CREATE SEQUENCE seq_operador INCREMENT BY 1 START WITH 1;

create table operador 
(
	id_operador bigint NOT NULL DEFAULT nextval('seq_operador'::regclass),
  	nombre VARCHAR(50) NOT NULL,
  	pais VARCHAR(100) NOT NULL,
  	PRIMARY KEY (id_operador)
);

/*==============================================================*/
/* Dimensión: Hora                                              */
/*==============================================================*/

CREATE SEQUENCE seq_hora INCREMENT BY 1 START WITH 1;

create table hora 
(
	id_hora bigint NOT NULL DEFAULT nextval('seq_hora'::regclass),
  	horario_inicio time without time zone NOT NULL,
  	horario_fin time without time zone NOT NULL,
  	PRIMARY KEY (id_hora)
);

/*==============================================================*/
/* Dimensión: Fecha                                             */
/*==============================================================*/

CREATE SEQUENCE seq_fecha INCREMENT BY 1 START WITH 1;

create table fecha 
(
	id_fecha bigint NOT NULL DEFAULT nextval('seq_fecha'::regclass),
  	PRIMARY KEY (id_fecha)
);


/*============================================================================================================================*/
/*=========================================================== HECHOS  =========================================================*/
/*============================================================================================================================*/


/*==============================================================*/
/* Hecho: abandono colmovil                                     */
/*==============================================================*/

CREATE SEQUENCE seq_abandono INCREMENT BY 1 START WITH 1;

create table abandono 
(
	id_abandono bigint NOT NULL DEFAULT nextval('seq_abandono'::regclass),
	fecha bigint references fecha (id_fecha),
  	cliente bigint references cliente (id_cliente),
  	demografia bigint references demografia (id_demografia),
  	flag_abandono dominio_flag NOT NULL,
  	PRIMARY KEY (id_abandono)
);

/*==============================================================*/
/* Hecho: ventas                                                */
/*==============================================================*/

CREATE SEQUENCE seq_venta INCREMENT BY 1 START WITH 1;

create table venta 
(
	id_venta bigint NOT NULL DEFAULT nextval('seq_venta'::regclass),
	fecha bigint references fecha (id_fecha),
  	cliente bigint references cliente (id_cliente),
  	demografia bigint references demografia (id_demografia),
  	plan bigint references plan (id_plan),
  	telefono bigint references telefono (id_telefono),
  	sucursal bigint references sucursal (id_sucursal),
  	operador bigint references operador (id_operador),
  	tarifa_minuto numeric NOT NULL, 
  	tarifa_datos numeric NOT NULL, 
  	valor_total numeric NOT NULL, 
  	PRIMARY KEY (id_venta)
);

/*==============================================================*/
/* Hecho: Llamadas                                              */
/*==============================================================*/

CREATE SEQUENCE seq_llamada INCREMENT BY 1 START WITH 1;

create table llamada 
(
	id_llamada bigint NOT NULL DEFAULT nextval('seq_llamada'::regclass),
	fecha bigint references fecha (id_fecha),
	hora bigint references hora (id_hora),
  	cliente bigint references cliente (id_cliente),
  	demografia bigint references demografia (id_demografia),
  	telefono bigint references telefono (id_telefono),
  	duracion_llamada integer NOT NULL, /*asumiendo duracion en minutos*/
  	flag_roaming dominio_flag NOT NULL, 
  	PRIMARY KEY (id_llamada)
);

/*==============================================================*/
/* Hecho: uso_datos                                                 */
/*==============================================================*/

CREATE SEQUENCE seq_datos INCREMENT BY 1 START WITH 1;

create table uso_datos
(
	id_datos bigint NOT NULL DEFAULT nextval('seq_datos'::regclass),
	fecha bigint references fecha (id_fecha),
	hora bigint references hora (id_hora),
  	cliente bigint references cliente (id_cliente),
  	demografia bigint references demografia (id_demografia),
  	telefono bigint references telefono (id_telefono),
  	duracion_uso_datos integer NOT NULL, /*asumiendo duracion en minutos*/
  	PRIMARY KEY (id_datos)
);