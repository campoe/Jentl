package math;

import java.awt.*;

public class BoundingBox2f extends Rectangle {

    public BoundingBox2f(Vector2f v1, Vector2f v2) {
        this(v1.x, v1.y, v2.x - v1.x, v2.y - v1.y);
    }

    public BoundingBox2f(float x, float y, float width, float height) {
        super((int) x, (int) y, (int) width, (int) height);
    }

    public boolean contains(Vector2f v) {
        return v.x >= this.x && v.x <= this.x + this.width && v.y >= this.y && v.y <= this.y + this.height;
    }

    public boolean intersects(BoundingBox2f b) {
        if (b.width <= 0 || b.height <= 0 || width <= 0 || height <= 0) {
            return false;
        }
        return b.width + b.x > this.x && b.height + b.y > this.y && this.width + this.x > b.x && this.height + this.y > b.y;
    }

    public float area() {
        return this.width * this.height;
    }

    public Vector2f center() {
        return new Vector2f(this.x + this.width / 2, this.y + this.height / 2);
    }

}
