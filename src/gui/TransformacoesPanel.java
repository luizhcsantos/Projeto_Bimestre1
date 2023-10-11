package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class TransformacoesPanel extends JPanel {

    public BufferedImage buffer;
    private double[][] pontosTotal = new double[10][4];
    private int[][] arestas = new int[17][2];
    private double[][] resultadoPrintado = new double[10][4];
    private double[][] matrizDeProjecao = new double[4][4];

    public TransformacoesPanel() {
        initComponents();
        buffer = ImagePanel.getBuffer();
        //inicializaPontoEAresta();
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JTextField jTextField5;
        JPanel jPanel1 = new JPanel(); // painel 1
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel1 = new javax.swing.JLabel();
        jButtonDesenhaCasinha = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        JPanel jPanel2 = new JPanel(); // painel 2
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JTextField jTextField1 = new JTextField();
        JTextField jTextField2 = new JTextField();
        JTextField jTextField3 = new JTextField();
        jButtonTranslacao = new javax.swing.JButton();
        JLabel jLabel6 = new JLabel();
        JLabel jLabel7 = new JLabel();
        JTextField jTextField4 = new JTextField();
        JLabel jLabel8 = new JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButtonRotacao1Origem = new javax.swing.JButton();
        JLabel jLabel9 = new JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButtonRotacaoObjeto = new javax.swing.JButton();
        JPanel jPanel3 = new JPanel(); // painel 3
        jPanel3.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JTextField jTextField8 = new JTextField();
        JTextField jTextField9 = new JTextField();
        JTextField jTextField10 = new JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        JTextField jTextField11 = new JTextField();
        jButton6 = new javax.swing.JButton();
        JPanel jPanel4 = new JPanel(); // painel 4
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.black));
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
        JTextField jTextField24 = new JTextField();
        JTextField jTextField23 = new JTextField();
        JTextField jTextField25 = new JTextField();
        JTextField jTextField26 = new JTextField();
        JTextField jTextField27 = new JTextField();

        eixoRotacaoCentro = new JTextField();
        grausRotacaoCentro = new JTextField();

        jButton7 = new javax.swing.JButton();

        jLabel1.setText("Desenhar Objeto");

        jButtonDesenhaCasinha.setText("Plano XY");
        jButtonDesenhaCasinha.addActionListener(new BotaoCasinhaListener());

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new BotaoResetListener());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jButtonDesenhaCasinha))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jButtonReset)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonDesenhaCasinha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonReset)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Translação");

        jLabel3.setText("X");

        jLabel4.setText("Y");

        jLabel5.setText("Z");

        jTextField1.setText("0");

        jTextField2.setText("0");

        jTextField3.setText("0");

        jButtonTranslacao.setText("Transladar");
        jButtonTranslacao.addActionListener(new BotaoTranslacaoListener());

        jLabel6.setText("Rotação em Relação à Origem");

        jLabel7.setText("Eixo");

        jTextField4.setText("x");

        jLabel8.setText("Graus");

        jTextField5.setText("0");

        jButtonRotacao1Origem.setText("Rotacionar");

        jLabel9.setText("Rotação em Relação ao Centro do Objeto");

        jLabel10.setText("Eixo");

        jLabel11.setText("Graus");
        jLabel11.setToolTipText("");

        eixoRotacaoCentro.setText("x");

        grausRotacaoCentro.setText("0");

        jButtonRotacaoObjeto.setText("Rotacionar");
        jButtonRotacaoObjeto.addActionListener(new BotaoRotacaoCentroListener());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                                                .addComponent(jLabel11)
                                                                                .addGap(4, 4, 4)
                                                                                .addComponent(grausRotacaoCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                                                .addComponent(jLabel10)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(eixoRotacaoCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(24, 24, 24)
                                                                .addComponent(jButtonRotacaoObjeto)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel8)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabel2)
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(jTextField1)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(45, 45, 45))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jButtonTranslacao))
                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addComponent(jLabel7)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jButtonRotacao1Origem)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonTranslacao))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonRotacao1Origem))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(eixoRotacaoCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonRotacaoObjeto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(grausRotacaoCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(14, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jLabel17)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTextField11))
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jLabel12)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(jLabel13)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                                                .addGap(11, 11, 11)
                                                                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(45, 45, 45)))
                                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(120, 120, 120))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jButton6)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel14)
                                                        .addComponent(jLabel15)
                                                        .addComponent(jLabel16))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(35, 35, 35)
                                                .addComponent(jLabel13)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel18.setText("Shearing (Cizalhamento)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jLabel18, gridBagConstraints);

        jTextField12.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField12, gridBagConstraints);

        jTextField13.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField13, gridBagConstraints);

        jTextField14.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField14, gridBagConstraints);

        jTextField15.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField15, gridBagConstraints);

        jTextField16.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField16, gridBagConstraints);

        jTextField17.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField17, gridBagConstraints);

        jTextField18.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField18, gridBagConstraints);

        jTextField19.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField19, gridBagConstraints);

        jTextField20.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField20, gridBagConstraints);

        jTextField21.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField21, gridBagConstraints);

        jTextField22.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField22, gridBagConstraints);

        jTextField24.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField24, gridBagConstraints);

        jTextField23.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField23, gridBagConstraints);

        jTextField25.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField25, gridBagConstraints);

        jTextField26.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField26, gridBagConstraints);

        jTextField27.setText("1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jTextField27, gridBagConstraints);

        jButton7.setText("Executar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(4, 3, 4, 4);
        jPanel4.add(jButton7, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );



    }

    private void inicializaPontoEAresta() {
        double[][] pontos = {
                {100, 300, 100, 1}, {100, 300, -100, 1}, {300, 300, 100, 1},
                {300, 300, -100, 1}, {100, 100, 100, 1}, {100, 100, -100, 1},
                {300, 100, 100, 1}, {300, 100, -100, 1}, {200, 50, 100, 1},
                {200, 50, -100, 1}
        };

        for (int i = 0; i < 10; i++) {
            System.arraycopy(pontos[i], 0, pontosTotal[i], 0, 4);
        }

        int[][] arestasArray = {
                {1, 2}, {1, 3}, {1, 5}, {2, 6}, {2, 4}, {3, 4},
                {3, 7}, {4, 8}, {7, 8}, {5, 6}, {5, 7}, {9, 10},
                {5, 9}, {7, 9}, {8, 10}, {6, 10}, {6, 8}
        };

        for (int i = 0; i < 17; i++) {
            System.arraycopy(arestasArray[i], 0, arestas[i], 0, 2);
        }
    }

    public void desenhaCasinha() {

        limparBuffer();

        double[][] matrizT = {
                {1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}
        };

        matrizDeProjecao = matrizT;

        for (int aux = 0; aux < 10; aux++) {
            double[] resultado = new double[10];
            multiplicarMatriz(pontosTotal[aux], matrizT, resultado);
            System.arraycopy(resultado, 0, resultadoPrintado[aux], 0, 4);
        }

        for (int aux = 0; aux < 17; aux++) {
            int xx1 = (int) Math.round(resultadoPrintado[arestas[aux][0] - 1][0]);
            int yy1 = (int) Math.round(resultadoPrintado[arestas[aux][0] - 1][1]);
            int xx2 = (int) Math.round(resultadoPrintado[arestas[aux][1] - 1][0]);
            int yy2 = (int) Math.round(resultadoPrintado[arestas[aux][1] - 1][1]);
            // chame a função para desenhar a linha com pontos (xx1, yy1) e (xx2, yy2)
            ImagePanel.desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }

    }

    private void multiplicarMatriz(double[] ponto, double[][] mat, double[] resultado) {
        for (int x = 0; x < 4; x++) {
            double soma = 0;
            for (int y = 0; y < 4; y++) {
                soma += ponto[y] * mat[y][x];
            }
            resultado[x] = soma;
        }
    }


    private void rotacaoCentroObjeto() {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizT = new double[4][4];
        double[] resultado = new double[4];
        double somax, somay, somaz;
        int contador;
        double graus;

        limparBuffer();

        graus = (Double.parseDouble(grausRotacaoCentro.getText()) * Math.PI) / 180;

        somax = 0;
        somay = 0;
        somaz = 0;
        for (contador = 0; contador < 10; contador++) {
            somax += pontosTotal[contador][0];
            somay += pontosTotal[contador][1];
            somaz += pontosTotal[contador][2];
        }
        somax /= 10;
        somay /= 10;
        somaz /= 10;

        translacao(-somax, -somay, -somaz);

        if (eixoRotacaoCentro.getText().equalsIgnoreCase("x")) {
            matrizT[0][0] = 1;
            matrizT[1][1] = Math.cos(graus);
            matrizT[1][2] = -Math.sin(graus);
            matrizT[2][1] = Math.sin(graus);
            matrizT[2][2] = Math.cos(graus);
            matrizT[3][3] = 1;
        } else if (eixoRotacaoCentro.getText().equalsIgnoreCase("y")) {
            matrizT[0][0] = Math.cos(graus);
            matrizT[0][2] = Math.sin(graus);
            matrizT[1][1] = 1;
            matrizT[2][0] = -Math.sin(graus);
            matrizT[2][2] = Math.cos(graus);
            matrizT[3][3] = 1;
        } else if (eixoRotacaoCentro.getText().equalsIgnoreCase("z")) {
            matrizT[0][0] = Math.cos(graus);
            matrizT[0][1] = -Math.sin(graus);
            matrizT[1][0] = Math.sin(graus);
            matrizT[1][1] = Math.cos(graus);
            matrizT[2][2] = 1;
            matrizT[3][3] = 1;
        }

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizT, resultado);
            System.out.println("resultado: "+resultado);
            System.arraycopy(resultado, 0, pontosTotal[aux], 0, 4);
        }

        translacao(somax, somay, somaz);

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(resultado, matrizDeProjecao, resultado);
            resultadoPrintado[aux][0] = resultado[0];
            resultadoPrintado[aux][1] = resultado[1];
            resultadoPrintado[aux][2] = resultado[2];
            resultadoPrintado[aux][3] = resultado[3];
        }

        for (aux = 0; aux < 17; aux++) {
            xx1 = (int) Math.round(resultadoPrintado[arestas[aux][0] - 1][0]);
            yy1 = (int) Math.round(resultadoPrintado[arestas[aux][0] - 1][1]);
            xx2 = (int) Math.round(resultadoPrintado[arestas[aux][1] - 1][0]);
            yy2 = (int) Math.round(resultadoPrintado[arestas[aux][1] - 1][1]);
            // Chame a função para desenhar a linha com pontos (xx1, yy1) e (xx2, yy2)
            ImagePanel.desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }
    }

    private void translacao(double x, double y, double z) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizT = new double[4][4];
        double[] resultado = new double[4];

        //limparForm1();

        matrizT[0][0] = 1;
        matrizT[0][1] = 0;
        matrizT[0][2] = 0;
        matrizT[0][3] = 0;

        matrizT[1][0] = 0;
        matrizT[1][1] = 1;
        matrizT[1][2] = 0;
        matrizT[1][3] = 0;

        matrizT[2][0] = 0;
        matrizT[2][1] = 0;
        matrizT[2][2] = 1;
        matrizT[2][3] = 0;

        matrizT[3][0] = x;
        matrizT[3][1] = y;
        matrizT[3][2] = z;
        matrizT[3][3] = 1;

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizT, resultado);
            pontosTotal[aux] = resultado;
            multiplicarMatriz(resultado, matrizDeProjecao, resultado);
            resultadoPrintado[aux][0] = resultado[0];
            resultadoPrintado[aux][1] = resultado[1];
            resultadoPrintado[aux][2] = resultado[2];
            resultadoPrintado[aux][3] = resultado[3];
        }

        for (aux = 0; aux < 17; aux++) {
            xx1 = (int) Math.round(resultadoPrintado[arestas[aux][0] - 1][0]);
            yy1 = (int) Math.round(resultadoPrintado[arestas[aux][0] - 1][1]);
            xx2 = (int) Math.round(resultadoPrintado[arestas[aux][1] - 1][0]);
            yy2 = (int) Math.round(resultadoPrintado[arestas[aux][1] - 1][1]);
            // Chame a função para desenhar a linha com pontos (xx1, yy1) e (xx2, yy2)
            ImagePanel.desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }
    }

    public JButton getjButtonDesenhaCasinha() {
        return jButtonDesenhaCasinha;
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButtonDesenhaCasinha;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonTranslacao;
    private javax.swing.JButton jButtonRotacao1Origem;
    private javax.swing.JButton jButtonRotacaoObjeto;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;

    private JTextField eixoRotacaoCentro;
    private JTextField grausRotacaoCentro;


    private class BotaoCasinhaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            inicializaPontoEAresta();
            desenhaCasinha();
        }
    }
    private class BotaoResetListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            inicializaPontoEAresta();
        }
    }

    private class BotaoRotacaoCentroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            rotacaoCentroObjeto();
        }
    }

    private class BotaoTranslacaoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //translacao();
        }
    }

    public void limparBuffer() {
        Graphics2D g2d = buffer.createGraphics();
        g2d.drawImage(buffer, 0, 0, null);
        g2d.dispose();
    }
}