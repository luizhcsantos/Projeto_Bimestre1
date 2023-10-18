package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.ArrayList;

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

    private double[][] pontosTotal = new double[10][4];
    private int[][] arestas = new int[17][2];
    private double[][] resultadoPrintado = new double[10][4];
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

    public ImagePanel() {

        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        JFramePrincipal.controlador.setBuffer(buffer);
        setBackground(Color.BLACK);

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
                    desenharCircBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if (drawingMethod == DrawingMethod.RETA) {
                    desenharRetaSimples(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if  (drawingMethod == DrawingMethod.PIXEL) {
                    desenharPixel(buffer.getGraphics(), xx1, yy1);
                }
                repaint();
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

        if (image != null) {
            g.drawImage(buffer, 0, 0, this);
        }
    }

    private void desenharRetaSimples(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.drawLine(xx1, yy1, xx2, yy2);
        repaint();
    }

    private void desenharPixel(Graphics g, int xx1, int yy1) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.drawRect(xx1, yy1, 1, 1);
        repaint();
    }

    private void desenharReta(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
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

    public static void desenharRetaBresenham(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
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
        } else {
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

        g2d.setColor(Color.GREEN);
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

            g2d.setColor(Color.GREEN);
            g2d.fillRect((int) xr, (int) yr, 1, 1);

            ang += (float) step;
        } while (ang < 2* Math.PI);
    }

    private void desenharCircImplicita(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
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

    private static void espelharCircBresenham(Graphics g, int xx1, int yy1, int x, int y) {

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

    private static void desenharCircBresenham(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
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

    private static void desenharRetaParametrica(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
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

    public static void drawCircle(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
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

    public static BufferedImage getBuffer() {
        return buffer;
    }

    public void inicializaPontoEAresta() {
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
        inicializaPontoEAresta();

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


    public void rotacaoCentroObjeto(String eixo, double grs) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizT = new double[4][4];
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
        somax /= 10;
        somay /= 10;
        somaz /= 10;

        translacao(-somax, -somay, -somaz);

        if (eixo.equalsIgnoreCase("x")) {
            matrizT[0][0] = 1;
            matrizT[1][1] = Math.cos(graus);
            matrizT[1][2] = -Math.sin(graus);
            matrizT[2][1] = Math.sin(graus);
            matrizT[2][2] = Math.cos(graus);
            matrizT[3][3] = 1;
        } else if (eixo.equalsIgnoreCase("y")) {
            matrizT[0][0] = Math.cos(graus);
            matrizT[0][2] = Math.sin(graus);
            matrizT[1][1] = 1;
            matrizT[2][0] = -Math.sin(graus);
            matrizT[2][2] = Math.cos(graus);
            matrizT[3][3] = 1;
        } else if (eixo.equalsIgnoreCase("z")) {
            matrizT[0][0] = Math.cos(graus);
            matrizT[0][1] = -Math.sin(graus);
            matrizT[1][0] = Math.sin(graus);
            matrizT[1][1] = Math.cos(graus);
            matrizT[2][2] = 1;
            matrizT[3][3] = 1;
        }

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizT, resultado);
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

    public void translacao(double x, double y, double z) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizT = new double[4][4];
        double[] resultado = new double[4];

        limparBuffer();

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

    public void escalas(double x, double y, double z, double global) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizT = new double[4][4];
        double[] resultado = new double[4];

        limparBuffer();

        matrizT[0][0] = x;
        matrizT[0][1] = 0;
        matrizT[0][2] = 0;
        matrizT[0][3] = 0;

        matrizT[1][0] = 0;
        matrizT[1][1] = y;
        matrizT[1][2] = 0;
        matrizT[1][3] = 0;

        matrizT[2][0] = 0;
        matrizT[2][1] = 0;
        matrizT[2][2] = z;
        matrizT[2][3] = 0;

        matrizT[3][0] = 0;
        matrizT[3][1] = 0;
        matrizT[3][2] = 0;
        matrizT[3][3] = global;

        for (aux = 1; aux <= 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizT, resultado);
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
            ImagePanel.desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }
    }

    public void shearing(ArrayList<Double> valores) {
        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizT = new double[4][4];
        double[] resultado = new double[4];

        limparBuffer();

        matrizT[0][0] = valores.get(0); // A
        matrizT[0][1] = valores.get(1); // B
        matrizT[0][2] = valores.get(2); // C
        matrizT[0][3] = 1;                                           // D

        matrizT[1][0] = valores.get(3); // E
        matrizT[1][1] = valores.get(4); // F
        matrizT[1][2] = valores.get(5); // G
        matrizT[1][3] = 1;                                           // H

        matrizT[2][0] = valores.get(6); // I
        matrizT[2][1] = valores.get(7); // J
        matrizT[2][2] = valores.get(8); // K
        matrizT[2][3] = 1;                                           // L

        matrizT[3][0] = 1; // M
        matrizT[3][1] = 1; // N
        matrizT[3][2] = 1; // O
        matrizT[3][3] = 1; // P

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizT, resultado);
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
            ImagePanel.desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }

    }
    
    public void rotacaoOrigem(String eixo, double grs) {

        int aux;
        int xx1, yy1, xx2, yy2;
        double[][] matrizT = new double[4][4];
        double[] resultado = new double[4];
        double graus;

        limparBuffer();

        graus = (grs * Math.PI) / 180;


        if (eixo.equals("x")) {
            matrizT[0][0] = 1;
            matrizT[1][1] = Math.cos(graus);
            matrizT[1][2] = -Math.sin(graus);
            matrizT[2][1] = Math.sin(graus);
            matrizT[2][2] = Math.cos(graus);
            matrizT[3][3] = 1;
        } else if (eixo.equals("y")) {
            matrizT[0][0] = Math.cos(graus);
            matrizT[0][2] = Math.sin(graus);
            matrizT[1][1] = 1;
            matrizT[2][0] = -Math.sin(graus);
            matrizT[2][2] = Math.cos(graus);
            matrizT[3][3] = 1;
        } else if (eixo.equals("z")) {
            matrizT[0][0] = Math.cos(graus);
            matrizT[0][1] = -Math.sin(graus);
            matrizT[1][0] = Math.sin(graus);
            matrizT[1][1] = Math.cos(graus);
            matrizT[2][2] = 1;
            matrizT[3][3] = 1;
        }

        for (aux = 0; aux < 10; aux++) {
            multiplicarMatriz(pontosTotal[aux], matrizT, resultado);
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
            ImagePanel.desenharRetaBresenham(buffer.getGraphics(), xx1, yy1, xx2, yy2);
        }

    }

}
