package GUI;

import Controlador.ControladorPerfilUsuarioPlanPospago;
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
public class UiPerfilUsuarioPlanPospago extends UiPerfil {

    ControladorPerfilUsuarioPlanPospago controladorPlanesPospago;
    ArrayList dataContratos;
    FXPieChart PieChart;
    FXBarChart BarChart;
    FXLineChart LineChart;

    public UiPerfilUsuarioPlanPospago() {

        controladorPlanesPospago = new ControladorPerfilUsuarioPlanPospago();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {

        //verificamos que el rango de estrato sea correcto
        if (evaluarRangoEstrato()) {
            if (pieRadio.isSelected() || barRadio.isSelected() || lineRadio.isSelected()) {

                String sexoFemenino = "" + checkBoxSexoFemenino.isSelected();
                String sexoMasculino = "" + checkBoxSexoMasculino.isSelected();
                String estadoCivil = "" + comboBoxEstadoCivil.getSelectedItem();
                String inicioEstrato = "" + comboBoxInicioEstrato.getSelectedItem();
                String finEstrato = "" + comboBoxFinEstrato.getSelectedItem();

                dataContratos = controladorPlanesPospago.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);

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
                     PieChart.addData("Contratos Planes Pospago Mes-a-Mes", meses, dataContratos);
                     BarChart.addData(meses, dataContratos);
                     LineChart.addData(meses, dataContratos);
                     }
                     });

                     } else {*/
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (pieRadio.isSelected()) {
                                PieChart = new FXPieChart("Contratos Planes Pospago Mes-a-Mes", meses, dataContratos);
                            } else if (barRadio.isSelected()) {
                                BarChart = new FXBarChart("Contratos Mes-a-Mes", "meses", meses, "Contratos", dataContratos, "Contratos Pospago");
                            } else if (lineRadio.isSelected()) {
                                LineChart = new FXLineChart("Contratos Mes-a-Mes", "meses", meses, "Contratos", dataContratos, "Contratos Pospago");
                            }

                        }
                    });

                    //}
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha extraido la información");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar el tipo de gráfico");
            }
        }

    }
}
