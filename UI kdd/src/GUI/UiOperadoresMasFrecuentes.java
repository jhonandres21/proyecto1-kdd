package GUI;

import Controlador.ControladorFranjasUsoRed;
import Controlador.ControladorOperadoresMasFrecuentes;
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
public class UiOperadoresMasFrecuentes extends UiPerfil {

    ControladorOperadoresMasFrecuentes controladorOperadoresMasFrecuentes;
    ArrayList<Integer> datosOperadores;
    FXPieChart PieChart;
    FXBarChart BarChart;
    FXLineChart LineChart;

    public UiOperadoresMasFrecuentes() {

        controladorOperadoresMasFrecuentes = new ControladorOperadoresMasFrecuentes();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {
        if (pieRadio.isSelected() || barRadio.isSelected() || lineRadio.isSelected()) {

            String mes = "" + comboBoxMeses.getSelectedItem();

            datosOperadores = controladorOperadoresMasFrecuentes.obtenerOperadores(mes);

            ArrayList<String> operadores = new ArrayList();
            operadores.add("AT&T");
            operadores.add("Avantel");
            operadores.add("Colmovil");
            operadores.add("Comcel");
            operadores.add("EMCALI");
            operadores.add("EPM");
            operadores.add("ETB");
            operadores.add("ETP");
            operadores.add("Metrotel");
            operadores.add("Movistar");
            operadores.add("Orange");
            operadores.add("Telebucaramanga");
            operadores.add("Telefonica");
            operadores.add("Telmex");
            operadores.add("Tigo");
            operadores.add("UNE");
            operadores.add("Vodafone");

            if (!datosOperadores.isEmpty()) {
                /*if (!Visualizador.estadoInicial) {
                 System.out.println("Update");
                 SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                 PieChart.addData("Operadores Mas Frecuentes", operadores, datosOperadores);
                 BarChart.addData(operadores, datosOperadores);
                 LineChart.addData(operadores, datosOperadores);
                 }
                 });

                 } else {*/
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (pieRadio.isSelected()) {
                            PieChart = new FXPieChart("Operadores Mas Frecuentes", operadores, datosOperadores);
                        } else if (barRadio.isSelected()) {
                            BarChart = new FXBarChart("Operadores Mas Frecuentes", "Operadores", operadores, "Cantidad de Llamadas", datosOperadores, "Grafica de Barras");
                        } else if (lineRadio.isSelected()) {
                            LineChart = new FXLineChart("Operadores Mas Frecuentes", "Operadores", operadores, "Cantidad de Llamadas", datosOperadores, "Grafica Lineal");
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
