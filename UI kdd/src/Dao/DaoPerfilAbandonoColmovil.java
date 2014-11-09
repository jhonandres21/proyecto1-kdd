package Dao;

import ConectorBD.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DaoPerfilAbandonoColmovil {

    ConexionBD conexionBd;

    public DaoPerfilAbandonoColmovil() {
        conexionBd = new ConexionBD();
    }

    public ArrayList<String[]> listaPerfiles() {

        ArrayList<String[]> resultado = new ArrayList<String[]>();

        Statement sentencia;
        Connection connection = conexionBd.conectar();
        ResultSet resultSet;
        System.out.println("Inicia Consulta");
        try {

            sentencia = connection.createStatement();
            String consulta = "SELECT * FROM bodega.retiro, bodega.demografia\n"
                    + "WHERE retiro.demografia = demografia.sk_demografia;";

            resultSet = sentencia.executeQuery(consulta);

            while (resultSet.next()) {

                String temp[] = new String[9];
                temp[0] = "" + resultSet.getObject(1);
                temp[1] = "" + resultSet.getObject(2);
                temp[2] = "" + resultSet.getObject(3);
                temp[3] = "" + resultSet.getObject(4);
                temp[4] = "" + resultSet.getObject(5);
                temp[5] = "" + resultSet.getObject(6);
                temp[6] = "" + resultSet.getObject(7);
                temp[7] = "" + resultSet.getObject(8);
                temp[8] = "" + resultSet.getObject(9);

                resultado.add(temp);
            }
            System.out.println("Termina Consulta");
            resultSet.close();
            connection.close();

        } catch (SQLException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage());
        }

        return resultado;
    }
}
