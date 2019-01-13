package math;

public class Vector2f {

    public float x, y;

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(float x, float y) {
        this.set(x, y);
    }

    public Vector2f(Vector2f v) {
        this.set(v);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2f v) {
        this.set(v.x, v.y);
    }

    public void multiply(float f) {
        this.x *= f;
        this.y *= f;
    }

    public void divide(float f) {
        this.multiply(1 / f);
    }

    public void add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void subtract(Vector2f v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void conjugate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    public Vector2f unit() {
        Vector2f v = new Vector2f(this);
        v.divide(v.length());
        return v;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void lerp(Vector2f a, Vector2f b, float f) {
        this.set(a.x + f * (b.x - a.x), a.y + f * (b.y - a.y));
    }

    @Override
    public String toString() {
        return "math.Vector2f:{x:" + this.x + ", y:" + this.y + "}";
    }

}
