import java.util.*;

public class MaxFlowDinics {
	static ArrayList<Edge>[] adj;
	static int[] level;
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		adj = new ArrayList[6];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		level = new int[6];
		
		// Quick test -- the answer should be 23
		Edge.addToGraph(adj, 0, 1, 16 );
		Edge.addToGraph(adj, 0, 2, 13 );
		Edge.addToGraph(adj, 1, 2, 10 );
		Edge.addToGraph(adj, 1, 3, 12 );
		Edge.addToGraph(adj, 2, 1, 4 );
		Edge.addToGraph(adj, 2, 4, 14);
		Edge.addToGraph(adj, 3, 2, 9 );
		Edge.addToGraph(adj, 3, 5, 20 );
		Edge.addToGraph(adj, 4, 3, 7 );
		Edge.addToGraph(adj, 4, 5, 4);
		
		System.out.println(maxFlow(0, 5));
	}

	static boolean computeLevelGraph(int s, int t) {
		Arrays.fill(level, -1);
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		level[s] = 0;
		q.offer(s);
		
		while (!q.isEmpty()) {
			int u = q.poll();
			
			for (Edge e : adj[u]) {
				if (level[e.v] == -1 && e.flow < e.cap) {
					q.offer(e.v);
					level[e.v] = level[u] + 1;
				}
			}
		}
		
		return level[t] >= 0;
	}
	
	static long sendFlow(int[] ptr, int u, int t, long f) {
		if (u == t) {
			return f;
		}
		
		for (; ptr[u] < adj[u].size(); ptr[u]++) {
			Edge e = adj[u].get(ptr[u]);
			
			if (level[e.v] == level[u] + 1 && e.flow < e.cap) {
				long df = sendFlow(ptr, e.v, t, Math.min(f, e.cap - e.flow));
				
				if (df > 0) {
					e.flow += df;
					e.rev.flow -= df;
					
					return df;
				}
			}
		}
		
		return 0;
	}
	
	static long maxFlow(int s, int t) {
		if (s == t)
			return -1;
		
		long total = 0;
		
		while (computeLevelGraph(s, t)) {
			while (true) {
				long flow = sendFlow(new int[adj.length], s, t, Integer.MAX_VALUE);
				if (flow == 0)
					break;
				else
					total += flow;
			}
		}
		
		return total;
	}
	
	static void minCutSet(int s, int t) {
		boolean[] seen = new boolean[adj.length];
		ArrayDeque<Integer> stk = new ArrayDeque<>();
		stk.push(s);
		seen[s] = true;
		
		while (!stk.isEmpty()) {
			int u = stk.pop();
			
			for (Edge e : adj[u]) {
				if (!seen[e.v] && e.cap - e.flow > 0) {
					seen[e.v] = true;
					stk.push(e.v);
				}
			}
		}
		
		for (int u = 0; u < adj.length; u++) {
			for (Edge e : adj[u]) {
				if (seen[e.u] && !seen[e.v]) {
					System.out.printf("%d %d%n", e.u + 1, e.v + 1);
				}
			}
		}
	}
	
	private static class Edge {
		int u, v;
		long cap, flow;
		Edge rev;
		
		public Edge(int u, int v, long cap) {
			this.u = u;
			this.v = v;
			this.cap = cap;
			flow = 0;
		}
		
		static void addToGraph(ArrayList<Edge>[] adj, int u, int v, long cap) {
			Edge fwd = new Edge(u, v, cap);
			Edge bck = new Edge(v, u, 0);
			
			fwd.rev = bck;
			bck.rev = fwd;
			
			adj[u].add(fwd);
			adj[v].add(bck);
		}
	}

}
