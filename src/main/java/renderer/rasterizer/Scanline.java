package renderer.rasterizer;

public class Scanline {

    private int left, right;

    public Scanline() {
        this.clear();
    }

    public Scanline(Scanline scanline) {
        this.set(scanline);
    }

    public void clear() {
        this.left = Integer.MAX_VALUE;
        this.right = Integer.MIN_VALUE;
    }

    public void set(Scanline scanline) {
        this.left = scanline.left;
        this.right = scanline.right;
    }

    public boolean isValid() {
        return this.left <= this.right;
    }

    public void set(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public void setBounds(int x) {
        if (x < this.left) {
            this.left = x;
        }
        if (x - 1 > this.right) {
            this.right = x - 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Scanline) {
            Scanline scanline = (Scanline) obj;
            return this.left == scanline.left && this.right == scanline.right;
        }
        return false;
    }

}
