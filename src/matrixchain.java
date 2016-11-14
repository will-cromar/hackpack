import java.awt.Point;
import java.util.*;

public class matrixchain {

	static Point[] matricies;
	static Long[][] memo;

	public static void main(String[] args) {
		/*
		 * matricies = new Matrix[]{ new Matrix(200, 100), new Matrix(100, 2),
		 * new Matrix(2, 30), new Matrix(30, 20), new Matrix(20, 200), new
		 * Matrix(200, 1000), new Matrix(1000, 4), new Matrix(4, 2), new
		 * Matrix(2, 1), new Matrix(1, 1), new Matrix(1, 10) };
		 */
		matricies = new Point[]{
				new Point(8, 2),
				new Point(2, 4),
				new Point(4, 1)
				};
		
		memo = new Long[matricies.length][matricies.length];

		long result = go(0, matricies.length - 1);
		System.out.println(result);
	}

	static Long go(int i, int j) {
		if (i == j)
			return 0L;

		if (memo[i][j] != null)
			return memo[i][j];

		long bestCost = Long.MAX_VALUE;

		// Split on parenthesis [i,k] and [k+1,j]
		for (int k = i; k < j; k++) {
			int A = matricies[i].x;
			int B = matricies[k].y;
			int C = matricies[j].y;
			long curCost = go(i, k) + go(k + 1, j) + A * B * C;
			bestCost = Math.min(bestCost, curCost);
		}

		memo[i][j] = bestCost;
		return bestCost;
	}
}
