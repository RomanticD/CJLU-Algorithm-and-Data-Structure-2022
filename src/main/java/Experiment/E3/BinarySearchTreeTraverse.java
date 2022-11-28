package Experiment.E3;

/**
 * @author 翟俊华
 */
public class BinarySearchTreeTraverse {
    public static void main(String[] args) {
        System.out.println("--- BinarySearchTree traverse result ---");

        printPreOrderTraverseResult();
        printInOrderTraverseResult();
        printPostOrderTraverseResult();
        printNumbersOfLeaf();
    }

    /**
     * preOrder Traverse
     * @param node Node to start traversing
     */
    public void preOrderTraverse(BinarySearchTree.BinaryNode<Integer> node){
        if (node == null) return;
        System.out.print(node.element + " ");
        this.preOrderTraverse(node.left);
        this.preOrderTraverse(node.right);
    }

    /**
     * inOrderTraverse
     * @param node Node to start traversing
     */
    public void inOrderTraverse(BinarySearchTree.BinaryNode<Integer> node){
        if (node == null) return;
        this.inOrderTraverse(node.left);
        System.out.print(node.element + " ");
        this.inOrderTraverse(node.right);
    }

    /**
     * postOrderTraverse
     * @param node Node to start traversing
     */
    public void postOrderTraverse(BinarySearchTree.BinaryNode<Integer> node){
        if (node == null) return;

        this.postOrderTraverse(node.left);
        this.postOrderTraverse(node.right);
        System.out.print(node.element + " ");
    }

    /**
     * Insert the specified element in the tree according to the requirements in the question
     * use {@link BinarySearchTree}
     * @return the initialized tree
     */
    public static BinarySearchTree<Integer> initializeTreeTree(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
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
        new BinarySearchTreeTraverse().preOrderTraverse(initializeTreeTree().getRoot());
        System.out.println();
    }

    public static void printInOrderTraverseResult(){
        System.out.println("In-Order Traverse the tree: ");
        new BinarySearchTreeTraverse().inOrderTraverse(initializeTreeTree().getRoot());
        System.out.println();
    }

    public static void printPostOrderTraverseResult(){
        System.out.println("Post-Order Traverse the tree: ");
        new BinarySearchTreeTraverse().postOrderTraverse(initializeTreeTree().getRoot());
        System.out.println();
    }

    public static void printNumbersOfLeaf(){
        System.out.println("The tree has " + countLeaves(tree.getRoot(), 0) + " leaves");
    }

    private static BinarySearchTree tree = initializeTreeTree();

    /**
     * Return the number of leaves owned by the tree with the specified root node
     * @param root tree's root
     * @param leafCount current number of leaves
     * @return the number of leaves
     */
    public static int countLeaves(BinarySearchTree.BinaryNode root, int leafCount){

        if (root == null) return 0;
        if (root.left == null && root.right == null) leafCount ++;

        int leftLeafCount = countLeaves(root.left, leafCount);
        int rightLeafCount = countLeaves(root.right, leafCount);

        return  leafCount + leftLeafCount + rightLeafCount;
    }
}
