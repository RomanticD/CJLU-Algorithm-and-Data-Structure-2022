package Experiment.E5;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 翟俊华
 */
public class TestSort {
    private static final int[] TEST_CAPACITY = {100, 1000, 5000, 10000, 50000, 100000};

    public static void main(String[] args) throws IOException {
        TestSort testSort = new TestSort();
        for (int capacity:
                TEST_CAPACITY) {
            testSort.testAllSort(capacity);
        }
        createExcel(excel_name, headList, fieldList, dataList);
    }

    /**
     * Selection Sort
     * @param array Array to be sorted
     */
    public static void selectionSort(int[] array){
        long startTime = System.nanoTime();

        int length = array.length;
        int min;

        for(int i = 0; i < length; i++){
            min = i;
            for(int j = i + 1; j < length; j++){
                if(array[j] < array[min]){
                    min = j;
                }
            }
            int tmp = array[i];
            array[i] = array[min];
            array[min] = tmp;
        }

        long endTime = System.nanoTime();
        System.out.println("SelectionSort takes "+ (endTime - startTime) + " ns");
    }

    /**
     * Bubble Sort
     * @param array Array to be sorted
     */
    public static void bubbleSort(int[] array) {
        long startTime = System.nanoTime();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp;
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }

        long endTime = System.nanoTime();
        System.out.println("BubbleSort takes "+ (endTime - startTime) + " ns");
    }

    /**
     * Insertion Sort
     * @param array Array to be sorted
     */
    public static void insertionSort(int[] array) {
        long startTime = System.nanoTime();

        for (int i = 1; i < array.length; i++) {

            int tmp = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > tmp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }

        long endTime = System.nanoTime();
        System.out.println("InsertionSort takes "+ (endTime - startTime) + " ns");
    }

    /**
     * Shell Sort
     * @param array Array to be sorted
     */
    public static void shellSort(int[] array){
        long startTime = System.nanoTime();

        int temp;

        for (int gap = array.length / 2; gap >= 1; gap /= 2){
            for (int i = gap; i< array.length; i++){
                for (int j = i-gap; j >= 0; j = j-gap){
                    if (array[j] > array[j+gap]){
                        temp = array[j];
                        array[j] = array[j+gap];
                        array[j+gap] = temp;
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        System.out.println("ShellSort takes "+ (endTime - startTime) + " ns");
    }

    /**
     * Merge Sort
     * @param array Array to be sorted
     */
    public static void mergeSort(int[] array){
        long startTime = System.nanoTime();

        //Before sorting, build a temporary array whose length is equal to the original array length to avoid frequent space opening in recursion
        int[] temp = new int[array.length];
        sort(array,0,array.length-1,temp);

        long endTime = System.nanoTime();
        System.out.println("MergeSort takes "+ (endTime - startTime) + " ns");
    }

    /**
     * implementation of MergeSort
     * @param array the array to be sorted
     * @param left Start position of sorting in the array
     * @param right end position of sorting in the array
     * @param temp array after sorting
     */
    private static void sort(int[] array,int left,int right,int[] temp){

        if(left < right){
            int mid = (left+right)/2;
            sort(array,left,mid,temp);//Left merge and sort to make left subsequences orderly
            sort(array,mid+1,right,temp);//Right merge and sort to make right subsequences orderly
            merge(array,left,mid,right,temp);//Merge two ordered subarrays
        }
    }

    /**
     * merge two arrays
     * @param array the array to be sorted
     * @param left start position of the first array
     * @param mid start position of the second array
     * @param right end position of the second array
     * @param temp the array after merge operation
     */
    private static void merge(int[] array, int left, int mid, int right, int[] temp){

        int i = left;//Left Sequence Pointer
        int j = mid+1;//Left Sequence Pointer
        int t = 0;//Temporary array pointer

        while (i <= mid && j <= right){
            if(array[i] <= array[j]){
                temp[t++] = array[i++];
            }else {
                temp[t++] = array[j++];
            }
        }
        while(i <= mid){//Fill the remaining elements on the left into the temp
            temp[t++] = array[i++];
        }
        while(j <= right){//Fill the remaining elements on the right into the temp
            temp[t++] = array[j++];
        }
        t = 0;

        //Copy all the elements in temp to the original array
        while(left <= right){
            array[left++] = temp[t++];
        }
    }

    /**
     * Used in QuickSort
     * @param array Array to be sorted
     * @param left Start position of sorting in the array
     * @param right End position of sorting in the array
     */
    public static int partition(int[] array, int left, int right) {
        //make the last element pivot
        int pivot = array[right];
        //Define a pointer that is larger than the center element, and first point to the first element
        int pointer = left;
        //Iterate through all the elements in the array, placing the ones larger than the central element on the right and the ones smaller than the central element on the left

        for (int i = left; i < right; i++) {
            if (array[i] <= pivot) {
                //Swap the element smaller than the center element with the element pointed by the pointer
                //If the first element is smaller than the center element, this is where you exchange positions with yourself. The pointer and index move to the next position
                //If the element is larger than the central element, the index moves downward, and the pointer points to the larger element until the element smaller than the central element is found, and the position is exchanged, and the pointer moves downward
                int temp = array[i];
                array[i] = array[pointer];
                array[pointer] = temp;
                pointer++;
            }
        }

        //Swap the central element and the element pointed to by the pointer

        int temp = array[pointer ];
        array[pointer] = array[right];
        array[right] = temp;

        return pointer;
    }

    public static void quickSort(int[] array, int left, int right) {

        if (left < right) {
            //Get the position of subarray division
            int position = partition(array, left, right);
            //Left Subarray Recursive Call
            quickSort(array, left, position -1);
            //Right Subarray Recursive Call
            quickSort(array, position + 1, right);
        }
    }


/**
 * bucket Sort
 * @param array the array to be sorted
 */
    public static void bucketSort(int[] array){
        long startTime = System.nanoTime();

        int max = array[0];
        for(int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        int min = array[0];
        for(int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }


        int[] temp =new int[max - min + 1];

        for (int i = 0; i < array.length; i++) {
            temp[array[i] - min]++;
        }

        long endTime = System.nanoTime();
        System.out.println("BucketSort takes "+ (endTime - startTime) + " ns");
    }

    /**
     * radix Sort
     * @param array the array to be sorted
     */
    public static void radixSort(int[] array) {
        long startTime = System.nanoTime();

        int length = 0;

        int[][] bucketGreaterZero = new int[10][array.length];
        int[][] bucketLessZero = new int[10][array.length];

        int[] bucketGreaterZeroElementCounts = new int[10];
        int[] bucketLessZeroElementCounts = new int[10];

        int digitOfGreaterElement = 0;
        int digitOfLessElement = 0;

        int i = 0;

        int index1 = 0;
        int index2 = 0;
        for (i = 0; i < array.length; i++) {
            if (array[i] >= 0) {
                index1++;
            } else {
                index2++;
            }
        }

        int[] arrLessZero = new int[index2];
        int[] arrGreaterZero = new int[array.length - index2];

        index1 = 0;
        index2 = 0;

        for (i = 0; i < array.length; i++) {
            if (array[i] >= 0) {
                arrGreaterZero[index1] = array[i];
                index1++;
            } else {
                arrLessZero[index2] = array[i];
                index2++;
            }
        }

        for (i = 0; i < arrLessZero.length; i++) {
            if (arrLessZero[i] != 0) {
                arrLessZero[i] *= (-1);
            }
        }

        int maxGreaterZero = 0;
        for (i = 0; i < arrGreaterZero.length; i++) {
            if (maxGreaterZero < String.valueOf(arrGreaterZero[i]).length()) {
                maxGreaterZero = String.valueOf(arrGreaterZero[i]).length();
            }
        }

        int maxLessZero = 0;
        for (i = 0; i < arrLessZero.length; i++) {
            if (maxLessZero < String.valueOf(arrLessZero[i]).length()) {
                maxLessZero = String.valueOf(arrLessZero[i]).length();
            }
        }

        int index = 0;
        int n1 = 1;
        for (i = 0, n1 = 1; i < maxLessZero; i++, n1 *= 10) {
            for (int j = 0; j < arrLessZero.length; j++) {
                digitOfLessElement = arrLessZero[j] / n1 % 10;
                bucketLessZero[digitOfLessElement][bucketLessZeroElementCounts[digitOfLessElement]] = arrLessZero[j];//放入对应的桶
                bucketLessZeroElementCounts[digitOfLessElement]++;
            }
            index = 0;

            for (int k = 0; k < bucketLessZeroElementCounts.length; k++) {
                if (bucketLessZeroElementCounts[k] != 0) {
                    for (int l = 0; l < bucketLessZeroElementCounts[k]; l++) {
                        arrLessZero[index] = bucketLessZero[k][l];
                        index++;
                    }
                }
                bucketLessZeroElementCounts[k] = 0;
            }
        }

        int[] temp = new int[arrLessZero.length];
        int index3 = 0;
        for (i = arrLessZero.length - 1; i >= 0; i--) {
            temp[index3] = arrLessZero[i] * (-1);
            index3++;
        }


        int n2 = 1;
        for (i = 0, n2 = 1; i < maxGreaterZero; i++, n2 *= 10) {
            for (int j = 0; j < arrGreaterZero.length; j++) {
                digitOfGreaterElement = arrGreaterZero[j] / n2 % 10;
                bucketGreaterZero[digitOfGreaterElement][bucketGreaterZeroElementCounts[digitOfGreaterElement]] = arrGreaterZero[j];//放入对应的桶
                bucketGreaterZeroElementCounts[digitOfGreaterElement]++;
            }

            index = 0;

            for (int k = 0; k < bucketGreaterZeroElementCounts.length; k++) {
                if (bucketGreaterZeroElementCounts[k] != 0) {
                    for (int l = 0; l < bucketGreaterZeroElementCounts[k]; l++) {
                        arrGreaterZero[index] = bucketGreaterZero[k][l];
                        index++;
                    }
                }
                bucketGreaterZeroElementCounts[k] = 0;
            }
        }

        //merge two arrays
        System.arraycopy(temp, 0, array, 0, temp.length);
        System.arraycopy(arrGreaterZero, 0, array, temp.length, arrGreaterZero.length);

        long endTime = System.nanoTime();
        System.out.println("RadixSort takes "+ (endTime - startTime) + " ns");
    }

    /**
     * Generate an array of specified capacity
     * @param size the size of the array
     * @return the array
     */
    public static int[] initArray(int size){
        int[] array= new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    public void testAllSort(int capacity){
        ArrayList<Long> runTimeList = new ArrayList<>();

        System.out.println("----Array with " + capacity + " integers----");
        int[] array = initArray(capacity);

        long startTime_selectionSort = System.nanoTime();
        selectionSort(array);
        long endTime_selectionSort = System.nanoTime();

        long startTime_bubbleSort = System.nanoTime();
        bubbleSort(array);
        long endTime_bubbleSort = System.nanoTime();

        long startTime_insertionSort = System.nanoTime();
        insertionSort(array);
        long endTime_insertion = System.nanoTime();

        long startTime_shellSort = System.nanoTime();
        shellSort(array);
        long endTime_shellSort = System.nanoTime();

        long startTime_mergeSort = System.nanoTime();
        mergeSort(array);
        long endTime_mergeSort = System.nanoTime();

        long startTime_quickSort = System.nanoTime();
        //Stack overflow Exception if array size is greater than 25000 while using quickSort
        try {
            quickSort(array, 0 , array.length - 1);
        }catch (StackOverflowError e){
            Logger logger = Logger.getLogger("Experiment.E5.TestSort");
            logger.log(Level.WARNING, "StackOverflowError, call the quick sort in the Arrays.java");
            Arrays.sort(array);
        }
        long endTime_quickSort = System.nanoTime();
        System.out.println("QuickSort takes "+ (endTime_quickSort - startTime_quickSort) + " ns");

        long startTime_bucketSort = System.nanoTime();
        bucketSort(array);
        long endTime_bucketSort = System.nanoTime();

        long startTime_radixSort = System.nanoTime();
        radixSort(array);
        long endTime_radixSort = System.nanoTime();

        System.out.println();

        runTimeList.add(endTime_selectionSort - startTime_selectionSort);
        runTimeList.add(endTime_bubbleSort - startTime_bubbleSort);
        runTimeList.add(endTime_insertion - startTime_insertionSort);
        runTimeList.add(endTime_shellSort - startTime_shellSort);
        runTimeList.add(endTime_mergeSort - startTime_mergeSort);
        runTimeList.add(endTime_quickSort - startTime_quickSort);
        runTimeList.add(endTime_bucketSort - startTime_bucketSort);
        runTimeList.add(endTime_radixSort - startTime_radixSort);

        dataList.add(runTimeList);
    }

    public static final String[] fieldList = {"N", "100", "1000", "5000", "10000", "50000", "100000"};
    public static final String excel_name = "/Users/romanticd/Desktop/Experiment5.xlsx";
    public static final String[] headList = {"Selection Sort", "Bubble Sort", "Insertion Sort", "Shell Sort", "Merge Sort", "Quick Sort", "Bucket Sort", "Radix Sort"};
    public static final List<List<Long>> dataList = new ArrayList<>();


    /**
     * Write the data to a Excel file
     * @param path_name the path of the file
     * @param headList the headline of the table
     * @param fieldList the field of the table
     * @param dataList the data of the table
     * @throws Exception
     */
    public static void createExcel(String path_name, String[] headList, String[] fieldList, List<List<Long>> dataList) throws IOException {
        //create a new Excel file
        XSSFWorkbook workbook = new XSSFWorkbook();
        //create a new Excel sheet with default name
        XSSFSheet sheet = workbook.createSheet();
        //create a new row as the headline
        XSSFRow row = sheet.createRow(0);

        //fill the headline with headList
        for (int i = 0; i < headList.length + 1; i++) {

            XSSFCell cell = row.createCell(i);
            //Define cell as string type
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            //Specify cell contents
            if (i == 0) {
                cell.setCellValue(" ");
            } else {
                cell.setCellValue(headList[i - 1]);
            }
        }

        //add data
        for (int n = 0; n < dataList.size(); n++) {
            //start Create data at the second row
            XSSFRow row_value = sheet.createRow(n + 1);
            List<Long> row_data = dataList.get(n);

            for (int i = 0; i < row_data.size() + 1; i++) {

                //create cell
                XSSFCell cell = row_value.createCell(i);
                //Define cell as string type
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                if (i == 0) cell.setCellValue(fieldList[n]);
                else cell.setCellValue(row_data.get(i - 1).toString());
            }

        }
        //create a new fileOutput stream
        FileOutputStream fos = new FileOutputStream(path_name);

        //save the file
        workbook.write(fos);
        fos.flush();

        //close the file
        fos.close();
    }
}
