package scene;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AnimationManager {

    protected Map<String, Animation> animations;
    private String currentAnimation;

    public AnimationManager() {
        this.currentAnimation = null;
        this.animations = new HashMap<>();
    }

    public void add(Animation animation) {
        this.animations.put(animation.getName(), animation);
        if (this.currentAnimation == null) {
            this.currentAnimation = animation.getName();
        }
    }

    public void add(String name, float animationDelay, boolean loop) {
        Animation animation = new Animation(name, animationDelay, loop);
        this.add(animation);
    }

    public void add(String name, boolean loop, String... resources) {
        Animation animation = new Animation(name, loop, resources);
        this.add(animation);
    }

    public void add(String name, String... resources) {
        Animation animation = new Animation(name, resources);
        this.add(animation);
    }

    public void add(String name, float animationDelay, boolean loop, String... resources) {
        Animation animation = new Animation(name, animationDelay, loop, resources);
        this.add(animation);
    }

    public void set(String name, boolean restartFrame) {
        this.currentAnimation = name;
        if (restartFrame) {
            this.animations.get(this.currentAnimation).restart();
        }
    }

    public void set(String name) {
        this.set(name, true);
    }

    public void update(float elapsedTime) {
        if (this.currentAnimation != null) {
            this.animations.get(this.currentAnimation).update(elapsedTime);
        }
    }

    public void draw(Graphics2D g, int x, int y) {
        if (this.currentAnimation != null) {
            this.animations.get(this.currentAnimation).draw(g, x, y);
        }
    }

    public void replaceColor(int srcColor, int dstColor) {
        for (Animation animation : this.animations.values()) {
            animation.replaceColor(srcColor, dstColor);
        }
    }

}
