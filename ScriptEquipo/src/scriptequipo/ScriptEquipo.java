/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptequipo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author eallanosm
 */
public class ScriptEquipo {

    ConexionBD conexion;
    Connection con;
    ArrayList<Equipo> equipos;
    Equipo equipo;

    public ScriptEquipo() {
        conexion = new ConexionBD();
        equipos = null;
        equipo = new Equipo();
        equipos = new ArrayList();
    }

    public void extraerDatos() {

        try {

            con = conexion.conectar();
            Statement stmt = con.createStatement();
            String consulta = "SELECT id_equipo, marca, modelo, precio FROM colmovil.equipo_celular;";
            ResultSet rs = stmt.executeQuery(consulta);
            rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                
                if (rs.getObject(1).equals(null) || rs.getObject(1).equals("") || (long) rs.getObject(1) < 0) {
                    equipo.setId_equipo(0);
                } else {
                    equipo.setId_equipo((long) rs.getObject(1));
                }
                
                if (rs.getObject(2).equals(null) || rs.getObject(2).equals("")) {
                    equipo.setMarca("No Registra");
                } else {
                    equipo.setMarca(rs.getObject(2) + "");
                }
                
                if (rs.getObject(3).equals(null) || rs.getObject(3).equals("")) {
                    equipo.setModelo("No Registra");
                } else {
                    equipo.setModelo(rs.getObject(3) + "");
                }
                
                if (rs.getObject(4).equals(null) || rs.getObject(4).equals("") || (int) rs.getObject(4) < 0) {
                    equipo.setPrecio(0);
                } else {
                    equipo.setPrecio((int) rs.getObject(4));
                }                
                        
                equipos.add(equipo);
                equipo = new Equipo();
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
        int numRegistros = equipos.size();

        for (int i = 0; i < numRegistros; i++) {
            sql = "INSERT INTO colmovil_dwh.equipo (id_equipo, marca, modelo, precio) VALUES (" + equipos.get(i).getId_equipo() + ", '" + equipos.get(i).getMarca() + "', '" + equipos.get(i).getModelo() + "', " + equipos.get(i).getPrecio() + ");";

            try {
                con = conexion.conectar();
                Statement sentencia = con.createStatement();
                sentencia.executeUpdate(sql);
                conexion.desconectarBaseDeDatos(con);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
            }
        }
        System.out.println("Carga Exitosa!");

    }

    public static void main(String[] args) {

        ScriptEquipo e = new ScriptEquipo();
        e.extraerDatos();
        e.cargarDatos();
    }
}
