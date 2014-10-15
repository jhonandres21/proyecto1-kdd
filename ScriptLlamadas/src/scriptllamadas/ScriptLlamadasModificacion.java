/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptllamadas;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Luisfemm
 */
public class ScriptLlamadasModificacion {

    ConexionBD conexion;
    Connection con;
    ArrayList<Llamada> llamadas;
    long fecha, tiempo, cliente, demografia, sim_card, plan_voz = 0;
    String nombre_operador, flag_roamming = "";
    int duracion_llamada = 0;

    public ScriptLlamadasModificacion() {
        conexion = new ConexionBD();
        llamadas = new ArrayList();
    }

    public long extraerId_Fecha(Timestamp fecha_timestamp) {

        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();
            String consultaInterna = "SELECT '" + fecha_timestamp + "'::DATE;";
            ResultSet resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            Date fechaTemp = (Date) resultSet.getObject(1);
            resultSet.close();

            consultaInterna = "SELECT sk_fecha FROM colmovil_dwh.fecha WHERE fecha = '" + fechaTemp + "'";
            resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            long fecha = (long) resultSet.getObject(1);
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

            return fecha;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public long extraerId_Tiempo(Timestamp fecha_timestamp) {
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();
            String consultaInterna = "SELECT '" + fecha_timestamp + "'::TIME;";
            ResultSet resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            String tiempoTemp = "" + resultSet.getObject(1);
            String[] tiempoX = tiempoTemp.split(":");
            String tiempoY = tiempoX[0] + ":" + tiempoX[1];
            resultSet.close();

            consultaInterna = "SELECT sk_tiempo FROM colmovil_dwh.tiempo WHERE tiempo = '" + tiempoY + "';";
            resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            long tiempo = (long) resultSet.getObject(1);
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

            return tiempo;

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
            
            String consultaInterna = "SELECT sk_cliente FROM colmovil_dwh.cliente WHERE numero_id = " + numero_cedula + ";";
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
            String consultaInterna = "SELECT sk_demografia FROM colmovil_dwh.demografia WHERE estrato = " + estrato + " AND genero = '" + genero + "' AND estado_civil = '" + estado_civil + "';";
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

    public long extraerId_Sim_Card(long id_sim_card) {
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();
            String consultaInterna = "SELECT sk_sim_card FROM colmovil_dwh.sim_card WHERE id_sim_card = " + id_sim_card + ";";
            ResultSet resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            long sk_sim_card = (long) resultSet.getObject(1);
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

            return sk_sim_card;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public long extraerId_Plan_Voz(long id_plan_voz) {
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();
            
            String consultaInterna = "SELECT sk_plan_voz FROM colmovil_dwh.plan_voz WHERE id_plan_voz = " + id_plan_voz + ";";
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

    public int extraerDuracion_Llamada(Timestamp fecha_Inicial_Timestamp, Timestamp fecha_Final_Timestamp) {
        try {
            con = conexion.conectar();
            Statement stmt = con.createStatement();
            stmt = con.createStatement();
            String consultaInterna = "SELECT '" + fecha_Inicial_Timestamp + "'::TIME;";
            ResultSet resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            Time tiempo_Inicial_Temp = (Time) resultSet.getObject(1);
            resultSet.close();

            consultaInterna = "SELECT '" + fecha_Final_Timestamp + "'::TIME;";
            resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            Time tiempo_Final_Temp = (Time) resultSet.getObject(1);
            resultSet.close();

            consultaInterna = "SELECT (TIME '" + tiempo_Final_Temp + "' - TIME '" + tiempo_Inicial_Temp + "')::TIME;";
            resultSet = stmt.executeQuery(consultaInterna);
            resultSet.next();

            String tiempoTemp = "" + resultSet.getObject(1);
            resultSet.close();
            String[] tiempoX = tiempoTemp.split(":");

            int tiempo_horas = Integer.parseInt(tiempoX[0]) * 60;
            int tiempo_minutos = Integer.parseInt(tiempoX[1]);

            int duracion_llamada = tiempo_horas + tiempo_minutos;
            conexion.desconectarBaseDeDatos(con);

            return duracion_llamada;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public String extraerFlag_roaming(String flag_roamming) {
        String flag = "";
        if (flag_roamming.equals("SI")) {
            flag = "True";
        } else {
            flag = "False";
        }
        return flag;
    }

    public void extraerDatos() {

        try {

            con = conexion.conectar();
            Statement stmt = con.createStatement();
            String consulta = "SELECT CLIENTE.numero_identificacion, CLIENTE.estrato, CLIENTE.genero, CLIENTE.estado_civil, LLAMADA.fecha_inicio, LLAMADA.fecha_finalizacion, LLAMADA.utilizo_roaming, CONTRATO.id_sim_card, CONTRATO.id_plan_voz, OPERADOR.nombre FROM COLMOVIL.CLIENTE, COLMOVIL.LLAMADA, COLMOVIL.CONTRATO, COLMOVIL.OPERADOR WHERE CLIENTE.idcliente = CONTRATO.id_cliente AND LLAMADA.id_contrato = CONTRATO.id_contrato AND LLAMADA.id_operador_destino = OPERADOR.id_operador;";
            ResultSet resultSet = stmt.executeQuery(consulta);
            int i = 0;

            while (resultSet.next()) {
                
                cliente = extraerId_Cliente((int) resultSet.getObject(1));
                demografia = extraerId_Demografia((int) resultSet.getObject(2), "" + resultSet.getObject(3), "" + resultSet.getObject(4));
                fecha = extraerId_Fecha((Timestamp) resultSet.getObject(5));
                tiempo = extraerId_Tiempo((Timestamp) resultSet.getObject(5));
                duracion_llamada = extraerDuracion_Llamada((Timestamp) resultSet.getObject(5), (Timestamp) resultSet.getObject(6));
                flag_roamming = extraerFlag_roaming("" + resultSet.getObject(7));
                sim_card = extraerId_Sim_Card((long) resultSet.getObject(8));
                plan_voz = extraerId_Plan_Voz((long) resultSet.getObject(9));
                nombre_operador = "" + resultSet.getObject(10);

                Llamada llamada = new Llamada();
                llamada.setFecha(fecha);
                llamada.setTiempo(tiempo);
                llamada.setCliente(cliente);
                llamada.setDemografia(demografia);
                llamada.setSim_card(sim_card);
                llamada.setPlan_voz(plan_voz);
                llamada.setNombre_operador(nombre_operador);
                llamada.setDuracion_llamada(duracion_llamada);
                llamada.setFlag_roaming(flag_roamming);
                llamadas.add(llamada);
                i++;
//                System.out.println("Extrayendo dato: " + i);
                if (i == 100) {
                    break;
                };

            }

            System.out.println("Extraccion y Transformacion Exitosa!");
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cargarDatos() {

        String sql;
        int numRegistros = llamadas.size();
        try {
            con = conexion.conectar();
            Statement sentencia = con.createStatement();

            for (int i = 0; i < numRegistros; i++) {
                sql = "INSERT INTO colmovil_dwh.llamada (fecha, tiempo, cliente, demografia, sim_card, plan_voz, nombre_operador, duracion_llamada, flag_roaming) VALUES ("
                        + llamadas.get(i).getFecha() + ", " + llamadas.get(i).getTiempo() + ", "
                        + llamadas.get(i).getCliente() + ", " + llamadas.get(i).getDemografia() + ", "
                        + llamadas.get(i).getSim_card() + ", " + llamadas.get(i).getPlan_voz() + ", '"
                        + llamadas.get(i).getNombre_operador() + "', " + llamadas.get(i).getDuracion_llamada() + ", '" + llamadas.get(i).getFlag_roaming() + "');";
                sentencia.executeUpdate(sql);
            }

            conexion.desconectarBaseDeDatos(con);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Carga Exitosa!");

    }

    public static void main(String[] args) {

        ScriptLlamadasModificacion script = new ScriptLlamadasModificacion();
        script.extraerDatos();
        script.cargarDatos();
    }
}
