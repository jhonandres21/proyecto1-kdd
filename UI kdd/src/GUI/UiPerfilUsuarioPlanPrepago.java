package GUI;

import Controlador.ControladorPerfilUsuarioPlanPrepago;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class UiPerfilUsuarioPlanPrepago extends UiPerfil {

    ControladorPerfilUsuarioPlanPrepago controladorPlanesPrepago;

    public UiPerfilUsuarioPlanPrepago() {

        controladorPlanesPrepago = new ControladorPerfilUsuarioPlanPrepago();
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
