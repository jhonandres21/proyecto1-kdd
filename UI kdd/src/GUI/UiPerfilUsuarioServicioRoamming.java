package GUI;

import Controlador.ControladorPerfilUsuarioServicioRoamming;
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
public class UiPerfilUsuarioServicioRoamming extends UiPerfil {

    ControladorPerfilUsuarioServicioRoamming controladorUsuarioServicioRoamming;
    ArrayList dataContratos;
    FXPieChart PieChart;
    FXBarChart BarChart;
    FXLineChart LineChart;

    public UiPerfilUsuarioServicioRoamming() {

        controladorUsuarioServicioRoamming = new ControladorPerfilUsuarioServicioRoamming();
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

                dataContratos = controladorUsuarioServicioRoamming.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);

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
                     PieChart.addData("Uso Roamming Prepago Mes-a-Mes", meses, dataContratos);
                     BarChart.addData(meses, dataContratos);
                     LineChart.addData(meses, dataContratos);
                     }
                     });

                     } else {*/
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (pieRadio.isSelected()) {
                                PieChart = new FXPieChart("Uso Roamming Prepago Mes-a-Mes", meses, dataContratos);
                            } else if (barRadio.isSelected()) {
                                BarChart = new FXBarChart("Uso Roamming Mes-a-Mes", "meses", meses, "Contratos", dataContratos, "Roamming");
                            } else if (lineRadio.isSelected()) {
                                LineChart = new FXLineChart("Uso Roamming Mes-a-Mes", "meses", meses, "Contratos", dataContratos, "Roamming");
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
