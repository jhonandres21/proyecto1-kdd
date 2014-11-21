package GUI;

import Controlador.ControladorPerfilUsuarioAbandonaColmovil;
import Gráficos.FXBarChart;
import Gráficos.FXLineChart;
import Gráficos.FXPieChart;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author john
 */
public class UiPerfilUsuarioAbandonaColmovil extends UiPerfil {

    ControladorPerfilUsuarioAbandonaColmovil controladorAbandono;

    public UiPerfilUsuarioAbandonaColmovil() {

        controladorAbandono = new ControladorPerfilUsuarioAbandonaColmovil();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {

        //verificamos que el rango de estrato sea correcto
        if (evaluarRangoEstrato()) {

            String sexoFemenino = "" + checkBoxSexoFemenino.isSelected();
            String sexoMasculino = "" + checkBoxSexoMasculino.isSelected();
            String estadoCivil = "" + comboBoxEstadoCivil.getSelectedItem();
            String inicioEstrato = "" + comboBoxInicioEstrato.getSelectedItem();
            String finEstrato = "" + comboBoxFinEstrato.getSelectedItem();

            ArrayList dataAbandonos = controladorAbandono.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);

            ArrayList<String> meses = new ArrayList();
            meses.add("Enero");
            meses.add("Febrero");
            meses.add("Marzo");
            meses.add("Abril");
            meses.add("Mayo");
            meses.add("Junio");
            meses.add("Julio");
            meses.add("Agosto");
            meses.add("Septiembre");
            meses.add("Octubre");
            meses.add("Noviembre");
            meses.add("Diciembre");

            if (!dataAbandonos.isEmpty()) {
                if (!Visualizador.estadoInicial) {
                    System.out.println("Update");
                    PieChart.addData("Abandonos Mes-a-Mes", meses, dataAbandonos);
                    BarChart.addData(meses, dataAbandonos);
                    LineChart.addData(meses, dataAbandonos);
                } else {
                    Visualizador.estadoInicial = false;
                    PieChart = new FXPieChart("Abandonos Mes-a-Mes", meses, dataAbandonos);
                    BarChart = new FXBarChart("Abandonos Mes-a-Mes", "meses", meses, "Abandonos", dataAbandonos, "Abandonos");
                    LineChart = new FXLineChart("Abandonos Mes-a-Mes", "meses", meses, "Abandonos", dataAbandonos, "Abandonos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se ha extraido la información");
            }
        }
    }
}
