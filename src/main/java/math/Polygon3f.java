package math;

public class Polygon3f {

    private Vector3f[] vertices;

    public Polygon3f() {
        this.vertices = new Vector3f[0];
    }

    public Polygon3f(Vector3f... vertices) {
        this.vertices = vertices;
    }

    public Polygon3f(Polygon3f p) {
        this.set(p);
    }

    public void set(Polygon3f p) {
        this.vertices = new Vector3f[p.vertices.length];
        for (int i = 0; i < this.vertices.length; i++) {
            this.vertices[i] = new Vector3f(p.vertices[i]);
        }
    }

    public Vector3f get(int i) {
        return this.vertices[i];
    }

    public Vector3f normal() {
        Vector3f u = new Vector3f(this.vertices[2]);
        u.subtract(this.vertices[1]);
        Vector3f v = new Vector3f(this.vertices[0]);
        v.subtract(this.vertices[1]);
        Vector3f normal = u.cross(v);
        return normal.unit();
    }

    public int vertexCount() {
        return this.vertices.length;
    }

    public boolean isTriangle() {
        return this.vertexCount() == 3;
    }

}
