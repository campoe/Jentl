package scene;

import game.Viewport;

import java.awt.*;

public class SceneTransition {

    public float percent;
    public float endPercent;

    public void update() {
        double diff = this.endPercent - this.percent;
        if (Math.abs(diff) <= 0.025) {
            this.percent = this.endPercent;
        } else if (diff > 0) {
            this.percent += 0.025;
        } else if (diff < 0) {
            this.percent -= 0.025;
        }
    }

    public boolean isFinished() {
        return this.percent == this.endPercent;
    }

    public void open() {
        this.endPercent = 0;
    }

    public void close() {
        this.endPercent = 1;
    }

    public void draw(Graphics2D g) {
        int w = (int) (Viewport.SCREEN_WIDTH * this.percent);
        int h = Viewport.SCREEN_HEIGHT;
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, w, h);
    }

}
