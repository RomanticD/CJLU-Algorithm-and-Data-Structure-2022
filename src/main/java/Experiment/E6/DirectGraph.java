package Experiment.E6;

import java.util.List;

/**
 * @author 翟俊华
 */
public class DirectGraph {
    public DirectGraph(int vertexNum, int edgeNum, int[][] adjMatrix, List<Vertex> vertex, int[] inDegree, int[] outDegree) {
        this.vertexNum = vertexNum;
        this.edgeNum = edgeNum;
        this.adjMatrix = adjMatrix;
        this.vertex = vertex;
        this.inDegree = inDegree;
        this.outDegree = outDegree;
    }

    public DirectGraph(int vertexNum, int edgeNum, int[][] adjMatrix) {
        this.vertexNum = vertexNum;
        this.edgeNum = edgeNum;
        this.vertex = vertex;
    }

    // 顶点数
    private int vertexNum;
    // 边数
    private int edgeNum;
    // 邻接矩阵
    private int[][] adjMatrix;
    // 顶点数组
    private List<Vertex> vertex;
    // 顶点的入度
    private int[] inDegree;
    // 顶点的出度
    private int[] outDegree;

    public int size(){
        return vertexNum;
    }

    public List<Vertex> values(){
        return vertex;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void setAdjMatrix(int[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public List<Vertex> getVertex() {
        return vertex;
    }

    public void setVertex(List<Vertex> vertex) {
        this.vertex = vertex;
    }

    public int[] getInDegree() {
        return inDegree;
    }

    public void setInDegree(int[] inDegree) {
        this.inDegree = inDegree;
    }

    public int[] getOutDegree() {
        return outDegree;
    }

    public void setOutDegree(int[] outDegree) {
        this.outDegree = outDegree;
    }
}
