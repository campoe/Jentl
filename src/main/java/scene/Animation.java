package scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Animation {

    private String name;
    private int currentFrame;
    private List<Sprite> frames;
    private float animationDelay;
    private float lastIterationTime;
    private boolean loop;
    private boolean finished;

    public Animation(String name, boolean loop) {
        this(name, 0, loop);
    }

    public Animation(String name, float animationDelay, boolean loop) {
        this.name = name;
        this.loop = loop;
        this.frames = new ArrayList<>();
        this.animationDelay = animationDelay;
        this.lastIterationTime = 0;
        this.currentFrame = 0;
    }

    public Animation(String name, float animationDelay, boolean loop, String... resources) {
        this(name, animationDelay, loop);
        this.add(resources);
    }

    public Animation(String name, boolean loop, String... resources) {
        this(name, loop);
        this.add(resources);
    }

    public Animation(String name, String... resources) {
        this(name);
        this.add(resources);
    }

    public Animation(String name) {
        this(name, false);
    }

    public boolean isLoop() {
        return this.loop;
    }

    public String getName() {
        return this.name;
    }

    public void add(String... resources) {
        for (String resource : resources) {
            Sprite sprite = new Sprite(resource);
            this.frames.add(sprite);
        }
    }

    public void update(float elapsedTime) {
        if (this.frames.size() > 1) {
            this.lastIterationTime += elapsedTime;
            if (this.lastIterationTime >= this.animationDelay) {
                this.lastIterationTime = 0;
                this.currentFrame = ++this.currentFrame;
                if (this.loop) {
                    this.currentFrame %= this.frames.size();
                } else if (this.currentFrame >= this.frames.size()) {
                    this.finished = true;
                }
            }
        }
    }

    public int size() {
        return this.frames.size();
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void draw(Graphics2D g, int x, int y) {
        if (!this.frames.isEmpty()) {
            int frame = this.currentFrame;
            if (this.finished) {
                frame = this.frames.size() - 1;
            }
            Sprite sprite = this.frames.get(frame);
            sprite.draw(g, x, y);
        }
    }

    public void replaceColor(int srcColor, int dstColor) {
        for (Sprite sprite : this.frames) {
            sprite.replaceColor(srcColor, dstColor);
        }
    }

    public void restart() {
        this.currentFrame = 0;
    }

}
