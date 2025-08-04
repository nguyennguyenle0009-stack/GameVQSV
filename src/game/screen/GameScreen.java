package game.screen;

import java.awt.Graphics;

//Lớp trừu tượng đại diện cho một màn hình trong game
public abstract class GameScreen {
 public abstract void update();
 public abstract void render(Graphics g);
}

