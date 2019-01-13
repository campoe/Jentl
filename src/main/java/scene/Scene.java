package scene;

import game.Viewport;

import java.awt.*;

public abstract class Scene {

    protected SceneManager manager;

    public Scene(SceneManager manager) {
        this.manager = manager;
        this.setup();
    }

    public abstract void setup();

    public abstract void dispose();

    public abstract void update(float elapsedTime);

    public abstract void handleInput();

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Viewport.SCREEN_WIDTH, Viewport.SCREEN_HEIGHT);
    }

}
