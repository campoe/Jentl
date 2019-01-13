package scene;

import game.Viewport;

import java.awt.*;
import java.util.Stack;

public class SceneManager {

    protected static int numberOfScenes = 1;
    public static final int NUMBER_OF_SCENES = numberOfScenes;

    protected boolean paused;
    protected SceneTransition transition;
    protected Scene[] scenes;
    protected int currentScene;
    protected Stack<Integer> sceneStack;
    protected Scene pauseScene;

    public SceneManager() {
        this.transition = new SceneTransition();
        this.scenes = new Scene[NUMBER_OF_SCENES];
        this.paused = false;
        this.pauseScene = new NullScene(this);
        this.sceneStack = new Stack<>();
        this.setup();
    }

    protected void loadScene(int sceneId) {
        this.scenes[sceneId] = new PresentsScene(this);
    }

    protected void setScene(int sceneId) {
        this.currentScene = sceneId;
        if (this.scenes[this.currentScene] == null) {
            this.loadScene(sceneId);
        }
        this.sceneStack.push(this.currentScene);
    }

    protected void setup() {
        this.loadScene(0);
    }

    public void toPreviousScene() {
        this.sceneStack.pop();
        this.setScene(this.sceneStack.peek());
    }

    public void pause() {
        this.paused = true;
    }

    public void unpause() {
        this.paused = false;
        this.pauseScene = new NullScene(this);
    }

    public boolean isPaused() {
        return this.paused;
    }

    public void update(float elapsedTime) {
        if (this.isPaused()) {
            this.pauseScene.update(elapsedTime);
        } else if (this.scenes[this.currentScene] != null) {
            this.scenes[this.currentScene].update(elapsedTime);
        }
    }

    public void draw(Graphics2D g) {
        if (this.isPaused()) {
            this.pauseScene.draw(g);
        } else if (this.scenes[this.currentScene] != null) {
            this.scenes[this.currentScene].draw(g);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Viewport.SCREEN_WIDTH, Viewport.SCREEN_HEIGHT);
        }
    }

}
