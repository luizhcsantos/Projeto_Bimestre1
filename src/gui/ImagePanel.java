package gui;

import Sistema.Controlador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class ImagePanel extends JPanel {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -1753912953142514844L;
    private transient BufferedImage image;
    private static BufferedImage buffer;
    private int xx1;
    private int xx2;
    private int yy1;
    private int yy2;
    private boolean drawing = false;
    private final Controlador controlador;
    //private int cont = 0;
    private final double[][] pontosTotal = new double[10][4];
    private final int[][] arestas = new int[17][2];
    private final double[][] resultadoPrintado = new double[10][4];
    private double[][] matrizDeProjecao = new double[4][4];
    public enum DrawingMethod {
        RETA,           // desenha reta com função do próprio java graphics
        PIXEL,          // desenha apenas um pixel
        RBRESENHAM,     // reta com alg. de bresenham
        RSIMPLES,       // reta com equação da reta y = ax + b
        RPARAMETRICA,   // reta com equação paramétrica
        CPARAMETRICA,   // circunferencia com equação paramétrica
        CIMPLICITA,     // circunferencia com equação implicita
        CBRESENHAM      // circunferencia com alg. de bresenham
    }
    private DrawingMethod drawingMethod;
    private boolean floodfill4Selecionado = false;
    private boolean floodfill8Selecionado = false;
    private int[][] pontosPoligono;
    private final int[][] arestasEdgefill = {
            {16, 180, 180, 180},
            {180, 86, 180, 180},
            {120, 120, 180, 86},
            {16, 36, 120, 120},
            {16, 36, 16, 180}
    };

    private final int[][] boundingBox = {
            {12, 30},
            {184, 184}
    };
    private boolean edgeFillSelecionado = false;
    public ImagePanel(Controlador controlador) {
        this.controlador = controlador;
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        JFramePrincipal.controlador.setBuffer(buffer);
        setBorder(javax.swing.BorderFactory.createLoweredBevelBorder());
        setBackground(Color.BLACK);
        inicializaPontoEAresta();


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xx1 = e.getX();
                yy1 = e.getY();
                drawing = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                xx2 = e.getX();
                yy2 = e.getY();
                drawing = false;
                if (drawingMethod == DrawingMethod.RBRESENHAM) {
                    desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                } else if (drawingMethod == DrawingMethod.RSIMPLES) {
                    desenharReta(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                } else if (drawingMethod == DrawingMethod.RPARAMETRICA) {
                    desenharRetaParametrica(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if (drawingMethod == DrawingMethod.CPARAMETRICA) {
                    desenharCircParametrica(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if (drawingMethod == DrawingMethod.CIMPLICITA) {
                    desenharCircImplicita(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if (drawingMethod == DrawingMethod.CBRESENHAM) {
                    //System.out.println("xx1:"+xx1+"\tyy1? "+yy1+"\txx2: "+xx2+"\tyy2: "+yy2);
                    desenharCircBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if (drawingMethod == DrawingMethod.RETA) {
                    desenharRetaSimples(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if  (drawingMethod == DrawingMethod.PIXEL) {
                    desenharPixel(buffer.getGraphics(), xx1, yy1);
                }
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int xx3 = e.getX();
                int yy3 = e.getY();


                acionarFloodfill4(xx3, yy3);

                acionarFloodfill8(xx3, yy3);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawing) {
                    xx2 = e.getX();
                    yy2 = e.getY();
                    repaint();
                }
            }
        });
    }

    public void metodoDesenho(DrawingMethod drawingMethod) {
        this.drawingMethod = drawingMethod;
        limparBuffer(); // Limpa o buffer ao alterar o modo de desenho
    }

    public DrawingMethod getDrawingMethod() {
        return drawingMethod;
    }

    public void limparBuffer() {
        Graphics2D g2d = buffer.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (image != null) {
            g.drawImage(buffer, 0, 0, this);
        }

        if (edgeFillSelecionado) {
            g2d.setColor(new Color(250, 218, 94)); // Cor de preenchimento
            g2d.fillRect(0, 0, getWidth(), getHeight()); // Preenche toda a área com a cor
        }

        drawFigure(g2d); // Chama o método para desenhar a figura

    }

    private void drawFigure(Graphics2D g2d) {
        g2d.setColor(Color.BLACK); // Cor das linhas

        for (int[] edge : arestasEdgefill) {
            int x1 = edge[0];
            int y1 = edge[1];
            int x2 = edge[2];
            int y2 = edge[3];

            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }

    public boolean isFloodfill4Selecionado() {
        return floodfill4Selecionado;
    }

    public void setFloodfill4Selecionado(boolean floodfill4Selecionado) {
        this.floodfill4Selecionado = floodfill4Selecionado;

    }

    private void acionarFloodfill4(int x, int y) {
        if (floodfill4Selecionado) {
            floodfill8Selecionado = false;
            System.out.println("\nxx3: " + x + "\tyy3: " + y);
            floodFill4(buffer, x, y, Color.BLACK.getRGB(), Color.CYAN.getRGB());

        }

    }

    private void acionarFloodfill8(int x, int y) {
        if (floodfill8Selecionado) {
            floodfill4Selecionado = false;
            System.out.println("\nxx3: " + x + "\tyy3: " + y);
            floodfill8(buffer, x, y, Color.BLACK.getRGB(), Color.GREEN.getRGB());

        }

    }

    public void floodFill4(BufferedImage image, int x, int y, int targetColor, int replacementColor) {

        // Verifica se as coordenadas estão dentro dos limites da imagem
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            return;
        }

        // Obtém a cor do pixel atual
        int currentColor = image.getRGB(x, y);

        // Verifica se o pixel já foi preenchido ou se tem a cor alvo
        if (currentColor != targetColor) {
            return;
        }

        // Cria uma fila para armazenar os pixels a serem processados
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            x = point.x;
            y = point.y;

            // Verifica se as novas coordenadas estão dentro dos limites da imagem
            if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                // Obtém a cor do pixel atual
                currentColor = image.getRGB(x, y);

                // Verifica se o pixel ainda tem a cor alvo
                if (currentColor == targetColor) {
                    // Pinta o pixel com a nova cor
                    image.setRGB(x, y, replacementColor);

                    // Adiciona os pixels vizinhos à fila para processamento
                    queue.add(new Point(x - 1, y));
                    queue.add(new Point(x + 1, y));
                    queue.add(new Point(x, y - 1));
                    queue.add(new Point(x, y + 1));
                }
            }
        }
    }

    public boolean isFloodfill8Selecionado() {
        return floodfill8Selecionado;
    }

    public void setFloodfill8Selecionado(boolean floodfill8Selecionado) {
        this.floodfill8Selecionado = floodfill8Selecionado;
    }

    public void floodfill8(BufferedImage image, int x, int y, int targetColor, int replacementColor) {
        // Verifica se as coordenadas estão dentro dos limites da imagem
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            return;
        }

        // Obtém a cor do pixel atual
        int currentColor = image.getRGB(x, y);

        // Verifica se o pixel já foi preenchido ou se tem a cor alvo
        if (currentColor != targetColor) {
            return;
        }

        // Cria uma fila para armazenar os pixels a serem processados
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            x = point[0];
            y = point[1];

            // Verifica se as novas coordenadas estão dentro dos limites da imagem
            if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
                // Obtém a cor do pixel atual
                currentColor = image.getRGB(x, y);

                // Verifica se o pixel ainda tem a cor alvo
                if (currentColor == targetColor) {
                    // Pinta o pixel com a nova cor
                    image.setRGB(x, y, replacementColor);

                    // Adiciona os pixels vizinhos à fila para processamento (vizinhança 8)
                    addValidPoint(queue, x - 1, y);
                    addValidPoint(queue, x + 1, y);
                    addValidPoint(queue, x, y - 1);
                    addValidPoint(queue, x, y + 1);
                    addValidPoint(queue, x - 1, y - 1);
                    addValidPoint(queue, x + 1, y - 1);
                    addValidPoint(queue, x - 1, y + 1);
                    addValidPoint(queue, x + 1, y + 1);
                }
            }
        }
    }

    private void addValidPoint(Queue<int[]> queue, int x, int y) {
        // Adiciona o ponto à fila somente se estiver dentro dos limites da imagem
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            queue.add(new int[]{x, y});
        }
    }

    public void carregarImagem() {
        try {
            // Carregar imagem BMP da pasta raiz do projeto
            String caminhoImagem = "Testar_FloodFill.bmp";  // Nome do arquivo da imagem
            File arquivoImagem = new File(caminhoImagem);

            // Verificar se o arquivo existe antes de tentar ler
            if (!arquivoImagem.exists()) {
                System.err.println("Arquivo de imagem não encontrado: " + caminhoImagem);
                return;
            }

            BufferedImage imagem = ImageIO.read(arquivoImagem);

            // Encontrar coordenadas do polígono (pixels pretos) e desenhá-lo na nova imagem
            for (int i = 0; i < imagem.getWidth(); i++) {
                for (int j = 0; j < imagem.getHeight(); j++) {
                    int corPixel = imagem.getRGB(i, j);

                    // Verificar se o pixel é preto
                    if (corPixel == -16777216) {  // 0xFF000000/-16777216 representa a cor preta no formato ARGB
                        // Definir a cor do pixel correspondente no novo BufferedImage
                        buffer.setRGB(50+i, 50+j, -1);
                    }
                }
            }

            // Encontrar coordenadas do polígono (exemplo: procurando por pixels de uma cor específica)
            //identificarPontosPoligono(imagem);

            // Repintar a tela para exibir o polígono
            repaint();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adicionarPontoPoligono(int x, int y) {
        // Adicionar coordenadas do ponto ao array de pontos do polígono
        // Aqui você pode implementar a lógica para armazenar os pontos do polígono
        // (por exemplo, adicionar ao ArrayList, matriz, etc.)

        // Verifica se a matriz de pontos do polígono já foi inicializada
        if (pontosPoligono == null) {
            pontosPoligono = new int[2][1];
            pontosPoligono[0][0] = x;
            pontosPoligono[1][0] = y;
        } else {
            // Amplia a matriz para incluir o novo ponto
            int colunasAtuais = pontosPoligono[0].length;
            int[][] novaMatriz = new int[2][colunasAtuais + 1];

            // Copia os pontos existentes para a nova matriz
            for (int i = 0; i < colunasAtuais; i++) {
                novaMatriz[0][i] = pontosPoligono[0][i];
                novaMatriz[1][i] = pontosPoligono[1][i];
            }

            // Adiciona o novo ponto à nova matriz
            novaMatriz[0][colunasAtuais] = x;
            novaMatriz[1][colunasAtuais] = y;

            // Atualiza a referência da matriz de pontos do polígono
            pontosPoligono = novaMatriz;
        }
    }

    public static void floodFill(BufferedImage img, int xo, int yo, int paintColor, int targetColor) {
        if (paintColor == targetColor) {
            return;
        }

        img.setRGB(xo, yo, paintColor);

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{xo, yo});

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];

            if (x > 0 && img.getRGB(x - 1, y) == targetColor) {
                img.setRGB(x - 1, y, paintColor);
                stack.push(new int[]{x - 1, y});
            }
            if (x < img.getWidth() - 1 && img.getRGB(x + 1, y) == targetColor) {
                img.setRGB(x + 1, y, paintColor);
                stack.push(new int[]{x + 1, y});
            }
            if (y > 0 && img.getRGB(x, y - 1) == targetColor) {
                img.setRGB(x, y - 1, paintColor);
                stack.push(new int[]{x, y - 1});
            }
            if (y < img.getHeight() - 1 && img.getRGB(x, y + 1) == targetColor) {
                img.setRGB(x, y + 1, paintColor);
                stack.push(new int[]{x, y + 1});
            }
        }
    }

    public boolean isEdgeFillSelecionado() {
        return edgeFillSelecionado;
    }

    public void setEdgeFillSelecionado(boolean edgeFillSelecionado) {
        this.edgeFillSelecionado = edgeFillSelecionado;
    }

    public void edgefill() {

        for (int[] edge : arestasEdgefill) {
            int x1 = edge[0];
            int y1 = edge[1];
            int x2 = edge[2];
            int y2 = edge[3];

            if (y1 == y2) {
                continue;
            }

            if (y1 > y2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;

                temp = y1;
                y1 = y2;
                y2 = temp;
            }

            for (int y = y1; y <= y2; y++) {
                int x = Math.round(x1 + (float) ((x2 - x1) * (y - y1)) / (y2 - y1));

                for (int i = x; i <= boundingBox[1][0]; i++) {
                    int rgb = buffer.getRGB(i, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;
                    buffer.setRGB(i, y, new Color(255 - r, 255 - g, 255 - b, 255).getRGB());

                }
            }
        }
    }

    /*************************************/
    /**FUNÇÕES DO TRABALHO DO BIMESTRE 1**/
    /*************************************/
    private void desenharRetaSimples(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.BLUE);
        g2d.drawLine(xx1, yy1, xx2, yy2);
        repaint();
    }

    private void desenharPixel(Graphics g, int xx1, int yy1) {
        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.BLUE);
        g2d.drawRect(xx1, yy1, 1, 1);
        repaint();
    }

    private void desenharReta(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.BLUE);

        double m = (double) (yy2 - yy1) / (xx2 - xx1);

        int dx = Math.abs(xx2 - xx1);
        int dy = Math.abs(yy2 - yy1);

        if (dx > dy) {
            if (xx1 < xx2) {
                for (int xi = xx1; xi <= xx2; xi++) {
                    int yi = (int) Math.round(m * (xi - xx1) + yy1);
                    g2d.fillRect(xi, yi, 1, 1);
                }
            } else {
                for (int xi = xx2; xi <= xx1; xi++) {
                    int yi = (int) Math.round(m * (xi - xx1) + yy1);
                    g2d.fillRect(xi, yi, 1, 1);
                }
            }
        } else {
            if (yy1 < yy2) {
                for (int yi = yy1; yi <= yy2; yi++) {
                    int xi = (int) Math.round((yi - yy1) / m + xx1);
                    g2d.fillRect(xi, yi, 1, 1);
                }
            } else {
                for (int yi = yy2; yi <= yy1; yi++) {
                    int xi = (int) Math.round((yi - yy1) / m + xx1);
                    g2d.fillRect(xi, yi, 1, 1);
                }
            }
        }

        repaint();
    }

    public void desenharRetaBresenham(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.GREEN);

        int incy1 = 0;
        int incx1 = 0;
        int incx2;
        int incy2;

        int dx = Math.abs(xx2 - xx1);
        int dy = Math.abs(yy2 - yy1);

        if (dx > dy) {
            if (xx2 < xx1) {
                incx2 = -1;
                incx1 = -1;
            } else {
                incx2 = 1;
                incx1 = 1;
            }

            if (yy2 < yy1) {
                incy2 = -1;
            } else {
                incy2 = 1;
            }
        }
        else {
            int auxiliar = dx;
            dx = dy;
            dy = auxiliar;

            if (yy2 < yy1) {
                incy2 = -1;
                incy1 = -1;
            } else {
                incy2 = 1;
                incy1 = 1;
            }

            if (xx2 < xx1) {
                incx2 = -1;
            } else {
                incx2 = 1;
            }
        }

        int d = (2 * dy) - dx;
        int aE = 2 * dy;
        int aNE = 2 * (dy - dx);
        int ax = xx1;
        int ay = yy1;

        int auxiliar = Math.max(dx, dy);

        //g2d.setColor(Color.GREEN);
        g2d.fillRect(ax, ay, 1, 1);

        for (int cont = 0; cont < auxiliar; cont++) {
            if (d <= 0) { // escolhe aE
                d = d + aE;
                ay = ay + incy1;
                ax = ax + incx1;
            } else { // escolhe aNE
                d = d + aNE;
                ay = ay + incy2;
                ax = ax + incx2;
            }
            g2d.fillRect(ax, ay, 1, 1);
        }
    }

    private void desenharCircParametrica(Graphics g, int xx1, int yy1, int xx2, int yy2) {    // desenha circunferência usando equação paramétrica

        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.BLUE);

        int xc;
        int yc;
        int raio;

        float xr;
        float yr;
        float ang;
        double step;

        xc =xx1;
        yc =yy1;
        raio = (int) Math.abs(Math.round(Math.sqrt(((yy2 - yy1) * (yy2 - yy1)) + ((xx2 - xx1) * (xx2 - xx1)))));
        ang = 0;
        step = 1 /(6.28 * raio);
        // número de pixels que serão pintados
        // valores maiores resultarão em mais pixels pintados
        // e, consequentemente, menos "buracos" na circunferencia
        do {
            xr = (float) (xc + (raio * Math.cos(ang)));
            yr = (float) (yc + (raio * Math.sin(ang)));

            g2d.fillRect((int) xr, (int) yr, 1, 1);

            ang += (float) step;
        } while (ang < 2* Math.PI);
    }

    private void desenharCircImplicita(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.ORANGE);

        int raio;
        raio = (int) Math.abs(
                Math.round(
                        Math.sqrt(
                                (Math.pow((yy2 - yy1), 2))
                                        +
                                        (Math.pow((xx2 - xx1), 2))
                        )
                )
        );

        for (int x = -raio; x <= raio; x++) {
            int y = (int) Math.sqrt(raio*raio - x*x);
            g2d.fillRect(xx1 + x, yy1 + y, 1, 1);
            g2d.fillRect(xx1 + x, yy1 - y, 1, 1);
        }
    }

    private void espelharCircBresenham(Graphics g, int xx1, int yy1, int x, int y) {

        if (controlador.getCor() != null) {
            g.setColor(controlador.getCor());
        }
        else
            g.setColor(Color.WHITE);
        g.fillRect(xx1 + x,yy1 + y,1, 1);
        g.fillRect(xx1 - x,yy1 + y,1, 1);
        g.fillRect(xx1 - y,yy1 + x,1, 1);
        g.fillRect(xx1 - y,yy1 - x,1, 1);
        g.fillRect(xx1 - x,yy1 - y,1, 1);
        g.fillRect(xx1 + x,yy1 - y,1, 1);
        g.fillRect(xx1 + y,yy1 - x,1, 1);
        g.fillRect(xx1 + y,yy1 + x,1, 1);

    }

    private static void drawSymmetricPoints(Graphics g, int xx1, int yy1, int x, int y) {
        g.fillRect(xx1 + x, yy1 + y, 1, 1);
        g.fillRect(xx1 - x, yy1 + y, 1, 1);
        g.fillRect(xx1 + x, yy1 - y, 1, 1);
        g.fillRect(xx1 - x, yy1 - y, 1, 1);
        g.fillRect(xx1 + y, yy1 + x, 1, 1);
        g.fillRect(xx1 - y, yy1 + x, 1, 1);
        g.fillRect(xx1 + y, yy1 - x, 1, 1);
        g.fillRect(xx1 - y, yy1 - x, 1, 1);
    }

    private void desenharCircBresenham(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.RED);

        int x;
        int y;
        int raio;
        int h;
        double dSE;
        double dE;

        raio = (int) Math.abs(Math.round(Math.sqrt((yy2 - yy1)*(yy2 - yy1) + (xx2 - xx1)*(xx2 - xx1))));
        x = 0;
        y = raio;
        h = 1 - raio;
        dE = 3;
        dSE = (-2 * raio) + 5;

        while (x <= y) {
            espelharCircBresenham(g2d, xx1, yy1, x, y);
            if (h < 0) { // seleciona E
                h += (int) dE;
                dE += 2;
                dSE += 2;
            } else { // seleciona SE
                h += (int) dSE;
                dE += 2;
                dSE += 4;
                y--;
            }
            x++;
            //espelharCircBresenham(g2d, xx1, yy1, x, y);
        }
    }

    private void desenharRetaParametrica(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.MAGENTA);

        for (double t = 0; t <= 1; t += 0.01) {
            double x = xx1 + (xx2 - xx1) * t;
            double y = yy1 + (yy2 - yy1) * t;
            g2d.fillRect((int) x, (int) y, 1, 1);
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = buffer.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void drawCircle(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        if (controlador.getCor() != null) {
            g2d.setColor(controlador.getCor());
        }
        else
            g2d.setColor(Color.RED);

        int radius = (int) Math.abs(Math.round(Math.sqrt((yy2 - yy1) * (yy2 - yy1) + (xx2 - xx1) * (xx2 - xx1))));
        int x = 0;
        int y = radius;
        int d = 3 - 2 * radius;

        while (x <= y) {
            espelharCircBresenham(g2d, xx1, yy1, x, y);
            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    public void inicializaPontoEAresta() {
        double[][] pontos = {
                {100, 300, 100, 1}, {100, 300, -100, 1}, {300, 300, 100, 1}, {300, 300, -100, 1}, {100, 100, 100, 1},
                {100, 100, -100, 1}, {300, 100, 100, 1}, {300, 100, -100, 1}, {200, 50, 100, 1}, {200, 50, -100, 1}
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

        double[][] matrizTransformacao = {
                {1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}
        };

        matrizDeProjecao = matrizTransformacao;

        for (int aux = 0; aux < 10; aux++) {
            double[] resultado = new double[10];
            multiplicarMatriz(pontosTotal[aux], matrizTransformacao, resultado);
            System.arraycopy(resultado, 0, resultadoPrintado[aux], 0, 4);
        }

        for (int aux = 0; aux < 17; aux++) {
            int xx1 = (int) Math.round(resultadoPrintado[arestas[aux][0] - 1][0]);
            int yy1 = (int) Math.round(resultadoPrintado[arestas[aux][0] - 1][1]);
            int xx2 = (int) Math.round(resultadoPrintado[arestas[aux][1] - 1][0]);
            int yy2 = (int) Math.round(resultadoPrintado[arestas[aux][1] - 1][1]);
            // chame a função para desenhar a linha com pontos (xx1, yy1) e (xx2, yy2)
            desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
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

    public void rotacaoCentroObjeto(String eixo, double grs) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizTransformacao = new double[4][4];
        double[] resultado = new double[4];
        double somax, somay, somaz;
        int contador;
        double graus;

        limparBuffer();
        graus = (grs * Math.PI) / 180;

        somax = 0;
        somay = 0;
        somaz = 0;
        for (contador = 0; contador < 10; contador++) {
            somax += pontosTotal[contador][0];
            somay += pontosTotal[contador][1];
            somaz += pontosTotal[contador][2];
        }
        somax /= 10.0;
        somay /= 10.0;
        somaz /= 10.0;

        translacao(-somax, -somay, -somaz);

        if (eixo.equalsIgnoreCase("x")) {
            matrizTransformacao[0][0] = 1;
            matrizTransformacao[1][1] = Math.cos(graus);
            matrizTransformacao[1][2] = -Math.sin(graus);
            matrizTransformacao[2][1] = Math.sin(graus);
            matrizTransformacao[2][2] = Math.cos(graus);
            matrizTransformacao[3][3] = 1;
        } else if (eixo.equalsIgnoreCase("y")) {
            matrizTransformacao[0][0] = Math.cos(graus);
            matrizTransformacao[0][2] = Math.sin(graus);
            matrizTransformacao[1][1] = 1;
            matrizTransformacao[2][0] = -Math.sin(graus);
            matrizTransformacao[2][2] = Math.cos(graus);
            matrizTransformacao[3][3] = 1;
        } else if (eixo.equalsIgnoreCase("z")) {
            matrizTransformacao[0][0] = Math.cos(graus);
            matrizTransformacao[0][1] = -Math.sin(graus);
            matrizTransformacao[1][0] = Math.sin(graus);
            matrizTransformacao[1][1] = Math.cos(graus);
            matrizTransformacao[2][2] = 1;
            matrizTransformacao[3][3] = 1;
        }

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizTransformacao, resultado);
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
            desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }
    }

    public static void salvarLog(int cont, int xx1, int yy1, int xx2, int yy2, double[] resultado) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));

            writer.write("cont: " +cont+ "\n");
            writer.write("xx1: " + xx1 + "\n");
            writer.write("yy1: " + yy1 + "\n");
            writer.write("xx2: " + xx2 + "\n");
            writer.write("yy2: " + yy2 + "\n");

            writer.write("Resultados:\n");
            for (int i = 0; i < resultado.length; i++) {
                writer.write("Resultado[" + i + "]: " + resultado[i] + "\n");
            }

            writer.close();
            //System.out.println("Log salvo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o log: " + e.getMessage());
        }
    }

    public void translacao(double x, double y, double z) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizTransformacao = new double[4][4];
        double[] resultado = new double[4];
        double[][] pontosTemp = new double[10][4]; // Crie uma cópia dos pontos originais


        // Copie os pontos originais para a matriz temporária
        for (aux = 0; aux < 10; aux++) {
            System.arraycopy(pontosTotal[aux], 0, pontosTemp[aux], 0, 4);
        }
        limparBuffer();

        matrizTransformacao[0][0] = 1; matrizTransformacao[0][1] = 0; matrizTransformacao[0][2] = 0; matrizTransformacao[0][3] = 0;
        matrizTransformacao[1][0] = 0; matrizTransformacao[1][1] = 1; matrizTransformacao[1][2] = 0; matrizTransformacao[1][3] = 0;
        matrizTransformacao[2][0] = 0; matrizTransformacao[2][1] = 0; matrizTransformacao[2][2] = 1; matrizTransformacao[2][3] = 0;
        matrizTransformacao[3][0] = x; matrizTransformacao[3][1] = y; matrizTransformacao[3][2] = z; matrizTransformacao[3][3] = 1;

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTemp[aux], matrizTransformacao, resultado);
            pontosTemp[aux] = resultado.clone();
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
            desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
            //salvarLog(cont, xx1, yy1, xx2, yy2, resultado);
            //cont++;
        }

        for (aux = 0; aux < 10; aux++) {
            System.arraycopy(pontosTemp[aux], 0, pontosTotal[aux], 0, 4);
        }
    }

    public void escalas(double x, double y, double z, double global) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizTransformacao = new double[4][4];
        double[] resultado = new double[4];

        limparBuffer();

        matrizTransformacao[0][0] = x; matrizTransformacao[0][1] = 0; matrizTransformacao[0][2] = 0; matrizTransformacao[0][3] = 0;
        matrizTransformacao[1][0] = 0; matrizTransformacao[1][1] = y; matrizTransformacao[1][2] = 0; matrizTransformacao[1][3] = 0;
        matrizTransformacao[2][0] = 0; matrizTransformacao[2][1] = 0; matrizTransformacao[2][2] = z; matrizTransformacao[2][3] = 0;
        matrizTransformacao[3][0] = 0; matrizTransformacao[3][1] = 0; matrizTransformacao[3][2] = 0; matrizTransformacao[3][3] = global;


        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizTransformacao, resultado);
            resultado[0] /= resultado[3];
            resultado[1] /= resultado[3];
            resultado[2] /= resultado[3];
            resultado[3] /= resultado[3];
            pontosTotal[aux] = resultado.clone();
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
            desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }
    }

    public void shearing(ArrayList<Double> valores) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizTransformacao = new double[4][4];
        double[] resultado = new double[4];

        limparBuffer();

        matrizTransformacao[0][0] = valores.get(0); // A
        matrizTransformacao[0][1] = valores.get(1); // B
        matrizTransformacao[0][2] = valores.get(2); // C
        matrizTransformacao[0][3] = 1;              // D

        matrizTransformacao[1][0] = valores.get(3); // E
        matrizTransformacao[1][1] = valores.get(4); // F
        matrizTransformacao[1][2] = valores.get(5); // G
        matrizTransformacao[1][3] = 1;              // H

        matrizTransformacao[2][0] = valores.get(6); // I
        matrizTransformacao[2][1] = valores.get(7); // J
        matrizTransformacao[2][2] = valores.get(8); // K
        matrizTransformacao[2][3] = 1;              // L

        matrizTransformacao[3][0] = 1; // M
        matrizTransformacao[3][1] = 1; // N
        matrizTransformacao[3][2] = 1; // O
        matrizTransformacao[3][3] = 1; // P

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizTransformacao, resultado);
            pontosTotal[aux] = resultado.clone();
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
            desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }

    }

    public void rotacaoOrigem(String eixo, double grs) {

        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizTransformacao = new double[4][4];
        double[] resultado = new double[4];
        double graus;

        limparBuffer();

        graus = (grs * Math.PI) / 180;


        switch (eixo) {
            case "x" -> {
                matrizTransformacao[0][0] = 1;
                matrizTransformacao[1][1] = Math.cos(graus);
                matrizTransformacao[1][2] = -Math.sin(graus);
                matrizTransformacao[2][1] = Math.sin(graus);
                matrizTransformacao[2][2] = Math.cos(graus);
                matrizTransformacao[3][3] = 1;
            }
            case "y" -> {
                matrizTransformacao[0][0] = Math.cos(graus);
                matrizTransformacao[0][2] = Math.sin(graus);
                matrizTransformacao[1][1] = 1;
                matrizTransformacao[2][0] = -Math.sin(graus);
                matrizTransformacao[2][2] = Math.cos(graus);
                matrizTransformacao[3][3] = 1;
            }
            case "z" -> {
                matrizTransformacao[0][0] = Math.cos(graus);
                matrizTransformacao[0][1] = -Math.sin(graus);
                matrizTransformacao[1][0] = Math.sin(graus);
                matrizTransformacao[1][1] = Math.cos(graus);
                matrizTransformacao[2][2] = 1;
                matrizTransformacao[3][3] = 1;
            }
        }

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizTransformacao, resultado);
            pontosTotal[aux] = resultado.clone();
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
            desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
            //salvarLog(cont, xx1, yy1, xx2, yy2, resultado);
        }

    }

    public BufferedImage imagemNegativa(BufferedImage imagem) {
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        BufferedImage novaImagem = imagem;

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int p = imagem.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // subtract RGB from 255
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                // set new RGB value
                p = (a << 24) | (r << 16) | (g << 8) | b;
                novaImagem.setRGB(x, y, p);
            }
        }
        return novaImagem;
    }

    public BufferedImage imagemGreyscale(BufferedImage imagem) {
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        BufferedImage novaImagem = imagem;

        int[] pixels = imagem.getRGB(0, 0, largura, altura, null, 0, largura);
        // convert to grayscale
        for (int i = 0; i < pixels.length; i++) {

            // Here i denotes the index of array of pixels
            // for modifying the pixel value.
            int p = pixels[i];

            int a = (p >> 24) & 0xff;
            int r = (p >> 16) & 0xff;
            int g = (p >> 8) & 0xff;
            int b = p & 0xff;

            // calculate average
            int media = (r + g + b) / 3;

            // replace RGB value with avg
            p = (a << 24) | (media << 16) | (media << 8) | media;

            pixels[i] = p;
        }
        novaImagem.setRGB(0, 0, largura, altura, pixels, 0, largura);

        return novaImagem;
    }
}
