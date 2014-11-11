
package GUI;

import Controlador.ControladorPerfilPlanesPospago;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class UiPerfilPlanesPospago extends UiPerfil {
    
       ControladorPerfilPlanesPospago controladorPlanesPospago;

    public UiPerfilPlanesPospago() {

        controladorPlanesPospago = new ControladorPerfilPlanesPospago();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {

        //verificamos que el rango de estrato sea correcto
        if (evaluarRangoEstrato()) {

            String sexoFemenino = "" + checkBoxSexoFemenino.isSelected();
            String sexoMasculino = "" + checkBoxSexoMasculino.isSelected();
            String estadoCivil = "" + comboBoxEstadoCivil.getSelectedItem();
            String inicioEstrato = "" + comboBoxInicioEstrato.getSelectedItem();
            String finEstrato = "" + comboBoxFinEstrato.getSelectedItem();

            controladorPlanesPospago.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);
        }

    }
}
