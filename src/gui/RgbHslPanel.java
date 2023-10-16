package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class RgbHslPanel extends JPanel {

    private final ArrayList<JTextField> listatextfieldsRGB;
    private final ArrayList<JTextField> listatextfieldsHSL;

    private Color corPainel;

    public RgbHslPanel() {

        setPreferredSize(new Dimension(150, 300));

        setLayout(new GridBagLayout());

        listatextfieldsRGB = new ArrayList<>();
        listatextfieldsHSL = new ArrayList<>();

        String[] labelNames = {"Red", "Green", "Blue",
                "Hue", "Saturation", "Lightness"};

        JPanel painel1 = new JPanel(new GridBagLayout());
        JPanel painel2 = new JPanel(new GridLayout(2, 1));
        JPanel painel3 = new JPanel(new GridBagLayout());

        // Adiciona os painéis internos ao painel principal
        add(painel1);
        add(painel2);
        add(painel3);

        // Adiciona os componentes ao primeiro e terceiro painel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        for (int i = 0; i < 3; i++) {
            JLabel label1 = new JLabel(labelNames[i]);
            JTextField textField1 = new JTextField(10);
            textField1.setName("textFieldRGB" + i);
            JLabel label2 = new JLabel(labelNames[i + 3]);
            JTextField textField2 = new JTextField(10);
            textField2.setName("textFieldHSL" + (i+3));
            listatextfieldsRGB.add(textField1);
            listatextfieldsHSL.add(textField2);

            gbc.gridx = 0;
            gbc.gridy = i;
            painel1.add(label1, gbc);

            gbc.gridx = 1;
            painel1.add(textField1, gbc);

            gbc.gridx = 0;
            gbc.gridy = i;
            painel3.add(label2, gbc);

            gbc.gridx = 1;
            painel3.add(textField2, gbc);

        }

        // Adiciona os botões ao segundo painel
        JButton botaoRGB = new JButton("->");
        JButton botaoHSL = new JButton("<-");

        // Adiciona ActionListener para cada botão
        botaoRGB.addActionListener(new BotaoRGBListener());
        botaoHSL.addActionListener(new BotaoHSLListener());

        painel2.add(botaoRGB);
        painel2.add(botaoHSL);
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
}
