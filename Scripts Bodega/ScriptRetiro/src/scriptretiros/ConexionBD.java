package scriptretiros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionBD {

    private final String driver = "org.postgresql.Driver";
    private final String name_dir = "jdbc:postgresql://localhost:5432/colmovil";
    private final String user = "postgres";
    private final String password = "pipe14";
    private Connection conexion;
   
    public Connection conectar() {

        boolean errorCargandoDriver = false;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "No se encontró el controlador", JOptionPane.ERROR_MESSAGE);
            errorCargandoDriver = true;
        }

        if (!errorCargandoDriver) {

            try {
                conexion = DriverManager.getConnection(name_dir, user, password);
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, se.getMessage(), "Error en base de datos!", JOptionPane.ERROR_MESSAGE);
            }
        }

        return conexion;
    }// fin metodo conectar

    public void desconectarBaseDeDatos(Connection conexion) {

        try {
            conexion.close();
        } catch (Exception e) {
            System.out.println("No se pudo cerrar.");
        }
    }// fin del metodo desconectarDeBaseDatos
}
