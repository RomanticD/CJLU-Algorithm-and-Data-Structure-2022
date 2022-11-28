package Experiment.E6;

import java.util.List;

public class Vertex {
    public Vertex(int dist, int inDegree, int topNum) {
        this.dist = dist;
        this.inDegree = inDegree;
        this.topNum = topNum;
    }

    public Vertex(int dist, Vertex path, boolean known) {
        this.dist = dist;
        this.path = path;
        this.known = known;
    }

    int dist;
    int inDegree;
    int topNum;
    List<Edge> adjEdges;
    List<Vertex> adjVertex;
    Vertex path;

    boolean known;

    /**
     * @return the in-degree of this vertex
     * @param vertex the vertex to check
     */
    public static int getInDegree(Vertex vertex) {
        int inDegree = 0;
        for (Edge edge : vertex.adjEdges) {
            inDegree ++;
        }
        return inDegree;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public int getTopNum() {
        return topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

    public List<Edge> getAdjEdges() {
        return adjEdges;
    }

    public void setAdjEdges(List<Edge> adjEdges) {
        this.adjEdges = adjEdges;
    }

    public List<Vertex> getAdjVertex() {
        return adjVertex;
    }

    public void setAdjVertex(List<Vertex> adjVertex) {
        this.adjVertex = adjVertex;
    }

    public Vertex getPath() {
        return path;
    }

    public void setPath(Vertex path) {
        this.path = path;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }
}
