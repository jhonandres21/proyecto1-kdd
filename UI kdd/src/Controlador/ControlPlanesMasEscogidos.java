/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoPlanesMasEscogidos;
import Logico.PlanDatos;
import Logico.PlanVoz;
import java.sql.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ControlPlanesMasEscogidos {
    
    DaoPlanesMasEscogidos daoPlanesMasEscogidos;

    public ControlPlanesMasEscogidos() {
        daoPlanesMasEscogidos = new DaoPlanesMasEscogidos();
    }
    
    public void consulta(int numDocIdCandidato, String nombre_Proceso/*Paramatros dinamicos*/) {

    }

    public void desconectar() {
        daoPlanesMasEscogidos.desconectar();
    }
}
