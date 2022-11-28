package Experiment.E6;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 翟俊华
 */
public class TopSortTest_Q2 {
    /**
     * a program to perform a topological sort according to the pseudocode showed in Figure 9.7 in the textbook.
     * the result of topological sort is the sequence of vertex after operation dequeue.
     * @param directedGraph the directed graph
     * @throws CycleFoundException if there is a cycle
     */
    public Queue<Vertex> topSort(DirectGraph directedGraph) throws CycleFoundException {
        Queue<Vertex> q = new LinkedList<>();

        int counter = 0;

        directedGraph.values().forEach(v -> {
            if (Vertex.getInDegree(v) == 0) {
                q.offer(v);
            }
        });

        // start topological sort
        while (!q.isEmpty()) {

            //while the queue is not empty, remove a vertex from the queue
            Vertex v = q.poll();

            v.topNum = ++counter;
            if (v.adjEdges != null) {
                v.adjEdges.forEach(edge -> {
                    //for each vertex w adjacent to v, decrease the in-degree of w by 1
                    edge.endVertex.inDegree--;
                    //if the in-degree of w is zero, put w into the queue
                    if (edge.endVertex.inDegree == 0) {
                        q.offer(edge.endVertex);
                    }
                });
            }
        }
        if (counter != directedGraph.size()) {
            throw new CycleFoundException();
        }

        //print the result of topological sort
        while (!q.isEmpty()) {
            System.out.println(q.poll().dist);
        }

        return q;
    }
}

