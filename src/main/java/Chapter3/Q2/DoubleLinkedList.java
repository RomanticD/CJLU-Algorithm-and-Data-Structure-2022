package Chapter3.Q2;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<AnyType> implements Iterable<AnyType> {
    //集合长度
    private int theSize;
    //用来在迭代器循环中判断集合是否被修改过
    private int modCount;
    //头节点
    private Node<AnyType> beginMarker;
    //尾节点
    private Node<AnyType> endMarker;

    //表示一个存储节点的内部类，包括存储的数据，前驱链和后驱链，还有一个构造方法
    private static class Node<AnyType>{
        public Node(AnyType data,Node<AnyType> prev,Node<AnyType> next){
            this.data =data;
            this.prev=prev;
            this.next =next;
        }
        //存储的数据
        public AnyType data;
        //指向前一个节点的链
        public Node<AnyType> prev;
        ///指向后一个节点的链
        public Node<AnyType> next;
    }

    public DoubleLinkedList(){

        doClear();
    }

    public void clear(){
        doClear();
    }
    //初始化或者清空集合
    public void doClear(){
        beginMarker = new Node<AnyType>(null,null,null);
        endMarker = new Node<AnyType>(null,beginMarker,null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }
    //返回集合长度
    public int size(){
        return theSize;
    }
    //判断集合是否为空
    public boolean isEmpty(){
        return size()==0;
    }
    //直接在尾部插入一个元素
    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }
    //在某个位置后边插入一个元素
    public void  add(int idx,AnyType x){
        addBefore(getNode(idx,0,size()),x);
    }

    public AnyType get(int idx){
        return getNode(idx).data;
    }

    public AnyType set(int idx,AnyType newVal){
        Node<AnyType> p =getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    private void  addBefore(Node<AnyType> p,AnyType x){
        Node<AnyType> newNode = new Node<>(x,p.prev,p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    public Node<AnyType> getNode(int idx){
        return getNode(idx,0,size()-1);
    }
    //如果索引在集合中前半部分我们将从第一个数据像后开始遍历，
    // 如果索引在后半分则从最后一个数据向前遍历
    public Node<AnyType> getNode(int idx,int lower,int upper){
        Node<AnyType> p;
        if(idx<lower||idx >upper)
            throw new IndexOutOfBoundsException();
        if (idx<size()/2){
            p = beginMarker.next;
            for (int i = 0;i<idx;i++)
                p = p.next;
        }else {
            p = endMarker;
            for (int i = 0;i<idx;i++)
                p = p.prev;
        }
        return p;
    }
    //删除某个元素时，先根据索引查出元素节点
    private  AnyType remove(int idx){
        return remove((getNode(idx)));
    }
    //再将该节点的下一个节点的前驱链指向该节点的上一个节点，
    // 将该节点的前一个节点的后驱链指向该节点的下一个节点
    private AnyType remove(Node<AnyType> p){
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }
    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }
    //实现iterator接口，modCount != expectedModCount 判断是否在迭代器循环的时候是否做了修改，有则抛出异常
    private class LinkedListIterator implements Iterator<AnyType>{
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext(){
            return  current != endMarker;
        }

        public AnyType next(){
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            AnyType nextItem = current.data;
            current =current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove(){
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if (!okToRemove){
                throw new IllegalStateException();
            }
            DoubleLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    public void exchangeElementNearBy(Node<AnyType> previousNode, Node<AnyType> subSequentNode){
        AnyType temp;
        Node<AnyType> head = previousNode.prev;
        Node<AnyType> tail = subSequentNode.next;

        temp = subSequentNode.data;
        subSequentNode.data = previousNode.data;
        previousNode.data = temp;

        head.next = subSequentNode;
        subSequentNode.prev = head;

        subSequentNode.next = previousNode;
        previousNode.prev = subSequentNode;

        previousNode.next = tail;
        tail.prev = previousNode;
    }
}

