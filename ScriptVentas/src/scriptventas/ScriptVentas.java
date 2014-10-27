/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptventas;

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
public class ScriptVentas {

    ConexionBD conexion;
    Connection con;
    ArrayList<Venta> ventas;
    Venta venta;

    public ScriptVentas() {
        conexion = new ConexionBD();
        ventas = null;
        venta = new Venta();
        ventas = new ArrayList();
    }

    public void extraerDatos() {

        try {
            int contador = 0;

            con = conexion.conectar();
            Statement stmt = con.createStatement();
            String consulta = "SELECT fecha_contrato, id_oficina, id_cliente, id_plan_voz, id_plan_datos, id_equipo_celular,"
                    + " id_sim_card FROM colmovil.contrato;";
            ResultSet rs = stmt.executeQuery(consulta);
            System.out.println("Si tira consulta");
            rs = stmt.executeQuery(consulta);

            long startTime1 = System.nanoTime();
            while (rs.next()) {

                //Descarga Fecha
                if (rs.getObject(1).equals(null) || rs.getObject(1).equals("")) {
                    venta.setId_dim_fecha("0");
                } else {
                    venta.setId_dim_fecha(extraerSkFecha(rs.getObject(1) + ""));
                }
                //Descarga Oficina y localizacion
                if (rs.getObject(2).equals(null) || rs.getObject(2).equals("")) {
                    venta.setId_dim_oficina("0");
                    venta.setId_dim_localizacion("0");

                } else {
                    venta.setId_dim_oficina(extraerSkOficina("" + rs.getObject(2)));
                    venta.setId_dim_localizacion(extraerSkLocalizacion("" + rs.getObject(2)));
                }
                //Descarga Cliente y Demografia
                if (rs.getObject(3).equals(null) || rs.getObject(3).equals("")) {
                    venta.setId_dim_cliente("0");
                    venta.setId_dim_demografia("0");

                } else {
                    venta.setId_dim_cliente(extraerSkCliente("" + rs.getObject(3)));
                    venta.setId_dim_demografia(extraerSkDemografia("" + rs.getObject(3)));
                }
                //Descarga Plan_Voz
                if (rs.getObject(4).equals(null) || rs.getObject(4).equals("")) {
                    venta.setId_dim_plan_voz("0");
                } else {
                    venta.setId_dim_plan_voz(extraerSkPlanVoz("" + rs.getObject(4)));
                }
                //Descarga Plan_Datos
                if (rs.getObject(5).equals(null) || rs.getObject(5).equals("")) {
                    venta.setId_dim_plan_datos("0");
                } else {
                    venta.setId_dim_plan_datos(extraerSkPlanDatos("" + rs.getObject(5)));
                }
                //Descarga Equipo
                if (rs.getObject(6).equals(null) || rs.getObject(6).equals("")) {
                    venta.setId_dim_equipo("0");
                } else {
                    venta.setId_dim_equipo(extraerSkEquipo("" + rs.getObject(6)));
                }
                //Descarga Sim_Card
                if (rs.getObject(7).equals(null) || rs.getObject(7).equals("")) {
                    venta.setId_dim_sim_card("0");
                } else {
                    venta.setId_dim_sim_card(extraerSkSimCard("" + rs.getObject(7)));
                }

                if (rs.getObject(4).equals(null) || rs.getObject(4).equals("") || rs.getObject(5).equals(null) || rs.getObject(5).equals("")) {
                    venta.setPrecio_plan("0");
                } else {
                    //venta.setPrecio_plan(calcularPrecio(rs.getObject(4).toString(), rs.getObject(5).toString()));
                    venta.setPrecio_plan("0");
                }
                long endTime1 = System.nanoTime();
                ventas.add(venta);
                venta = new Venta();
                contador++;

                if (contador % 100 == 0) {
                    long duration1 = (endTime1 - startTime1) / 1000000;
                    System.out.println("Se demoró " + duration1 + " ms, para descargar " + contador + " datos");
                }
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
        int numRegistros = ventas.size();

        for (int i = 0; i < numRegistros; i++) {
//            System.out.println("SK Fecha: " + ventas.get(i).getId_dim_fecha()
//                    + "// SK localizacion: " + ventas.get(i).getId_dim_localizacion()
//                    + "// SK Cliente: " + ventas.get(i).getId_dim_cliente()
//                    + "// SK Demografia: " + ventas.get(i).getId_dim_demografia()
//                    + "// SK Plan Voz: " + ventas.get(i).getId_dim_plan_voz()
//                    + "// SK Plan Datos: " + ventas.get(i).getId_dim_plan_datos()
//                    + "// SK Equipo: " + ventas.get(i).getId_dim_equipo()
//                    + "// SK Oficina: " + ventas.get(i).getId_dim_oficina()
//                    + "// SK Sim Card: " + ventas.get(i).getId_dim_sim_card());

            sql += "INSERT INTO colmovil_dwh.venta ( fecha, localizacion, cliente, demografia, plan_voz, plan_datos,"
                    + "  equipo, oficina, sim_card, precio_plan)"
                    + " VALUES (" + ventas.get(i).getId_dim_fecha()
                    + ", " + ventas.get(i).getId_dim_localizacion()
                    + ", " + ventas.get(i).getId_dim_cliente()
                    + ", " + ventas.get(i).getId_dim_demografia()
                    + ", " + ventas.get(i).getId_dim_plan_voz()
                    + ", " + ventas.get(i).getId_dim_plan_datos()
                    + ", " + ventas.get(i).getId_dim_equipo()
                    + ", " + ventas.get(i).getId_dim_oficina()
                    + ", " + ventas.get(i).getId_dim_sim_card()
                    + ", " + ventas.get(i).getPrecio_plan()
                    + ");\n";
        }
        System.out.println("Comienza subida a BD!");
        long startTime2 = System.nanoTime();
        try {
            con = conexion.conectar();
            Statement sentencia = con.createStatement();
            sentencia.executeUpdate(sql);
            conexion.desconectarBaseDeDatos(con);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);

        }
        long endTime2 = System.nanoTime();

        long duration2 = (endTime2 - startTime2) / 1000000;
        System.out.println("Duración de subida a BD: " + duration2 + " ms");
    }

    public String extraerSkFecha(String Fecha) {

        try {

            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_fecha FROM colmovil_dwh.fecha WHERE fecha = '" + Fecha + "';";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String extraerSkOficina(String oficina) {

        try {
            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_oficina FROM colmovil_dwh.oficina WHERE id_oficina = " + oficina + ";";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String extraerSkLocalizacion(String oficina) {

        try {

            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_localizacion FROM colmovil_dwh.localizacion WHERE departamento = (SELECT departamento FROM colmovil.localizacion WHERE idlocalizacion = (SELECT id_localizacion FROM colmovil.oficina WHERE id_oficina =" + oficina + ")) AND ciudad = (SELECT ciudad FROM colmovil.localizacion WHERE idlocalizacion = (SELECT id_localizacion FROM colmovil.oficina WHERE id_oficina = " + oficina + "));";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String extraerSkCliente(String cliente) {

        try {

            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_cliente FROM colmovil_dwh.cliente WHERE sk_cliente = " + cliente + ";";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String extraerSkDemografia(String cliente) {

        try {

            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_demografia FROM colmovil_dwh.demografia WHERE estado_civil = (SELECT estado_civil FROM colmovil.cliente WHERE idcliente = " + cliente + ") AND estrato = (SELECT estrato FROM colmovil.cliente WHERE idcliente = " + cliente + ") AND genero = (SELECT genero FROM colmovil.cliente WHERE idcliente = " + cliente + ");";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String extraerSkPlanVoz(String plan_voz) {

        try {

            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_plan_voz FROM colmovil_dwh.plan_voz WHERE id_plan_voz = " + plan_voz + ";";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String extraerSkPlanDatos(String plan_datos) {

        try {

            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_plan_datos FROM colmovil_dwh.plan_datos WHERE id_plan_datos = " + plan_datos + ";";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String extraerSkEquipo(String equipo) {

        try {

            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_equipo FROM colmovil_dwh.equipo WHERE id_equipo = " + equipo + ";";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String extraerSkSimCard(String sim_card) {

        try {

            Statement stmt = con.createStatement();
            String consulta = "SELECT sk_sim_card FROM colmovil_dwh.sim_card WHERE id_sim_card = " + sim_card + ";";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    return "0";
                } else {
                    return resultSet.getObject(1).toString();
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";
    }

    public String calcularPrecio(String plan_voz, String plan_datos) {

        try {
            
            int precio = 0;
            
            System.out.println("SELECT precio FROM colmovil.plan_voz WHERE id_plan_voz = " + plan_voz + ";");

            Statement stmt = con.createStatement();
            String consulta = "SELECT precio FROM colmovil.plan_voz WHERE id_plan_voz = " + plan_voz + ";";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);
            
            System.out.println(resultSet.getObject(1) + "");

            while (resultSet.next()) {
                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    precio += 0;
                } else {
                    precio += Integer.parseInt(resultSet.getObject(1).toString());
                }
            }
            
            Statement stmt2 = con.createStatement();
            String consulta2 = "SELECT tipo_tarifa, valor_tarifa FROM colmovil.plan_datos WHERE id_plan_datos = " + plan_datos + ";";
            ResultSet resultSet2 = stmt2.executeQuery(consulta2);
            resultSet2 = stmt2.executeQuery(consulta2);

            while (resultSet2.next()) {
                if (resultSet2.getObject(1).equals(null) || resultSet2.getObject(1).equals("") || !resultSet2.getObject(1).toString().equals("Fija") || !resultSet2.getObject(1).toString().equals("0")) {
                    precio += 0;
                } else {
                    precio += Integer.parseInt(resultSet2.getObject(2).toString());
                }
            }

            resultSet.close();
            conexion.desconectarBaseDeDatos(con);
            
            return precio + "";
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }
        return "0";

    }

    public static void main(String[] args) {
        ScriptVentas v = new ScriptVentas();
        v.extraerDatos();
        v.cargarDatos();

    }

}
