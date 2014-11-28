package GUI;

import Controlador.ControladorPlanesMasEscogidos;
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
public class UiPlanesMasEscogidos extends UiPerfil {

    ControladorPlanesMasEscogidos controladorPlanesMasEscogidos;
    ArrayList<Integer> datosPlanes;

    public UiPlanesMasEscogidos() {

        controladorPlanesMasEscogidos = new ControladorPlanesMasEscogidos();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {

        if (evaluarPlanesEscogidos()) {
            if (pieRadio.isSelected() || barRadio.isSelected() || lineRadio.isSelected()) {

                String datos = "" + checkBoxDatos.isSelected();
                String prepago = "" + checkBoxPrepagoVoz.isSelected();
                String postpago = "" + checkBoxPostpagoVoz.isSelected();
                String corporativo = "" + checkBoxCorporativo.isSelected();
                
                boolean datosBool = checkBoxDatos.isSelected();
                boolean prepagoBool = checkBoxPrepagoVoz.isSelected();
                boolean postpagoBool = checkBoxPostpagoVoz.isSelected();
                boolean corporativoBool = checkBoxCorporativo.isSelected();

                datosPlanes = controladorPlanesMasEscogidos.obtenerPlanes(datos, prepago, postpago, corporativo);

                ArrayList<String> parametros = new ArrayList();

                if (datosPlanes.get(datosPlanes.size() - 1) == 1) {
                    parametros.add("Planes Datos");
                    parametros.add("Planes Voz");
                } else if (datosPlanes.get(datosPlanes.size() - 1) == 2) {
                    parametros.add("Datos 5Megas");
                    parametros.add("Datos 50Megas");
                    parametros.add("Datos 100Megas");
                    parametros.add("Datos 1Giga");
                    parametros.add("Datos Ilimitados");
                    parametros.add("Datos Estandar X Kb");
                    parametros.add("Datos Premium X Kb");
                    parametros.add("Datos Gold X Kb");
                } else if (datosPlanes.get(datosPlanes.size() - 1) == 3) {
                    parametros.add("Plan Casual");
                    parametros.add("Plan Estandar");
                    parametros.add("Plan Premium");
                    parametros.add("Plan Familia");
                    parametros.add("Plan Gold");
                    parametros.add("Plan Total_1600");
                    parametros.add("Plan Total_600");
                    parametros.add("Plan Casual Abierto");
                    parametros.add("Plan Estandar Abierto");
                    parametros.add("Plan Premium Abierto");
                    parametros.add("Plan Familia Abierto");
                    parametros.add("Plan Gold Abierto");
                    parametros.add("Plan Total_1600 Abierto");
                    parametros.add("Plan Total_600 Abierto");
                } else if (datosPlanes.get(datosPlanes.size() - 1) == 4) {
                    parametros.add("Plan Estandar Corporativo");
                    parametros.add("Plan Premium Corporativo");
                    parametros.add("Plan Gold Corporativo");
                } else if (datosPlanes.get(datosPlanes.size() - 1) == 5) {
                    parametros.add("Prepago");
                    parametros.add("Postpago");
                } else if (datosPlanes.get(datosPlanes.size() - 1) == 6) {
                    parametros.add("Prepago");
                    parametros.add("Plan Casual");
                    parametros.add("Plan Estandar");
                    parametros.add("Plan Premium");
                    parametros.add("Plan Familia");
                    parametros.add("Plan Gold");
                    parametros.add("Plan Total_1600");
                    parametros.add("Plan Total_600");
                    parametros.add("Plan Casual Abierto");
                    parametros.add("Plan Estandar Abierto");
                    parametros.add("Plan Premium Abierto");
                    parametros.add("Plan Familia Abierto");
                    parametros.add("Plan Gold Abierto");
                    parametros.add("Plan Total_1600 Abierto");
                    parametros.add("Plan Total_600 Abierto");
                    parametros.add("Plan Estandar Corporativo");
                    parametros.add("Plan Premium Corporativo");
                    parametros.add("Plan Gold Corporativo");
                }

                if (!datosPlanes.isEmpty()) {
                    /*if (!Visualizador.estadoInicial) {
                     System.out.println("Update");
                     SwingUtilities.invokeLater(new Runnable() {
                     @Override
                     public void run() {
                     PieChart.addData("Planes Más Escogidos Hasta la Fecha", parametros, datosPlanes);
                     BarChart.addData(parametros, datosPlanes);
                     LineChart.addData(parametros, datosPlanes);
                     }
                     });

                     } else {
                    perfilesActivo = false;
                    operadoresActivo = false;
                    franjasActivo = false;
                    tendenciaMesesActivo = false;
                    tendenciaAniosActivo = false;
                    if (datosBool && prepagoBool && postpagoBool && corporativoBool) {
                        datosYVozActivo = true;
                        vozActivo = false;
                        datosActivo = false;
                        preVsPosActivo = false;
                        corpActivo = false;
                        preActivo = false;
                        posActivo = false;
                    }else if(!datosBool && prepagoBool && postpagoBool && corporativoBool){
                        datosYVozActivo = false;
                        vozActivo = true;
                        datosActivo = false;
                        preVsPosActivo = false;
                        corpActivo = false;
                        preActivo = false;
                        posActivo = false;
                    }else if(datosBool && !prepagoBool && !postpagoBool && !corporativoBool){
                        datosYVozActivo = false;
                        vozActivo = false;
                        datosActivo = true;
                        preVsPosActivo = false;
                        corpActivo = false;
                        preActivo = false;
                        posActivo = false;
                    }else if(!datosBool && prepagoBool && postpagoBool && !corporativoBool){
                        datosYVozActivo = false;
                        vozActivo = false;
                        datosActivo = false;
                        preVsPosActivo = true;
                        corpActivo = false;
                        preActivo = false;
                        posActivo = false;
                    }else if((!datosBool && !prepagoBool && postpagoBool && corporativoBool) || (!datosBool && !prepagoBool && !postpagoBool && corporativoBool)){
                        datosYVozActivo = false;
                        vozActivo = false;
                        datosActivo = false;
                        preVsPosActivo = false;
                        corpActivo = true;
                        preActivo = false;
                        posActivo = false;
                    }else if(!datosBool && prepagoBool && !postpagoBool && !corporativoBool){
                        datosYVozActivo = false;
                        vozActivo = false;
                        datosActivo = false;
                        preVsPosActivo = false;
                        corpActivo = false;
                        preActivo = true;
                        posActivo = false;
                    }else if(!datosBool && !prepagoBool && postpagoBool && !corporativoBool){
                        datosYVozActivo = false;
                        vozActivo = false;
                        datosActivo = false;
                        preVsPosActivo = false;
                        corpActivo = false;
                        preActivo = false;
                        posActivo = true;
                    }*/

                    if (pieRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                PieChart = new FXPieChart("Planes Más Escogidos Hasta la Fecha", parametros, datosPlanes);
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Pie Chart", PieChart);
                                /*if(datosYVozActivo){
                                    datosYVozPie = true;
                                   datosYVozBar = false;
                                   datosYVozLine = false;
                                   vozPie = false;
                                   vozLine = false;
                                   datosPie = false;
                                   datosBar = false;
                                   datosLine = false;
                                   preVsPosPie = false;
                                   preVsPosBar = false;
                                   preVsPosLine = false;
                                   corpPie = false;
                                   corpBar = false;
                                   corpLine = false;
                                   prePie = false;
                                   preBar = false;
                                   preLine = false;
                                   posPie = false;
                                   posBar = false;
                                   posLine = false;
                                }else if(vozActivo){
                                   datosYVozPie = false;
                                   datosYVozBar = false;
                                   datosYVozLine = false;
                                   vozPie = true;
                                   vozLine = false;
                                   datosPie = false;
                                   datosBar = false;
                                   datosLine = false;
                                   preVsPosPie = false;
                                   preVsPosBar = false;
                                   preVsPosLine = false;
                                   corpPie = false;
                                   corpBar = false;
                                   corpLine = false;
                                   prePie = false;
                                   preBar = false;
                                   preLine = false;
                                   posPie = false;
                                   posBar = false;
                                   posLine = false;
                                }*/
                            }
                        });
                    } else if (barRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                BarChart = new FXBarChart("Planes Más Escogidos Hasta la Fecha", "", parametros, "Cantidad de Ventas", datosPlanes, "Planes más Escogidos");
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Bar Chart", BarChart);
                            }
                        });
                    } else if (lineRadio.isSelected()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                LineChart = new FXLineChart("Planes Más Escogidos Hasta la Fecha", "", parametros, "Cantidad de Ventas", datosPlanes, "Planes más Escogidos");
                                Visualizador.panelPestanas.removeAll();
                                Visualizador.panelPestanas.add("Line Chart", LineChart);
                            }
                        });
                    }
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
