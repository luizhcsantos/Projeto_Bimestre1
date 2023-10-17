package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class RgbHslPanel extends JPanel {

    private JFramePrincipal jFramePrincipal;
    private final ArrayList<JTextField> listatextfieldsRGB;
    private final ArrayList<JTextField> listatextfieldsHSL;

    private Color corPainel;

    public RgbHslPanel(JFramePrincipal jFramePrincipal) {

        this.jFramePrincipal = jFramePrincipal;
        setPreferredSize(new Dimension(150, 200));

        setLayout(new GridBagLayout());

        listatextfieldsRGB = new ArrayList<>();
        listatextfieldsHSL = new ArrayList<>();

        initComponents();


    }

    public void initComponents() {
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setPreferredSize(new java.awt.Dimension(200, 400));

        jLabel19 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonDesenhaCasinha = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel19.setText("RGB");

        jTextField1.setText("0");

        jTextField2.setText("0");

        jTextField3.setText("0");

        jButton8.setText("->");

        jButton9.setText("<-");

        jLabel20.setText("HSL");

        jTextField4.setText("0");

        jTextField5.setText("0");

        jTextField6.setText("0");

        jLabel1.setText("Desenhar Objeto");

        jButtonDesenhaCasinha.setText("Plano XY");

        jButtonDesenhaCasinha.addActionListener(new DesenhaActionListener());

        jButton2.setText("Reset");

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
                                                                .addComponent(jButton2)))
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
                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addGap(39, 39, 39)))
                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel20)
                                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                .addComponent(jButton8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton9))
                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel2))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel3))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel4)))
                                                        .addGroup(RgbHslPanelLayout.createSequentialGroup()
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel5))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel6))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel7))))))
                                .addGap(31, 31, 31)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(RgbHslPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonDesenhaCasinha)
                                        .addComponent(jButton2))
                                .addContainerGap(23, Short.MAX_VALUE))
        );


    }


    public static double[] rgbParaHsl(double r, double g, double b) {
        r /= 255;
        g /= 255;
        b /= 255;

        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double h;
        double s;
        double l = (max + min) / 2;

        if (max == min) {
            h = s = 0; // achromatic
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
        double [] hsl = new double[3];
        hsl[0] = Math.round( ((h/360)*240)*360);
        hsl[1] = Math.round(s*240);
        hsl[2] = Math.round(l*240);

        return hsl;
    }

    public static double[] hslParaRgb(double valor1, double valor2, double valor3) {
        double[] resp = new double[3];
        double[] hsl = new double[3];
        double temp1;
        double temp2;

        hsl[0] = (valor1 * 360) / 240;
        hsl[1] = valor2 / 240;
        hsl[2] = valor3 / 240;

        if (hsl[0] == 0) {
            resp[0] = (hsl[2] / 100) * 255;
            resp[1] = (hsl[2] / 100) * 255;
            resp[2] = (hsl[2] / 100) * 255;
        }

        if (hsl[2] < 0.5) {
            temp1 = hsl[2] * (1 + hsl[1]);
        } else {
            temp1 = (hsl[2] + hsl[1]) - (hsl[2] * hsl[1]);
        }

        temp2 = (2 * hsl[2]) - temp1;

        hsl[0] = hsl[0] / 360;

        resp[0] = hsl[0] + 0.333;
        resp[1] = hsl[0];
        resp[2] = hsl[0] - 0.333;

        for (int i = 0; i < 3; i++) {
            if (resp[i] < 0) {
                resp[i] = resp[i] + 1;
            }

            if (resp[i] > 1) {
                resp[i] = resp[i] - 1;
            }

            if (6 * resp[i] < 1) {
                resp[i] = temp2 + (((temp1 - temp2) * 6) * resp[i]);
            } else if (2 * resp[i] < 1) {
                resp[i] = temp1;
            } else if (3 * resp[i] < 2) {
                resp[i] = temp2 + ((temp1 - temp2) * (0.666 - resp[i]) * 6);
            } else {
                resp[i] = temp2;
            }

            resp[i] = resp[i] * 255;
        }

        resp[0] = Math.round(resp[0]);
        resp[1] = Math.round(resp[1]);
        resp[2] = Math.round(resp[2]);

        return resp;
    }

    private class BotaoRGBListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // rgb - hsl

            ArrayList<String> valoresRGB = new ArrayList<>();

            for (JTextField jTextField : listatextfieldsRGB) {
                if (jTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Valores inválidos!");
                }
                else {
                    valoresRGB.add(jTextField.getText());
                }

            }

            double [] valor = new double[3];
            valor[0] = Double.parseDouble(valoresRGB.get(0));
            valor[1] = Double.parseDouble(valoresRGB.get(1));
            valor[2] = Double.parseDouble(valoresRGB.get(2));
            double[] resposta = rgbParaHsl(valor[0], valor[1], valor[2]);

            corPainel = new Color((int) valor[0],
                    (int) valor[1],
                    (int) valor[2]);
            repaint();

            IntStream.range(0, valoresRGB.size()).forEach(i -> {
                JTextField jTextField = listatextfieldsHSL.get(i);
                if (!jTextField.getText().isEmpty())
                    jTextField.setText("");
                jTextField.setText(String.valueOf(resposta[i]));

            });
        }
    }

    private class BotaoHSLListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // hsl - rgb
            ArrayList<String> valoresHSL = new ArrayList<>();

            for (JTextField jTextField : listatextfieldsHSL) {
                if (jTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Valores inválidos!");
                }
                else {
                    valoresHSL.add(jTextField.getText());
                }

            }

            double [] valor = new double[3];
            valor[0] = Double.parseDouble(valoresHSL.get(0));
            valor[1] = Double.parseDouble(valoresHSL.get(1));
            valor[2] = Double.parseDouble(valoresHSL.get(2));
            double[] resposta = hslParaRgb(valor[0], valor[1], valor[2]);

            corPainel = new Color((int) resposta[0],
                    (int) resposta[1],
                    (int) resposta[2]);
            repaint();

            IntStream.range(0, valoresHSL.size()).forEach(i -> {
                JTextField jTextField = listatextfieldsRGB.get(i);
                if (!jTextField.getText().isEmpty())
                    jTextField.setText("");
                jTextField.setText(String.valueOf(resposta[i]));
            });
        }
    }

    public Color getCorPainel() {
        return corPainel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (corPainel != null) {
            g.setColor(corPainel);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private javax.swing.JButton jButtonDesenhaCasinha;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;

    private class DesenhaActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jFramePrincipal.getImagePanel().desenhaCasinha();
            jFramePrincipal.getImagePanel().repaint();
        }
    }


}
