package math;

public class Quaternion {

    public float x, y, z, w;

    public Quaternion() {
        this(0, 0, 0, 0);
    }

    public Quaternion(float x, float y, float z, float w) {
        this.set(x, y, z, w);
    }

    public Quaternion(float angle, Vector4f axis) {
        float s = (float) Math.sin(angle / 2);
        this.x = axis.x * s;
        this.y = axis.y * s;
        this.z = axis.z * s;
        this.w = (float) Math.cos(angle / 2);
    }

    public Quaternion(Quaternion q) {
        this(q.x, q.y, q.z, q.w);
    }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void set(Quaternion q) {
        this.set(q.x, q.y, q.z, q.w);
    }

    public void multiply(Quaternion q) {
        float x = this.x * q.w + this.w * q.x + this.y * q.z - this.z * q.y;
        float y = this.y * q.w + this.w * q.y + this.z * q.x - this.x * q.z;
        float z = this.z * q.w + this.w * q.z + this.x * q.y - this.y * q.x;
        float w = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;
        this.set(x, y, z, w);
    }

    public void divide(float f) {
        this.x /= f;
        this.y /= f;
        this.z /= f;
        this.w /= f;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion unit() {
        Quaternion q = new Quaternion(this);
        q.divide(q.length());
        return q;
    }

    public void conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public Vector4f rotate(Vector4f p) {
        Quaternion tmp1 = new Quaternion(p.x, p.y, p.z, 0);
        Quaternion tmp2 = new Quaternion(this);
        tmp2.multiply(tmp1);
        tmp1.set(this);
        tmp1.conjugate();
        tmp2.multiply(tmp1);
        return new Vector4f(tmp2.x, tmp2.y, tmp2.z, p.w);
    }

}
