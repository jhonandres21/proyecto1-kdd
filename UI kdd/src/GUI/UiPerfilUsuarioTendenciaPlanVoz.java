/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controlador.ControladorTendenciaPlanVoz;
import Gráficos.FXBarChart;
import Gráficos.FXLineChart;
import Gráficos.FXPieChart;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Juan Olaya O
 */
public class UiPerfilUsuarioTendenciaPlanVoz extends UiPerfil {

    ControladorTendenciaPlanVoz controladorTendenciaPLanVoz;
    ArrayList<Integer> dataLlamadas;
    FXPieChart PieChart;
    FXBarChart BarChart;
    FXLineChart LineChart;

    public UiPerfilUsuarioTendenciaPlanVoz() {

        controladorTendenciaPLanVoz = new ControladorTendenciaPlanVoz();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {
        if (pieRadio.isSelected() || barRadio.isSelected() || lineRadio.isSelected()) {

            //verificamos que el rango de estrato sea correcto
            String inicioAnio = "" + comboBoxInicioAnios.getSelectedItem();

            dataLlamadas = controladorTendenciaPLanVoz.getPerfiles(inicioAnio);
            System.out.println(dataLlamadas.size());
            ArrayList<String> dataString = new ArrayList();
            if (dataLlamadas.size() == 12) {
                System.out.println("meses");
                dataString.add("Enero");
                dataString.add("Febrero");
                dataString.add("Marzo");
                dataString.add("Abril");
                dataString.add("Mayo");
                dataString.add("Junio");
                dataString.add("Julio");
                dataString.add("Agosto");
                dataString.add("Septiembre");
                dataString.add("Octubre");
                dataString.add("Noviembre");
                dataString.add("Diciembre");
            } else {
                for (int i = 2000; i <= 2014; i++) {
                    dataString.add(i + "");
                }
            }
            System.out.println("Tags size: " + dataString + " Values size: " + dataLlamadas);
            if (!dataLlamadas.isEmpty()) {
                /*if (!Visualizador.estadoInicial) {
                 System.out.println("Update");
                 SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                 PieChart.addData("Tendencia Llamadas Mes-a-Mes", dataString, dataLlamadas);
                 BarChart.addData(dataString, dataLlamadas);
                 LineChart.addData(dataString, dataLlamadas);
                 }
                 });
                 } else {*/
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (pieRadio.isSelected()) {
                            PieChart = new FXPieChart("Tendencia Llamadas Mes-a-Mes", dataString, dataLlamadas);
                        } else if (barRadio.isSelected()) {
                            BarChart = new FXBarChart("Tendencia Llamadas Mes-a-Mes", "Años", dataString, "Llamadas", dataLlamadas, "Llamadas");
                        } else if (lineRadio.isSelected()) {
                            LineChart = new FXLineChart("Uso Roamming Mes-a-Mes", "Años", dataString, "Llamadas", dataLlamadas, "Llamadas");
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
