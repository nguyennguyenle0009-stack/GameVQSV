package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.menu.MenuScreen;
import game.screen.GameScreen;
import game.screen.GameScreenManager;

public class KeyHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        GameScreenManager gsm = GameScreenManager.getInstance();
        GameScreen currentScreen = gsm != null ? gsm.peekCurrentScreen() : null;

        // Tuỳ màn hình có thể xử lý phím khác nhau
        if (currentScreen instanceof MenuScreen menu) {
            menu.keyPressed(keyCode);
        }
        // TODO: Thêm phím cho WorldMap, Battle...
    }
}
