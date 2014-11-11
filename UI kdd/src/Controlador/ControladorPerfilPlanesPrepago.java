
package Controlador;

import Dao.DaoPerfilPlanesPrepago;
import Logico.PlanesPrepago;

/**
 *
 * @author john
 */
public class ControladorPerfilPlanesPrepago {

    DaoPerfilPlanesPrepago daoPerfilPlanesPrepago;

    public ControladorPerfilPlanesPrepago() {

        daoPerfilPlanesPrepago = new DaoPerfilPlanesPrepago();
    }

    public void getPerfiles(String sexoFemenino, String sexoMasculino, String estadoCivil, String inicioEstrato, String finEstrato) {

        PlanesPrepago planesPrepago = new PlanesPrepago();
        planesPrepago.setSexoFemenino(sexoFemenino);
        planesPrepago.setSexoMasculino(sexoMasculino);
        planesPrepago.setEstadoCivil(estadoCivil);
        planesPrepago.setInicioEstrato(inicioEstrato);
        planesPrepago.setFinEstrato(finEstrato);

        String restriccionesClausulaWhere = daoPerfilPlanesPrepago.prepararRestriccionesClausulaWherePerfiles(planesPrepago);
        daoPerfilPlanesPrepago.listaPerfiles(restriccionesClausulaWhere);
    }
}
