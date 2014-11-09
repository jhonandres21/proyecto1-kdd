package Logico;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

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
    JComboBox estadoCivil, inicioEstrato, finEstrato;

    public UiPerfil() {

        checkBoxSexoFemenino = new JCheckBox("Femenino");
        checkBoxSexoMasculino = new JCheckBox("Masculino");
        estadoCivil = new JComboBox();
        inicioEstrato = new JComboBox();
        finEstrato = new JComboBox();
        inicializarCheckBox(checkBoxSexoFemenino);
        inicializarCheckBox(checkBoxSexoMasculino);
        inicializarEstadoCivil();
        inicializarEstrato(inicioEstrato);
        inicializarEstrato(finEstrato);
    }

    private void inicializarCheckBox(JCheckBox checkBox) {

        checkBox.setVisible(true);
        checkBox.setSize(100, 45);
        checkBox.setMargin(new java.awt.Insets(0, 0, 10, 0));
    }

    private JComboBox inicializarEstadoCivil() {

        estadoCivil = new JComboBox();
        estadoCivil.setVisible(true);
        estadoCivil.setSize(100, 45);

        String estados[][] = new String[6][1];
        estados[0][0] = "Escoger una Opción";
        estados[1][0] = "Casado";
        estados[2][0] = "Divorciado";
        estados[3][0] = "Soltero";
        estados[4][0] = "Unión Libre";
        estados[5][0] = "Viudo";

        for (int i = 0; i < estados.length; i++) {
            estadoCivil.addItem(estados[i][0]);
        }

        return estadoCivil;
    }

    private void inicializarEstrato(JComboBox estrato) {

        estrato.setVisible(true);
        estrato.setSize(100, 45);

        String estratos[][] = new String[7][1];
        estratos[0][0] = "Escoger una Opción";
        estratos[1][0] = "1";
        estratos[2][0] = "2";
        estratos[3][0] = "3";
        estratos[4][0] = "4";
        estratos[5][0] = "5";
        estratos[6][0] = "6";

        for (int i = 0; i < estratos.length; i++) {
            estrato.addItem(estratos[i][0]);
        }
    }

    public JCheckBox getCheckBoxSexoFemenino() {
        return checkBoxSexoFemenino;
    }

    public JCheckBox getCheckBoxSexoMasculino() {
        return checkBoxSexoMasculino;
    }

    public JComboBox getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(JComboBox estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public JComboBox getInicioEstrato() {
        return inicioEstrato;
    }

    public void setInicioEstrato(JComboBox inicioEstrato) {
        this.inicioEstrato = inicioEstrato;
    }

    public JComboBox getFinEstrato() {
        return finEstrato;
    }

    public void setFinEstrato(JComboBox finEstrato) {
        this.finEstrato = finEstrato;
    }
}
