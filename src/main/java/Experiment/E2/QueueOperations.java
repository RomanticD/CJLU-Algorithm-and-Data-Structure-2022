package Experiment.E2;

/**
 * @author 翟俊华
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class QueueOperations {

    public static void main(String[] args) {
        Queue<Integer> initialQueue = initializeQueue();

        addItemsTo(initialQueue, new Scanner(System.in).nextInt());
        removeItemsFrom(initialQueue, new Scanner(System.in).nextInt());
    }

    /**
     * add a number to queue
     * @param queue the queue to add the number to
     * @param num the number to add
     */
    public static void addItemsTo(Queue<Integer> queue, int num){
        List<Integer> list = new LinkedList<>();
        list.add(num);

        queue.addAll(list);

        System.out.print("Queue after Add an integer " + num + " : ");
        System.out.print("{ ");
        for (int item:
             queue) {
            System.out.print(item + " ");
        }
        System.out.print("}");
        System.out.println();
    }

    /**
     * delete items from queue
     * @param queue the queue to delete
     * @param numToRemove The number of characters to be removed
     */
    public static void removeItemsFrom(Queue<Integer> queue, int numToRemove){
        if (numToRemove > queue.size()) throw new RuntimeException("The number you entered is greater than the queue size！");

        for (int i = 0; i < numToRemove; i++) {
            queue.remove();
        }

        System.out.print("Queue after Delete " + numToRemove + " integers: ");
        System.out.print("{ ");
        for (int item:
                queue) {
            System.out.print(item + " ");
        }
        System.out.print("}");
        System.out.println();
    }

    /**
     * initialize a test queue
     * @return the test queue
     */
    public static Queue<Integer> initializeQueue(){
        Queue<Integer> initialQueue = new LinkedList<>();
        initialQueue.offer(1);
        initialQueue.offer(2);
        initialQueue.offer(3);
        initialQueue.offer(4);

        System.out.print("The initial Queue includes elements:  ");
        System.out.print("{ ");
        for (int item:
                initialQueue) {
            System.out.print(item + " ");
        }
        System.out.print("}");
        System.out.println();
        return initialQueue;
    }
}
