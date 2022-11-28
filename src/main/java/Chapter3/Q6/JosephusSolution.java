package Chapter3.Q6;

public class JosephusSolution {
    public int solutionDepends(int n, int m) {
        if(n == 0 || m == 0){
            return -1;
        }
        int index = 1;
        for(int i = 2 ; i <= n ; i++){
            index = (index + m) % i;
            if(index == 0){
                index += i;
            }
        }
        return index;
    }

    public int solution(int n, int m){
        return solutionDepends(n,m+1);
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println(new JosephusSolution().solution(5,0));
        long endTime = System.nanoTime();
        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");
    }
}
