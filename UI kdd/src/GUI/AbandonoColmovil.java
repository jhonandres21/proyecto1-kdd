package GUI;

import Controlador.ControlAbandonoColmovil;
import java.awt.event.ActionEvent;

/**
 *
 * @author john
 */
public class AbandonoColmovil extends UiPerfil {

    ControlAbandonoColmovil controladorAbandono;

    public AbandonoColmovil() {

        controladorAbandono = new ControlAbandonoColmovil();
    }

    @Override
    void hacerConsulta(ActionEvent evt) {
        
        //verificamos que el rango de estrato sea correcto
        if(evaluarRangoEstrato()){
            
            System.out.println("Femenino: Seleccionado?" + checkBoxSexoFemenino.isSelected());
            System.out.println("Estado Civil: " + estadoCivil.getSelectedItem());
            controladorAbandono.getPerfiles();
        }
        
    }
}
