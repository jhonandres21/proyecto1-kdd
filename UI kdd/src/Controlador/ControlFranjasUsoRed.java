/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public void consulta(int numDocIdCandidato, String nombre_Proceso/*Paramatros dinamicos*/) {

    }

    public void desconectar() {
        daoFranjasUsoRed.desconectar();
    }
}
