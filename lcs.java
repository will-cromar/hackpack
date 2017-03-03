import java.util.*;

public class lcs {
    // Memo for traceback function. Used purely to make my runtime analysis
    // easier.
    static String[][] memo;

    public static void main(String[] args) {
        // Should give "refer"
        String ans = findLCS("firefighter", "rethgiferif");
        System.out.println(ans);
    }

    static String findLCS(String s1, String s2) {
        // Find length
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < dp.length - 1; i++) {
            for (int j = 0; j < dp[i].length - 1; j++) {
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                else
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }

        memo = new String[dp.length][dp[0].length];
        return traceback(dp, s1, s2, s1.length(), s2.length());
    }

    static String traceback(int[][] dp, String s1, String s2, int i, int j) {
        if (i == 0 || j == 0)
            return "";
        else if (memo[i][j] != null)
            return memo[i][j];

        String res = null;
        if (s1.charAt(i - 1) == s2.charAt(j - 1))
            res = traceback(dp, s1, s2, i - 1, j - 1) + s1.charAt(i - 1);
        else if (dp[i][j - 1] > dp[i - 1][j])
            res = traceback(dp, s1, s2, i, j - 1);
        else if (dp[i][j - 1] < dp[i - 1][j])
            res = traceback(dp, s1, s2, i - 1, j);
        else
            res = longer(traceback(dp, s1, s2, i, j - 1), traceback(dp, s1, s2, i - 1, j));

        memo[i][j] = res;
        return res;
    }

    static String longer(String s1, String s2) {
        // If lengths are equal, return lexicographically smaller string
        if (s1.length() == s2.length())
            return s1.compareTo(s2) < 0 ? s1 : s2;
        // Return longer string
        else
            return s1.length() > s2.length() ? s1 : s2;
    }
}