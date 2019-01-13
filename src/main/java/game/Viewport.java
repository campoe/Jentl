package game;

import scene.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Viewport extends JPanel {

    public static final int SCREEN_WIDTH = 256;
    public static final int SCREEN_HEIGHT = 192;
    public static final int SCREEN_SCALE = 3;

    private SceneManager sceneManager;
    private BufferedImage image;

    public Viewport() {
        this.sceneManager = new SceneManager();
        Dimension dimension = new Dimension(SCREEN_WIDTH * SCREEN_SCALE, SCREEN_HEIGHT * SCREEN_SCALE);
        this.setPreferredSize(dimension);
        this.image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public void start() {
        GraphicsThread graphicsThread = new GraphicsThread(this);
        graphicsThread.start();
    }

    public void update(float elapsedTime) {
        this.sceneManager.update(elapsedTime);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.render((Graphics2D) this.image.getGraphics());
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(SCREEN_SCALE, SCREEN_SCALE);
        g2d.drawImage(this.image, 0, 0, null);
        g2d.dispose();
    }

    private void render(Graphics2D g) {
        setRenderingHints(g);
        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.sceneManager.draw(g);
    }

    private void setRenderingHints(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    public void drawCenteredString(String text, Font font, Rectangle rect) {
        Graphics g = this.getGraphics();
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

}
