package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.screen.GameScreenManager;
import game.state.GameState;

public class GamePanel extends JPanel {
    public static final int WIDTH = 240;
    public static final int HEIGHT = 320;

    private Thread gameThread;
    private boolean running;

    private BufferedImage image;
    private Graphics g;

    private final GameController controller;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        controller = GameScreenManager.getInstance();
        GameScreenManager.getInstance().changeState(GameState.STATE_WORLD_MAP);
        addKeyListener(new KeyHandler());
    }

    public void startGameLoop() {
        if (gameThread == null) {
            gameThread = new Thread(() -> runGameLoop());
            gameThread.start();
        }
    }

    private void runGameLoop() {
        init();

        final int FPS = 60;
        final long targetTime = 1000 / FPS;

        while (running) {
            long start = System.nanoTime();

            update();
            render();
            draw();

            long elapsed = System.nanoTime() - start;
            long wait = targetTime - elapsed / 1000000;
            if (wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            } catch (Exception ignored) {}
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        running = true;
    }

    private void update() {
        controller.update();
    }

    private void render() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (controller instanceof GameScreenManager gsm) {
            gsm.render(g);
        }
    }

    private void draw() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }
    
    public void keyPressed(KeyEvent e) {
        GameScreenManager.getInstance().keyPressed(e.getKeyCode());
    }
}