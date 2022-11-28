package Experiment.E1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 翟俊华
 */
public class IntersectionListSolution {
    public static void main(String[] args) {
        long startTime1 = System.currentTimeMillis(); //record start time for my method

        //initialize the test case
        List<Integer> L1 = new ArrayList<>();
        List<Integer> L2 = new ArrayList<>();

        L1.add(1);
        L1.add(2);
        L1.add(3);
        L1.add(4);
        L1.add(5);
        L1.add(6);
        L1.add(7);

        L2.add(8);
        L2.add(9);
        L2.add(10);
        L2.add(11);

        System.out.println(new IntersectionListSolution().intersectionList(L1, L2));

        long endTime1 = System.currentTimeMillis();//record end time for my method
        long runtime1 = endTime1 - startTime1;

        long startTime2 = System.currentTimeMillis();//record start time for method in java.util.List
        L1.retainAll(L2);
        long endTime2 = System.currentTimeMillis();//record end time for method in java.util.List
        long runtime2 = endTime2 - startTime2;

        System.out.println("Method intersectionList() running cost " + runtime1 + " ms");
        System.out.println("Method retainAll() running cost " + runtime2 + " ms");
    }
    public List<Integer> intersectionList(List<Integer> L1, List<Integer> L2){
        Collections.sort(L1);
        Collections.sort(L2);
        List<Integer> result = new ArrayList<>();// the return value

        if (L2.size() >= L1.size()) {
            for (int i: L1) {
                if (L2.contains(i)) {
                    result.add(i);
                }
            }
        }else {
            for (int i: L2) {
                if (L1.contains(i)) {
                    result.add(i);
                }
            }
        }
        return result;
    }
}
