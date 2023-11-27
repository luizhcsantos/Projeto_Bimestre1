package gui;

import Sistema.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EdgeFillPanel extends JPanel {

    private final Controlador controlador;

    public EdgeFillPanel(Controlador controlador) {
        init();
        this.controlador = controlador;
    }

    public void init() {
        setPreferredSize(new Dimension(280, 280));

        JButton jButtonPreencer = new JButton();
        add(jButtonPreencer);
        jButtonPreencer.setText("Preencher");

        jButtonPreencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.edgefill();
            }
        });
    }
}
