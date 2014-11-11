
package Controlador;

import Dao.DaoPerfilUsuarioPlanPrepago;
import Logico.UsuarioPlanPrepago;

/**
 *
 * @author john
 */
public class ControladorPerfilUsuarioPlanPrepago {

    DaoPerfilUsuarioPlanPrepago daoPerfilPlanesPrepago;

    public ControladorPerfilUsuarioPlanPrepago() {

        daoPerfilPlanesPrepago = new DaoPerfilUsuarioPlanPrepago();
    }

    public void getPerfiles(String sexoFemenino, String sexoMasculino, String estadoCivil, String inicioEstrato, String finEstrato) {

        UsuarioPlanPrepago planesPrepago = new UsuarioPlanPrepago();
        planesPrepago.setSexoFemenino(sexoFemenino);
        planesPrepago.setSexoMasculino(sexoMasculino);
        planesPrepago.setEstadoCivil(estadoCivil);
        planesPrepago.setInicioEstrato(inicioEstrato);
        planesPrepago.setFinEstrato(finEstrato);

        String restriccionesClausulaWhere = daoPerfilPlanesPrepago.prepararRestriccionesClausulaWherePerfiles(planesPrepago);
        daoPerfilPlanesPrepago.listaPerfiles(restriccionesClausulaWhere);
    }
}
