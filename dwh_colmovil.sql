
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
  	estado_civil dominio_estado_civil NOT NULL;
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
  	tipo_plan_datos VARCHAR(100) NOT NULL;
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
  	horario_inicio date NOT NULL,
  	horario_fin date NOT NULL,
  	PRIMARY KEY (id_hora)
);

/*==============================================================*/
/* Dimensión: Fecha                                             */
/*==============================================================*/
