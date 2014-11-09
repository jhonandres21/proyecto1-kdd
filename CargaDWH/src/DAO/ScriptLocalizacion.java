
package DAO;

import ConectorBD.ConexionBD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Olaya O
 */
public class ScriptLocalizacion {

    String scriptDirPath = "src/SQL";
    ConexionBD conexion;
    Connection con;

    public ScriptLocalizacion() {
        conexion = new ConexionBD();
    }

    private String extraerSentenciaDeSQL() {

        try {
            File sqlDir = new File(scriptDirPath);

            if (sqlDir.canRead() && sqlDir.isDirectory()) {

                //Si listan todos los documentos del directorio.
                File[] sqlDocFiles = sqlDir.listFiles();
                //Revisa que la lista no sea nula.
                if (sqlDocFiles != null) {
                    for (int i = 0; i < sqlDocFiles.length; i++) {

                        //Se revisa que se itere sobre todos los archivos csv
                        if (!sqlDocFiles[i].isDirectory()) {
                            String nombreArchivoSQL = sqlDocFiles[i].getName().substring(0, sqlDocFiles[i].getName().length() - 4);
                            System.out.println(nombreArchivoSQL);
                            if ("Script_Localizacion".equals(nombreArchivoSQL)) {
                                String texto = "";
                                BufferedReader br = new BufferedReader(new FileReader(sqlDocFiles[i]));

                                try {
                                    StringBuilder sb = new StringBuilder();
                                    String lineaSQL = br.readLine();

                                    while (lineaSQL != null) {

                                        sb.append(lineaSQL);
                                        sb.append("\n");
                                        lineaSQL = br.readLine();
                                    }

                                    texto += sb.toString();

                                } finally {
                                    br.close();
                                }
                                return texto;
                            }
                        }

                    }
                }
            }
            return "";
        } catch (IOException e) {
            System.out.println("Error de tipo " + e.getClass() + "\n dice: " + e.getMessage());
        }
        return "";
    }

    public void cargarSentenciaSQL() {
        try {
            con = conexion.conectar();
            Statement sentencia = con.createStatement();
            String sql = extraerSentenciaDeSQL();
            
            sentencia.executeUpdate(sql);
            conexion.desconectarBaseDeDatos(con);

            System.out.println("Carga Exitosa!");

        } catch (SQLException ex) {
            Logger.getLogger(ScriptLocalizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
