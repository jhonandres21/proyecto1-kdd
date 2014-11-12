/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import ConectorBD.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaoPlanesMasEscogidos {

    ConexionBD BaseDeDatos;
    Connection conn;
    Statement stmt;
    public static ResultSet rsCandidato;

    public DaoPlanesMasEscogidos() {
        BaseDeDatos = new ConexionBD();
    }
    
//    public String prepararRestriccionesClausulaWherePerfiles(UsuarioAbandonaColmovil abandono) {
//
//        String where = "";
//
//        if (abandono.getSexoFemenino().equals("true") && abandono.getSexoMasculino().equals("true")) {
//
//            where += " AND (demografia.genero = 'Femenino' OR demografia.genero = 'Masculino') ";
//
//        } else if (abandono.getSexoMasculino().equals("true")) {
//
//            where += " AND demografia.genero = 'Masculino' ";
//
//        } else if (abandono.getSexoFemenino().equals("true")) {
//
//            where += " AND demografia.genero = 'Femenino' ";
//        }
//
//
//        if (!abandono.getEstadoCivil().equals("Escoger una Opción")) {
//            where += " AND estado_civil = '" + abandono.getEstadoCivil() + "'";
//        }
//
//        if (!abandono.getInicioEstrato().equals("Escoger una Opción")) {
//
//            where += " AND (demografia.estrato BETWEEN " + abandono.getInicioEstrato() + " AND " + abandono.getFinEstrato() + ")";
//        }
//
//        return where;
//    }

    public void consultaGeneral() {
        
        String sql_select;
        sql_select = "";
        try {
            conn = BaseDeDatos.conectar();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }

    public void consultarPrepago(String tipoPlan, boolean corporativo) {
        String sql_select;
        sql_select = "";
        try {
            conn = BaseDeDatos.conectar();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void consultarPostPago(String tipoPlan, boolean corporativo) {
        String sql_select;
        sql_select = "";
        try {
            conn = BaseDeDatos.conectar();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()) {

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void desconectar() {
        try {
            rsCandidato.close();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
