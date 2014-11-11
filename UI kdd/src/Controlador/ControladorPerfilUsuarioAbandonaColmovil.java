package Controlador;

import Dao.DaoPerfilUsuarioAbandonaColmovil;
import Logico.UsuarioAbandonaColmovil;

public class ControladorPerfilUsuarioAbandonaColmovil {

    DaoPerfilUsuarioAbandonaColmovil daoPerfilAbandonanColmovil;

    public ControladorPerfilUsuarioAbandonaColmovil() {

        daoPerfilAbandonanColmovil = new DaoPerfilUsuarioAbandonaColmovil();
    }

    public void getPerfiles(String sexoFemenino, String sexoMasculino, String estadoCivil, String inicioEstrato, String finEstrato) {

        UsuarioAbandonaColmovil abandono = new UsuarioAbandonaColmovil();
        abandono.setSexoFemenino(sexoFemenino);
        abandono.setSexoMasculino(sexoMasculino);
        abandono.setEstadoCivil(estadoCivil);
        abandono.setInicioEstrato(inicioEstrato);
        abandono.setFinEstrato(finEstrato);
        
        String restriccionesClausulaWhere = daoPerfilAbandonanColmovil.prepararRestriccionesClausulaWherePerfiles(abandono);
        daoPerfilAbandonanColmovil.listaPerfiles(restriccionesClausulaWhere);
    }
}
