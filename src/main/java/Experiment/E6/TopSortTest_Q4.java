package Experiment.E6;

/**
 * @author 翟俊华
 */
public class TopSortTest_Q4 {
    public static void main(String[] args) {
        //just an example to demonstrate how to use the algorithm
        initGraph(null);
        dijkstra(null);
        printPath(null);
    }

    /**
     * a program to implement Kruskal’s algorithm
     * @param s a connected undirected graph G = (V, E) with edge weights.
     */
    public static void dijkstra(Vertex s){
        //set source vertex s
        s.dist = 0;
        s.known = true;

        //while there is an unknown vertex
        while (true){
            Vertex v = findSmallestUnknownVertex(s);

            //if there is no unknown vertex, break
            if (v == null){
                break;
            }

            //set the vertex as known
            v.known = true;

            //for each vertex w adjacent to v
            for (Vertex w : v.adjVertex){
                //if w is unknown
                if (!w.known){
                    //distType cvw = cost of edge from v to w
                    int cvw = 0;
                    for (Edge edge : v.adjEdges){
                        if (edge.adjVertex == w){
                            cvw = edge.weight;
                            }
                        }

                    int currentDist = v.dist + cvw;
                    if (currentDist < w.dist){
                        //update w
                        w.dist = currentDist;
                        w.path = v;
                    }
                }
            }
        }
    }

    public static Vertex findSmallestUnknownVertex(Vertex v){
        for (Vertex vertex : v.adjVertex){
            if (!vertex.known && (v == null || vertex.dist < v.dist)){
                v = vertex;
            }
        }
        return v;
    }

    public static void initGraph(DirectGraph graph){
        for (Vertex vertex : graph.getVertex()) {
            vertex.known = false;
            vertex.dist = Integer.MAX_VALUE;
            vertex.path = null;
        }
    }

    /**
     * assume that the path exists
     * @param v Print shortest path to v after dijkstra has run
     */
    public static void printPath(Vertex v){
        try{
            if (v.path != null){
                printPath(v.path);
                System.out.print(" to ");
            }
            System.out.print(v);
        }catch (Exception e){
            System.out.println("No path");
        }

    }
}
