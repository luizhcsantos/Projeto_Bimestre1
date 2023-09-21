package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class JFramePrincipal extends JFrame {

    @Serial
    private static final long serialVersionUID = 2726163241486755910L;
    private final ImagePanel imagePanel;

    public JFramePrincipal() {
        setTitle("Projeto de Imagem");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);

        setLayout(new GridBagLayout());

        // Criação da barra de menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem openMenuItem = new JMenuItem("Abrir Imagem");

        JMenu drawMenu = new JMenu("Desenhar");
        JMenuItem drawLinhaMenuItem = new JMenuItem("Desenhar Linha");
        JMenuItem drawPixel = new JMenuItem("Desenha Pixel");
        JMenuItem drawRetaBresenhamMenuItem = new JMenuItem("Reta (Alg. Bresenham)");
        JMenuItem drawRetaSimplesMenuItem = new JMenuItem("Reta (Eq. da Reta)");
        JMenuItem drawRetaParametricaMenuItem = new JMenuItem("Reta (Eq. Pametrica)");


        JMenuItem drawCircParametricaMenuItem = new JMenuItem("Circunferência (Eq. Paramétrica)");
        JMenuItem drawCircImplicitaMenuItem = new JMenuItem("Circunferência (Eq. Implicita)");
        JMenuItem drawCircBresenhamMenuItem = new JMenuItem("Circunferência (Alg. Bresenham)");


        imagePanel = new ImagePanel();

        openMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedImage image = null;
                try {
                    image = ImageIO.read(selectedFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (image != null) {
                    imagePanel.setImage(image);
                }
            }
        });

        drawLinhaMenuItem.addActionListener(e -> {
            imagePanel.metodoDesenho(ImagePanel.DrawingMethod.RETA);
        });

        drawPixel.addActionListener(e -> {
            imagePanel.metodoDesenho(ImagePanel.DrawingMethod.PIXEL);
        });


        drawRetaBresenhamMenuItem.addActionListener(e -> {
            imagePanel.metodoDesenho(ImagePanel.DrawingMethod.RBRESENHAM);
        });

        drawRetaSimplesMenuItem.addActionListener(e -> {
            imagePanel.metodoDesenho(ImagePanel.DrawingMethod.RSIMPLES);
        });

        drawCircParametricaMenuItem.addActionListener(e -> {
            imagePanel.metodoDesenho(ImagePanel.DrawingMethod.CPARAMETRICA);
        });

        drawCircImplicitaMenuItem.addActionListener(e -> {
            imagePanel.metodoDesenho(ImagePanel.DrawingMethod.CIMPLICITA);
        });

        drawCircBresenhamMenuItem.addActionListener(e -> {
            imagePanel.metodoDesenho(ImagePanel.DrawingMethod.CBRESENHAM);
        });

        drawRetaParametricaMenuItem.addActionListener(e -> {
            imagePanel.metodoDesenho(ImagePanel.DrawingMethod.RPARAMETRICA);
        });

        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        drawMenu.add(drawLinhaMenuItem);
        drawMenu.add(drawPixel);
        drawMenu.add(drawRetaBresenhamMenuItem);
        drawMenu.add(drawRetaSimplesMenuItem);
        drawMenu.add(drawRetaParametricaMenuItem);
        drawMenu.add(drawCircParametricaMenuItem);
        drawMenu.add(drawCircImplicitaMenuItem);
        drawMenu.add(drawCircBresenhamMenuItem);
        menuBar.add(drawMenu);

        // Configura as restrições para o primeiro painel
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 1; // coluna 2
        gbc1.gridy = 0;
        gbc1.weightx = 2; // Proporção 1
        gbc1.weighty = 1; // Proporção 2
        gbc1.fill = GridBagConstraints.BOTH; // Preenche ambos os eixos
        // definimos a âncora para GridBagConstraints.WEST para ambos os painéis,
        // o que alinha os painéis à esquerda dentro de suas células do layout.
        //gbc1.anchor = GridBagConstraints.WEST; // Alinha à direita

        RGBHSL rgbhslPanel = new RGBHSL();
        rgbhslPanel.setPreferredSize(new Dimension(150, 300));

        // Configura as restrições para o segundo painel
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0; // Coluna 1
        gbc2.gridy = 0; // Mesma linha que o primeiro painel
        gbc2.weightx = 1; // Proporção 1
        gbc2.weighty = 1; // Proporção 1
        gbc2.fill = GridBagConstraints.BOTH; // Preenche ambos os eixos
        //gbc2.anchor = GridBagConstraints.WEST; // Alinha à esquerda

        add(imagePanel, gbc1);
        add(rgbhslPanel, gbc2);
    }
}
