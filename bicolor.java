import java.util.*;

public class bicolor {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        while (true) {
            int n = stdin.nextInt();
            if (n == 0)
                break;

            int l = stdin.nextInt();

            ArrayList<Integer>[] edges = new ArrayList[n];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < l; i++) {
                int a = stdin.nextInt();
                int b = stdin.nextInt();

                edges[a].add(b);
            }

            boolean ans = bicolorable(edges, 0);
            System.out.println(ans ? "BICOLORABLE." : "NOT BICOLORABLE.");
        }

    }

    static boolean bicolorable(ArrayList<Integer>[] edges, int s) {
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();

        int[] color = new int[edges.length];
        Arrays.fill(color, -1);
        color[0] = 0;

        boolean ans = true;

        q.offer(0);
        while (!q.isEmpty() && ans) {
            int u = q.poll();
            for (int v : edges[u]) {
                if (color[v] == -1) {
                    color[v] = 1 - color[u];
                    q.push(v);
                } else if (color[v] == color[u]) {
                    ans = false;
                    break;
                }
            }
        }

        return ans;
    }

}
