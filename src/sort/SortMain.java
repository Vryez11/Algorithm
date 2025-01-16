package sort;

import sort.merge.MergeSort;
import sort.quick.QuickSort;
import sort.radix.RadixSortV1;

import java.util.Arrays;

public class SortMain {

    public static void main(String[] args) {
        int[] arr = {34, 1934, 342, 20, 20, 14, 50, 1, 34, 534};
        int[] copyArr = new int[arr.length];
        int[] cocopyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        System.arraycopy(arr, 0, cocopyArr, 0, arr.length);

        MergeSort.mergeSort(arr, 0, arr.length - 1);
        QuickSort.quickSort(copyArr, 0, copyArr.length - 1);
        RadixSortV1.radixSort(cocopyArr);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(copyArr));
        System.out.println(Arrays.toString(cocopyArr));
    }
}
