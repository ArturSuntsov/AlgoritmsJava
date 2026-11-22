package practice3;

import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(g[i], -1);
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            if (g[u][v] == -1 || w < g[u][v]) { g[u][v] = w; g[v][u] = w; }
        }
        int[] dist = new int[n];
        boolean[] used = new boolean[n];
        int[] prev = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[s] = 0;
        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int j = 0; j < n; j++) if (!used[j] && (u == -1 || dist[j] < dist[u])) u = j;
            if (dist[u] == Integer.MAX_VALUE) break;
            used[u] = true;
            for (int v = 0; v < n; v++) {
                if (g[u][v] >= 0 && dist[u] + g[u][v] < dist[v]) {
                    dist[v] = dist[u] + g[u][v];
                    prev[v] = u;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        for (int v = t; v != -1; v = prev[v]) path.add(v);
        Collections.reverse(path);
        StringBuilder sb = new StringBuilder("distance=" + dist[t] + " path=");
        for (int i = 0; i < path.size(); i++) { if (i > 0) sb.append(' '); sb.append(path.get(i)); }
        System.out.println(sb);
    }
}