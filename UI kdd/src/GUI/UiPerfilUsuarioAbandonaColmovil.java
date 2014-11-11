package GUI;

import Controlador.ControladorPerfilUsuarioAbandonaColmovil;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class UiPerfilUsuarioAbandonaColmovil extends UiPerfil {

    ControladorPerfilUsuarioAbandonaColmovil controladorAbandono;

    public UiPerfilUsuarioAbandonaColmovil() {

        controladorAbandono = new ControladorPerfilUsuarioAbandonaColmovil();
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

            controladorAbandono.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);
        }
    }
}
