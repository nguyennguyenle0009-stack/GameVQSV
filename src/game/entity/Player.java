package game.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
    private int x, y;
    private int speed = 2;
    private Direction direction = Direction.DOWN;
    private Image spriteSheet;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        try {
            spriteSheet = ImageIO.read(getClass().getResource("/res/hero.png"));
        } catch (IOException e) {
            System.err.println("[Player] Failed to load sprite.");
        }
    }

    public void move(Direction dir) {
        this.direction = dir;
        switch (dir) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }
    }

    public void update() {
        // Future animation or state update
    }

    public void render(Graphics g) {
        if (spriteSheet != null) {
            g.drawImage(spriteSheet, x, y, null);
        } else {
            g.fillRect(x, y, 16, 16);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
} 
