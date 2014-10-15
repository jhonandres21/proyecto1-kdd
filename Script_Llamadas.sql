CREATE VIEW COLMOVIL.DATOS_LLAMADA AS (
SELECT 
CLIENTE.numero_identificacion, 
CLIENTE.estrato, 
CLIENTE.genero, 
CLIENTE.estado_civil, 
LLAMADA.fecha_inicio, 
LLAMADA.fecha_finalizacion, 
LLAMADA.utilizo_roaming, 
CONTRATO.id_sim_card, 
CONTRATO.id_plan_voz, 
OPERADOR.nombre 
FROM COLMOVIL.CLIENTE, COLMOVIL.LLAMADA, COLMOVIL.CONTRATO, COLMOVIL.OPERADOR 
WHERE CLIENTE.idcliente = CONTRATO.id_cliente 
AND LLAMADA.id_contrato = CONTRATO.id_contrato 
AND LLAMADA.id_operador_destino = OPERADOR.id_operador);

CREATE OR REPLACE FUNCTION Script_Llamadas() RETURNS VOID AS $$

DECLARE
	numero_identificacion int = 0;
	estrato VARCHAR(100) = '';
	genero VARCHAR(100) = '';
	estado_civil VARCHAR(100) = '';
	fecha_inicio TIMESTAMP;
	fecha_finalizacion TIMESTAMP;
	utilizo_roaming VARCHAR(25);
	id_sim_card BIGINT = 0;
	id_plan_voz BIGINT = 0;
	nombre VARCHAR (50) = '';
BEGIN
    
    id_cliente = (SELECT id_sim_card FROM COLMOVIL.DATOS_LLAMADA WHERE id_sim_card = 11);
    
END;
$$ LANGUAGE plpgsql;

SELECT somefunc();
