package game.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.sprite.PlayerSpriteBuilder;

//Lớp Player dùng lại logic cũ từ GameObject: tải sprite từ spriteSetId và vẽ nhân vật
public class Player {
    private int x, y;
    private int speed = 2;
    private Direction direction = Direction.DOWN;
    private Image[][] spriteFrames; // chứa từng frame animation theo hướng
    private int frameIndex = 0;
    private int frameDelay = 5, delayCounter = 0;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.spriteFrames = PlayerSpriteBuilder.loadSpriteSet("/data/player/img_100.png", 24, 32); // width x height mỗi frame

        // Debug nếu không load được ảnh
        if (spriteFrames == null || spriteFrames.length == 0 || spriteFrames[0][0] == null) {
            System.err.println("[Player] Không load được spriteFrames!");
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
        delayCounter++;
        if (delayCounter >= frameDelay) {
            frameIndex = (frameIndex + 1) % 3;
            delayCounter = 0;
        }
    }

    public void render(Graphics g) {
        int dirIndex = switch (direction) {
            case UP -> 0;
            case DOWN -> 1;
            case LEFT -> 2;
            case RIGHT -> 3;
        };

        try {
            Image frame = spriteFrames[dirIndex][frameIndex];
            if (frame != null) {
                g.drawImage(frame, x, y, null);
            } else {
                g.setColor(java.awt.Color.RED);
                g.fillRect(x, y, 24, 32);
                System.err.println("[Player] Thiếu frame ảnh tại dir=" + dirIndex + ", frame=" + frameIndex);
            }
        } catch (Exception e) {
            System.err.println("[Player] Lỗi khi vẽ nhân vật: " + e.getMessage());
            g.setColor(java.awt.Color.RED);
            g.fillRect(x, y, 24, 32);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 24, 32);
    }
}
