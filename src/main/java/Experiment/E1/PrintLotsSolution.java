package Experiment.E1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 翟俊华
 */
public class PrintLotsSolution {
    public void printLots(List<Integer> L, List<Integer> P){
        Collections.sort(L);
        Collections.sort(P);
        List<Integer> result = new ArrayList<>();// the return value

        for (int i:P) {
            if (i < L.size()){
                result.add(L.get(i));
            }else {
                throw new ArrayIndexOutOfBoundsException();//throw new Exception
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();//record the start time

        //initialize the test case
        List<Integer> L = new ArrayList<>();
        List<Integer> P = new ArrayList<>();
        
        L.add(1);
        L.add(3);
        L.add(5);
        L.add(7);
        L.add(9);
        L.add(100);
        L.add(1000);
        L.add(10000);

        P.add(0);
        P.add(1);
        P.add(3);
        P.add(6);
        
        new PrintLotsSolution().printLots(L, P);

        long endTime = System.currentTimeMillis();//record the end time
        long runtime = endTime - startTime;

        System.out.println("running cost " + runtime + " ms");
    }
}
