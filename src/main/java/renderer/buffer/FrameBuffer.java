package renderer.buffer;

public class FrameBuffer {

    private int[] pixels;
    private int width, height;

    public FrameBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.clear();
    }

    public void clear() {
        this.pixels = new int[this.width * this.height];
    }

    public void set(int x, int y, int c) {
        this.pixels[x + this.width * y] = c;
    }

    public void set(int x, int y, int r, int g, int b) {
        this.pixels[x + this.width * y] = (r << 16) + (g << 8) + b;
    }

    public int get(int x, int y) {
        return this.pixels[x + this.width * y];
    }

}
