package Experiment.E3;

/**
 * @author 翟俊华
 */
public class AvlTreeTraverse {
    public static void main(String[] args) {
        System.out.println(" --- AvlTree Traverse result ---");

        printPreOrderTraverseResult();
        printInOrderTraverseResult();
        printPostOrderTraverseResult();
        printNumbersOfLeaf();
    }

    /**
     * preOrder Traverse
     * @param node Node to start traversing
     */
    public void preOrderTraverse(AvlTree.AvlNode<Integer> node){
        if (node == null) return;
        System.out.print(node.element + " ");
        this.preOrderTraverse(node.left);
        this.preOrderTraverse(node.right);
    }

    /**
     * inOrderTraverse
     * @param node Node to start traversing
     */
    public void inOrderTraverse(AvlTree.AvlNode<Integer> node){
        if (node == null) return;
        this.inOrderTraverse(node.left);
        System.out.print(node.element + " ");
        this.inOrderTraverse(node.right);
    }

    /**
     * postOrderTraverse
     * @param node Node to start traversing
     */
    public void postOrderTraverse(AvlTree.AvlNode<Integer> node){
        if (node == null) return;

        this.postOrderTraverse(node.left);
        this.postOrderTraverse(node.right);
        System.out.print(node.element + " ");
    }

    /**
     * Insert the specified element in the tree according to the requirements in the question
     * use {@link AvlTree}
     * @return the initialized tree
     */
    public static AvlTree<Integer> initializeTreeTree(){
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(9);
        tree.insert(7);
        tree.insert(5);
        tree.insert(2);

        return tree;
    }

    public static void printPreOrderTraverseResult(){
        System.out.println("Pre-Order Traverse the tree: ");
        new AvlTreeTraverse().preOrderTraverse(initializeTreeTree().getRoot());
        System.out.println();
    }

    public static void printInOrderTraverseResult(){
        System.out.println("In-Order Traverse the tree: ");
        new AvlTreeTraverse().inOrderTraverse(initializeTreeTree().getRoot());
        System.out.println();
    }

    public static void printPostOrderTraverseResult(){
        System.out.println("Post-Order Traverse the tree: ");
        new AvlTreeTraverse().postOrderTraverse(initializeTreeTree().getRoot());
        System.out.println();
    }

    public static void printNumbersOfLeaf(){
        System.out.println("The tree has " + countLeaves(tree.getRoot(), 0) + " leaves");
    }

    private static AvlTree tree = initializeTreeTree();

    /**
     * Return the number of leaves owned by the tree with the specified root node
     * @param root tree's root
     * @param leafCount current number of leaves
     * @return the number of leaves
     */
    public static int countLeaves(AvlTree.AvlNode root, int leafCount){

        if (root == null) return 0;
        if (root.left == null && root.right == null) leafCount ++;

        int leftLeafCount = countLeaves(root.left, leafCount);
        int rightLeafCount = countLeaves(root.right, leafCount);

        return  leafCount + leftLeafCount + rightLeafCount;
    }
}
