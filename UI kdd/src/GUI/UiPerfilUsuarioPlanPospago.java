
package GUI;

import Controlador.ControladorPerfilUsuarioPlanPospago;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class UiPerfilUsuarioPlanPospago extends UiPerfil {
    
       ControladorPerfilUsuarioPlanPospago controladorPlanesPospago;

    public UiPerfilUsuarioPlanPospago() {

        controladorPlanesPospago = new ControladorPerfilUsuarioPlanPospago();
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
