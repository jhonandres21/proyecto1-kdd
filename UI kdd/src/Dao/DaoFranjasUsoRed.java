/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Logico.Tiempo;
import ConectorBD.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaoFranjasUsoRed {

    ConexionBD BaseDeDatos;
    Connection conn;
    Statement stmt;
    public static ResultSet rsCandidato;

    public DaoFranjasUsoRed() {
        BaseDeDatos = new ConexionBD();
    }

    public int[] consultaGeneral() {
        
        int night = 0;
        int morning = 0;
        int am = 0;
        int pm = 0;
        int evening = 0;

        int[] conteoLlamadas = new int[5];
        String sql_select;
        sql_select = "SELECT periodo_del_dia, cliente  FROM bodega.tiempo, "
                + "bodega.llamada WHERE bodega.tiempo.sk_tiempo = bodega.llamada.tiempo";
        try {
            conn = BaseDeDatos.conectar();
            System.out.println("Conexión establecida");
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()) {
                if (tabla.getObject(1).toString().equalsIgnoreCase("night")) {
                    night++;
                } else if (tabla.getObject(1).toString().equalsIgnoreCase("morning")) {
                    morning++;
                } else if (tabla.getObject(1).toString().equalsIgnoreCase("am")) {
                    am++;
                } else if (tabla.getObject(1).toString().equalsIgnoreCase("pm")) {
                    pm++;
                } else {
                    evening++;
                }
            }
            
            conteoLlamadas[0] = night;
            conteoLlamadas[1] = morning;
            conteoLlamadas[2] = am;
            conteoLlamadas[3] = pm;
            conteoLlamadas[4] = evening;
            
            System.out.println("Night: " + night);
            System.out.println("Morning: " + morning);
            System.out.println("Am: " + am);
            System.out.println("Pm: " + pm);
            System.out.println("Evening: " + evening);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return conteoLlamadas;
    }
    public static void main(String args[]) {
        DaoFranjasUsoRed p = new DaoFranjasUsoRed();
        p.consultaGeneral();
    }

    public int[] consultaPorOperador(String operador) {
        
        int night = 0;
        int morning = 0;
        int am = 0;
        int pm = 0;
        int evening = 0;

        int[] conteoLlamadas = new int[5];
        String sql_select;
        sql_select = "SELECT periodo_del_dia, cliente  FROM bodega.tiempo, "
                + "bodega.llamada WHERE bodega.tiempo.sk_tiempo = bodega.llamada.tiempo "
                + "AND WHERE bodega.llamada.nombre_operador = '" + operador + "';";
        try {
            conn = BaseDeDatos.conectar();
            Statement sentencia = conn.createStatement();
            ResultSet tabla = sentencia.executeQuery(sql_select);

            while (tabla.next()) {
                if (tabla.getObject(1).toString().equalsIgnoreCase("night")) {
                    night++;
                } else if (tabla.getObject(1).toString().equalsIgnoreCase("morning")) {
                    morning++;
                } else if (tabla.getObject(1).toString().equalsIgnoreCase("am")) {
                    am++;
                } else if (tabla.getObject(1).toString().equalsIgnoreCase("pm")) {
                    pm++;
                } else {
                    evening++;
                }
            }
            
            conteoLlamadas[0] = night;
            conteoLlamadas[1] = morning;
            conteoLlamadas[2] = am;
            conteoLlamadas[3] = pm;
            conteoLlamadas[4] = evening;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        return conteoLlamadas;
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
