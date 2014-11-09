/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoPerfilUsuariosPostPago;
import Logico.Cliente;
import Logico.Demografia;
import java.sql.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ControlUsuariosPostPago {
    
    DaoPerfilUsuariosPostPago daoPerfilUsuariosPostPago;

    public ControlUsuariosPostPago() {
        daoPerfilUsuariosPostPago = new DaoPerfilUsuariosPostPago();
    }
    
    public void consulta(int numDocIdCandidato, String nombre_Proceso/*Paramatros dinamicos*/) {

    }

    public void desconectar() {
        daoPerfilUsuariosPostPago.desconectar();
    }
}