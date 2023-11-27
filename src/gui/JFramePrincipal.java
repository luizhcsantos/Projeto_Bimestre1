package gui;

import Sistema.Controlador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;

public class JFramePrincipal extends JFrame {

    @Serial
    private static final long serialVersionUID = 2726163241486755910L;
    private ImagePanel imagePanel;

    public static Controlador controlador;

    public JFramePrincipal() {
        init();
    }

    public void init() {
        setTitle("Projeto de Imagem");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setResizable(false);
        setLayout(new GridBagLayout());

        controlador = new Controlador();

        // Criação da barra de menus
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem openMenuItem = new JMenuItem("Abrir Imagem");
        JMenuItem imagemNegativaMenuItem = new JMenuItem("Imagem Negativa");
        JMenuItem imagemGreyscaleMenuItem = new JMenuItem("Imagem - tons cinza");

        JMenu drawMenu = new JMenu("Desenhar");
        JMenuItem drawLinhaMenuItem = new JMenuItem("Desenhar Linha");
        JMenuItem drawPixel = new JMenuItem("Desenha Pixel");
        JMenuItem drawRetaBresenhamMenuItem = new JMenuItem("Reta (Alg. Bresenham)");
        JMenuItem drawRetaSimplesMenuItem = new JMenuItem("Reta (Eq. da Reta)");
        JMenuItem drawRetaParametricaMenuItem = new JMenuItem("Reta (Eq. Pametrica)");


        JMenuItem drawCircParametricaMenuItem = new JMenuItem("Circunferência (Eq. Paramétrica)");
        JMenuItem drawCircImplicitaMenuItem = new JMenuItem("Circunferência (Eq. Implicita)");
        JMenuItem drawCircBresenhamMenuItem = new JMenuItem("Circunferência (Alg. Bresenham)");


        imagePanel = new ImagePanel(controlador);
        controlador.setImagePanel(imagePanel);
        imagePanel.setPreferredSize(new Dimension(800, 600));

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

        imagemNegativaMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage image = controlador.imagePanel.getImage();
                if (image != null) {
                    controlador.imagemNevativa(image);
                }
            }
        });

        imagemGreyscaleMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage image = controlador.imagePanel.getImage();
                if (image != null) {
                    controlador.imagemGreyscale(image);
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
        fileMenu.add(imagemNegativaMenuItem);
        fileMenu.add(imagemGreyscaleMenuItem);
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


        RgbHslPanel rgbHslPanel = new RgbHslPanel(this, controlador);
        controlador.setRgbHslPanel(rgbHslPanel);
        rgbHslPanel.setPreferredSize(new Dimension(300, 300));
        rgbHslPanel.setBorder(javax.swing.BorderFactory.createLoweredSoftBevelBorder());
        rgbHslPanel.repaint();

        JPanel transformacoesPanel = new JPanel();
        transformacoesPanel.setPreferredSize(new Dimension(300, 300));
        transformacoesPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
        transformacoesPanel.repaint();

        JMenu transformacoesMenu = new JMenu("Transformações");
        JMenuItem translacaoMenuItem = new JMenuItem("Translação");
        JMenuItem rotacaoOrigemMenuItem = new JMenuItem("Rotação de Objeto em relação à origem");
        JMenuItem rotacaoCentroMenuItem = new JMenuItem("Rotação em relação ao centro do objeto");
        JMenuItem escalasMenuItem = new JMenuItem("Escalas (Local/Global)");
        JMenuItem shearingMenuItem = new JMenuItem("Shearing (Cisalhamento)");
        transformacoesMenu.add(translacaoMenuItem);
        transformacoesMenu.add(rotacaoOrigemMenuItem);
        transformacoesMenu.add(rotacaoCentroMenuItem);
        transformacoesMenu.add(escalasMenuItem);
        transformacoesMenu.add(shearingMenuItem);
        menuBar.add(transformacoesMenu);

        JMenu preenchimentoMenu = new JMenu("Preenchimento");
        JMenuItem floodfill4MenuItem = new JMenuItem("Floodfill vizinhança 4");
        JMenuItem floodfill8MenuItem = new JMenuItem("Floodfill vizinhança 8");
        JMenuItem edgefillMenuItem = new JMenuItem("Edge Fill (Inversão de Cores)");
        preenchimentoMenu.add(floodfill4MenuItem);
        preenchimentoMenu.add(floodfill8MenuItem);
        preenchimentoMenu.add(edgefillMenuItem);
        menuBar.add(preenchimentoMenu);

        floodfill4MenuItem.addActionListener(e -> {
            if (!imagePanel.isFloodfill4Selecionado())
                imagePanel.setFloodfill4Selecionado(true);
            imagePanel.carregarImagem();
        });

        floodfill8MenuItem.addActionListener(e -> {
            if (!imagePanel.isFloodfill8Selecionado())
                imagePanel.setFloodfill8Selecionado(true);
            imagePanel.carregarImagem();
        });

        edgefillMenuItem.addActionListener(e -> {
            imagePanel.setEdgeFillSelecionado(true);
            transformacoesPanel.removeAll();
            EdgeFillPanel edgeFillPanel = new EdgeFillPanel(controlador);
            edgeFillPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
            transformacoesPanel.add(edgeFillPanel);
            transformacoesPanel.validate();

        });

        translacaoMenuItem.addActionListener(e -> {

            transformacoesPanel.removeAll();
            TranslacaoPanel translacaoPanel = new TranslacaoPanel(controlador);
            translacaoPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
            transformacoesPanel.add(translacaoPanel);
            transformacoesPanel.validate();

        });
        rotacaoCentroMenuItem.addActionListener(e -> {
            transformacoesPanel.removeAll();
            RotacaoCentroPanel rotacaoCentroPanel = new RotacaoCentroPanel(controlador);
            rotacaoCentroPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
            transformacoesPanel.add(rotacaoCentroPanel);
            transformacoesPanel.validate();
        });
        rotacaoOrigemMenuItem.addActionListener(e -> {
            transformacoesPanel.removeAll();
            RotacaoOrigemPanel rotacaoOrigemPanel = new RotacaoOrigemPanel(controlador);
            rotacaoOrigemPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
            transformacoesPanel.add(rotacaoOrigemPanel);
            transformacoesPanel.validate();
        });
        escalasMenuItem.addActionListener(e -> {
            transformacoesPanel.removeAll();
            EscalasPanel escalasPanel = new EscalasPanel(controlador);
            escalasPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
            transformacoesPanel.add(escalasPanel);
            transformacoesPanel.validate();
        });
        shearingMenuItem.addActionListener(e -> {
            transformacoesPanel.removeAll();
            ShearingPanel shearingPanel = new ShearingPanel(controlador);
            shearingPanel.setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
            transformacoesPanel.add(shearingPanel);
            transformacoesPanel.validate();
        });


        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
                ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        ImagePanelLayout.setVerticalGroup(
                ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(transformacoesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(rgbHslPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rgbHslPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(transformacoesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        pack();

    }

}
