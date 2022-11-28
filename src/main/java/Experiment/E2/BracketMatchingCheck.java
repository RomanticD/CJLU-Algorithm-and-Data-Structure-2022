package Experiment.E2;

/**
 * @author 翟俊华
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class BracketMatchingCheck {
    public static void main(String[] args) throws IOException {
        String filename = "src/Experiment/E2/TestCase.txt";

        long startTime = System.currentTimeMillis();
        printResult(filename);
        long endTime = System.currentTimeMillis();
        System.out.println("Running cost " + (endTime - startTime) + " ms.");
    }

    /**
     * validate whether the bracket in the text matched exactly
     * @param fileContent the file content
     * @return true if the bracket matched
     */
    public static boolean isValid(String fileContent) {

        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < fileContent.length(); i++) {
            char character = fileContent.charAt(i);
            if (character == '('){
                stack.push(')');
            }else if (character == '['){
                stack.push(']');
            }else if (character == '{'){
                stack.push('}');
            }else if ((character == ')' || character == ']' || character == '}') && (stack.isEmpty()||character != stack.pop()))
                return false;
        }
        return stack.isEmpty();
    }

    /**
     * read content from Specified path
     * @param filename the path of the file
     * @return the file content
     * @throws IOException if file not found
     */
    public static String readFileContent (String filename) throws  IOException{
        StringBuilder stringInFile = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))){
            String line;
            while ((line = br.readLine()) != null) {
                stringInFile.append(line);

            }
        }
        return stringInFile.toString();
    }

    /**
     * print the result on the screen whether bracket matched
     * @param filename the file to be validated
     * @throws IOException if file not found
     */
    public static void printResult (String filename) throws IOException {
        boolean isValid;
        isValid = isValid(readFileContent(filename));
        if (isValid) {
            System.out.println("Brackets in text are VALID");
        }else {
            System.out.println("Brackets in text are NOT VALID!");
        }
    }
}
