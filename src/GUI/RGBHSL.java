package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

public class RGBHSL extends JPanel {


    public RGBHSL() {

        setLayout(new GridBagLayout());

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
            textField1.setText("0");
            textField1.setName("textField" + i);
            JLabel label2 = new JLabel(labelNames[i + 3]);
            JTextField textField2 = new JTextField(10);
            textField2.setText("0");
            textField2.setName("textField" + (i+3));

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
        JButton botao1 = new JButton("->");
        JButton botao2 = new JButton("<-");

        // Adiciona ActionListener para cada botão
        botao1.addActionListener(new botao1Listener());
        botao2.addActionListener(new botao2Listener());

        painel2.add(botao1);
        painel2.add(botao2);
    }


    public static double[] rgbToHsl(double valor1, double valor2, double valor3) {
        double[] resp = new double[3];
        double[] rgb = new double[3];

        rgb[0] = valor1 / 255;
        rgb[1] = valor2 / 255;
        rgb[2] = valor3 / 255;

        double min = minValue(rgb);
        double max = maxValue(rgb);

        double delta = max - min;

        resp[0] = 0;
        resp[1] = 0;
        resp[2] = 0;

        // calcular brilho (L)
        double brilho = (max + min) / 2;

        // calcular saturação (S)
        double saturacao;
        if (delta == 0) {
            saturacao = 0;
        } else {
            saturacao = delta / (1 - Math.abs(2 * brilho - 1));
        }

        // calcular tonalidade (H)
        double tonalidade = 0;
        if (delta != 0) {
            if (rgb[0] == max) { // vermelho é o max
                tonalidade = ((rgb[1] - rgb[2]) / delta) % 6 * 60; // g - b
            } else if (rgb[1] == max) { // verde é o max
                tonalidade = ((rgb[2] - rgb[0]) / delta + 2) * 60; // b - r
            } else if (rgb[2] == max) { // azul é o max
                tonalidade = ((rgb[0] - rgb[1]) / delta + 4) * 60; // r - g
            }
        }

        tonalidade = Math.round(tonalidade);

        resp[0] = Math.round((tonalidade / 360) * 240);
        resp[1] = Math.round(saturacao * 240);
        resp[2] = Math.round(brilho * 240);

        return resp;
    }

    private static double minValue(double[] arr) {
        double min = arr[0];
        for (double value : arr) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    private static double maxValue(double[] arr) {
        double max = arr[0];
        for (double value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static double[] hslToRgb(double valor1, double valor2, double valor3) {
        double[] resp = new double[3];
        double[] hsl = new double[3];
        double temp1, temp2;

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

    private class botao1Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // rgb - hsl
            System.out.println("teste");
            JOptionPane.showMessageDialog(null, "Botão 1 foi clicado!");
            Component[] components = getComponents();

            ArrayList<String> valoresRGB = new ArrayList<>();
            ArrayList<JTextField> listaFields = new ArrayList<>();
            ArrayList<Component> listaComponentes = new ArrayList<>();

            for (Component componente : components) {

                listaComponentes = percorrerComponentes(componente);

                if (componente instanceof JTextField jTextField) {
                    String texto = jTextField.getText();
                    if (!Objects.equals(texto, "0")) {
                        valoresRGB.add(texto);
                    }
                    else {
                        listaFields.add(jTextField);
                    }
                }
            }
            double valor1 = Double.parseDouble(valoresRGB.get(0));
            double valor2 = Double.parseDouble(valoresRGB.get(1));
            double valor3 = Double.parseDouble(valoresRGB.get(2));
            double[] resposta = rgbToHsl(valor1, valor2, valor3);

            IntStream.range(0, 3).forEach(i -> listaFields.get(i).setText(String.valueOf(resposta[i])));
        }
    }

    private class botao2Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // hsl - rgb
            JOptionPane.showMessageDialog(null, "Botão 2 foi clicado!");
        }
    }

    // Método para percorrer os componentes recursivamente
    private ArrayList<Component> percorrerComponentes(Component componente) {
        ArrayList<Component> componentesEncontrados = new ArrayList<>();

        if (componente instanceof Container) {
            Component[] componentes = ((Container) componente).getComponents();
            for (Component c : componentes) {
                componentesEncontrados.add(c);

                if (c instanceof JPanel) {
                    // Se for um JPanel, chama a função recursivamente
                    componentesEncontrados.addAll(percorrerComponentes(c));
                }
            }
        }

        return componentesEncontrados;
    }

}
