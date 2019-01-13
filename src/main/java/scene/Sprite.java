package scene;

import math.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite {

    private BufferedImage image;
    private Vector2f pivot;

    public Sprite(String resource) {
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/" + resource)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pivotX = this.image.getWidth() / 2;
        int pivotY = this.image.getHeight() / 2;
        this.pivot = new Vector2f(pivotX, pivotY);
    }

    public void draw(Graphics2D g, int x, int y) {
        g.drawImage(this.image, (int) (x - this.pivot.x), (int) (y - this.pivot.y), null);
    }

    public void replaceColor(int srcColor, int dstColor) {
        for (int y = 0; y < this.image.getHeight(); y++) {
            for (int x = 0; x < this.image.getWidth(); x++) {
                if (image.getRGB(x, y) == srcColor) {
                    image.setRGB(x, y, dstColor);
                }
            }
        }
    }

}
