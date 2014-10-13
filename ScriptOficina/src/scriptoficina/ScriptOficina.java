/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptoficina;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author esteban
 */
public class ScriptOficina {

    ConexionBD conexion;
    Connection con;
    ArrayList<Oficina> oficinas;
    Oficina oficina;

    public ScriptOficina() {
        conexion = new ConexionBD();
        oficinas = null;
        oficina = new Oficina();
        oficinas = new ArrayList();
    }

    public void extraerDatos() {

        try {

            con = conexion.conectar();
            Statement stmt = con.createStatement();
            String consulta = "SELECT direccion, numero_empleados, id_oficina FROM colmovil.oficina;";
            ResultSet rs = stmt.executeQuery(consulta);
            rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                if (rs.getObject(1).equals("") || rs.getObject(1).equals(null)) {
                    oficina.setDireccion("No Registra.");
                } else {
                    oficina.setDireccion(rs.getObject(1) + "");
                }

                if (rs.getObject(2).equals("") || rs.getObject(2).equals(null) || (int) rs.getObject(2) < 0) {
                    oficina.setNumero_empleados(0);
                } else {
                    oficina.setNumero_empleados((int) rs.getObject(2));
                }

                oficina.setId_oficina((long) rs.getObject(3));
                
                oficinas.add(oficina);
                oficina = new Oficina();
            }
            
            System.out.println("Extracción y Transformación Exitosa!");
            rs.close();
            conexion.desconectarBaseDeDatos(con);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void cargarDatos() {
        
        String sql;
        int numRegistros = oficinas.size();

        for (int i = 0; i < numRegistros; i++) {
            sql = "INSERT INTO colmovil_dwh.oficina (id_oficina, direccion, numero_empleados) VALUES (" + oficinas.get(i).getId_oficina() + ", '" + oficinas.get(i).getDireccion() + "', " + oficinas.get(i).getNumero_empleados() + " );";

            try {
                con = conexion.conectar();
                Statement sentencia = con.createStatement();
                sentencia.executeUpdate(sql);
                conexion.desconectarBaseDeDatos(con);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public static void main(String[] args) {

        ScriptOficina s = new ScriptOficina();
        s.extraerDatos();
        s.cargarDatos();
    }
}
