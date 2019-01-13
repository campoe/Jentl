package renderer.rasterizer;

import math.Polygon3f;

import java.util.LinkedList;
import java.util.List;

public class GlobalEdgeTable {

    private List<EdgeData>[] globalEdges;
    private Polygon3f poly;

    public GlobalEdgeTable(int height) {
        this(height, null);
    }

    public GlobalEdgeTable(int height, Polygon3f poly) {
        this.set(height, poly);
    }

    public void set(int height, Polygon3f poly) {
        this.globalEdges = new List[height];
        for (int i = 0; i < this.globalEdges.length; i++) {
            this.globalEdges[i] = new LinkedList<>();
        }
        if (poly != null) {
            this.set(poly);
        }
    }

    public void set(Polygon3f poly) {
        this.poly = poly;
    }

}
