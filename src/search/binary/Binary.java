package search.binary;

public class Binary{

    public static <E extends Comparable<E>> boolean binary (E[] arr, E findValue) {

        boolean find = false;
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            E midVal = arr[mid];

            int compareResult = midVal.compareTo(findValue);

            if (compareResult > 0) {
                end = mid - 1;
            } else if (compareResult < 0) {
                start = mid + 1;
            } else {
                find = true;
                break;
            }
        }

        return find;
    }


}
