package Controlador;

import Dao.DaoFranjasUsoRed;
import Logico.Tiempo;
import java.sql.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ControlFranjasUsoRed {
    
    DaoFranjasUsoRed daoFranjasUsoRed;

    public ControlFranjasUsoRed() {
        daoFranjasUsoRed = new DaoFranjasUsoRed();
    }
    
    public int[] consultaGeneral() {
        int[] conteoLlamadas = new int[5];
        
        conteoLlamadas = daoFranjasUsoRed.consultaGeneral();
        
        return conteoLlamadas;

    }
    
    public int[] consultaPorOperador(String operador) {
        
        int[] conteoLlamadas = new int[5];
        
        conteoLlamadas = daoFranjasUsoRed.consultaPorOperador(operador);
        
        return conteoLlamadas;
    }

    public void desconectar() {
        daoFranjasUsoRed.desconectar();
    }
}
