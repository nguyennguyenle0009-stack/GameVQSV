package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

class GamePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 240;
    private final int HEIGHT = 320;
    private Thread gameThread;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    public void startGameLoop() {
        gameThread = new Thread(() -> {
            while (true) {
                update();
                repaint();
                try {
                    Thread.sleep(16); // ~60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameThread.start();
    }

    private void update() {
        // TODO: Chuyển logic update từ GameCanvas.java ở đây
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO: Chuyển logic vẽ từ GameCanvas.paint() hoặc tương đương
    }
}