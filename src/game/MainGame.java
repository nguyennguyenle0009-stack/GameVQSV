package game;

import javax.swing.*;

public class MainGame {
    public static void main(String[] args) {
        JFrame window = new JFrame("Vuong Quoc Sung Vat");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameLoop();
    }
}
