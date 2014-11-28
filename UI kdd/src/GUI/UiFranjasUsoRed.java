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
                if (franjasActivo && ((franjasPie && pieRadio.isSelected()) || (franjasBar && barRadio.isSelected()) || (franjasLine && lineRadio.isSelected()))) {
                    System.out.println("Update");

                    if (franjasPie) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                PieChart.addData(franjas, datosFranjas);
                            }
                        });
                    } else if (franjasBar) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                BarChart.addData(franjas, datosFranjas);
                            }
                        });
                    } else if (franjasLine) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                LineChart.addData(franjas, datosFranjas);
                            }
                        });
                    }
                } else {
                    perfilesActivo = false;
                    operadoresActivo = false;
                    franjasActivo = true;
                    tendenciaAniosActivo = false;
                    datosYVozActivo = false;
                    vozActivo = false;
                    datosActivo = false;
                    preVsPosActivo = false;
                    corpActivo = false;
                    preActivo = false;
                    posActivo = false;

                    if (pieRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                PieChart = new FXPieChart("Franjas Más Usadas Hasta la Fecha", franjas, datosFranjas);
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Pie Chart", PieChart);
                                franjasPie = true;
                                franjasBar = false;
                                franjasLine = false;
                            }
                        });
                    } else if (barRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                BarChart = new FXBarChart("Franjas Más Usadas Hasta la Fecha", "Franjas", franjas, "Uso", datosFranjas, "Franjas");
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Bar Chart", BarChart);
                                franjasPie = false;
                                franjasBar = true;
                                franjasLine = false;
                            }
                        });
                    } else if (lineRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                LineChart = new FXLineChart("Franjas Más Usadas Hasta la Fecha", "Franjas", franjas, "Uso", datosFranjas, "Franjas");
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Line Chart", LineChart);
                                franjasPie = false;
                                franjasBar = false;
                                franjasLine = true;
                            }
                        });
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se ha extraido la información");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe Seleccionar el tipo de gráfico");
        }
    }
}
