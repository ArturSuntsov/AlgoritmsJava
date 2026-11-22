package practice3;

import java.util.*;

public class Task1 {
    static class MyStack {
        int[] a; int size = 0;
        MyStack(int cap) { a = new int[cap]; }
        void push(int x) { a[size++] = x; }
        int pop() { return a[--size]; }
        boolean empty() { return size == 0; }
    }

    static class MyQueue {
        int[] a; int head = 0, tail = 0;
        MyQueue(int cap) { a = new int[cap]; }
        void add(int x) { a[tail++] = x; }
        int poll() { return a[head++]; }
        boolean empty() { return head == tail; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt();
        String mode = sc.next();
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            g[u].add(v);
            g[v].add(u);
        }
        boolean[] used = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        List<Integer> order = new ArrayList<>();
        int checked = 0;
        used[s] = true;
        if (mode.equals("A")) {
            MyStack st = new MyStack(n);
            st.push(s);
            while (!st.empty()) {
                int u = st.pop();
                order.add(u);
                for (int v : g[u]) {
                    checked++;
                    if (!used[v]) { used[v] = true; parent[v] = u; st.push(v); }
                }
            }
        } else {
            MyQueue q = new MyQueue(n);
            q.add(s);
            while (!q.empty()) {
                int u = q.poll();
                order.add(u);
                for (int v : g[u]) {
                    checked++;
                    if (!used[v]) { used[v] = true; parent[v] = u; q.add(v); }
                }
            }
        }
        StringBuilder sb = new StringBuilder("order=");
        for (int i = 0; i < order.size(); i++) { if (i > 0) sb.append(' '); sb.append(order.get(i)); }
        sb.append(" checked=").append(checked);
        sb.append(" parent=");
        for (int i = 0; i < n; i++) { if (i > 0) sb.append(' '); sb.append(parent[i]); }
        System.out.println(sb);
    }
}