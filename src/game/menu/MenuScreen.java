package game.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.screen.GameScreen;
import game.screen.GameScreenManager;
import game.state.GameState;

public class MenuScreen extends GameScreen {
    private int selectedIndex = 0;
    private final String[] options = { "Vào bản đồ", "Thoát" };

    @Override
    public void update() {
        // Logic xử lý menu có thể mở rộng sau
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 240, 320);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Menu Chính", 70, 60);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        for (int i = 0; i < options.length; i++) {
            if (i == selectedIndex) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], 80, 120 + i * 30);
        }
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            selectedIndex = (selectedIndex - 1 + options.length) % options.length;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            selectedIndex = (selectedIndex + 1) % options.length;
        } else if (keyCode == KeyEvent.VK_ENTER) {
            if (selectedIndex == 0) {
                GameScreenManager.getInstance().changeState(GameState.STATE_WORLD_MAP);
            } else if (selectedIndex == 1) {
                System.exit(0);
            }
        }
    }
}
