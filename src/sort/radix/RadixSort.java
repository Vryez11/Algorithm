package sort.radix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RadixSort {

    public static void radixSort(int[] arr) {
        Queue<Integer>[] jarisuQueue = new Queue[10];
        for (int i = 0; i < 10; i++) {
            jarisuQueue[i] = new LinkedList<>();
        }

        int maxNum = Arrays.stream(arr).max().getAsInt();
        int maxDigit = (int) Math.log10(maxNum) + 1;

        int jarisu = 1;
        for (int i = 0; i < maxDigit; i++) {
            for (int num : arr) {
                int digit = (num / jarisu) % 10;
                jarisuQueue[digit].add(num);
            }

            int index = 0;
            for (Queue<Integer> queue : jarisuQueue) {
                while (!queue.isEmpty()) {
                    arr[index++] = queue.poll();
                }
            }

            jarisu *= 10;
        }
    }
}
