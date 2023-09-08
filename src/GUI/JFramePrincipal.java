package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JFramePrincipal extends JFrame {

    private static final long serialVersionUID = 2726163241486755910L;
    private final ImagePanel imagePanel;

    public JFramePrincipal() {
        setTitle("Projeto de Imagem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Criação da barra de menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem openMenuItem = new JMenuItem("Abrir Imagem");

        JMenu drawMenu = new JMenu("Desenhar");
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

        drawMenu.add(drawRetaBresenhamMenuItem);
        drawMenu.add(drawRetaSimplesMenuItem);
        drawMenu.add(drawRetaParametricaMenuItem);
        drawMenu.add(drawCircParametricaMenuItem);
        drawMenu.add(drawCircImplicitaMenuItem);
        drawMenu.add(drawCircBresenhamMenuItem);
        menuBar.add(drawMenu);

        add(imagePanel);
    }
}
