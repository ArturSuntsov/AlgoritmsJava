package practice3;

import java.util.*;

public class Task3 {
    static int[] p;
    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] eu = new int[m], ev = new int[m], ew = new int[m];
        for (int i = 0; i < m; i++) {
            eu[i] = sc.nextInt(); ev[i] = sc.nextInt(); ew[i] = sc.nextInt();
        }
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> ew[a] - ew[b]);
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        List<Integer> chosen = new ArrayList<>();
        int first = 0;
        for (int i : idx) {
            int a = find(eu[i]), b = find(ev[i]);
            if (a != b) { p[a] = b; first += ew[i]; chosen.add(i); }
        }
        int second = -1;
        for (int ci : chosen) {
            for (int i = 0; i < n; i++) p[i] = i;
            int cost = 0, cnt = 0;
            for (int i : idx) {
                if (i == ci) continue;
                int a = find(eu[i]), b = find(ev[i]);
                if (a != b) { p[a] = b; cost += ew[i]; cnt++; }
            }
            if (cnt == n - 1 && cost > first && (second == -1 || cost < second)) second = cost;
        }
        System.out.println("first=" + first + " second=" + second);
    }
}