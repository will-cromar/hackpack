import java.util.*;

public class lis {

    public static void main(String[] args) {
        int[] arr = { -7, 10, 9, 2, 3, 8, 8, 1, 2, 3, 4 };
        int[] L = new int[arr.length];
        Arrays.fill(L, Integer.MAX_VALUE / 2);

        int ans = 0;
        for (int num : arr) {
            int idx = Arrays.binarySearch(L, num);

            if (idx < 0) {
                idx = -idx - 1;
            }

            L[idx] = num;
            ans = Math.max(idx, ans);
        }

        System.out.println(ans);
    }

}
