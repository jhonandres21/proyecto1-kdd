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

    public UiPerfilUsuarioTendenciaPlanVoz() {

        controladorTendenciaPLanVoz = new ControladorTendenciaPlanVoz();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {
        System.out.println(perfilesActivo);
        System.out.println(tendenciaAniosActivo);
        if (pieRadio.isSelected() || barRadio.isSelected() || lineRadio.isSelected()) {

            //verificamos que el rango de estrato sea correcto
            String inicioAnio = "" + comboBoxInicioAnios.getSelectedItem();

            dataLlamadas = controladorTendenciaPLanVoz.getPerfiles(inicioAnio);
            System.out.println(dataLlamadas.size());
            ArrayList<String> dataString = new ArrayList();
            if (dataLlamadas.size() == 12) {
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
            if (!dataLlamadas.isEmpty()) {
                if (perfilesActivo && !inicioAnio.equals("Escoger una Opción") && ((perfilesPie && pieRadio.isSelected()) || (perfilesBar && barRadio.isSelected()) || (perfilesLine && lineRadio.isSelected()))) {
                    System.out.println("Update");
                    if (perfilesPie) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                PieChart.addData(dataString, dataLlamadas);
                            }
                        });
                    } else if (perfilesBar) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                BarChart.addData(dataString, dataLlamadas);
                            }
                        });
                    } else if (perfilesLine) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                LineChart.addData(dataString, dataLlamadas);
                            }
                        });
                    }
                } else if (tendenciaAniosActivo && inicioAnio.equals("Escoger una Opción") && ((tendenciaAniosPie && pieRadio.isSelected()) || (tendenciaAniosBar && barRadio.isSelected()) || (tendenciaAniosLine && lineRadio.isSelected()))) {
                    if (tendenciaAniosPie) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                PieChart.addData(dataString, dataLlamadas);
                            }
                        });
                    } else if (tendenciaAniosBar) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                BarChart.addData(dataString, dataLlamadas);
                            }
                        });
                    } else if (tendenciaAniosLine) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                LineChart.addData(dataString, dataLlamadas);
                            }
                        });
                    }
                } else {
                    if (!inicioAnio.equals("Escoger una Opción")) {
                        perfilesActivo = true;
                        operadoresActivo = false;
                        franjasActivo = false;
                        tendenciaAniosActivo = false;
                        datosYVozActivo = false;
                        vozActivo = false;
                        datosActivo = false;
                        preVsPosActivo = false;
                        corpActivo = false;
                        preActivo = false;
                        posActivo = false;
                    } else {
                        perfilesActivo = false;
                        operadoresActivo = false;
                        franjasActivo = false;
                        tendenciaAniosActivo = true;
                        datosYVozActivo = false;
                        vozActivo = false;
                        datosActivo = false;
                        preVsPosActivo = false;
                        corpActivo = false;
                        preActivo = false;
                        posActivo = false;
                    }
                    System.out.println(perfilesActivo);
                    System.out.println(tendenciaAniosActivo);
                    if (pieRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                PieChart = new FXPieChart("Tendencia Llamadas Mes-a-Mes", dataString, dataLlamadas);
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Pie Chart", PieChart);
                                if (perfilesActivo) {
                                    perfilesPie = true;
                                    perfilesBar = false;
                                    perfilesLine = false;
                                } else if (tendenciaAniosActivo) {
                                    tendenciaAniosPie = true;
                                    tendenciaAniosBar = false;
                                    tendenciaAniosLine = false;
                                }
                            }
                        });
                    } else if (barRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                BarChart = new FXBarChart("Tendencia Llamadas Mes-a-Mes", "Años", dataString, "Llamadas", dataLlamadas, "Llamadas");
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Bar Chart", BarChart);
                                if (perfilesActivo) {
                                    perfilesPie = false;
                                    perfilesBar = true;
                                    perfilesLine = false;
                                } else if (tendenciaAniosActivo) {
                                    tendenciaAniosPie = false;
                                    tendenciaAniosBar = true;
                                    tendenciaAniosLine = false;
                                }
                            }
                        });
                    } else if (lineRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                LineChart = new FXLineChart("Uso Roamming Mes-a-Mes", "Años", dataString, "Llamadas", dataLlamadas, "Llamadas");
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Line Chart", LineChart);
                                if (perfilesActivo) {
                                    perfilesPie = false;
                                    perfilesBar = false;
                                    perfilesLine = true;
                                } else if (tendenciaAniosActivo) {
                                    tendenciaAniosPie = false;
                                    tendenciaAniosBar = false;
                                    tendenciaAniosLine = true;
                                }
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
