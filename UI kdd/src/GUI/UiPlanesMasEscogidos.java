package GUI;

import Controlador.ControladorPlanesMasEscogidos;
import Gráficos.FXBarChart;
import Gráficos.FXPieChart;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author luisfemm
 */
public class UiPlanesMasEscogidos extends UiPerfil {

    ControladorPlanesMasEscogidos controladorPlanesMasEscogidos;
    ArrayList <Integer> datosPlanes;

    public UiPlanesMasEscogidos() {

        controladorPlanesMasEscogidos= new ControladorPlanesMasEscogidos();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {

        String operador = "" + comboBoxOperador.getSelectedItem();
        
//        datosPlanes = controladorPlanesMasEscogidos.obtenerFranjas(operador);

        ArrayList<String> franjas = new ArrayList();
        franjas.add("AM");
        franjas.add("Evening");
        franjas.add("Morning");
        franjas.add("Night");
        franjas.add("PM");

        FXPieChart PieChart;
        FXBarChart BarChart;

        if (!datosPlanes.isEmpty()) {
            PieChart = new FXPieChart("Franjas Más Usadas Hasta la Fecha", franjas, datosPlanes);
            BarChart = new FXBarChart("Franjas Más Usadas Hasta la Fecha", "Franjas", franjas, "Uso", datosPlanes);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha extraido la información");
        }

    }
}
