package sort.quick;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr, int start, int end) {
        int pivotIndex = partition(arr, start, end);

        if (pivotIndex - 1 > start) quickSort(arr, start, pivotIndex - 1);
        if (pivotIndex + 1 < end) quickSort(arr, pivotIndex + 1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        if (start + 1 == end) {
            if (arr[start] > arr[end])
                swap(arr, start, end);
            return end;
        }

        int mid = (start + end) / 2;
        swap(arr, start, mid);
        int pivot = arr[start];
        int i = start + 1; int j = end;
        while (i <= j) {
            while (i <= end && arr[i] < pivot) {
                i++;
            }
            while (j >= start + 1 && arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        arr[start] = arr[j];
        arr[j] = pivot;
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
