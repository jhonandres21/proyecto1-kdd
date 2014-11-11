
package Controlador;

import Dao.DaoPerfilUsuarioPlanPospago;
import Logico.UsuarioPlanPospago;

/**
 *
 * @author john
 */
public class ControladorPerfilUsuarioPlanPospago {
    
     DaoPerfilUsuarioPlanPospago daoPerfilPlanesPospago;

    public ControladorPerfilUsuarioPlanPospago() {

        daoPerfilPlanesPospago = new DaoPerfilUsuarioPlanPospago();
    }

    public void getPerfiles(String sexoFemenino, String sexoMasculino, String estadoCivil, String inicioEstrato, String finEstrato) {

        UsuarioPlanPospago planesPospago = new UsuarioPlanPospago();
        planesPospago.setSexoFemenino(sexoFemenino);
        planesPospago.setSexoMasculino(sexoMasculino);
        planesPospago.setEstadoCivil(estadoCivil);
        planesPospago.setInicioEstrato(inicioEstrato);
        planesPospago.setFinEstrato(finEstrato);

        String restriccionesClausulaWhere = daoPerfilPlanesPospago.prepararRestriccionesClausulaWherePerfiles(planesPospago);
        daoPerfilPlanesPospago.listaPerfiles(restriccionesClausulaWhere);
    }
}
