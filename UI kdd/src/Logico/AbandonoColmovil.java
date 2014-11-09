package Logico;

import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class AbandonoColmovil extends UiPerfil {

    public AbandonoColmovil() {
    }

    @Override
    void hacerConsulta(ActionEvent evt) {

        System.out.println("Funciona!");
        System.out.println("Femenino: Seleccionado?" + checkBoxSexoFemenino.isSelected());
        System.out.println("Estado Civil: " + estadoCivil.getSelectedItem());
    }
}
