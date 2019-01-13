package renderer.buffer;

import java.util.Arrays;

public class ZBuffer {

    private double[] depths;
    private int width, height;
    private double max;

    public ZBuffer(int width, int height) {
        this(width, height, -Double.POSITIVE_INFINITY);
    }

    public ZBuffer(int width, int height, double max) {
        this.width = width;
        this.height = height;
        this.depths = new double[this.width * this.height];
        this.max = max;
        this.clear();
    }

    public void clear() {
        Arrays.fill(this.depths, this.max);
    }

    public void set(int x, int y, int z) {
        this.depths[x + this.width * y] = z;
    }

    public double get(int x, int y) {
        return this.depths[x + this.width * y];
    }

    public boolean compareAndSet(int x, int y, int z) {
        int i = x + this.width * y;
        if (this.depths[i] < z) {
            this.depths[i] = z;
            return true;
        }
        return false;
    }

}
