package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -1753912953142514844L;
    private BufferedImage image;
    private BufferedImage buffer;
    private Graphics graphics;
    private int xx1, xx2, yy1, yy2;
    private boolean drawing = false;

    public enum DrawingMethod {
        BRESENHAM,
        SIMPLES,
        CPARAMETRICA,
        CIMPLICITA
    }
    private DrawingMethod drawingMethod;

    public ImagePanel() {
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        //graphics = image.getGraphics();

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
                if (drawingMethod == DrawingMethod.BRESENHAM) {
                    drawBresenhamLine(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                } else if (drawingMethod == DrawingMethod.SIMPLES) {
                    desenharReta(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if (drawingMethod == DrawingMethod.CPARAMETRICA) {
                    circunferenciaParametrica(buffer.getGraphics(), xx1, yy1, xx2, yy2);
                }else if (drawingMethod == DrawingMethod.CIMPLICITA) {
                    circunferenciaImplicita(buffer.getGraphics(), xx1, yy1, xx2, yy2);
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


    public void setDrawingMethod(DrawingMethod drawingMethod) {
        this.drawingMethod = drawingMethod;
        clearBuffer(); // Limpa o buffer ao alterar o modo de desenho
    }

    private void clearBuffer() {
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


    private void desenharReta(Graphics g, int x1, int y1, int x2, int y2) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        double m = (double) (y2 - y1) / (x2 - x1);

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        if (dx > dy) {
            if (x1 < x2) {
                for (int xi = x1; xi <= x2; xi++) {
                    int yi = (int) Math.round(m * (xi - x1) + y1);
                    g.fillRect(xi, yi, 1, 1);
                }
            } else {
                for (int xi = x2; xi <= x1; xi++) {
                    int yi = (int) Math.round(m * (xi - x1) + y1);
                    g.fillRect(xi, yi, 1, 1);
                }
            }
        } else {
            if (y1 < y2) {
                for (int yi = y1; yi <= y2; yi++) {
                    int xi = (int) Math.round((yi - y1) / m + x1);
                    g.fillRect(xi, yi, 1, 1);
                }
            } else {
                for (int yi = y2; yi <= y1; yi++) {
                    int xi = (int) Math.round((yi - y1) / m + x1);
                    g.fillRect(xi, yi, 1, 1);
                }
            }
        }

        repaint();
    }

    private void drawBresenhamLine(Graphics g, int xx1, int yy1, int xx2, int yy2) {

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

        g.setColor(Color.GREEN);
        g.fillRect(ax, ay, 1, 1);

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
            g.fillRect(ax, ay, 1, 1);
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

    private void circunferenciaParametrica(Graphics g, int xx1, int yy1, int xx2, int yy2) {    // desenha circunferência usando equação paramétrica

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
        step = 2 * Math.PI / 200;
        // número de pixels que serão pintados;
        // valores maiores resultarão em mais pixels pintados
        // e, consequentemente, menos "buracos" na circunferencia
        do {
            xr = (float) (xc + (raio * Math.cos(ang)));
            yr = (float) (yc + (raio * Math.sin(ang)));

            g.setColor(Color.GREEN);
            g.fillRect((int) xr, (int) yr, 1, 1);
            //graphics.drawOval(Math.round(xr), Math.round(yr), 1, 1);

            ang += (float) step;
        } while (ang < 2* Math.PI);
    }

    private void circunferenciaImplicita(Graphics g, int xx1, int yy1, int xx2, int yy2) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);

        int xc;
        int yc;
        int raio;

        raio = (int) Math.abs(Math.round(Math.sqrt(( Math.pow((yy2 - yy1), 2)) + (Math.pow((xx2 - xx1), 2)))));

        for (xc=-raio; xc< raio; xc++) {
            yc = (int) Math.sqrt((Math.pow(raio, 2)) - (Math.pow(xc, 2)));
            g.setColor(Color.GREEN);
            g.fillRect(xc, yc, 1, 1);
            g.fillRect(xc, -yc, 1, 1);

        }

//        do  {
//            yc = (int) Math.sqrt((Math.pow(raio, 2)) - (Math.pow(xc, 2)));
//            graphics.setColor(Color.GREEN);
//            graphics.drawOval(xc, yc, 1, 1);
//            graphics.drawOval(xc, -yc, 1, 1);
//            inc = (1 /(6.28 * raio))*100;
//            xc += (int) inc;
//        } while(xc != yc);
    }

    public BufferedImage getImage() {
        return image;
    }
}
