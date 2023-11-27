import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class EdgeFill extends JFrame {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    private final BufferedImage baseImage;

    private final int[][] edges = {
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

    public EdgeFill() {
        setTitle("Edge Fill");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        baseImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = baseImage.createGraphics();
        g2d.setColor(new Color(250, 218, 94));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        g2d.dispose();

        JButton fillButton = new JButton("Fill");
        fillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fill();
                repaint(); // Atualiza a interface após o preenchimento
            }
        });

        setLayout(new BorderLayout());
        add(fillButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int[] edge : edges) {
            int x1 = edge[0];
            int y1 = edge[1];
            int x2 = edge[2];
            int y2 = edge[3];

            g2d.draw(new Line2D.Double(x1, y1, x2, y2));

        }
    }

    public void fill() {
        for (int[] edge : edges) {
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
                    int rgb = baseImage.getRGB(i, y);
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;
                    baseImage.setRGB(i, y, new Color(255 - r, 255 - g, 255 - b, 255).getRGB());

                }
            }
        }
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EdgeFill edgeFill = new EdgeFill();
        });
    }
}
