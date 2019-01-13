package math;

public class Matrix4f {

    public float m00, m01, m02, m03,
            m10, m11, m12, m13,
            m20, m21, m22, m23,
            m30, m31, m32, m33;

    public Matrix4f() {
        this.set(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Matrix4f(float... m) {
        this.set(m);
    }

    public void set(float... m) {
        this.m00 = m[0];
        this.m01 = m[1];
        this.m02 = m[2];
        this.m03 = m[3];
        this.m10 = m[4];
        this.m11 = m[5];
        this.m12 = m[6];
        this.m13 = m[7];
        this.m20 = m[8];
        this.m21 = m[9];
        this.m22 = m[10];
        this.m23 = m[11];
        this.m30 = m[12];
        this.m31 = m[13];
        this.m32 = m[14];
        this.m33 = m[15];
    }

    public void identity() {
        this.set(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    }

    public void perspectiveProjection(float fov, float width) {
        double d = (width * 0.5) / Math.tan(fov * 0.5);
        this.identity();
        m32 = (float) (-1 / d);
        m33 = 0.0f;
    }

    public void scale(float sx, float sy, float sz) {
        this.identity();
        m00 = sx;
        m11 = sy;
        m22 = sz;
    }

    public void translation(float dx, float dy, float dz) {
        this.identity();
        m03 = dx;
        m13 = dy;
        m23 = dz;
    }

    public void rotationX(float angle) {
        this.identity();
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);
        m11 = c;
        m12 = s;
        m21 = -s;
        m22 = c;
    }

    public void rotationY(float angle) {
        this.identity();
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);
        m00 = c;
        m02 = -s;
        m20 = s;
        m22 = c;
    }

    public void rotationZ(float angle) {
        this.identity();
        float s = (float) Math.sin(angle);
        float c = (float) Math.cos(angle);
        m00 = c;
        m01 = s;
        m10 = -s;
        m11 = c;
    }

    public Matrix4f multiply(Matrix4f m) {
        float nm00 = m00 * m.m00 + m01 * m.m10 + m02 * m.m20 + m03 * m.m30;
        float nm01 = m00 * m.m01 + m01 * m.m11 + m02 * m.m21 + m03 * m.m31;
        float nm02 = m00 * m.m02 + m01 * m.m12 + m02 * m.m22 + m03 * m.m32;
        float nm03 = m00 * m.m03 + m01 * m.m13 + m02 * m.m23 + m03 * m.m33;
        float nm10 = m10 * m.m00 + m11 * m.m10 + m12 * m.m20 + m13 * m.m30;
        float nm11 = m10 * m.m01 + m11 * m.m11 + m12 * m.m21 + m13 * m.m31;
        float nm12 = m10 * m.m02 + m11 * m.m12 + m12 * m.m22 + m13 * m.m32;
        float nm13 = m10 * m.m03 + m11 * m.m13 + m12 * m.m23 + m13 * m.m33;
        float nm20 = m20 * m.m00 + m21 * m.m10 + m22 * m.m20 + m23 * m.m30;
        float nm21 = m20 * m.m01 + m21 * m.m11 + m22 * m.m21 + m23 * m.m31;
        float nm22 = m20 * m.m02 + m21 * m.m12 + m22 * m.m22 + m23 * m.m32;
        float nm23 = m20 * m.m03 + m21 * m.m13 + m22 * m.m23 + m23 * m.m33;
        float nm30 = m30 * m.m00 + m31 * m.m10 + m32 * m.m20 + m33 * m.m30;
        float nm31 = m30 * m.m01 + m31 * m.m11 + m32 * m.m21 + m33 * m.m31;
        float nm32 = m30 * m.m02 + m31 * m.m12 + m32 * m.m22 + m33 * m.m32;
        float nm33 = m30 * m.m03 + m31 * m.m13 + m32 * m.m23 + m33 * m.m33;
        return new Matrix4f(nm00, nm01, nm02, nm03, nm10, nm11, nm12, nm13, nm20, nm21, nm22, nm23, nm30, nm31, nm32, nm33);
    }

    public Vector4f multiply(Vector4f v) {
        float nx = m00 * v.x + m01 * v.y + m02 * v.z + m03 * v.w;
        float ny = m10 * v.x + m11 * v.y + m12 * v.z + m13 * v.w;
        float nz = m20 * v.x + m21 * v.y + m22 * v.z + m23 * v.w;
        float nw = m30 * v.x + m31 * v.y + m32 * v.z + m33 * v.w;
        return new Vector4f(nx, ny, nz, nw);
    }

}
