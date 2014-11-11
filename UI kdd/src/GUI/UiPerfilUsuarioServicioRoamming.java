package GUI;

import Controlador.ControladorPerfilUsuarioServicioRoamming;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class UiPerfilUsuarioServicioRoamming extends UiPerfil {

    ControladorPerfilUsuarioServicioRoamming controladorUsuarioServicioRoamming;

    public UiPerfilUsuarioServicioRoamming() {

        controladorUsuarioServicioRoamming = new ControladorPerfilUsuarioServicioRoamming();
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

            controladorUsuarioServicioRoamming.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);
        }

    }
}
