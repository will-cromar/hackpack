import java.util.*;

public class bruteforce {

    public static void main(String[] args) {
        int n = 4, k = 3;

        permutations(new int[k], new boolean[n], 0);
        combinations(new boolean[n], 0, 0, k);
        comboBitwise(n, k);
    }

    static void permutations(int[] perm, boolean[] used, int k) {
        if (k == perm.length) {
            for (int num : perm)
                System.out.print(num);

            System.out.println();
        }

        for (int i = 0; i < perm.length; i++) {
            if (!used[i]) {
                perm[k] = i;
                used[i] = true;
                permutations(perm, used, k + 1);
                used[i] = false;
            }
        }
    }

    static void combinations(boolean[] subset, int i, int cnt, int k) {
        if (i == subset.length && cnt <= k) {
            for (int j = 0; j < subset.length; j++)
                if (subset[j])
                    System.out.print(j);

            System.out.println();
        } else if (i < subset.length) {
            combinations(subset, i + 1, cnt, k);
            subset[i] = true;
            combinations(subset, i + 1, cnt + 1, k);
            subset[i] = false;
        }
    }

    static void comboBitwise(int n, int k) {
        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) > k)
                continue;

            for (int i = 0; i < n; i++) {
                int submask = (1 << i);
                if ((mask & submask) != 0)
                    System.out.print(i);
            }
            System.out.println();
        }
    }

}
