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
    private Image[][] spriteFrames; // chứa từng frame animation theo hướng
    private int frameIndex = 0;
    private int frameDelay = 5, delayCounter = 0;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        loadSpriteSet();
    }

    // Tải sprite nhân vật từ các ảnh chia theo hướng và frame (ví dụ: hero_down_0.png)
    private void loadSpriteSet() {
        spriteFrames = new Image[4][3]; // 4 hướng x 3 frame
        try {
            // Load toàn bộ sprite sheet (giả đuôi .mid nhưng thực chất là PNG)
            Image sheet = ImageIO.read(getClass().getResource("/data/player/img_100.png")); // đã đổi tên

            int frameWidth = 24; // ví dụ: mỗi frame 24x32
            int frameHeight = 32;

            for (int dir = 0; dir < 4; dir++) {
                for (int f = 0; f < 3; f++) {
                    spriteFrames[dir][f] = ((java.awt.image.BufferedImage) sheet)
                        .getSubimage(f * frameWidth, dir * frameHeight, frameWidth, frameHeight);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi tải sprite sheet: " + e.getMessage());
        }
    }
//    private void loadSpriteSet() {
//        spriteFrames = new Image[4][3]; // 4 hướng, mỗi hướng 3 frame
//        String[] dirs = {"up", "down", "left", "right"};
//
//        for (int d = 0; d < 4; d++) {
//            for (int f = 0; f < 3; f++) {
//                try {
//                    spriteFrames[d][f] = ImageIO.read(getClass().getResource("/data/player/img_100" + dirs[d] + "_" + f + ".mid"));
//                } catch (IOException | IllegalArgumentException e) {
//                    System.err.println("[Player] Failed to load frame: " + dirs[d] + "_" + f);
//                }
//            }
//        }
//    }

    // Di chuyển theo hướng nhất định
    public void move(Direction dir) {
        this.direction = dir;
        switch (dir) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }
    }

    // Cập nhật frame animation
    public void update() {
        delayCounter++;
        if (delayCounter >= frameDelay) {
            frameIndex = (frameIndex + 1) % 3;
            delayCounter = 0;
        }
    }

    // Vẽ nhân vật
    public void render(Graphics g) {
        int dirIndex = switch (direction) {
            case UP -> 0;
            case DOWN -> 1;
            case LEFT -> 2;
            case RIGHT -> 3;
        };

        Image frame = spriteFrames[dirIndex][frameIndex];
        if (frame != null) {
            g.drawImage(frame, x, y, null);
        } else {
            g.fillRect(x, y, 16, 16); // fallback nếu thiếu ảnh
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}