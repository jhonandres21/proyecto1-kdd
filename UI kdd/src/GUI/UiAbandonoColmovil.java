package GUI;

import Controlador.ControlAbandonoColmovil;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class UiAbandonoColmovil extends UiPerfil {

    ControlAbandonoColmovil controladorAbandono;

    public UiAbandonoColmovil() {

        controladorAbandono = new ControlAbandonoColmovil();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {

        //verificamos que el rango de estrato sea correcto
        if (evaluarRangoEstrato()) {

            //System.out.println("Femenino: Seleccionado?" + checkBoxSexoFemenino.isSelected());
            //System.out.println("Estado Civil: " + comboBoxEstadoCivil.getSelectedItem());
            String sexoFemenino = "" + checkBoxSexoFemenino.isSelected();
            String sexoMasculino = "" + checkBoxSexoMasculino.isSelected();
            String estadoCivil = "" + comboBoxEstadoCivil.getSelectedItem();
            String inicioEstrato = "" + comboBoxInicioEstrato.getSelectedItem();
            String finEstrato = "" + comboBoxFinEstrato.getSelectedItem();

            controladorAbandono.getPerfiles(sexoFemenino, sexoMasculino, estadoCivil, inicioEstrato, finEstrato);
        }

    }
}
