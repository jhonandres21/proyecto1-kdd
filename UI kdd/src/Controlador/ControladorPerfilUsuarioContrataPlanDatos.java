package Controlador;

import Dao.DaoPerfilUsuarioContrataPlanDatos;
import Logico.UsuarioContrataPlanDatos;

/**
 *
 * @author john
 */
public class ControladorPerfilUsuarioContrataPlanDatos {

    DaoPerfilUsuarioContrataPlanDatos daoPerfilUsuarioContrataPlanDatos;

    public ControladorPerfilUsuarioContrataPlanDatos() {

        daoPerfilUsuarioContrataPlanDatos = new DaoPerfilUsuarioContrataPlanDatos();
    }

    public void getPerfiles(String sexoFemenino, String sexoMasculino, String estadoCivil, String inicioEstrato, String finEstrato) {

        UsuarioContrataPlanDatos usuarioPlanDatos = new UsuarioContrataPlanDatos();
        usuarioPlanDatos.setSexoFemenino(sexoFemenino);
        usuarioPlanDatos.setSexoMasculino(sexoMasculino);
        usuarioPlanDatos.setEstadoCivil(estadoCivil);
        usuarioPlanDatos.setInicioEstrato(inicioEstrato);
        usuarioPlanDatos.setFinEstrato(finEstrato);

        String restriccionesClausulaWhere = daoPerfilUsuarioContrataPlanDatos.prepararRestriccionesClausulaWherePerfiles(usuarioPlanDatos);
        daoPerfilUsuarioContrataPlanDatos.listaPerfiles(restriccionesClausulaWhere);
    }
}
