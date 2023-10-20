package gui;

import Sistema.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscalasPanel extends JPanel {

    private final Controlador controlador;
    public EscalasPanel(Controlador controlador) {
        this.controlador = controlador;
        init();
    }

    public void init() {

        setPreferredSize(new Dimension(280, 280));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 0, 112)));

        JLabel jLabelEscalaTitulo = new JLabel();
        JLabel jLabelEscalaLocal = new JLabel();
        JLabel jLabelX = new JLabel();
        JLabel jLabelY = new JLabel();
        JLabel jLabelZ = new JLabel();
        JLabel jLabelGlobal = new JLabel();
        JTextField jTextFieldX = new JTextField();
        JTextField jTextFieldY = new JTextField();
        JTextField jTextFieldZ = new JTextField();
        JTextField jTextFieldGlobal = new JTextField();
        JButton jButtonExecutar = new JButton();

        jLabelEscalaTitulo.setText("Escala");
        jLabelEscalaLocal.setText("Local");

        jLabelX.setText("X");
        jTextFieldX.setText("1");

        jLabelY.setText("Y");
        jTextFieldY.setText("1");

        jLabelZ.setText("Z");
        jTextFieldZ.setText("1");

        jLabelGlobal.setText("Global");
        jTextFieldGlobal.setText("1");

        jButtonExecutar.setText("Executar");

        jButtonExecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(jTextFieldX.getText());
                double y = Double.parseDouble(jTextFieldY.getText());
                double z = Double.parseDouble(jTextFieldZ.getText());
                double global = Double.parseDouble(jTextFieldGlobal.getText());

                controlador.desenhaEscalas(x, y, z, global);

            }
        });

        javax.swing.GroupLayout painel5Layout = new javax.swing.GroupLayout(this);
        setLayout(painel5Layout);
        painel5Layout.setHorizontalGroup(
                painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabelEscalaTitulo, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painel5Layout.createSequentialGroup()
                                                .addComponent(jLabelEscalaLocal)
                                                .addGap(18, 18, 18)
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextFieldX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jTextFieldZ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painel5Layout.createSequentialGroup()
                                                .addComponent(jLabelGlobal)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldGlobal))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painel5Layout.createSequentialGroup()
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jButtonExecutar, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(120, 120, 120))
        );
        painel5Layout.setVerticalGroup(
                painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(painel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(painel5Layout.createSequentialGroup()
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabelX)
                                                        .addComponent(jLabelY)
                                                        .addComponent(jLabelZ))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextFieldZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(painel5Layout.createSequentialGroup()
                                                .addComponent(jLabelEscalaTitulo)
                                                .addGap(35, 35, 35)
                                                .addComponent(jLabelEscalaLocal)))
                                .addGap(18, 18, 18)
                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelGlobal)
                                        .addComponent(jTextFieldGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonExecutar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }
}
