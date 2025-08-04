package game.screen;

import java.awt.Graphics;

import game.GameController;
import game.map.WorldMapScreen;
import game.menu.MenuScreen;
import game.state.GameState;

public class GameScreenManager implements GameController {
    private static GameScreenManager instance;

    private byte currentState;
    private byte previousState;
    private GameScreen currentScreen;

    private MenuScreen menuScreen;
    private WorldMapScreen worldMapScreen;
    // TODO: Thêm các màn khác như BattleScreen...

    private GameScreenManager() {
        this.currentState = GameState.STATE_MENU;
        this.previousState = -1;
        this.menuScreen = new MenuScreen();
        this.worldMapScreen = new WorldMapScreen();
        this.currentScreen = menuScreen;
    }

    public static GameScreenManager getInstance() {
        if (instance == null) {
            instance = new GameScreenManager();
        }
        return instance;
    }

    public void changeState(byte newState) {
        if (currentState != newState) {
            previousState = currentState;
            currentState = newState;

            switch (currentState) {
                case GameState.STATE_MENU:
                    if (menuScreen == null) menuScreen = new MenuScreen();
                    currentScreen = menuScreen;
                    break;
                case GameState.STATE_WORLD_MAP:
                    if (worldMapScreen == null) worldMapScreen = new WorldMapScreen();
                    currentScreen = worldMapScreen;
                    break;
                // case GameState.STATE_BATTLE:
                //     currentScreen = ...;
                //     break;
                default:
                    System.out.println("[GameScreenManager] Unknown state: " + currentState);
                    break;
            }
        }
    }

    @Override
    public void update() {
        if (currentScreen != null) {
            currentScreen.update();
        }
    }

    public void render(Graphics g) {
        if (currentScreen != null) {
            currentScreen.render(g);
        }
    }
}
