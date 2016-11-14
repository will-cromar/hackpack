import java.util.*;

public class mst {
    static int[] parent;

    public static void main(String[] args) {
        int numVerts = 0; // read these from stdin
        int numEdges = 0;

        Edge[] edges = new Edge[numEdges];

        // Put everything it its own set
        parent = new int[numVerts];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;

        // Read in edges...

        Arrays.sort(edges, (a, b) -> a.cost - b.cost);

        int costTotal = 0;
        for (Edge e : edges) {
            if (find(e.x) == find(e.y))
                continue;

            union(e.x, e.y);
            costTotal += e.cost;
        }
    }

    private static int find(int a) {
        if (parent[a] != a)
            parent[a] = find(parent[a]);
        return parent[a];
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        parent[aRoot] = parent[bRoot];
    }

    public static boolean completeSpanningTree() {
        boolean foundOrphan = false;

        for (int i = 0; i < parent.length; i++) {
            if (i == parent[i]) {
                if (foundOrphan)
                    return false;

                foundOrphan = true;
            }
        }

        return true;
    }

    private static class Edge {
        int x, y;
        int cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
