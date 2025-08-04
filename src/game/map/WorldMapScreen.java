package game.map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.GamePanel;
import game.entity.Player;
import game.screen.GameScreen;

public class WorldMapScreen extends GameScreen {

    private Player player;

    public WorldMapScreen() {
        player = new Player(100, 100); // khởi tạo nhân vật tại vị trí ban đầu
    }

    // Hàm update() được gọi mỗi frame để cập nhật trạng thái của màn chơi
    @Override
    public void update() {
        player.update();
    }

    // Hàm render(Graphics) được gọi mỗi frame để vẽ mọi thứ lên màn hình
    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN); // tô nền cho bản đồ thế giới
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        player.render(g); // vẽ nhân vật người chơi
    }

    // Hàm xử lý phím bấm từ người chơi (di chuyển nhân vật)
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case 37 -> player.move(Player.Direction.LEFT);   // Left arrow
            case 38 -> player.move(Player.Direction.UP);     // Up arrow
            case 39 -> player.move(Player.Direction.RIGHT);  // Right arrow
            case 40 -> player.move(Player.Direction.DOWN);   // Down arrow
        }
    }
}
