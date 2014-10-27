package DAO;

import ConectorBD.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import scriptretiros.Retiro;

/**
 *
 * @author John Medina
 */
public class ScriptRetiro {

    ConexionBD conexion;
    Connection con;
    ArrayList<Retiro> retiros;
    long fecha, cliente, demografia, planVoz = 0, planDatos = 0;

    public ScriptRetiro() {

        conexion = new ConexionBD();
        retiros = new ArrayList();
    }

    public void extraerDatos() {

        try {

            con = conexion.conectar();
            Statement stmt = con.createStatement();

            String consulta = "select info_retiro.fecha, colmovil.cliente.numero_identificacion, colmovil.cliente.estrato, colmovil.cliente.genero,\n"
                    + "       colmovil.cliente.estado_civil, info_retiro.id_plan_voz, info_retiro.id_plan_datos \n"
                    + "       from\n"
                    + "	   (select * from\n"
                    + "		        (select * from colmovil.contrato where id_contrato in (select id_contrato from colmovil.retiro)) as info_contrato,\n"
                    + "		        colmovil.retiro \n"
                    + "		     where info_contrato.id_contrato = colmovil.retiro.id_contrato) as info_retiro,\n"
                    + "	   colmovil.cliente\n"
                    + "	Where colmovil.cliente.idcliente = info_retiro.id_cliente;";

            ResultSet resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {

                fecha = extraerId_Fecha("" + resultSet.getObject(1));
                cliente = extraerId_Cliente((int) resultSet.getObject(2));
                demografia = extraerId_Demografia((int) resultSet.getObject(3), "" + resultSet.getObject(4), "" + resultSet.getObject(5));
                planVoz = extraerIdPlanVoz((long) resultSet.getObject(6));
                planDatos = extraerIdPlanDatos((long) resultSet.getObject(7));

                Retiro retiro = new Retiro();
                retiro.setFecha(fecha);
                retiro.setCliente(cliente);
                retiro.setDemografia(demografia);
                retiro.setPlanVoz(planVoz);
                retiro.setPlanDatos(planDatos);
                retiros.add(retiro);
            }

            System.out.println("Extraccion y Transformacion Exitosa!");
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
    }//fin metodo extraer datos

    public long extraerId_Fecha(String fecha) {

        try {

            long idFecha = 0;
            con = conexion.conectar();
            Statement stmt = con.createStatement();

            String consulta = "select sk_fecha from bodega.fecha where fecha = '" + fecha + "';";

            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet.next();

            idFecha = (long) resultSet.getObject(1);

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

            return idFecha;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public long extraerId_Cliente(int numero_cedula) {
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();

            String consultaInterna = "SELECT sk_cliente FROM bodega.cliente WHERE numero_id = " + numero_cedula + ";";
            ResultSet resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            long sk_cliente = (long) resultSet.getObject(1);
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

            return sk_cliente;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public long extraerId_Demografia(int estrato, String genero, String estado_civil) {
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();
            String consultaInterna = "SELECT sk_demografia FROM bodega.demografia WHERE estrato = " + estrato + " AND genero = '" + genero + "' AND estado_civil = '" + estado_civil + "';";
            ResultSet resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            long sk_demografia = (long) resultSet.getObject(1);
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

            return sk_demografia;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public long extraerIdPlanVoz(long idPlanVoz) {
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();

            String consultaInterna = "SELECT sk_plan_voz FROM bodega.plan_voz WHERE id_plan_voz = " + idPlanVoz + ";";
            ResultSet resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            long sk_plan_voz = (long) resultSet.getObject(1);
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

            return sk_plan_voz;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public long extraerIdPlanDatos(long idPlanDatos) {
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();

            String consultaInterna = "SELECT sk_plan_datos FROM bodega.plan_datos WHERE id_plan_datos = " + idPlanDatos + ";";
            ResultSet resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            long sk_plan_voz = (long) resultSet.getObject(1);
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

            return sk_plan_voz;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public void cargarDatos() {

        String sql;
        int numRegistros = retiros.size();

        try {
            con = conexion.conectar();
            Statement sentencia = con.createStatement();

            for (int i = 0; i < numRegistros; i++) {

                sql = "INSERT INTO bodega.retiro (fecha, cliente, demografia, plan_voz, plan_datos) VALUES ("
                        + retiros.get(i).getFecha() + ", " + retiros.get(i).getCliente() + ", " + retiros.get(i).getDemografia()
                        + ", " + retiros.get(i).getPlanVoz() + ", " + retiros.get(i).getPlanDatos() + ");";

                sentencia.executeUpdate(sql);

            }

            conexion.desconectarBaseDeDatos(con);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Carga Exitosa!");
    }
}
