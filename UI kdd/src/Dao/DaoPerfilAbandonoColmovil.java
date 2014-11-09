package Dao;

import ConectorBD.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaoPerfilAbandonoColmovil {

    ConexionBD BaseDeDatos;
    Connection conn;
    Statement stmt;

    public DaoPerfilAbandonoColmovil() {
        BaseDeDatos = new ConexionBD();
    }

    public void consulta(int numDocIdCandidato, String nombre_Proceso/*Parametros dinamicos de las preguntas*/) {
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

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
