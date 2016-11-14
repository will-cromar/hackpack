import java.util.*;

public class fibonacci {
	private static final long MOD = (long) 1e9;

	public static void main(String[] args) {
		Matrix base = new Matrix(new long[][]{{1, 1}, {1, 0}});
		
		int i = 19; // read this from stdin
		
		// result should be 4181
		System.out.println(base.exp(i - 1).mat[0][0]);
	}
	
	private static class Matrix {
		private long[][] mat;
		int m, n;
		
		public Matrix(int m, int n) {
			mat = new long[m][n];
			this.m = m;
			this.n = n;
		}
		
		public Matrix(long[][] mat) {
			this.mat = mat;
			this.m = mat.length;
			this.n = mat[0].length;
		}
		
		public Matrix mult(Matrix m2) {
			int x = this.m;
			int y = this.n;
			int z = m2.n;
			
			Matrix res = new Matrix(x, z);
			
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < z; j++) {
					for (int k = 0; k < y; k++) {
						res.mat[i][j] += (this.mat[i][k] * m2.mat[k][j]) % MOD;
						res.mat[i][j] %= MOD;
					}
				}
			}
			
			return res;
		}
		
		public Matrix exp(long p) {
			Matrix m = this;
			
			if (p == 0)
				return new Matrix(new long[][]{{1, 0}, {0, 1}});
			else if (p == 1)
				return m;
			else if (p % 2 == 1)
				return m.mult(m.exp(p - 1));
			else {
				return m.mult(m).exp(p / 2);
			}
		}
	}
}
