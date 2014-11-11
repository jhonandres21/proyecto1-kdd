package Controlador;

import Dao.DaoPerfilAbandonoColmovil;
import Logico.AbandonoColmovil;

public class ControladorPerfilAbandonoColmovil {

    DaoPerfilAbandonoColmovil daoPerfilAbandonanColmovil;

    public ControladorPerfilAbandonoColmovil() {

        daoPerfilAbandonanColmovil = new DaoPerfilAbandonoColmovil();
    }

    public void getPerfiles(String sexoFemenino, String sexoMasculino, String estadoCivil, String inicioEstrato, String finEstrato) {

        AbandonoColmovil abandono = new AbandonoColmovil();
        abandono.setSexoFemenino(sexoFemenino);
        abandono.setSexoMasculino(sexoMasculino);
        abandono.setEstadoCivil(estadoCivil);
        abandono.setInicioEstrato(inicioEstrato);
        abandono.setFinEstrato(finEstrato);
        
        String restriccionesClausulaWhere = daoPerfilAbandonanColmovil.prepararRestriccionesClausulaWherePerfiles(abandono);
        daoPerfilAbandonanColmovil.listaPerfiles(restriccionesClausulaWhere);
    }
}
