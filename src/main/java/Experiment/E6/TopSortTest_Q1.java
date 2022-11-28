package Experiment.E6;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 翟俊华
 */
public class TopSortTest_Q1 {
    public static int vertexNum;
    public static int edgeNum;
    public static int[][] adjTable;
    public static int[] inDegree;

    public static ArrayList<Integer> result = new ArrayList<>();

    /**
     * In the first line, enter the number of vertices and edges
     * The number of each vertex in the directed graph and the connected edges are input subsequently
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the number of vertices: ");
        vertexNum = sc.nextInt();

        System.out.println("Please enter the number of edges: ");
        edgeNum = sc.nextInt();

        adjTable = new int[vertexNum+1][vertexNum+1];
        inDegree = new int[vertexNum+1];

        int vertex1,vertex2;

        for(int i = 0; i < edgeNum; i++) {
            System.out.println("Please enter the two vertices of the edge (" + (i + 1) + "): ");
            vertex1 = sc.nextInt();
            vertex2 = sc.nextInt();
            adjTable[vertex1][vertex2] = 1;
        }

        int inDegree = 0;
        for(int j = 1 ; j < vertexNum+1 ; j++) {
            for(int i = 1 ; i < vertexNum+1 ; i++) {
                if(adjTable[i][j] == 1) {
                    inDegree++;
                }
            }
            TopSortTest_Q1.inDegree[j] = inDegree;
            inDegree = 0;
        }

        System.out.println("该图的邻接矩阵表示为：");
        for(int i = 1 ; i < vertexNum+1 ; i++) {
            for(int j = 1 ; j < vertexNum+1 ; j++) {
                System.out.print(adjTable[i][j]+" ");
            }
            System.out.println();
        }

        topSort();
    }

    public static void topSort() {
        int vertex = findIndegreeZero();

        while(vertex != -1) {
            result.add(vertex);
            inDegree[vertex] = -1;
            for(int j = 1 ; j < vertexNum+1 ; j++) {
                if(adjTable[vertex][j] > 0) {
                    inDegree[j] --;
                }
            }
            vertex = findIndegreeZero();

        }

        if(result.size() < vertexNum) {
            System.out.println("该图存在环");
        }
        else {
            System.out.println("拓扑排序的结果是：");
            result.forEach(integer -> System.out.print(integer + " "));
        }


    }

    /**
     * find if the indegree is 0 of a vertex, return -1 if not found
     * @return the index of the vertex
     */
    public static int findIndegreeZero() {
        for(int i = 1 ; i < vertexNum+1 ; i++) {
            if(inDegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }

}

