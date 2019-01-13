package math;

public class Vector4f {

    public float x, y, z, w;

    public Vector4f() {
        this(0, 0, 0, 0);
    }

    public Vector4f(float x, float y, float z, float w) {
        this.set(x, y, z, w);
    }

    public Vector4f(Vector4f v) {
        this.set(v);
    }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void set(Vector4f v) {
        this.set(v.x, v.y, v.z, v.w);
    }

    public void multiply(float f) {
        this.x *= f;
        this.y *= f;
        this.z *= f;
    }

    public void divide(float f) {
        this.multiply(1 / f);
    }

    public void add(Vector4f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
    }

    public void subtract(Vector4f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
    }

    public void conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public void translate(float dx, float dy, float dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
    }

    public void translate(Vector4f v) {
        this.add(v);
    }

    public void rotate(float angleX, float angleY, float angleZ) {
        this.rotateX(angleX);
        this.rotateY(angleY);
        this.rotateZ(angleZ);
    }

    public void rotateX(float angle) {
        double s = Math.sin(angle);
        double c = Math.cos(angle);
        double nz = z * c - y * s;
        double ny = z * s + y * c;
        this.z = (float) nz;
        this.y = (float) ny;
    }

    public void rotateY(float angle) {
        double s = Math.sin(angle);
        double c = Math.cos(angle);
        double nx = x * c - z * s;
        double nz = x * s + z * c;
        this.x = (float) nx;
        this.z = (float) nz;
    }

    public void rotateZ(float angle) {
        double s = Math.sin(angle);
        double c = Math.cos(angle);
        double nx = x * c - y * s;
        double ny = x * s + y * c;
        this.x = (float) nx;
        this.y = (float) ny;
    }

    public Vector4f unit() {
        Vector4f v = new Vector4f(this);
        v.divide(v.length());
        return v;
    }

    public float dot(Vector4f v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vector4f cross(Vector4f v) {
        float x = this.y * v.z - this.z * v.y;
        float y = this.z * v.x - this.x * v.z;
        float z = this.x * v.y - this.y * v.x;
        return new Vector4f(x, y, z, this.w);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public void lerp(Vector4f a, Vector4f b, float f) {
        this.set(a.x + f * (b.x - a.x), a.y + f * (b.y - a.y), a.z + f * (b.z - a.z), a.w + f * (b.w - a.w));
    }

    public void perspectiveDivision() {
        this.divide(this.w);
    }

    @Override
    public String toString() {
        return "math.Vector4f:{x:" + this.x + ", y:" + this.y + ", z:" + this.z + ", w:" + this.w + "}";
    }

}
