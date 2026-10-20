package practice2;

import java.util.Arrays;

public class CountingSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countingSort(new int[]{6, 1, 6, 3, 2})));
        System.out.println(Arrays.toString(countingSort(new int[]{4, 4, 2, 5})));
    }

    public static int[] countingSort(int[] arr) {
        int[] count = new int[7];

        for (int x : arr) {
            count[x]++;
        }

        int idx = 0;
        int[] result = new int[arr.length];
        for (int value = 1; value <= 6; value++) {
            for (int j = 0; j < count[value]; j++) {
                result[idx++] = value;
            }
        }
        return result;
    }
}