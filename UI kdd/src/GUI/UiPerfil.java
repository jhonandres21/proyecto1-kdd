package GUI;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author john En esta clase se van a poner todos los componentes de la
 * interfaz gráfica que estén relacionados con las preguntas sobre "Perfiles"
 */
public abstract class UiPerfil {

    protected JCheckBox checkBoxSexoFemenino, checkBoxSexoMasculino;
    protected JComboBox estadoCivil, inicioEstrato, finEstrato;
    protected JLabel labelSexo, labelEstadoCivil, labelInicioEstrato, labelFinEstrato;
    protected JButton consultar;

    public UiPerfil() {

        labelSexo = new JLabel();
        inicializarJLabel(labelSexo, "Sexo:          ");

        checkBoxSexoFemenino = new JCheckBox("Femenino");
        checkBoxSexoFemenino.setSelected(true);
        checkBoxSexoMasculino = new JCheckBox("Masculino");
        checkBoxSexoMasculino.setSelected(true);

        estadoCivil = new JComboBox();
        inicioEstrato = new JComboBox();
        finEstrato = new JComboBox();

        labelEstadoCivil = new JLabel();
        inicializarJLabel(labelEstadoCivil, "Estado Civil:");

        labelInicioEstrato = new JLabel();
        inicializarJLabel(labelInicioEstrato, "Estrato:");

        labelFinEstrato = new JLabel();
        inicializarJLabel(labelFinEstrato, " Hasta:");

        consultar = new JButton("Consultar");

        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hacerConsulta(evt);
            }
        });

        inicializarCheckBox(checkBoxSexoFemenino);
        inicializarCheckBox(checkBoxSexoMasculino);
        inicializarEstadoCivil(estadoCivil);
        inicializarEstrato(inicioEstrato);
        inicializarEstrato(finEstrato);
    }

    protected void inicializarJLabel(JLabel label, String texto) {

        label.setText(texto);
        label.setSize(170, 30);
        label.setMinimumSize(new Dimension(170, 30));
        label.setMaximumSize(new Dimension(170, 30));
        label.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
    }

    protected void inicializarCheckBox(JCheckBox checkBox) {

        checkBox.setVisible(true);
        checkBox.setSize(170, 40);
        checkBox.setMinimumSize(new Dimension(170, 40));
        checkBox.setMaximumSize(new Dimension(170, 40));
        checkBox.setMargin(new java.awt.Insets(0, 0, 5, 0));
    }

    protected void inicializarEstadoCivil(JComboBox estadoCivil) {

        estadoCivil.setVisible(true);
        estadoCivil.setMaximumSize(new Dimension(170, 30));

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
    }

    protected void inicializarEstrato(JComboBox estrato) {

        estrato.setVisible(true);
        estrato.setMaximumSize(new Dimension(170, 30));

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

    abstract void hacerConsulta(java.awt.event.ActionEvent evt);

    protected boolean evaluarRangoEstrato() {

        if (!inicioEstrato.getSelectedItem().equals("Escoger una Opción")) {

            if (finEstrato.getSelectedItem().equals("Escoger una Opción")) {

                JOptionPane.showMessageDialog(null, "Debe escoger un valor para el rango de Estrato");
                return false;

            } else {

                int valorInicioEstrato = Integer.parseInt("" + inicioEstrato.getSelectedItem());
                int valorFinEstrato = Integer.parseInt("" + finEstrato.getSelectedItem());

                if (valorFinEstrato < valorInicioEstrato) {
                    JOptionPane.showMessageDialog(null, "Debe escoger un valor MAYOR para el rango de Estrato");
                    return false;
                } else {
                    return true;
                }
            }//fin else
        }

        return true;
    }

    public JLabel getLabelSexo() {
        return labelSexo;
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

    public JLabel getLabelEstadoCivil() {
        return labelEstadoCivil;
    }

    public JLabel getLabelInicioEstrato() {
        return labelInicioEstrato;
    }

    public JLabel getLabelFinEstrato() {
        return labelFinEstrato;
    }

    public JButton getConsultar() {
        return consultar;
    }
}
