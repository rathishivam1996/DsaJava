package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayGfg {
    public static void main(String[] args) {
//        System.out.println(candies(new int[]{1, 2, 1, 3, 4}));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(7);
        list.add(8);

        System.out.println(maxMin(3, list));
    }

    public static int candies(int[] students) {
        int[] candies = new int[students.length];

        Arrays.fill(candies, 1);

        for (int i = 1; i < students.length; i++) {
            if (students[i] > students[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = students.length - 2; i >= 0; i--) {
            if (students[i] > students[i + 1] && candies[i] < candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }

        int countCandies = 0;
        for (int count : candies) {
            countCandies += count;
        }
        return countCandies;
    }

    public static int maxMin(int k, List<Integer> arr) {
        // Write your code here

        Collections.sort(arr);
        int si = 0;
        int ei = (si + k) - 1;

        int minDiff = Integer.MAX_VALUE;
        while (si <= ei && ei < arr.size()) {
            int a = arr.get(si);
            int b = arr.get(ei);
            int diff = b - a;
            minDiff = Math.min(minDiff, diff);
            si++;
            ei = (si + k) - 1;
        }

        return minDiff;
    }
}
