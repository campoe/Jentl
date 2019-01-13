package math;

public class Plane4f {

    public Vector4f normal;
    public Vector4f p;

    public Plane4f() {
        this.normal = new Vector4f(0, 1, 0, 0);
        this.p = new Vector4f(0, 0, 0, 1);
    }

    public Plane4f(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.normal = new Vector4f(x1, y1, z1, 0);
        this.p = new Vector4f(x2, y2, z2, 1);
    }

    public void set(float x1, float y1, float z1, float x2, float y2, float z2) {
        this.normal.set(x1, y1, z1, 0);
        this.p.set(x2, y2, z2, 1);
    }

    public Vector4f intersection(LineSegment4f l) {
        Vector4f tmp1 = new Vector4f(l.p2);
        tmp1.subtract(l.p1);
        double d1 = tmp1.dot(this.normal);
        if (d1 == 0) {
            return null;
        }
        Vector4f tmp2 = new Vector4f(this.p);
        tmp2.subtract(l.p1);
        double d2 = tmp2.dot(this.normal);
        tmp1.multiply((float) (d2 / d1));
        tmp1.add(l.p1);
        return tmp1;
    }

}
