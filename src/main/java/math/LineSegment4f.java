package math;

public class LineSegment4f {

    public Vector4f p1;
    public Vector4f p2;

    public LineSegment4f() {
        this.p1 = new Vector4f(0, 0, 0, 1);
        this.p2 = new Vector4f(0, 1, 0, 1);
    }

    public LineSegment4f(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.p1 = new Vector4f(x1, y1, z1, 1);
        this.p2 = new Vector4f(x2, y2, z2, 1);
    }

    public void set(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.p1.set(x1, y1, z1, 1);
        this.p2.set(x2, y2, z2, 1);
    }

}
