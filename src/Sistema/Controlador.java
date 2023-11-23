package Sistema;

import gui.ImagePanel;
import gui.RgbHslPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Controlador {

    public ImagePanel imagePanel;
    public RgbHslPanel rgbHslPanel;
    public BufferedImage buffer;
    public Color cor = null;

    public Controlador() {
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public void setImagePanel(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public void setRgbHslPanel(RgbHslPanel rgbHslPanel) {
        this.rgbHslPanel = rgbHslPanel;
    }

    public void limparBuffer() {
        imagePanel.limparBuffer();
    }
    public void desenhaCasinha() {
        imagePanel.desenhaCasinha();
        imagePanel.repaint();
    }

    public void desenhaTranslacao(double x, double y, double z) {
        imagePanel.translacao(x, y, z);
        imagePanel.repaint();
    }

    public void resetaCasinha() {
        imagePanel.inicializaPontoEAresta();
        imagePanel.limparBuffer();
        imagePanel.repaint();
    }

    public void desenhaRotacaoCentro(String eixo, double graus) {
        imagePanel.rotacaoCentroObjeto(eixo, graus);
        imagePanel.repaint();
    }

    public void desenhaRotacaoOrigem(String eixo, double graus) {
        imagePanel.rotacaoOrigem(eixo, graus);
        imagePanel.repaint();
    }

    public void desenhaEscalas(double x, double y, double z, double global) {
        imagePanel.escalas(x, y, z, global);
        imagePanel.repaint();
    }

    public void desenhaShearing(ArrayList<Double> valores) {
        imagePanel.shearing(valores);
        imagePanel.repaint();
    }

    public void setCor(Color color) {
        this.cor = color;
    }

    public Color getCor() {
        return cor;
    }

    public void imagemNevativa(BufferedImage image) {
        BufferedImage bufferedImage = imagePanel.imagemNegativa(image);
        imagePanel.setImage(bufferedImage);
    }

    public void imagemGreyscale(BufferedImage image) {
        BufferedImage bufferedImage = imagePanel.imagemGreyscale(image);
        imagePanel.setImage(bufferedImage);
    }
}
