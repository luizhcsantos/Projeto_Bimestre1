package Sistema;

import gui.ImagePanel;
import gui.RgbHslPanel;
import gui.TransformacoesPanel;

import java.awt.image.BufferedImage;

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
}
