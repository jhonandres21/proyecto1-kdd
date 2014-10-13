package scriptsimcard;

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
public class ScriptSimCard {

    ConexionBD conexion;
    Connection con;
    ArrayList<Simcard> simcards;
    Simcard simcard;

    public ScriptSimCard() {
        conexion = new ConexionBD();
        simcards = null;
        simcard = new Simcard();
        simcards = new ArrayList();
    }

    public void extraerDatos() {

        try {

            con = conexion.conectar();
            Statement stmt = con.createStatement();
            String consulta = "SELECT numero_serie, numero_telefono, id_sim_card FROM colmovil.sim_card;";
            ResultSet resultSet = stmt.executeQuery(consulta);
            resultSet = stmt.executeQuery(consulta);

            while (resultSet.next()) {

                if (resultSet.getObject(1).equals(null) || resultSet.getObject(1).equals("")) {
                    simcard.setNumeroSerie("No Registra");
                } else {
                    simcard.setNumeroSerie("" + resultSet.getObject(1));
                }

                if (resultSet.getObject(2).equals(null) || resultSet.getObject(2).equals("")) {
                    simcard.setNumeroTelefono("No Registra");
                } else {
                    simcard.setNumeroTelefono("" + resultSet.getObject(2));
                }

                simcard.setId_sim_card((int)resultSet.getObject(3));

                simcards.add(simcard);
                simcard = new Simcard();
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
        int numRegistros = simcards.size();

        for (int i = 0; i < numRegistros; i++) {
            sql = "INSERT INTO colmovil_dwh.sim_card (id_sim_card, numero_serie, numero_telefono) VALUES (" + simcards.get(i).getId_sim_card()+ ", '" + simcards.get(i).getNumeroSerie() + "', '" + simcards.get(i).getNumeroTelefono() + "');";

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

        ScriptSimCard e = new ScriptSimCard();
        e.extraerDatos();
        e.cargarDatos();
    }
}
