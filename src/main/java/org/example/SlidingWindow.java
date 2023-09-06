package org.example;

import javax.sound.midi.Soundbank;
import java.util.*;

public class SlidingWindow {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("Max in each window of size K: " + Arrays.toString(maxSlidingWindow(arr, 3, 0, arr.length - 1)));
        System.out.println("Max substring with unique char: " + lengthOfLongestSubstring("acbdbefgh"));
        System.out.println("Max substring with unique char: " + lengthOfLongestSubstring1("acbdbefgh"));
        System.out.println("Is Valid parentheses: " + isValidParentheses("()"));
        System.out.println("max diff b/w two elements in arr: " + maxDiffArray(new int[]{7, 8}));
    }

    public static int[] maxSlidingWindow(int[] arr, int k, int si, int ei) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();
        int i = 0;
        while (i < k) {
            while (!dq.isEmpty() && arr[dq.peekLast()] < arr[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
            i++;
        }

        while (i <= ei) {
            if (!dq.isEmpty()) res.add(arr[dq.peekFirst()]);

            while (!dq.isEmpty() && dq.peekFirst() <= i - k) { // Remove all useless indices
                dq.removeFirst();
            }
            while (!dq.isEmpty() && arr[dq.peekLast()] < arr[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
            i++;
        }
        if (!dq.isEmpty()) res.add(arr[dq.peekFirst()]); // process the last window
        return res.stream().mapToInt(x -> x).toArray();
    }

    public static int lengthOfLongestSubstring(String str) {
        int wSi = 0;
        int wEi = 0;
        int max = Integer.MIN_VALUE;
        Map<Character, Integer> counts = new HashMap<>();

        while (wEi < str.length()) {
            char c = str.charAt(wEi);

            if (!counts.containsKey(c)) {
                counts.put(c, 1);
                wEi++;
                int len = wEi - wSi;
                max = Math.max(max, len);
            } else {
                while (wSi < wEi) {
                    char temp = str.charAt(wSi);
                    if (temp != c) {
                        counts.remove(temp);
                        wSi++;
                    } else {
                        counts.remove(temp);
                        wSi++;
                        break;
                    }
                }
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring1(String str) {
        int max = 0;
        Set<Character> set = new HashSet<>();
        int left = 0;
        for (int right = 0; right < str.length(); right++) {
            char curr = str.charAt(right);
            if (!set.contains(curr)) {
                set.add(curr);
                int len = (right - left) + 1;
                max = Math.max(max, len);
            } else {
                while (str.charAt(left) != curr) {
                    set.remove(str.charAt(left));
                    left++;
                }
                set.remove(str.charAt(left));
                left++;
                set.add(curr);
            }
        }
        return max;
    }

    public static boolean isValidParentheses(String str) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (curr == '[' || curr == '(' || curr == '{') {
                stack.push(curr);
            } else if (stack.isEmpty() || stack.pop() != map.get(curr))
                return false;
        }
        return stack.isEmpty();
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    // O(n), O(1)
    public static int maxDiffArray(int[] arr) {
        if (arr.length <= 1) return 0;
        int maxDiff = 0;
        int minElem = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= minElem) maxDiff = Math.max(maxDiff, arr[i] - minElem);
            minElem = Math.min(minElem, arr[i]);
        }
        return maxDiff;
    }

}

