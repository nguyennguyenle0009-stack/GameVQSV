package game.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import game.screen.GameScreen;

public class MenuScreen extends GameScreen {
    private String[] menuOptions = {"Bắt đầu", "Tiếp tục", "Cài đặt", "Thoát"};
    private int selectedIndex = 0;

    @Override
    public void update() {
        // TODO: Thêm xử lý input ở đây
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 240, 320);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Vương Quốc Sủng Vật", 30, 50);

        for (int i = 0; i < menuOptions.length; i++) {
            if (i == selectedIndex) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.drawString(menuOptions[i], 80, 100 + i * 30);
        }
    }
}