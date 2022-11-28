package Experiment.E1;

import java.util.*;

/**
 * @author 翟俊华
 */
public class PolynomialsSolution {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        //initial Polynomials P1 and P2
        Polynomial p1 = PolynomialsSolution.initializeForP_1();
        Polynomial p2 = PolynomialsSolution.initializeForP_2();

        //print the result of add and multiply operation
        System.out.println(new PolynomialsSolution().add(p1, p2));
        System.out.println(new PolynomialsSolution().multiply(p1, p2));

        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;

        System.out.println("it takes " + runTime +" ms to solve the problem");
    }

    /**
     * Add two polynomials
     * @param p1 the first polynomial
     * @param p2 the second polynomial
     * @return String whose addition results are sorted in exponential descending order
     */
    public String add(Polynomial p1, Polynomial p2){
        //Store final results
        StringBuilder result = new StringBuilder();

        //save nodes in lists
        List<Node> nodes1 = p1.getNodes();
        List<Node> nodes2 = p2.getNodes();

        //For the convenience of calculation, exchange the two Polynomials to put the larger one in the first position
        if (nodes1.size() < nodes2.size()){
            List<Node> temp = nodes1;
            nodes1 = nodes2;
            nodes2 = temp;
        }

        List<Node> nodesAfterAdd = new ArrayList<>();

        for (Node nodeInNodes1 : nodes1){
            for (Node nodeInNodes2: nodes2){
                if (nodeInNodes1.getExponent() == nodeInNodes2.getExponent() && !nodeInNodes1.isCalculated() && !nodeInNodes2.isCalculated()) {
                    Node newNode = new Node(nodeInNodes1.getCoefficient() + nodeInNodes2.getCoefficient(), nodeInNodes1.getExponent(),Node.CALCULATED);
                    nodesAfterAdd.add(newNode);

                    nodeInNodes1.setIsCalculated(Node.CALCULATED);
                    nodeInNodes2.setIsCalculated(Node.CALCULATED);

                }
            }
        }
        for(Node node1: nodes1){
            if (!node1.isCalculated()) nodesAfterAdd.add(node1);
        }
        for(Node node2: nodes2){
            if (!node2.isCalculated()) nodesAfterAdd.add(node2);
        }

        //Ensure that the final output results are sorted in descending order of exponent
        Collections.sort(nodesAfterAdd, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.getExponent() - o1.getExponent();
            }
        });

        for (Node node : nodesAfterAdd){
            result.append(node.toString());
        }
        return "Polynomial after Add operation = " + result.substring(0, result.length() - 2);
    }

    public int myCompare(Node o1, Node o2){
        return o2.getExponent() - o1.getExponent();
    }

    /**
     * Multiply two polynomials
     * @param p1 the first polynomial
     * @param p2 the second polynomial
     * @return String whose addition results are sorted in exponential descending order
     */
    public String multiply(Polynomial p1, Polynomial p2){
        //Store final results
        StringBuilder result = new StringBuilder();

        //save nodes in lists
        List<Node> nodes1 = p1.getNodes();
        List<Node> nodes2 = p2.getNodes();

        //For the convenience of calculation, exchange the two Polynomials to put the larger one in the first position
        if (nodes1.size() < nodes2.size()){
            List<Node> temp = nodes1;
            nodes1 = nodes2;
            nodes2 = temp;
        }

        List<Node> nodesAfterMultiply = new ArrayList<>();

        for (Node nodeInNodes1 : nodes1){
            for (Node nodeInNodes2: nodes2){
                Node newNode = new Node(nodeInNodes1.getCoefficient() * nodeInNodes2.getCoefficient(), nodeInNodes1.getExponent() + nodeInNodes2.getExponent(),Node.NOT_CALCULATED);
                nodesAfterMultiply.add(newNode);
            }
        }

        //Merge Similar Items
        List<Node> nodesAfterMerge = new ArrayList<>();

        for (int i = 0; i < nodesAfterMultiply.size(); i++) {

            if (nodesAfterMultiply.get(i).isCalculated()) continue;
                int mergedCoefficient = nodesAfterMultiply.get(i).getCoefficient();
                int mergedExponent = nodesAfterMultiply.get(i).getExponent();

                for (int j = i + 1; j < nodesAfterMultiply.size(); j++){
                    if (nodesAfterMultiply.get(i).getExponent() == nodesAfterMultiply.get(j).getExponent() && !nodesAfterMultiply.get(j).isCalculated()) {

                        mergedCoefficient += nodesAfterMultiply.get(j).getCoefficient();

                        nodesAfterMultiply.get(j).setIsCalculated(Node.CALCULATED);
                    }
                }
                nodesAfterMerge.add(new Node(mergedCoefficient, mergedExponent, Node.CALCULATED));

                nodesAfterMultiply.get(i).setIsCalculated(Node.CALCULATED);

        }

        //Ensure that the final output results are sorted in descending order of exponent
//        Collections.sort(nodesAfterMerge, new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                return o2.getExponent() - o1.getExponent();
//            }
//        });

        //Collections.sort(nodesAfterMerge, (Node o1, Node o2) -> o2.getExponent() - o1.getExponent());
        Collections.sort(nodesAfterMerge, this::myCompare);

        for (Node node: nodesAfterMerge){
            result.append(node.toString());
        }

        return "Polynomial after Multiply operation = " + result.substring(0, result.length() - 2);
    }

    /**
     * initialize the value of the test case
     * @return the first test case of Polynomial
     */
    private static Polynomial initializeForP_1(){
        Node p1_1 = new Node(13, 897, Node.NOT_CALCULATED);
        Node p1_2 = new Node(4, 25, Node.NOT_CALCULATED);
        Node p1_3 = new Node(1, 0, Node.NOT_CALCULATED);

        List<Node> poly1 = new ArrayList<>();

        poly1.add(p1_1);
        poly1.add(p1_2);
        poly1.add(p1_3);

        Polynomial p1 = new Polynomial(poly1);

        return  p1;
    }

    /**
     * initialize the value of the test case
     * @return the second test case of Polynomial
     */
    private static Polynomial initializeForP_2(){
        Node p2_1 = new Node(167, 1000, Node.NOT_CALCULATED);
        Node p2_2 = new Node(-5, 1872, Node.NOT_CALCULATED);
        Node p2_3 = new Node(3, 1, Node.NOT_CALCULATED);
        Node p2_4 = new Node(8, 0, Node.NOT_CALCULATED);

        List<Node> poly2 = new ArrayList<>();

        poly2.add(p2_1);
        poly2.add(p2_2);
        poly2.add(p2_3);
        poly2.add(p2_4);

        Polynomial p2 = new Polynomial(poly2);

        return p2;
    }
}

class Node{
    //Set composed of coefficients and exponent of one node
    private Map<Integer, Integer> node = new HashMap<>();
    private int coefficient;
    private int exponent;

    //Record whether each node has completed the operation
    private int isCalculated;
    public static final int CALCULATED = 1;
    public static final int NOT_CALCULATED = 0;

    public Node(int coefficient, int exponent, int isCalculated){
        node.put(coefficient, exponent);
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.isCalculated = isCalculated;

        //throw an exception if the exponent is a negative integer
        if (exponent < 0) throw new RuntimeException("Coefficient must be a Nonnegative integer");
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public String toString() {
        //override toString() to return the value of the node
        return this.coefficient + "X^" + this.exponent + " + ";
    }

    public void setIsCalculated(int isCalculated) {
        this.isCalculated = isCalculated;
    }

    public boolean isCalculated(){
        return isCalculated == 1;
    }
}

class Polynomial{
    //Put all Nodes in the polynomial in the List
    private List<Node> nodes = new ArrayList<>();

    public Polynomial(List<Node> nodes){
        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
