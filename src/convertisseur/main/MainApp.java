package convertisseur.main;

import convertisseur.ui.ConvertisseurFrame;

public class MainApp {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new ConvertisseurFrame();
        });

    }
}