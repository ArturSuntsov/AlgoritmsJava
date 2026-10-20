package practice2;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(radixSort(new int[]{123456, 654321, 111111})));
        System.out.println(Arrays.toString(radixSort(new int[]{9000, 1000, 99})));
    }

    public static int[] radixSort(int[] arr) {
        if (arr.length == 0) return arr;

        int max = arr[0];
        for (int x : arr) {
            if (x > max) max = x;
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
        return arr;
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int x : arr) {
            int digit = (x / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }
}