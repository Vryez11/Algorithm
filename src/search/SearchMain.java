package search;

import search.binary.Binary;

public class SearchMain {

    public static void main(String[] args) {

        Integer[] arr = {5, 7, 10, 11, 50, 61, 68, 75, 100, 120, 153};

        boolean result = Binary.binary(arr, 93);
        System.out.println("result = " + result);
    }
}
