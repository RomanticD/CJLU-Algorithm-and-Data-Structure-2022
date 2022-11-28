package Experiment.E6;

public class Edge {
    public Edge(Vertex adjVertex, int weight, Vertex endVertex, Vertex beginVertex) {
        this.adjVertex = adjVertex;
        this.weight = weight;
        this.endVertex = endVertex;
        this.beginVertex = beginVertex;
    }

    public Vertex adjVertex;
    public int weight;
    Vertex endVertex;
    Vertex beginVertex;

    public Vertex getAdjVertex() {
        return adjVertex;
    }

    public void setAdjVertex(Vertex adjVertex) {
        this.adjVertex = adjVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public Vertex getBeginVertex() {
        return beginVertex;
    }

    public void setBeginVertex(Vertex beginVertex) {
        this.beginVertex = beginVertex;
    }
}
