package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

class GamePanel extends JPanel implements Runnable {
    private final int WIDTH = 240;
    private final int HEIGHT = 320;
    private Thread gameThread;

    private long frameStartTime = 0L;
    private long frameEndTime = 0L;
    private long frameElapsedTime = 0L;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            frameStartTime = System.currentTimeMillis();

            update();
            repaint();

            frameEndTime = System.currentTimeMillis();
            frameElapsedTime = frameEndTime - frameStartTime;

            try {
                Thread.sleep(Math.max(0, 16 - frameElapsedTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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