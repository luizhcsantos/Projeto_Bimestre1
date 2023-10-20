package gui;

import Sistema.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShearingPanel extends JPanel {

    private final Controlador controlador;
    public ShearingPanel(Controlador controlador) {
        this.controlador = controlador;
        init();
    }

    public void init() {

        setPreferredSize(new Dimension(280, 280));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 0, 112)));
        setLayout(new java.awt.GridBagLayout());
        GridBagConstraints gridBagConstraints;

        JButton jButton7 = new JButton();
        JLabel jLabel18 = new JLabel();
        JTextField jTextField12 = new JTextField();
        JTextField jTextField13 = new JTextField();
        JTextField jTextField14 = new JTextField();
        JTextField jTextField15 = new JTextField();
        JTextField jTextField16 = new JTextField();
        JTextField jTextField17 = new JTextField();
        JTextField jTextField18 = new JTextField();
        JTextField jTextField19 = new JTextField();
        JTextField jTextField20 = new JTextField();
        JTextField jTextField21 = new JTextField();
        JTextField jTextField22 = new JTextField();
        JTextField jTextField23 = new JTextField();
        JTextField jTextField24 = new JTextField();
        JTextField jTextField25 = new JTextField();
        JTextField jTextField26 = new JTextField();
        JTextField jTextField27 = new JTextField();

        ArrayList<JTextField> listaTextfield = new ArrayList<>();

        jLabel18.setText("Shearing (Cisalhamento)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jLabel18, gridBagConstraints);

        jTextField12.setText("1");
        jTextField12.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField12, gridBagConstraints);
        listaTextfield.add(jTextField12);

        jTextField13.setText("0");
        jTextField13.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField13, gridBagConstraints);
        listaTextfield.add(jTextField13);

        jTextField14.setText("0");
        jTextField14.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField14, gridBagConstraints);
        listaTextfield.add(jTextField14);

        jTextField15.setText("0");
        jTextField15.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField15, gridBagConstraints);

        jTextField16.setText("0");
        jTextField16.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField16, gridBagConstraints);
        listaTextfield.add(jTextField16);

        jTextField17.setText("1");
        jTextField17.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField17, gridBagConstraints);
        listaTextfield.add(jTextField17);

        jTextField18.setText("0");
        jTextField18.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField18, gridBagConstraints);
        listaTextfield.add(jTextField18);

        jTextField19.setText("0");
        jTextField19.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField19, gridBagConstraints);

        jTextField20.setText("0");
        jTextField20.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField20, gridBagConstraints);
        listaTextfield.add(jTextField20);

        jTextField21.setText("0");
        jTextField21.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField21, gridBagConstraints);
        listaTextfield.add(jTextField21);

        jTextField22.setText("1");
        jTextField22.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField22, gridBagConstraints);
        listaTextfield.add(jTextField22);

        jTextField24.setText("0");
        jTextField24.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField24, gridBagConstraints);

        jTextField23.setText("0");
        jTextField23.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField23, gridBagConstraints);
        listaTextfield.add(jTextField23);

        jTextField25.setText("0");
        jTextField25.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField25, gridBagConstraints);
        listaTextfield.add(jTextField25);

        jTextField26.setText("0");
        jTextField26.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField26, gridBagConstraints);
        listaTextfield.add(jTextField26);

        jTextField27.setText("1");
        jTextField27.setPreferredSize(new Dimension(48, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jTextField27, gridBagConstraints);

        jButton7.setText("Executar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        add(jButton7, gridBagConstraints);

        jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Double> valores = new ArrayList<>();

                for (JTextField j : listaTextfield) {
                    valores.add(Double.valueOf(j.getText()));
                }
                controlador.desenhaShearing(valores);
            }
        });

    }
}
