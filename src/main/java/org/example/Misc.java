package org.example;

public class Misc {
    public static void main(String[] args) {
        System.out.println("Is Integer Palindrome: " + isPalindrome(121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        return (x == rev || x == rev / 10);
    }
}
