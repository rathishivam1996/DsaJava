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

        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println("binary search: " + binarySearch(arr, 0, arr.length - 1, 6));

        System.out.println("binary search: " + binarySearchFirstOcc(new int[]{1, 2, 2, 2, 3, 3, 4}, 0));

        int[] arrRotated = new int[]{1, 2, 3, 0};
        System.out.println("binary search rotated array: " + binarySearchRotated(arrRotated, 2));
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

    public static int binarySearch(int[] arr, int si, int ei, int key) {
//        int si = 0;
//        int ei = arr.length - 1;

        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (key == arr[mid]) {
                return mid;
            } else if (key < arr[mid]) {
                ei = mid - 1;
            } else {
                si = mid + 1;
            }
        }
        return -1;
    }

    // O(log(n))
    public static int binarySearchFirstOcc(int[] arr, int k) {
        int si = 0;
        int ei = arr.length - 1;
        int res = -1;

        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (k == arr[mid]) {
                // found candidate
                res = mid;
                // keep on searching left
                ei = mid - 1;
            } else if (k < arr[mid]) {
                ei = ei - 1;
            } else {
                si = mid + 1;
            }
        }
        return res;
    }

    public static int binarySearchRotated(int[] arr, int target) {
        int si = 0;
        int ei = arr.length - 1;

        // if (si == ei) return arr[si] == target ? si : -1;
        // single element is always sorted, or if array is already sorted
        if (arr[si] <= arr[ei]) return binarySearch(arr, si, ei, target);

        int minIdx = 1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            int next = (mid + 1) % arr.length;
            int prev = (mid + arr.length - 1) % arr.length;
            if (arr[mid] < arr[prev] && arr[mid] < arr[next]) {
                minIdx = mid;
                break;
            } else if (arr[mid] < arr[0]) {
                ei = mid - 1;
            } else if (arr[mid] > arr[arr.length - 1]) {
                si = mid + 1;
            }
        }

        int a = binarySearch(arr, 0, minIdx - 1, target);
        int b = binarySearch(arr, minIdx, arr.length - 1, target);

        return a == -1 ? b : a;
    }
}
