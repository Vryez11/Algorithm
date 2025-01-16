package sort.radix;

import java.util.Arrays;

//자릿수를 배열에 저장, 배열 합을 이용하여 인덱스 계산 줄임.

public class RadixSortV2 {

    public static void radixSort(int[] arr) {
        int maxNum = Arrays.stream(arr).max().getAsInt();
        int maxDigit = (int) Math.log10(maxNum) + 1;

        int[] output = new int[arr.length];
        int[] count = new int[10];

        int jarisu = 1;

        for (int d = 0; d < maxDigit; d++) {
            Arrays.fill(count, 0);

            // 각 숫자의 자리수를 기준으로 개수 카운트
            for (int num : arr) {
                int digit = (num / jarisu) % 10;
                count[digit]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = arr.length - 1; i >= 0; i--) {
                int digit = (arr[i] / jarisu) % 10;
                output[--count[digit]] = arr[i];
            }

            System.arraycopy(output, 0, arr, 0, arr.length);

            jarisu *= 10;
        }
    }
}
