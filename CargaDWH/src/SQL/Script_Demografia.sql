INSERT INTO BODEGA.DEMOGRAFIA (estado_civil, estrato, genero)
SELECT DISTINCT ON(
CCLIENTE.estado_civil, 
CCLIENTE.estrato, 
CCLIENTE.genero) 
CCLIENTE.estado_civil, 
CCLIENTE.estrato, 
CCLIENTE.genero 
FROM
COLMOVIL.CLIENTE AS CCLIENTE
ORDER BY CCLIENTE.genero;
