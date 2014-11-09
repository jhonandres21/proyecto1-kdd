package Logico;

import javax.swing.JCheckBox;

/**
 *
 * @author john
 */
/*
 * En esta clase se van a poner todos los componentes de la interfaz gráfica
 * que estén relacionados con las preguntas sobre "Perfiles"
 */
public class UiPerfil {

    JCheckBox checkBoxSexoFemenino, checkBoxSexoMasculino;

    public UiPerfil() {

        checkBoxSexoFemenino = new JCheckBox("Femenino");
        checkBoxSexoMasculino = new JCheckBox("Masculino");
        inicializarCheckBox(checkBoxSexoFemenino);
        inicializarCheckBox(checkBoxSexoMasculino);
    }

    private void inicializarCheckBox(JCheckBox checkBox) {

        checkBox.setVisible(true);
        checkBox.setSize(100, 45);
        checkBox.setMargin(new java.awt.Insets(0, 0, 10, 0));
    }

    public JCheckBox getCheckBoxSexoFemenino() {
        return checkBoxSexoFemenino;
    }

    public JCheckBox getCheckBoxSexoMasculino() {
        return checkBoxSexoMasculino;
    }
}
