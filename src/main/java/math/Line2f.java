package math;

public class Line2f {

    private float a, b, c;

    public Line2f(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int evaluate(float x, float y) {
        float r = this.a * x + this.b * y + c;
        if (r < 0) {
            return -1;
        } else if (r > 0) {
            return 1;
        }
        return 0;
    }

    public float xfd() {
        return this.a;
    }

    public float yfd() {
        return this.b;
    }

}
