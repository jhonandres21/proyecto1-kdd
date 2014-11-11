
package Controlador;

import Dao.DaoPerfilUsuarioServicioRoamming;
import Logico.UsuarioServicioRoamming;

/**
 *
 * @author john
 */
public class ControladorPerfilUsuarioServicioRoamming {
    
    DaoPerfilUsuarioServicioRoamming daoPerfilUsuarioServicioRoamming;

    public ControladorPerfilUsuarioServicioRoamming() {

        daoPerfilUsuarioServicioRoamming = new DaoPerfilUsuarioServicioRoamming();
    }

    public void getPerfiles(String sexoFemenino, String sexoMasculino, String estadoCivil, String inicioEstrato, String finEstrato) {

        UsuarioServicioRoamming usuarioServicioRoamming = new UsuarioServicioRoamming();
        usuarioServicioRoamming.setSexoFemenino(sexoFemenino);
        usuarioServicioRoamming.setSexoMasculino(sexoMasculino);
        usuarioServicioRoamming.setEstadoCivil(estadoCivil);
        usuarioServicioRoamming.setInicioEstrato(inicioEstrato);
        usuarioServicioRoamming.setFinEstrato(finEstrato);

        String restriccionesClausulaWhere = daoPerfilUsuarioServicioRoamming.prepararRestriccionesClausulaWherePerfiles(usuarioServicioRoamming);
        daoPerfilUsuarioServicioRoamming.listaPerfiles(restriccionesClausulaWhere);
    }
}
