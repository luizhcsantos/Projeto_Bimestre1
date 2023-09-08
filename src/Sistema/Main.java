package Sistema;

import GUI.JFramePrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFramePrincipal frame = new JFramePrincipal();
            frame.setVisible(true);
        });
    }
}