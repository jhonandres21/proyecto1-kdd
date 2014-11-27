package GUI;

import Controlador.ControladorFranjasUsoRed;
import Gráficos.FXBarChart;
import Gráficos.FXLineChart;
import Gráficos.FXPieChart;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author luisfemm
 */
public class UiFranjasUsoRed extends UiPerfil {

    ControladorFranjasUsoRed controladorFranjasUsoRed;
    ArrayList<Integer> datosFranjas;
    FXPieChart PieChart;
    FXBarChart BarChart;
    FXLineChart LineChart;

    public UiFranjasUsoRed() {

        controladorFranjasUsoRed = new ControladorFranjasUsoRed();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {
        if (pieRadio.isSelected() || barRadio.isSelected() || lineRadio.isSelected()) {

            String operador = "" + comboBoxOperador.getSelectedItem();

            datosFranjas = controladorFranjasUsoRed.obtenerFranjas(operador);

            ArrayList<String> franjas = new ArrayList();
            franjas.add("AM");
            franjas.add("Evening");
            franjas.add("Morning");
            franjas.add("Night");
            franjas.add("PM");

            if (!datosFranjas.isEmpty()) {
                /*if (!Visualizador.estadoInicial) {
                 System.out.println("Update");
                 SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                 PieChart.addData("Franjas Más Usadas Hasta la Fecha", franjas, datosFranjas);
                 BarChart.addData(franjas, datosFranjas);
                 LineChart.addData(franjas, datosFranjas);
                 }
                 });

                 } else {*/
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (pieRadio.isSelected()) {
                            PieChart = new FXPieChart("Franjas Más Usadas Hasta la Fecha", franjas, datosFranjas);
                        } else if (barRadio.isSelected()) {
                            BarChart = new FXBarChart("Franjas Más Usadas Hasta la Fecha", "Franjas", franjas, "Uso", datosFranjas, "Franjas");
                        } else if (lineRadio.isSelected()) {
                            LineChart = new FXLineChart("Franjas Más Usadas Hasta la Fecha", "Franjas", franjas, "Uso", datosFranjas, "Franjas");
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
