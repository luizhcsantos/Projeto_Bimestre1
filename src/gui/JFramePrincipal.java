package gui;

import Sistema.Controlador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serial;

public class JFramePrincipal extends JFrame {

    @Serial
    private static final long serialVersionUID = 2726163241486755910L;
    private ImagePanel imagePanel;
    private RgbHslPanel rgbHslPanel;

    //private final TransformacoesPanel transformacoesPanel;
    public static Controlador controlador;

    public JFramePrincipal() {
        init();
    }

    public void init() {
        setTitle("Projeto de Imagem");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);

        setLayout(new GridBagLayout());

        controlador = new Controlador();

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
        controlador.setImagePanel(imagePanel);
        imagePanel.setPreferredSize(new Dimension(700, 520));
        imagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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


        rgbHslPanel = new RgbHslPanel(this);
        controlador.setRgbHslPanel(rgbHslPanel);
        rgbHslPanel.setPreferredSize(new Dimension(300, 300));
        rgbHslPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        //transformacoesPanel = new TransformacoesPanel();
        //controlador.setTransformacoesPanel(new TransformacoesPanel());
        //transformacoesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        //transformacoesPanel.setPreferredSize(new Dimension(1000, 250));

        JMenu transformacoesMenu = new JMenu("Transformações");
        JMenuItem translacaoMenuItem = new JMenuItem("Translação");
        JMenuItem rotacaoOrigemMenuItem = new JMenuItem("Rotação de Objeto em relação à origem");
        JMenuItem rotacaoCentroMenuItem = new JMenuItem("Rotação em relação ao centro do objeto");
        JMenuItem escalaLocalMenuItem = new JMenuItem("Escala Local");
        JMenuItem escalaGlobalMenuItem = new JMenuItem("Escala Global");
        JMenuItem shearingMenuItem = new JMenuItem("Shearing (Cizalhamento)");
        transformacoesMenu.add(translacaoMenuItem);
        transformacoesMenu.add(rotacaoOrigemMenuItem);
        transformacoesMenu.add(rotacaoCentroMenuItem);
        transformacoesMenu.add(escalaLocalMenuItem);
        transformacoesMenu.add(escalaGlobalMenuItem);
        transformacoesMenu.add(shearingMenuItem);
        menuBar.add(transformacoesMenu);

//        translacaoMenuItem.addActionListener(e -> {
//            transformacoesPanel.tipoTransformacao(TransformacoesPanel.Transformacao.TRANSLACAO);
//        });
//        rotacaoCentroMenuItem.addActionListener(e -> {
//            transformacoesPanel.tipoTransformacao(TransformacoesPanel.Transformacao.ROTACAO_ORIGEM);
//        });
//        rotacaoCentroMenuItem.addActionListener(e -> {
//            transformacoesPanel.tipoTransformacao(TransformacoesPanel.Transformacao.ROTACAO_CENTRO);
//        });
//        escalaLocalMenuItem.addActionListener(e -> {
//            transformacoesPanel.tipoTransformacao(TransformacoesPanel.Transformacao.ESCALA_LOCAL);
//        });
//        escalaGlobalMenuItem.addActionListener(e -> {
//            transformacoesPanel.tipoTransformacao(TransformacoesPanel.Transformacao.ESCALA_GLOBAL);
//        });
//        shearingMenuItem.addActionListener(e -> {
//            transformacoesPanel.tipoTransformacao(TransformacoesPanel.Transformacao.SHEARING);
//        });

        imagePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imagePanel.setPreferredSize(new java.awt.Dimension(800, 400));

        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
                ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 708, Short.MAX_VALUE)
        );
        ImagePanelLayout.setVerticalGroup(
                ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

//        transformacoesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//
//        javax.swing.GroupLayout transformacoesPanelLayout = new javax.swing.GroupLayout(transformacoesPanel);
//        transformacoesPanel.setLayout(transformacoesPanelLayout);
//        transformacoesPanelLayout.setHorizontalGroup(
//                transformacoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 0, Short.MAX_VALUE)
//        );
//        transformacoesPanelLayout.setVerticalGroup(
//                transformacoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 355, Short.MAX_VALUE)
//        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        //.addComponent(transformacoesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(rgbHslPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rgbHslPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)))
                                //.addComponent(transformacoesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        pack();

    }



    public Controlador getControlador() {
        return controlador;
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }
}
