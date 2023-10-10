package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serial;

public class ImagePanel extends JPanel {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -1753912953142514844L;
    private transient BufferedImage image;
    private transient BufferedImage buffer;
    private int xx1;
    private int xx2;
    private int yy1;
    private int yy2;
    private boolean drawing = false;


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

    private void limparBuffer() {
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

    private void desenharRetaBresenham(Graphics g, int xx1, int yy1, int xx2, int yy2) {

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

    void setImage(BufferedImage image) {
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

        int radius = (int) Math.abs(Math.round(Math.sqrt((yy2 - yy1)*(yy2 - yy1) + (xx2 - xx1)*(xx2 - xx1))));
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


}
