package Experiment.E6;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 翟俊华
 */
public class TopSortTest_Q3 {
    /**
     * a program to solve the single-source shortest-path problem.
     * The input is a directed graph G = (V, E) with nonnegative edge weights and a source vertex s.
     * The output is a shortest-path tree rooted at s.
     * The algorithm is to compute a shortest-path tree rooted at s, where the tree is represented as a parent array.
     * @param vertices the vertices of the graph
     * @param source index of a source vertex
     */
    public void BFS_ShortestPath(Vertex[] vertices, int source) {
        //Initialize the vertices
        for (int i = 0; i < vertices.length; i++) {
            vertices[i].inDegree = Integer.MAX_VALUE;
            vertices[i].topNum = 0;
        }

        //Set the source vertex
        vertices[source].inDegree = 0;
        vertices[source].topNum = 1;

        //Set the queue
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertices[source]);

        //Start BFS
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            vertex.adjEdges.forEach(edge -> {
                Vertex adjVertex = edge.adjVertex;
                if (adjVertex.inDegree == Integer.MAX_VALUE) {
                    adjVertex.inDegree = vertex.inDegree + edge.weight;
                    adjVertex.topNum = vertex.topNum + 1;
                    queue.add(adjVertex);
                }
            });
        }
    }
}
