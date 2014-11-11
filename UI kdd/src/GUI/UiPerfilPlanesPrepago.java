package GUI;

import Controlador.ControladorPerfilPlanesPrepago;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class UiPerfilPlanesPrepago extends UiPerfil {

    ControladorPerfilPlanesPrepago controladorPlanesPrepago;

    public UiPerfilPlanesPrepago() {

        controladorPlanesPrepago = new ControladorPerfilPlanesPrepago();
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

            controladorPlanesPrepago.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);
        }

    }
}
