import java.util.*;

public class floyds {

    public static void main(String[] args) {
        int intersections = 0; // read in from stdin

        double[][] dists = new double[intersections][intersections];
        for (int i = 0; i < dists.length; i++) {
            for (int j = 0; j < dists.length; j++) {
                if (i == j)
                    dists[i][j] = 0;
                else
                    dists[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        // Read in inputs to fill in distance matrix...
        for (int pivot = 0; pivot < dists.length; pivot++) {
            for (int start = 0; start < dists.length; start++) {
                for (int end = 0; end < dists.length; end++) {
                    dists[start][end] = Math.min(dists[start][end], dists[start][pivot] + dists[pivot][end]);
                }
            }
        }
    }
}
