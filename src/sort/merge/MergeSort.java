package sort.merge;

public class MergeSort {

    public static void mergeSort(int[] arr, int start, int end) {
        if (end - start < 1) return;

        int mid = (start + end) / 2;

        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        int[] temp = new int[end - start + 1];

        int k = 0;
        int index1 = start; int index2 = mid + 1;

        while (index1 <= mid && index2 <= end) {
            if (arr[index1] < arr[index2]) {
                temp[k++] = arr[index1++];
            } else {
                temp[k++] = arr[index2++];
            }
        }
        while (index1 <= mid) {
            temp[k++] = arr[index1++];
        }
        while (index2 <= end) {
            temp[k++] = arr[index2++];
        }

        System.arraycopy(temp, 0, arr, start, temp.length);
    }
}
