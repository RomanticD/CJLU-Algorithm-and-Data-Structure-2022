package Experiment.E1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 翟俊华
 */
public class UnionListSolution {
    public static void main(String[] args) {
    long startTime1 = System.currentTimeMillis();//record the start time of my method

    List<Integer> L1 = new ArrayList<>();
    List<Integer> L2 = new ArrayList<>();

    L1.add(1);
    L1.add(2);
    L1.add(3);
    L1.add(4);
    L1.add(5);
    L1.add(6);
    L1.add(7);

    L2.add(1);
    L2.add(3);
    L2.add(9);
    L2.add(8);

    System.out.println(new UnionListSolution().unionList(L1, L2));

    long endTime1 = System.currentTimeMillis();//record the end time of my method
    long runtime1 = endTime1 - startTime1;

    long startTime2 = System.currentTimeMillis();//record the start time of method in java.util.List
    L1.addAll(L2);
    long endTime2 = System.currentTimeMillis();////record the end time of method in java.util.List
    long runtime2 = endTime2 - startTime2;

    System.out.println("Method UnionList() running cost " + runtime1 + " ms");
    System.out.println("Method addAll() running cost " + runtime2 + " ms");
}
    public List<Integer> unionList(List<Integer> L1, List<Integer> L2){
        Collections.sort(L1);
        Collections.sort(L2);
        List<Integer> result = new ArrayList<>();// the return value

        if (L2.size() >= L1.size()) {
            for (int i: L1) {
                if (!L2.contains(i)) {
                    result.add(i);
                }
            }
            for (int j: L2){
                result.add(j);
            }
        }else {
            for (int i: L2) {
                if (!L1.contains(i)) {
                    result.add(i);
                }
            }
            for (int j: L1){
                result.add(j);
            }
        }
        Collections.sort(result);
        return result;
    }


}
