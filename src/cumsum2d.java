import java.util.Arrays;

public class cumsum2d {

    public static void main(String[] args) {
        int[][] mat = { { 1, 2, 3, 4 }, { 4, 1, 2, 3 }, { 1, 1, 4, 4 } };
        int[][] ans = prefixSumMatrix(mat);

        // This should be 13
        System.out.println(rangeSum(ans, 1, 0, 2, 2));
    }

    static int[][] prefixSumMatrix(int[][] mat) {
        int[][] res = new int[mat.length + 1][mat[0].length + 1];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                res[i + 1][j + 1] = res[i][j + 1] + res[i + 1][j] - res[i][j] + mat[i][j];
            }
        }

        return res;
    }

    static int rangeSum(int[][] psMat, int r1, int c1, int r2, int c2) {
        return psMat[r2 + 1][c2 + 1] - psMat[r2 + 1][c1] - psMat[r1][c2 + 1] + psMat[r1][c1];
    }

}
