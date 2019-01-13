package game;

public class GraphicsThread implements Runnable {

    public static final double TICK_SPEED = 1.0 / 60.0;

    private boolean running;
    private Viewport viewport;

    public GraphicsThread(Viewport viewport) {
        this.viewport = viewport;
        this.running = false;
    }

    public synchronized void start() {
        this.running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        this.running = false;
    }

    @Override
    public synchronized void run() {
        double time;
        double lastTime = System.nanoTime() / 1000000000.0;
        double elapsedTime;
        double delta = 0;
        double rate = 0;
        int frames = 0;
        int fps = 0;
        while (this.running) {
            boolean render = false;
            time = System.nanoTime() / 1000000000.0;
            elapsedTime = time - lastTime;
            lastTime = time;
            delta += elapsedTime;
            rate += elapsedTime;
            while (delta >= TICK_SPEED) {
                delta -= TICK_SPEED;
                render = true;
                this.viewport.update((float) elapsedTime);
                if (rate >= 1.0) {
                    rate = 0;
                    fps = frames;
                    frames = 0;
                }
            }
            if (render) {
                this.viewport.repaint();
                frames++;
            } else {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.dispose();
    }

    public void dispose() {

    }

}
