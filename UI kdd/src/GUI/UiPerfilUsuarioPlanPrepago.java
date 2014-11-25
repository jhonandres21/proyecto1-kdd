package GUI;

import Controlador.ControladorPerfilUsuarioPlanPrepago;
import Gráficos.FXBarChart;
import Gráficos.FXLineChart;
import Gráficos.FXPieChart;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author john
 */
public class UiPerfilUsuarioPlanPrepago extends UiPerfil {

    ControladorPerfilUsuarioPlanPrepago controladorPlanesPrepago;
    ArrayList dataContratos;

    public UiPerfilUsuarioPlanPrepago() {

        controladorPlanesPrepago = new ControladorPerfilUsuarioPlanPrepago();
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

            dataContratos = controladorPlanesPrepago.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);

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

            if (!dataContratos.isEmpty()) {
                /*if (!Visualizador.estadoInicial) {
                    System.out.println("Update");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            PieChart.addData("Contratos Planes Prepago Mes-a-Mes", meses, dataContratos);
                            BarChart.addData(meses, dataContratos);
                            LineChart.addData(meses, dataContratos);
                        }
                    });

                } else {*/
                    Visualizador.estadoInicial = false;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            PieChart = new FXPieChart("Contratos Planes Prepago Mes-a-Mes", meses, dataContratos);
                            BarChart = new FXBarChart("Contratos Planes Prepago Mes-a-Mes", "meses", meses, "Contratos", dataContratos, "Contratos Prepago");
                            LineChart = new FXLineChart("Contratos Planes Prepago Mes-a-Mes", "meses", meses, "Contratos", dataContratos, "Contratos Prepago");
                        }
                    });
                //}
            } else {
                JOptionPane.showMessageDialog(null, "No se ha extraido la información");
            }
        }

    }
}
