package Controlador;

import Dao.DaoFranjasUsoRed;

public class ControlFranjasUsoRed {

    DaoFranjasUsoRed daoFranjasUsoRed;

    public ControlFranjasUsoRed() {
        daoFranjasUsoRed = new DaoFranjasUsoRed();
    }

    public int[] obtenerFranjas(String operador) {
        int[] conteoLlamadas = new int[5];
        
        String where = "";
        
        where = daoFranjasUsoRed.prepararRestriccionesClausulaWhereFranjas(operador);

        conteoLlamadas = daoFranjasUsoRed.listaFranjas(where);

        return conteoLlamadas;

    }

    public void desconectar() {
        daoFranjasUsoRed.desconectar();
    }
}
