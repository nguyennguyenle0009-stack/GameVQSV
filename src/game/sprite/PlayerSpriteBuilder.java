package game.sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerSpriteBuilder {

    // Kích thước mặc định mỗi frame
    private static final int FRAME_WIDTH = 24;
    private static final int FRAME_HEIGHT = 32;
    private static final int FRAMES_PER_DIRECTION = 3;
    private static final int DIRECTIONS = 4;

    /**
     * Load sprite từ ảnh .png có cấu trúc layer dọc giống file .mid gốc.
     * Mỗi layer là một phần cơ thể (thân, tóc, mắt, v.v.), thứ tự layer từ trên xuống
     */
    public static Image[][] loadSpriteSet(String resourcePath, int frameW, int frameH) {
        try {
            BufferedImage spriteSheet = ImageIO.read(PlayerSpriteBuilder.class.getResourceAsStream(resourcePath));
            if (spriteSheet == null) {
                System.err.println("[PlayerSpriteBuilder] Không tìm thấy file ảnh: " + resourcePath);
            } else {
                System.out.println("[PlayerSpriteBuilder] Đã load ảnh: " + resourcePath + " với kích thước: " + spriteSheet.getWidth() + "x" + spriteSheet.getHeight());
            }
            int layerCount = spriteSheet.getHeight() / (frameH * DIRECTIONS);
            List<BufferedImage[][]> layers = new ArrayList<>();

            for (int l = 0; l < layerCount; l++) {
                BufferedImage[][] layerFrames = new BufferedImage[DIRECTIONS][FRAMES_PER_DIRECTION];
                for (int dir = 0; dir < DIRECTIONS; dir++) {
                    for (int f = 0; f < FRAMES_PER_DIRECTION; f++) {
                        int x = f * frameW;
                        int y = (l * DIRECTIONS + dir) * frameH;
                        if (x + frameW <= spriteSheet.getWidth() && y + frameH <= spriteSheet.getHeight()) {
                            layerFrames[dir][f] = spriteSheet.getSubimage(x, y, frameW, frameH);
                        }
                    }
                }
                layers.add(layerFrames);
            }

            // Gộp các layer lại thành từng frame
            BufferedImage[][] finalFrames = new BufferedImage[DIRECTIONS][FRAMES_PER_DIRECTION];
            for (int dir = 0; dir < DIRECTIONS; dir++) {
                for (int f = 0; f < FRAMES_PER_DIRECTION; f++) {
                    BufferedImage combined = new BufferedImage(frameW, frameH, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = combined.createGraphics();
                    for (BufferedImage[][] layer : layers) {
                        if (layer[dir][f] != null)
                            g2.drawImage(layer[dir][f], 0, 0, null);
                    }
                    g2.dispose();
                    finalFrames[dir][f] = combined;
                }
            }
            return finalFrames;
        } catch (IOException e) {
            e.printStackTrace();
            return new BufferedImage[DIRECTIONS][FRAMES_PER_DIRECTION];
        }
    }
}