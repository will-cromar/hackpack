import java.util.*;

public class guasselim {

	public static void main(String[] args) {
		// Test input; should give <1, 2, 3>
		double[][] mat = {{1, 1, 2, 9}, {2, 4, -3, 1}, {3, 6, -5, 0}};
		double[] ans = GaussianElimination(mat);
		System.out.println(Arrays.toString(ans));
	}
	
	// Aug is an n x n + 1 augmented matrix
	static double[] GaussianElimination(double[][] aug) {
		int n = aug.length;
		double[] X = new double[n]; // represents a column vector
		
		// Convert matrix to row-echelon form
		for (int j = 0; j < n - 1; j++) {
			int l = j;
			for (int i = j + 1; i < n; i++)
				// Find the row with the largest column value
				if (Math.abs(aug[i][j]) > Math.abs(aug[l][j]))
					l = i;
			
			// Swap rows
			for (int k = j; k <= n; k++) {
				double tmp = aug[j][k];
				aug[j][k] = aug[l][k];
				aug[l][k] = tmp;
			}
			// Forward elimination
			for (int i = j + 1; i < n; i++)
				for (int k = n; k >= j; k--)
					aug[i][k] -= aug[j][k] * aug[i][j] / aug[j][j];
		}
		
		// Back-substitute
		for (int j = n - 1; j >= 0; j--) {
			double t = 0.0;
			for (int k = j + 1; k < n; k++)
				t += aug[j][k] * X[k];
			
			X[j] = (aug[j][n] - t) / aug[j][j];
		}
		
		return X;
	}

}
