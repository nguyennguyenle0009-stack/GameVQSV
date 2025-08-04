package game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameScreenManager implements GameController {
    private static GameScreenManager instance;
    private byte currentState;
    private byte previousState;
    private int stateCounter;

    private GameScreenManager() {
        currentState = 0;
        previousState = -1;
        stateCounter = 0;
    }

    public static GameScreenManager getInstance() {
        if (instance == null) {
            instance = new GameScreenManager();
        }
        return instance;
    }

    @Override
    public void update() {
        // TODO: Di chuyển logic cập nhật màn chơi ở đây
        System.out.println("[GameScreenManager] Updating state: " + currentState);
        stateCounter++;
    }
}
