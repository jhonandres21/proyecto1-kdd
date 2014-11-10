package Dao;

import ConectorBD.ConexionBD;
import Logico.AbandonoColmovil;
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

    public String prepararRestriccionesClausulaWherePerfiles(AbandonoColmovil abandono) {

        String where = "";

        if (abandono.getSexoFemenino().equals("true") && abandono.getSexoMasculino().equals("true")) {

            where += " AND (demografia.genero = 'Femenino' OR demografia.genero = 'Masculino') ";

        } else if (abandono.getSexoMasculino().equals("true")) {

            where += " AND demografia.genero = 'Masculino' ";

        } else if (abandono.getSexoFemenino().equals("true")) {

            where += " AND demografia.genero = 'Femenino' ";
        }


        if (!abandono.getEstadoCivil().equals("Escoger una Opción")) {
            where += " AND estado_civil = '" + abandono.getEstadoCivil() + "'";
        }

        if (!abandono.getInicioEstrato().equals("Escoger una Opción")) {

            where += " AND (demografia.estrato = " + abandono.getInicioEstrato() + " OR demografia.estrato = " + abandono.getFinEstrato() + ")";
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
            String consulta = "SELECT * FROM bodega.retiro, bodega.demografia\n"
                    + "WHERE retiro.demografia = demografia.sk_demografia" + where + ";";

            System.out.println("Consulta: " + consulta);

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
            System.out.println("----------Termina Consulta\n");
            resultSet.close();
            connection.close();

        } catch (SQLException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage());
        }

        return resultado;
    }
}
