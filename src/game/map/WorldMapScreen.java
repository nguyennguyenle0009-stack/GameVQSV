package game.map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.screen.GameScreen;

public class WorldMapScreen extends GameScreen {
    private BufferedImage background;

    public WorldMapScreen() {
        try {
            background = ImageIO.read(getClass().getResource("/data/logo/0.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("[WorldMapScreen] Không thể tải nền bản đồ: " + e.getMessage());
            background = null;
        }
    }

    @Override
    public void update() {
        // TODO: xử lý di chuyển trên bản đồ hoặc chọn màn chơi
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 240, 320);

        if (background != null) {
            g.drawImage(background, 0, 0, 240, 320, null);
        } else {
            g.setColor(Color.RED);
            g.drawString("Không có ảnh nền!", 60, 160);
        }

        g.setColor(Color.WHITE);
        g.drawString("Bản đồ thế giới", 70, 20);
        g.drawRect(100, 100, 40, 40); // Ô chọn màn
        g.drawString("Vào ải", 105, 125);
    }
}
