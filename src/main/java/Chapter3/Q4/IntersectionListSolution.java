package Chapter3.Q4;

import java.util.*;

public class IntersectionListSolution<AnyType> {
    private List<AnyType> solution(List<AnyType> list1, List<AnyType> list2) {

        List<AnyType> resultList = new ArrayList<>();
        Map<String,AnyType> map = new HashMap<>();
        list1.forEach(a1->{
            map.put(a1+"",a1);
        });

        list2.forEach(a2->{
            AnyType element = map.get(a2 + "");
            if (element!=null){
                resultList.add(element);
            }
        });

        return resultList;
    }
}
