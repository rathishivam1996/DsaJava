package org.example;

import javax.swing.text.html.parser.Entity;
import java.util.*;

public class Subsequence {
    public static void main(String[] args) {
        String seq = "abc";
        List<String> allSubSeq = new ArrayList<>();
        allSubsequence(seq, "", allSubSeq, 0, seq.length());
        System.out.println("allSubSeq: " + allSubSeq + " len: " + allSubSeq.size());

        String seq1 = "1122";
        System.out.println("Count of SubSeq with unique elements: " + countSubSeqUniqueElem(seq1));

        System.out.println("SubSeq whose sum is perfect square: " + seqSumPerfectSquare("33381", "", 0, 0));

        HashMap<String, Boolean> map = new HashMap<>();
        System.out.println("SubSeq whose sum is perfect square Mem : " + hasPerfectSquareSumSubSeqMem("33381", "", 0, 0, map));

        HashMap<String, Integer> countMap = new HashMap<>();
        System.out.println("count SubSeq whose sum is perfect square mem: " + countPerfectSquareSumSubSeqMem("33381", "", 0, 0, countMap));

        System.out.println("Max avg sub seq: " + maxAvgSubSeq(new int[]{1, -1, 2, 3}, "", 0, 0));

        System.out.println("max avg sub seq by sorting: " + maxAvgSubSeqSorting(new int[]{1, -1, 2, 3}));

        System.out.println("lcs: " + lcs("aabcd", 0, "aabef", 0, ""));

        HashMap<String, Integer> mem = new HashMap<>();
        System.out.println("lcs mem: " + lcsMem("aabcd", 0, "aabef", 0, "", mem));
        for (Map.Entry<String, Integer> entry : mem.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        int[][] dp = new int[6][6];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println("lcs mem: " + lcsMem("aabcd", 0, "aabef", 0, "", dp));
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] != -1) {
//                    System.out.println(i + "," + j + " : " + dp[i][j]);
                }
            }
        }

        String lps = "BANANA";
        System.out.println("longestPalindromeSubSeq: " + longestPalindromeSubSeq(lps, 0, lps.length() - 1));
    }

    public static void allSubsequence(String seq, String ans, List<String> allSubSeq, int si, int ei) {
        if (si == ei) {
            allSubSeq.add(ans);
            return;
        }
        char ch = seq.charAt(si);
        allSubsequence(seq, ans + ch, allSubSeq, si + 1, ei);
        allSubsequence(seq, ans, allSubSeq, si + 1, ei);
    }

    public static int countSubSeqUniqueElem(String seq) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        // count freq. of elements. We can choose 1 from each freq.
        // if freq is x => x (XcR) + 1(not choosing)
        for (int i = 0; i < seq.length(); i++) {
            char ch = seq.charAt(i);
            if (freqMap.containsKey(ch)) {
                int currCount = freqMap.get(ch);
                freqMap.put(ch, currCount + 1);
            } else {
                freqMap.put(ch, 1);
            }
        }
        int count = 1;
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            count *= entry.getValue() + 1;
        }
        // for empty seq
        return count - 1;
    }

    public static boolean seqSumPerfectSquare(String seq, String ans, int sum, int si) {
        if (si == seq.length()) {
            int sqrt = (int) Math.sqrt(sum);
            return sum != 0 && sqrt * sqrt == sum;
        }
        int ch = Integer.parseInt(Character.toString(seq.charAt(si)));
        if (seqSumPerfectSquare(seq, ans + ch, sum + ch, si + 1)) {
//            System.out.println(ans);
            return true;
        }
        if (seqSumPerfectSquare(seq, ans, sum, si + 1)) {
//            System.out.println(ans);
            return true;
        }
        return false;
    }

    public static boolean hasPerfectSquareSumSubSeqMem(String seq, String ans, int sum, int si, HashMap<String, Boolean> map) {
        if (si == seq.length()) {
            int sqrt = (int) Math.sqrt(sum);
            return sum != 0 && sqrt * sqrt == sum;
        }
        // return from memory
        String memoKey = si + "," + sum;
        if (map.containsKey(memoKey)) return map.get(memoKey);
        // calculate
        int ch = Integer.parseInt(Character.toString(seq.charAt(si)));
        boolean inc = hasPerfectSquareSumSubSeqMem(seq, ans + ch, sum + ch, si + 1, map);
        boolean exc = hasPerfectSquareSumSubSeqMem(seq, ans, sum, si + 1, map);
        // return result
        boolean res = inc || exc;
        map.put(memoKey, res);
        return res;
    }

    public static int countPerfectSquareSumSubSeqMem(String seq, String ans, int sum, int si, HashMap<String, Integer> map) {
        if (si == seq.length()) {
            int sqrt = (int) Math.sqrt(sum);
            int res = (sum != 0 && sqrt * sqrt == sum) ? 1 : 0;
//            if (res == 1) System.out.println(ans);
            return res;
        }
        // return from memory
        String memoKey = si + "," + sum;
        if (map.containsKey(memoKey)) return map.get(memoKey);
        // calculate
        int ch = Integer.parseInt(Character.toString(seq.charAt(si)));
        int inc = countPerfectSquareSumSubSeqMem(seq, ans + ch, sum + ch, si + 1, map);
        int exc = countPerfectSquareSumSubSeqMem(seq, ans, sum, si + 1, map);
        // return result
        int res = inc + exc;
        map.put(memoKey, res);
        return res;
    }

    public static double maxAvgSubSeq(int[] arr, String ans, int sum, int si) {
        if (si == arr.length) {
            if (ans.length() != 0) {
                return sum / ans.length();
            }
            return 0;
        }

        int ch = arr[si];
        double inc = maxAvgSubSeq(arr, ans + ch, sum + ch, si + 1);
        double exc = maxAvgSubSeq(arr, ans, sum, si + 1);

        return Math.max(inc, exc);
    }

    public static double maxAvgSubSeqSorting(int[] arr) {
        Arrays.sort(arr);
        double sum = 0;
        double maxAvg = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            double avg = sum / i + 1;

            maxAvg = Math.max(maxAvg, avg);
        }
        return maxAvg;
    }

    public static int lcs(String a, int aSi, String b, int bSi, String ans) {
        if (aSi == a.length() || bSi == b.length()) {
//            System.out.println(ans);
            return 0;
        }
        if (a.charAt(aSi) == b.charAt(bSi)) {
            return 1 + lcs(a, aSi + 1, b, bSi + 1, ans + a.charAt(aSi));
        } else {
            return Math.max(lcs(a, aSi + 1, b, bSi, ans), lcs(a, aSi, b, bSi + 1, ans));
        }
    }

    public static int lcsMem(String a, int aSi, String b, int bSi, String ans, HashMap<String, Integer> map) {
        if (aSi == a.length() || bSi == b.length()) {
//            System.out.println(ans);
            return 0;
        }
        String memKey = aSi + "," + bSi;
        if (map.containsKey(memKey)) return map.get(memKey);

        if (a.charAt(aSi) == b.charAt(bSi)) {
            map.put(memKey, 1 + lcsMem(a, aSi + 1, b, bSi + 1, ans + a.charAt(aSi), map));
        } else {
            map.put(memKey, Math.max(lcsMem(a, aSi + 1, b, bSi, ans, map), lcsMem(a, aSi, b, bSi + 1, ans, map)));
        }
        return map.get(memKey);
    }

    public static int lcsMem(String a, int aSi, String b, int bSi, String ans, int[][] map) {
        if (aSi == a.length() || bSi == b.length()) {
//            System.out.println(ans);
            return 0;
        }
//        String memKey = aSi + "," + bSi;
        if (map[aSi][bSi] != -1) return map[aSi][bSi];

        if (a.charAt(aSi) == b.charAt(bSi)) {
            map[aSi][bSi] = 1 + lcsMem(a, aSi + 1, b, bSi + 1, ans + a.charAt(aSi), map);
        } else {
            map[aSi][bSi] = Math.max(lcsMem(a, aSi + 1, b, bSi, ans, map), lcsMem(a, aSi, b, bSi + 1, ans, map));
        }
        return map[aSi][bSi];
    }

    public static int longestPalindromeSubSeq(String a, int si, int ei) {
        if (si > ei) return 0;
        if (si == ei) return 1;
        if (a.charAt(si) == a.charAt(ei) && (si + 1 == ei)) return 2;

        if (a.charAt(si) == a.charAt(ei)) {
            return longestPalindromeSubSeq(a, si + 1, ei - 1) + 2;
        } else {
            return Math.max(longestPalindromeSubSeq(a, si + 1, ei),
                    longestPalindromeSubSeq(a, si, ei - 1));
        }
    }


}
