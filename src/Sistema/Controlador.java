package Sistema;

import gui.ImagePanel;
import gui.RgbHslPanel;
import gui.TransformacoesPanel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Controlador {

    public ImagePanel imagePanel;
    public RgbHslPanel rgbHslPanel;
    public TransformacoesPanel transformacoesPanel;

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public BufferedImage buffer;


    public Controlador() {
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public RgbHslPanel getRgbHslPanel() {
        return rgbHslPanel;
    }

    public void setRgbHslPanel(RgbHslPanel rgbHslPanel) {
        this.rgbHslPanel = rgbHslPanel;
    }

    public TransformacoesPanel getTransformacoesPanel() {
        return transformacoesPanel;
    }

    public void setTransformacoesPanel(TransformacoesPanel transformacoesPanel) {
        this.transformacoesPanel = transformacoesPanel;
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

}
