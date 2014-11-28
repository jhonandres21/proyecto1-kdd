package GUI;

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
                if (operadoresActivo && ((operadoresPie && pieRadio.isSelected()) || (operadoresBar && barRadio.isSelected()) || (operadoresLine && lineRadio.isSelected()))) {
                    System.out.println("Update");

                    if (operadoresPie) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                PieChart.addData(operadores, datosOperadores);
                            }
                        });
                    } else if (operadoresBar) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                BarChart.addData(operadores, datosOperadores);
                            }
                        });
                    } else if (operadoresLine) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                LineChart.addData(operadores, datosOperadores);
                            }
                        });
                    }
                } else {
                    perfilesActivo = false;
                    operadoresActivo = true;
                    franjasActivo = false;
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
                                PieChart = new FXPieChart("Operadores Mas Frecuentes", operadores, datosOperadores);
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Pie Chart", PieChart);
                                operadoresPie = true;
                                operadoresBar = false;
                                operadoresLine = false;
                            }
                        });
                    } else if (barRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                BarChart = new FXBarChart("Operadores Mas Frecuentes", "Operadores", operadores, "Cantidad de Llamadas", datosOperadores, "Grafica de Barras");
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Bar Chart", BarChart);
                                operadoresPie = false;
                                operadoresBar = true;
                                operadoresLine = false;
                            }
                        });
                    } else if (lineRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                LineChart = new FXLineChart("Operadores Mas Frecuentes", "Operadores", operadores, "Cantidad de Llamadas", datosOperadores, "Grafica Lineal");
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Line Chart", LineChart);
                                operadoresPie = false;
                                operadoresBar = false;
                                operadoresLine = true;
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
