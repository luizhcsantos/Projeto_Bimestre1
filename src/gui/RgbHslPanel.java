package gui;

import Sistema.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class RgbHslPanel extends JPanel {

    private final Controlador controlador;

    public RgbHslPanel(JFramePrincipal jFramePrincipal, Controlador controlador) {

        this.controlador = controlador;
        setBackground(new Color(159, 150, 150));
        setLayout(new GridBagLayout());

        initComponents();


    }

    public void initComponents() {
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(300, 300));
        setBackground(new Color(195, 195, 195));

        JLabel jLabel19 = new JLabel();
        JTextField jTextFieldR = new javax.swing.JTextField();
        JTextField jTextFieldG = new javax.swing.JTextField();
        JTextField jTextFieldB = new javax.swing.JTextField();
        JButton jButtonRgbHsl = new JButton();
        JButton jButtonHslRgb = new JButton();
        JLabel jLabel20 = new JLabel();
        JTextField jTextFieldH = new javax.swing.JTextField();
        JTextField jTextFieldS = new javax.swing.JTextField();
        JTextField jTextFieldL = new javax.swing.JTextField();
        JLabel jLabel1 = new JLabel();
        JButton jButtonDesenhaCasinha = new JButton();
        JButton jButtonResetaCasinha = new JButton();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        JLabel jLabel7 = new JLabel();

        jLabel19.setText("RGB");
        jTextFieldR.setText("0");
        jTextFieldG.setText("0");
        jTextFieldB.setText("0");

        jButtonRgbHsl.setText("->");

        jButtonRgbHsl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float r = Float.parseFloat(jTextFieldR.getText());
                float g = Float.parseFloat(jTextFieldG.getText());
                float b = Float.parseFloat(jTextFieldB.getText());

                if (!(r == 0 && g == 0 && b == 0)) {
                    float[] valoresHSL = rgbParaHsl(r, g, b);
                    jTextFieldH.setText(String.valueOf(valoresHSL[0]));
                    jTextFieldS.setText(String.valueOf(valoresHSL[1]));
                    jTextFieldL.setText(String.valueOf(valoresHSL[2]));

                    // seta a cor RGB que o usuário inseriu como cor padrão dos desenhos
                    controlador.setCor(new Color(
                            valoresHSL[0]/255,
                            valoresHSL[1]/255,
                            valoresHSL[2]/255));
                }
            }
        });

        jButtonHslRgb.setText("<-");

        jButtonHslRgb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float h = Float.parseFloat(jTextFieldH.getText());
                float s = Float.parseFloat(jTextFieldS.getText());
                float l = Float.parseFloat(jTextFieldL.getText());

                if (!(h == 0 && s == 0 && l == 0)) {
                    float[] valoresRGB = hslParaRgb(h, s, l);

                    jTextFieldR.setText(String.valueOf(valoresRGB[0]));
                    jTextFieldG.setText(String.valueOf(valoresRGB[1]));
                    jTextFieldB.setText(String.valueOf(valoresRGB[2]));

                    // seta a cor HSL que o usuário inseriu como cor padrão dos desenhos
                    controlador.setCor(new Color(
                            valoresRGB[0]/255,
                            valoresRGB[1]/255,
                            valoresRGB[2]/255));
                }

            }
        });
        jLabel20.setText("HSL");
        jTextFieldH.setText("0");
        jTextFieldS.setText("0");
        jTextFieldL.setText("0");

        jLabel1.setText("Desenhar Objeto");

        jButtonDesenhaCasinha.setText("Plano XY (Z = 0)");

        jButtonDesenhaCasinha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.limparBuffer();
                controlador.desenhaCasinha();
                controlador.imagePanel.repaint();
            }
        });

        jButtonResetaCasinha.setText("Reset");

        jButtonResetaCasinha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.resetaCasinha();
            }
        });

        jLabel2.setText("R");
        jLabel3.setText("G");
        jLabel4.setText("B");

        jLabel5.setText("H");
        jLabel6.setText("S");
        jLabel7.setText("L");

        javax.swing.GroupLayout RgbHslPanelLayout = new javax.swing.GroupLayout(this);
        setLayout(RgbHslPanelLayout);
        RgbHslPanelLayout.setHorizontalGroup(
                RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                                .addComponent(jButtonDesenhaCasinha)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jButtonResetaCasinha)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel19)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jLabel4))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextFieldR, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextFieldG, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextFieldB, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButtonRgbHsl, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jButtonHslRgb, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addGap(39, 39, 39)))
                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel20)
                                                        .addComponent(jTextFieldH, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldL, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(31, 31, 31))))
        );
        RgbHslPanelLayout.setVerticalGroup(
                RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addGap(20, 20, 20)
                                                .addComponent(jButtonRgbHsl)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonHslRgb))
                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextFieldR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel2))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextFieldG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel3))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextFieldB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel4)))
                                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextFieldH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel5))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextFieldS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel6))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextFieldL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel7))))))
                                .addGap(31, 31, 31)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonDesenhaCasinha)
                                        .addComponent(jButtonResetaCasinha))
                                .addContainerGap(23, Short.MAX_VALUE))
        );


    }

    public static float[] rgbParaHsl(float r, float g, float b) {
        r /= 255;
        g /= 255;
        b /= 255;

        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double h;
        double s;
        double l = (max + min) / 2;

        if (max == min) {
            h = s = 0; // acromatica
        } else {
            double d = max - min;
            s = l > 0.5 ? d / (2 - max - min) : d / (max + min);

            if (max == r) {
                h = (g - b) / d + (g < b ? 6 : 0);
            } else if (max == g) {
                h = (b - r) / d + 2;
            } else {
                h = (r - g) / d + 4;
            }

            h /= 6;
        }
        float [] hsl = new float[3];
        hsl[0] = Math.round( ((h/360)*240)*360);
        hsl[1] = Math.round(s*240);
        hsl[2] = Math.round(l*240);

        return hsl;
    }

    public float[] hslParaRgb(float h, float s, float l) {
        float[] rgb = new float[3];
        float[] hsl = new float[3];
        float temp1;
        float temp2;

        hsl[0] = (h * 360) / 240;
        hsl[1] = s / 240;
        hsl[2] = l / 240;

        if (hsl[0] == 0) {
            rgb[0] = (hsl[2] / 100) * 255;
            rgb[1] = (hsl[2] / 100) * 255;
            rgb[2] = (hsl[2] / 100) * 255;
        }

        if (hsl[2] < 0.5) {
            temp1 = hsl[2] * (1 + hsl[1]);
        } else {
            temp1 = (hsl[2] + hsl[1]) - (hsl[2] * hsl[1]);
        }

        temp2 = (2 * hsl[2]) - temp1;

        hsl[0] = hsl[0] / 360;

        rgb[0] = (float) (hsl[0] + 0.333);
        rgb[1] = hsl[0];
        rgb[2] = (float) (hsl[0] - 0.333);

        for (int i = 0; i < 3; i++) {
            if (rgb[i] < 0) {
                rgb[i] = rgb[i] + 1;
            }

            if (rgb[i] > 1) {
                rgb[i] = rgb[i] - 1;
            }

            if (6 * rgb[i] < 1) {
                rgb[i] = temp2 + (((temp1 - temp2) * 6) * rgb[i]);
            } else if (2 * rgb[i] < 1) {
                rgb[i] = temp1;
            } else if (3 * rgb[i] < 2) {
                rgb[i] = (float) (temp2 + ((temp1 - temp2) * (0.666 - rgb[i]) * 6));
            } else {
                rgb[i] = temp2;
            }

            rgb[i] = rgb[i] * 255;
        }

        rgb[0] = Math.round(rgb[0]);
        rgb[1] = Math.round(rgb[1]);
        rgb[2] = Math.round(rgb[2]);

        return rgb;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, getWidth(), getHeight());

    }

}
