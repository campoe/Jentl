package math;

public class Matrix3f {

    public float m00, m01, m02,
            m10, m11, m12,
            m20, m21, m22;

    public Matrix3f() {
        this.set(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Matrix3f(float... m) {
        this.set(m);
    }

    public void set(float... m) {
        this.m00 = m[0];
        this.m01 = m[1];
        this.m02 = m[2];
        this.m10 = m[3];
        this.m11 = m[4];
        this.m12 = m[5];
        this.m20 = m[6];
        this.m21 = m[7];
        this.m22 = m[8];
    }

    public void identity() {
        this.set(1, 0, 0, 0, 1, 0, 0, 0, 1);
    }

    public void scale(float sx, float sy) {
        this.identity();
        m00 = sx;
        m11 = sy;
    }

    public void translation(float dx, float dy) {
        this.identity();
        m02 = dx;
        m12 = dy;
    }

    public void rotation(float angle) {
        this.identity();
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);
        m00 = c;
        m01 = -s;
        m10 = s;
        m11 = c;
    }

    public Matrix3f multiply(Matrix3f m) {
        float nm00 = m00 * m.m00 + m01 * m.m10 + m02 * m.m20;
        float nm01 = m00 * m.m01 + m01 * m.m11 + m02 * m.m21;
        float nm02 = m00 * m.m02 + m01 * m.m12 + m02 * m.m22;
        float nm10 = m10 * m.m00 + m11 * m.m10 + m12 * m.m20;
        float nm11 = m10 * m.m01 + m11 * m.m11 + m12 * m.m21;
        float nm12 = m10 * m.m02 + m11 * m.m12 + m12 * m.m22;
        float nm20 = m20 * m.m00 + m21 * m.m10 + m22 * m.m20;
        float nm21 = m20 * m.m01 + m21 * m.m11 + m22 * m.m21;
        float nm22 = m20 * m.m02 + m21 * m.m12 + m22 * m.m22;
        return new Matrix3f(nm00, nm01, nm02, nm10, nm11, nm12, nm20, nm21, nm22);
    }

    public Vector3f multiply(Vector3f v) {
        float nx = m00 * v.x + m01 * v.y + m02 * v.z;
        float ny = m10 * v.x + m11 * v.y + m12 * v.z;
        float nz = m20 * v.x + m21 * v.y + m22 * v.z;
        return new Vector3f(nx, ny, nz);
    }

}
