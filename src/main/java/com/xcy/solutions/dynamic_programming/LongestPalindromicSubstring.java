package com.xcy.solutions.dynamic_programming;

/**
 * 5. Longest Palindromic Substring
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length<2){
            return s;
        }

        boolean[][] dp = new boolean[length][length];
        for (int i=0; i<length; i++){
            dp[i][i] = true;
        }
        int maxLen = 1;
        int begin = 0;
        // from the second last element, e.g. "abaaba", from index 4
        for (int i=length-2; i>=0; i--){
            // [4,5]
            // [3,4][3,5]
            for (int j=i+1;j<=length-1; j++){
                if (s.charAt(i) != s.charAt(j)){
                    dp[i][j] = false;
                }else{
                    if (j-i<3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if(dp[i][j] && (j-i+1)>maxLen){
                    maxLen=j-i+1;
                    begin = i;
                }

            }
        }
        return s.substring(begin, begin+maxLen);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
        assert "abaaba".equals(solution.longestPalindrome("abaaba"));
        assert "b".equals(solution.longestPalindrome("b"));
        assert "bab".equals(solution.longestPalindrome("ababd"));
        assert "bb".equals(solution.longestPalindrome("abbd"));


    }

}
