package GUI;

import Controlador.ControladorPerfilUsuarioContrataPlanDatos;
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
public class UiPerfilUsuarioContrataPlanDatos extends UiPerfil {

    ControladorPerfilUsuarioContrataPlanDatos controladorUsuarioContrataPlanDatos;
    ArrayList dataContratos;

    public UiPerfilUsuarioContrataPlanDatos() {

        controladorUsuarioContrataPlanDatos = new ControladorPerfilUsuarioContrataPlanDatos();
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

                dataContratos = controladorUsuarioContrataPlanDatos.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);

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
                    if (perfilesActivo && ((perfilesPie && pieRadio.isSelected()) || (perfilesBar && barRadio.isSelected()) || (perfilesLine && lineRadio.isSelected()))) {
                        System.out.println("Update");
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                if (perfilesPie) {
                                    PieChart.addData(meses, dataContratos);
                                } else if (perfilesBar) {
                                    BarChart.addData(meses, dataContratos);
                                } else if (perfilesLine) {
                                    LineChart.addData(meses, dataContratos);
                                }
                            }
                        });

                    } else {
                        perfilesActivo = true;
                        operadoresActivo = false;
                        franjasActivo = false;
                        tendenciaMesesActivo = false;
                        tendenciaAniosActivo = false;
                        datosYVozActivo = false;
                        vozActivo = false;
                        datosActivo = false;
                        preVsPosActivo = false;
                        corpActivo = false;
                        preActivo = false;
                        posActivo = false;
                        System.out.println("pie: " + pieRadio.isSelected());
                        System.out.println("bar: " + barRadio.isSelected());
                        System.out.println("line: " + lineRadio.isSelected());
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                if (pieRadio.isSelected()) {
                                    PieChart = new FXPieChart("Contratos Mes-a-Mes", meses, dataContratos);
                                    perfilesPie = true;
                                    perfilesBar = false;
                                    perfilesLine = false;
                                } else if (barRadio.isSelected()) {
                                    BarChart = new FXBarChart("Contratos Mes-a-Mes", "meses", meses, "Contratos", dataContratos, "Contratos");
                                    perfilesPie = false;
                                    perfilesBar = true;
                                    perfilesLine = false;
                                } else if (lineRadio.isSelected()) {
                                    LineChart = new FXLineChart("Contratos Mes-a-Mes", "meses", meses, "Contratos", dataContratos, "Contratos");
                                    perfilesPie = false;
                                    perfilesBar = false;
                                    perfilesLine = true;
                                }

                            }
                        });

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha extraido la información");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar el tipo de gráfico");
            }
        }

    }
}
