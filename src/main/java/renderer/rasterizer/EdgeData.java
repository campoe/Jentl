package renderer.rasterizer;

public class EdgeData {

    private float yMax;
    private float xMin;
    private float xIncrement;

    public float x;

    public EdgeData(float yMax, float xMin, float slope) {
        this.yMax = yMax;
        this.xMin = xMin;
        this.xIncrement = 1.0f / slope;
        this.x = this.xMin;
    }

    public boolean isValid(int scanLine) {
        return this.yMax > scanLine;
    }

    public void increment() {
        this.x += this.xIncrement;
    }

}
