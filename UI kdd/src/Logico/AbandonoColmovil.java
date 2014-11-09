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
        
        //verificamos que el rango de estrato sea correcto
        if(evaluarRangoEstrato()){
            
            System.out.println("Femenino: Seleccionado?" + checkBoxSexoFemenino.isSelected());
            System.out.println("Estado Civil: " + estadoCivil.getSelectedItem());
        }
        
    }
}
