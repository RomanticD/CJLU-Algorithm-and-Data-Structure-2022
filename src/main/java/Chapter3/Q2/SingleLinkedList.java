package Chapter3.Q2;

public class SingleLinkedList<AnyType> {

    private class Node<AnyType> {

        AnyType val;
        Node<AnyType> next;

        Node(AnyType paraVal) {
            val = paraVal;
        }
    }

    private Node<AnyType> head = new Node(new Object());

    private int size = 0;

    public void add(AnyType paraVal) {
        insert(size, paraVal);
    }

    public AnyType pop(){
        return delete(size - 1);
    }

    public void insert(int paraIdx, AnyType paraVal) {

        if (paraIdx > size) {
            throw new IndexOutOfBoundsException("The index error.");
        }

        Node<AnyType> tempNode = head;
        int i = 0;
        while (i++ < paraIdx) {
            tempNode = tempNode.next;
        }

        Node<AnyType> paraNode = new Node<>(paraVal);
        paraNode.next = tempNode.next;
        tempNode.next = paraNode;
        size++;
    }

    public AnyType delete(int paraIdx) {
        if (size == 0) {
            throw new RuntimeException("The single linked list is empty.");
        }

        if (size <= paraIdx) {
            throw new IndexOutOfBoundsException("The index error.");
        }

        Node<AnyType> retNode = head;
        int i = 0;
        while (i++ < paraIdx) {
            retNode = retNode.next;
        }

        retNode.next = retNode.next.next;

        size--;
        return retNode.val;
    }

    public int getSize() {
        return size;
    }

    public void display() {
        if (size == 0) {
            throw new RuntimeException("The single linked list is empty.");
        }

        System.out.print("The single linked list is:\n[");
        Node<AnyType> tempNode = head;
        int i = 0;
        while (i++ < size - 1) {
            tempNode = tempNode.next;
            System.out.printf("%s, ", tempNode.val);
        }
        System.out.printf("%s]\n", tempNode.next.val);
    }

    public void exchangeElementNearBy(Node<AnyType> previousNode, Node<AnyType> subSequentNode){
        Node<AnyType> prev = null;
        Node<AnyType> after = subSequentNode.next;

        while (head.next != null){
            if (head.next == previousNode) {
                prev = head.next;
                break;
            }
            head = head.next;
        }

        prev.next = subSequentNode;
        subSequentNode.next = previousNode;
        previousNode.next = after;

    }
}

