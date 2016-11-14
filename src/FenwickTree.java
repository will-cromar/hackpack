
public class FenwickTree {
	int[] tree;

	public FenwickTree(int size) {
		tree = new int[size];
	}

	public void addValueToIndex(int value, int index) {
		int i = index;

		while (i < tree.length) {
			tree[i] += value;
			i += Integer.lowestOneBit(i);
		}
	}

	public int prefixSum(int index) {
		int i = index;
		int sum = 0;

		while (i > 0) {
			sum += tree[i];
			i -= Integer.lowestOneBit(i);
		}

		return sum;
	}
	
	public int suffixSum(int index) {
		int total = prefixSum(tree.length - 1);
		
		return total - prefixSum(index);
	}
}
