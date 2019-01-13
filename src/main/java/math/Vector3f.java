package math;

public class Vector3f {

    public float x, y, z;

    public Vector3f() {
        this(0, 0, 0);
    }

    public Vector3f(float x, float y, float z) {
        this.set(x, y, z);
    }

    public Vector3f(Vector3f v) {
        this.set(v);
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vector3f v) {
        this.set(v.x, v.y, v.z);
    }

    public void multiply(float f) {
        this.x *= f;
        this.y *= f;
        this.z *= f;
    }

    public void divide(float f) {
        this.multiply(1 / f);
    }

    public void add(Vector3f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
    }

    public void subtract(Vector3f v) {
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

    public void translate(Vector3f v) {
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

    public float dot(Vector3f v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vector3f cross(Vector3f v) {
        float x = this.y * v.z - this.z * v.y;
        float y = this.z * v.x - this.x * v.z;
        float z = this.x * v.y - this.y * v.x;
        return new Vector3f(x, y, z);
    }

    public Vector3f unit() {
        Vector3f v = new Vector3f(this);
        v.divide(v.length());
        return v;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public void lerp(Vector3f a, Vector3f b, float f) {
        this.set(a.x + f * (b.x - a.x), a.y + f * (b.y - a.y), a.z + f * (b.z - a.z));
    }

    @Override
    public String toString() {
        return "math.Vector3f:{x:" + this.x + ", y:" + this.y + ", z:" + this.z + "}";
    }

}
