package cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author johnmm
 */
public class ScriptClientes {

    ConexionBD conexion;
    Connection con;
    ArrayList<Cliente> clientes;
    Cliente cliente;

    public ScriptClientes() {
        conexion = new ConexionBD();
        clientes = null;
        cliente = new Cliente();
        clientes = new ArrayList();
    }

    public void extraerDatos() {

        try {

            con = conexion.conectar();
            Statement stmt = con.createStatement();
            String consulta = "SELECT numero_identificacion, tipo_identificacion, nombre, apellido, fecha_nacimiento, email FROM colmovil.cliente;";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {

                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    cliente.setNumeroId(0);
                } else {
                    cliente.setNumeroId((int) resultSet.getObject(1));
                }

                if (resultSet.getObject(2).equals(null) || resultSet.getObject(2).equals("")) {
                    cliente.setTipoId("C.C");
                } else {
                    cliente.setTipoId("" + resultSet.getObject(2));
                }

                if (resultSet.getObject(3).equals(null) || resultSet.getObject(3).equals("")) {
                    cliente.setNombres("No Registra");
                } else {
                    cliente.setNombres("" + resultSet.getObject(3));
                }

                if (resultSet.getObject(4).equals(null) || resultSet.getObject(4).equals("")) {
                    cliente.setApellidos("No Registra");
                } else {
                    cliente.setApellidos("" + resultSet.getObject(4));
                }

                if (resultSet.getObject(5).equals(null) || resultSet.getObject(5).equals("")) {
                    cliente.setFecha_nacimiento("1995-01-01");
                } else {
                    cliente.setFecha_nacimiento("" + resultSet.getObject(5));
                }

                if (resultSet.getObject(6).equals(null) || resultSet.getObject(6).equals("")) {
                    cliente.setEmail("No Registra");
                } else {
                    cliente.setEmail("" + resultSet.getObject(6));
                }

                clientes.add(cliente);
                cliente = new Cliente();
            }

            System.out.println("Extracción y Transformación Exitosa!");
            resultSet.close();
            conexion.desconectarBaseDeDatos(con);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cargarDatos() {

        String sql;
        int numRegistros = clientes.size();

        for (int i = 0; i < numRegistros; i++) {
            sql = "INSERT INTO colmovil_dwh.cliente (numero_id, tipo_id, nombres, apellidos, fecha_nacimiento, email) "
                    + "VALUES (" + clientes.get(i).getNumeroId() + ", '" + clientes.get(i).getTipoId() + "','" + clientes.get(i).getNombres()
                    + "','" + clientes.get(i).getApellidos() + "','" + clientes.get(i).getFecha_nacimiento() + "','"
                    + clientes.get(i).getEmail() + "');";

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

        ScriptClientes script = new ScriptClientes();
        script.extraerDatos();
        script.cargarDatos();
    }
}
