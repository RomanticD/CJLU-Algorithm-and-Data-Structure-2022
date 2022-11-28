package Experiment.E3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author 翟俊华
 */
public class WordCount {
    public static void main(String[] args) throws IOException {
        String filePath = "src/Experiment/E3/test.txt";
        printResult(filePath);
    }

    /**
     * read content from Specified path
     * @param filePath the path of the file
     * @return the file content
     * @throws IOException if file cannot be open
     */
    public static String readFileContent (String filePath) throws  IOException{
        StringBuilder stringInFile = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))){
            String line;
            while ((line = br.readLine()) != null) {
                stringInFile.append(line);

            }
        }
        return stringInFile.toString();
    }

    /**
     * put words and its Number of occurrences into hashmap
     * @param fileContent the file content
     * @return a hashmap of words and its Number of occurrences
     */
    public static TreeMap<String, Integer> wordCounts(String fileContent){
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        String[] words = fileContent.split("\\W+");

        Set<String> wordsSet = treeMap.keySet();
        for (String word : words) {
            if (wordsSet.contains(word)) {
                Integer number = treeMap.get(word);
                number++;
                treeMap.put(word, number);
            }else {
                treeMap.put(word, 1);
            }
        }
        return treeMap;
    }

    /**
     * print result on screen
     * @param filePath the file path
     * @throws IOException if file cannot be opened
     */
    public static void printResult(String filePath) throws IOException {
        TreeMap<String, Integer> treeMap = wordCounts(readFileContent(filePath));

        List<Map.Entry<String, Integer>> list = new ArrayList<>(treeMap.entrySet());

        list.sort(new Comparator<Map.Entry<String, Integer>>() {//Sort by word frequency in descending order
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        Iterator<Map.Entry<String, Integer>> iter = list.iterator();
        System.out.println("Words frequency in DESCENDING order： ");
        while (iter.hasNext()) {
            Map.Entry<String, Integer> item = iter.next();
            String key = item.getKey();
            int value = item.getValue();
            System.out.println("words：[" + key + "] frequency： " + value + " ");
        }
    }
}
