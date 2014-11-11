
package Dao;

import ConectorBD.ConexionBD;
import Logico.PlanesPospago;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author john
 */
public class DaoPerfilPlanesPospago {
    
    ConexionBD conexionBd;

    public DaoPerfilPlanesPospago() {
        conexionBd = new ConexionBD();
    }

    public String prepararRestriccionesClausulaWherePerfiles(PlanesPospago planesPospago) {

        String where = "";

        if (planesPospago.getSexoFemenino().equals("true") && planesPospago.getSexoMasculino().equals("true")) {

            where += " AND (demografia.genero = 'Femenino' OR demografia.genero = 'Masculino') ";

        } else if (planesPospago.getSexoMasculino().equals("true")) {

            where += " AND demografia.genero = 'Masculino' ";

        } else if (planesPospago.getSexoFemenino().equals("true")) {

            where += " AND demografia.genero = 'Femenino' ";
        }

        if (!planesPospago.getEstadoCivil().equals("Escoger una Opción")) {
            where += " AND estado_civil = '" + planesPospago.getEstadoCivil() + "'";
        }

        if (!planesPospago.getInicioEstrato().equals("Escoger una Opción")) {

            where += " AND (demografia.estrato = " + planesPospago.getInicioEstrato() + " OR demografia.estrato = " + planesPospago.getFinEstrato() + ")";
        }

        return where;
    }

    public ArrayList<String[]> listaPerfiles(String where) {

        ArrayList<String[]> resultado = new ArrayList<String[]>();

        Statement sentencia;
        Connection connection = conexionBd.conectar();
        ResultSet resultSet;
        System.out.println("----------Inicia Consulta");
        try {

            sentencia = connection.createStatement();
            String consulta = "SELECT * FROM bodega.venta, bodega.demografia\n"
                            + "WHERE venta.demografia = demografia.sk_demografia\n"
                            + "AND (venta.plan_voz = 1 AND venta.plan_datos = 1) " + where + ";";

            System.out.println("Consulta: " + consulta);

            resultSet = sentencia.executeQuery(consulta);

            while (resultSet.next()) {

                String temp[] = new String[14];
                temp[0] = "" + resultSet.getObject(1);
                temp[1] = "" + resultSet.getObject(2);
                temp[2] = "" + resultSet.getObject(3);
                temp[3] = "" + resultSet.getObject(4);
                temp[4] = "" + resultSet.getObject(5);
                temp[5] = "" + resultSet.getObject(6);
                temp[6] = "" + resultSet.getObject(7);
                temp[7] = "" + resultSet.getObject(8);
                temp[8] = "" + resultSet.getObject(9);
                temp[9] = "" + resultSet.getObject(10);
                temp[10] = "" + resultSet.getObject(11);
                temp[11] = "" + resultSet.getObject(12);
                temp[12] = "" + resultSet.getObject(13);
                temp[13] = "" + resultSet.getObject(14);

                resultado.add(temp);
            }
            System.out.println("----------Termina Consulta\n");
            resultSet.close();
            connection.close();

        } catch (SQLException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
        }

        return resultado;
    }
}
