package gui;

import Sistema.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscalasPanel extends JPanel {

    private Controlador controlador;
    public EscalasPanel(Controlador controlador) {
        this.controlador = controlador;
        init();
    }

    public void init() {

        setPreferredSize(new Dimension(280, 280));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 0, 112)));
        
        JLabel jLabel12 = new JLabel();
        JLabel jLabel13 = new JLabel();
        JLabel jLabel14 = new JLabel();
        JLabel jLabel15 = new JLabel();
        JLabel jLabel16 = new JLabel();
        JLabel jLabel17 = new JLabel();
        JTextField jTextField8 = new JTextField();
        JTextField jTextField9 = new JTextField();
        JTextField jTextField10 = new JTextField();
        JTextField jTextField11 = new JTextField();
        JButton jButton6 = new JButton();

        jLabel12.setText("Escala");

        jLabel13.setText("Local");

        jTextField8.setText("1");

        jTextField9.setText("1");

        jTextField10.setText("1");

        jLabel14.setText("X");

        jLabel15.setText("Y");

        jLabel16.setText("Z");

        jButton6.setText("Executar");

        jLabel12.setText("Escala");

        jLabel13.setText("Local");

        jTextField8.setText("1");

        jTextField9.setText("1");

        jTextField10.setText("1");

        jLabel14.setText("X");

        jLabel15.setText("Y");

        jLabel16.setText("Z");

        jLabel17.setText("Global");

        jTextField11.setText("1");

        jButton6.setText("Executar");

        jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double x = Double.parseDouble(jTextField8.getText());
                double y = Double.parseDouble(jTextField9.getText());
                double z = Double.parseDouble(jTextField10.getText());
                double global = Double.parseDouble(jTextField11.getText());

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
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painel5Layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField11))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painel5Layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(18, 18, 18)
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painel5Layout.createSequentialGroup()
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING))
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
                                                        .addComponent(jLabel14)
                                                        .addComponent(jLabel15)
                                                        .addComponent(jLabel16))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(painel5Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(35, 35, 35)
                                                .addComponent(jLabel13)))
                                .addGap(18, 18, 18)
                                .addGroup(painel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }
}
