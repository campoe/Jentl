package scene;

import game.Time;
import game.Viewport;
import physics.World;

import java.awt.*;

public class PresentsScene extends Scene {

    private Sprite image;
    private float waitTime;
    private float y, vy;
    private boolean processPhysics;
    private Color bgColor;

    public PresentsScene(SceneManager manager) {
        super(manager);
        this.image = new Sprite("logo.png");
        this.bgColor = new Color(103, 144, 186);

    }

    @Override
    public void setup() {
        this.waitTime = -1;
        this.y = 0;
        this.vy = 0;
        this.processPhysics = true;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void update(float elapsedTime) {
        if (this.processPhysics) {
            y += vy;
            vy += World.GRAVITY;
            if (y > Viewport.SCREEN_HEIGHT / 2) {
                y = Viewport.SCREEN_HEIGHT / 2;
                vy *= -0.5;
                if (Math.abs(vy) < 2) {
                    this.processPhysics = false;
                }
            }
        } else if (this.waitTime < 0) {
            this.waitTime = Time.getCurrent() + 3;
        } else if (Time.getCurrent() > this.waitTime) {
            this.manager.setScene(0);
        }
    }

    @Override
    public void handleInput() {

    }

    public void draw(Graphics2D g) {
        super.draw(g);
        g.setBackground(this.bgColor);
        g.clearRect(0, 0, Viewport.SCREEN_WIDTH, Viewport.SCREEN_HEIGHT);
        this.image.draw(g, Viewport.SCREEN_WIDTH / 2, (int) y);
    }

}
