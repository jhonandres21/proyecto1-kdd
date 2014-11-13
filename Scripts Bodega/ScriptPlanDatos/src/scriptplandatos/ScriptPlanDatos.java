/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptplandatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Olaya O
 */
public class ScriptPlanDatos {

    ConexionBD conexion;
    Connection con;
    ArrayList<PlanDatos> planes;
    PlanDatos plan_datos;

    public ScriptPlanDatos() {
        conexion = new ConexionBD();
        planes = null;
        plan_datos = new PlanDatos();
        planes = new ArrayList();
    }

    public void extraerDatos() {

        try {

            con = conexion.conectar();
            Statement stmt = con.createStatement();
            String consulta = "SELECT DISTINCT id_plan_datos, nombre FROM colmovil.plan_datos GROUP BY id_plan_datos, nombre;";
            ResultSet rs = stmt.executeQuery(consulta);
            
            while (rs.next()) {
                if (rs.getObject(1) == null || rs.getObject(1).equals("") || (long) rs.getObject(1) < 0) {
                    plan_datos.setId_plan_datos(0);
                } else {
                    plan_datos.setId_plan_datos((long) rs.getObject(1));
                }

                if (rs.getObject(2) == null || rs.getObject(2).equals("")) {
                    plan_datos.setNombra_plan_datos("No Registra");
                } else {
                    plan_datos.setNombra_plan_datos(rs.getObject(2) + "");
                }
                if (plan_datos.getNombre_plan_datos().toLowerCase().contains("corporativo")) {
                    plan_datos.setEs_corporativo("SI");
                } else {
                    plan_datos.setEs_corporativo("NO");
                }

                planes.add(plan_datos);
                plan_datos = new PlanDatos();
            }

            System.out.println("Extracción y Transformación Exitosa!");
            rs.close();
            conexion.desconectarBaseDeDatos(con);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cargarDatos() {

        try {
            String sql = "";
            con = conexion.conectar();
            Statement sentencia = con.createStatement();
            int numRegistros = planes.size();
            System.out.println("numR " + numRegistros);

            for (int i = 0; i < numRegistros; i++) {
                System.out.println("i: " + i);
                sql = "INSERT INTO bodega.plan_datos (id_plan_datos, nombre_plan_datos, es_corporativo) VALUES (" + planes.get(i).getId_plan_datos() + ", '" + planes.get(i).getNombre_plan_datos() + "', '" + planes.get(i).getEs_corporativo() + "');";
                sentencia.executeUpdate(sql);
            }
            conexion.desconectarBaseDeDatos(con);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);

        }
        System.out.println("Carga Exitosa!");
    }

    public static void main(String[] args) {
        ScriptPlanDatos e = new ScriptPlanDatos();
        e.extraerDatos();
        e.cargarDatos();
    }

}
