package GUI;

import Controlador.ControladorPerfilUsuarioContrataPlanDatos;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class UiPerfilUsuarioContrataPlanDatos extends UiPerfil {

    ControladorPerfilUsuarioContrataPlanDatos controladorUsuarioContrataPlanDatos;

    public UiPerfilUsuarioContrataPlanDatos() {

        controladorUsuarioContrataPlanDatos = new ControladorPerfilUsuarioContrataPlanDatos();
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

            controladorUsuarioContrataPlanDatos.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);
        }

    }
}
