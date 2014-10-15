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
            String consulta = "SELECT id_plan_datos, nombre FROM colmovil.plan_datos;";
            ResultSet rs = stmt.executeQuery(consulta);
            System.out.println("Si tira consulta");
            rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                if (rs.getObject(1).equals(null) || rs.getObject(1).equals("") || (long) rs.getObject(1) < 0) {
                    plan_datos.setId_plan_datos(0);
                } else {
                    plan_datos.setId_plan_datos((long) rs.getObject(1));
                }

                if (rs.getObject(2).equals(null) || rs.getObject(2).equals("")) {
                    plan_datos.setNombra_plan_datos("No Registra");
                } else {
                    plan_datos.setNombra_plan_datos(rs.getObject(2) + "");
                }
                if (plan_datos.getNombre_plan_datos().toLowerCase().contains("corporativo")) {
                    plan_datos.setEs_corporativo("True");
                } else {
                    plan_datos.setEs_corporativo("False");
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

        String sql = "";
        int numRegistros = planes.size();

        for (int i = 0; i < numRegistros; i++) {
            System.out.println("ID: " + planes.get(i).getId_plan_datos() + "// Nombre del Plan: " + planes.get(i).getNombre_plan_datos() + "// Corporativo: " + planes.get(i).getEs_corporativo());

            sql += "INSERT INTO colmovil_dwh.plan_datos (id_plan_datos, nombre_plan_datos, es_corporativo) VALUES (" + planes.get(i).getId_plan_datos() + ", '" + planes.get(i).getNombre_plan_datos() + "', '" + planes.get(i).getEs_corporativo() + "');\n";
        }

        try {
            con = conexion.conectar();
            Statement sentencia = con.createStatement();
            sentencia.executeUpdate(sql);
            conexion.desconectarBaseDeDatos(con);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);

        }
        System.out.println("Carga Exitosa!");

        System.out.println(sql);
    }

    public static void main(String[] args) {
        ScriptPlanDatos e = new ScriptPlanDatos();
        e.extraerDatos();
        e.cargarDatos();
    }

}
