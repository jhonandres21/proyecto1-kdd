/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptplanvoz;

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
public class ScriptPlanVoz {

    ConexionBD conexion;
    Connection con;
    ArrayList<PlanVoz> planes;
    PlanVoz plan_voz;

    public ScriptPlanVoz() {
        conexion = new ConexionBD();
        planes = null;
        plan_voz = new PlanVoz();
        planes = new ArrayList();
    }

    public void extraerDatos() {

        try {

            con = conexion.conectar();
            Statement stmt = con.createStatement();
            String consulta = "SELECT id_plan_voz, nombre FROM colmovil.plan_voz;";
            ResultSet rs = stmt.executeQuery(consulta);
            
            while (rs.next()) {
                if (rs.getObject(1) == null || rs.getObject(1).equals("") || (long) rs.getObject(1) < 0) {
                    plan_voz.setId_plan(0);
                } else {
                    plan_voz.setId_plan((long) rs.getObject(1));
                }

                if (plan_voz.getId_plan() == 1) {
                    plan_voz.setTipo_plan("prepago");
                } else {
                    plan_voz.setTipo_plan("postpago");
                }

                if (rs.getObject(2) == null || rs.getObject(2).equals("")) {
                    plan_voz.setNombre_plan("No Registra");
                } else {
                    plan_voz.setNombre_plan(rs.getObject(2) + "");
                }
                if (plan_voz.getNombre_plan().toLowerCase().contains("corporativo")) {
                    plan_voz.setEs_corporativo("True");
                } else {
                    plan_voz.setEs_corporativo("False");
                }

                planes.add(plan_voz);
                plan_voz = new PlanVoz();
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
            con = conexion.conectar();
            Statement sentencia = con.createStatement();
            String sql = "";
            int numRegistros = planes.size();

            for (int i = 0; i < numRegistros; i++) {
                //System.out.println("ID: " + planes.get(i).getId_plan() + "// tipo: " + planes.get(i).getTipo_plan() + "// Nombre del Plan: " + planes.get(i).getNombre_plan() + "// Corporativo: " + planes.get(i).getEs_corporativo());
                sql += "INSERT INTO bodega.plan_voz (id_plan_voz, tipo_plan_voz, nombre_plan_voz, es_corporativo) VALUES (" + planes.get(i).getId_plan() + ", '" + planes.get(i).getTipo_plan() + "', '" + planes.get(i).getNombre_plan() + "', '" + planes.get(i).getEs_corporativo() + "');";
                sentencia.executeUpdate(sql);
            }
            conexion.desconectarBaseDeDatos(con);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Carga Exitosa!");
    }

    public static void main(String[] args) {
        ScriptPlanVoz e = new ScriptPlanVoz();
        e.extraerDatos();
        e.cargarDatos();
    }

}
