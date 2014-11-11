
package Controlador;

import Dao.DaoPerfilPlanesPospago;
import Logico.PlanesPospago;

/**
 *
 * @author john
 */
public class ControladorPerfilPlanesPospago {
    
     DaoPerfilPlanesPospago daoPerfilPlanesPospago;

    public ControladorPerfilPlanesPospago() {

        daoPerfilPlanesPospago = new DaoPerfilPlanesPospago();
    }

    public void getPerfiles(String sexoFemenino, String sexoMasculino, String estadoCivil, String inicioEstrato, String finEstrato) {

        PlanesPospago planesPospago = new PlanesPospago();
        planesPospago.setSexoFemenino(sexoFemenino);
        planesPospago.setSexoMasculino(sexoMasculino);
        planesPospago.setEstadoCivil(estadoCivil);
        planesPospago.setInicioEstrato(inicioEstrato);
        planesPospago.setFinEstrato(finEstrato);

        String restriccionesClausulaWhere = daoPerfilPlanesPospago.prepararRestriccionesClausulaWherePerfiles(planesPospago);
        daoPerfilPlanesPospago.listaPerfiles(restriccionesClausulaWhere);
    }
}
