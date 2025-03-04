package tree.lca;

import java.util.*;

class FastLCA {
    private int[][] parent;
    private int[] depth;
    private List<Integer>[] tree;
    private int n, maxK;

    public FastLCA(int n) {
        this.n = n;
        this.maxK = (int) (Math.log(n) / Math.log(2)) + 1;
        parent = new int[maxK + 1][n];
        depth = new int[n];
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        tree[u].add(v);
        tree[v].add(u);
    }

    private void dfs(int node, int par, int d) {
        parent[0][node] = par;
        depth[node] = d;
        for (int child : tree[node]) {
            if (child != par) {
                dfs(child, node, d + 1);
            }
        }
    }

    public void preprocess(int root) {
        dfs(root, -1, 0);
        for (int k = 1; k <= maxK; k++) {
            for (int i = 0; i < n; i++) {
                if (parent[k - 1][i] != -1) {
                    parent[k][i] = parent[k - 1][parent[k - 1][i]];
                } else {
                    parent[k][i] = -1;
                }
            }
        }
    }

    public int findLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int k = maxK; k >= 0; k--) {
            if (parent[k][u] != -1 && depth[parent[k][u]] >= depth[v]) {
                u = parent[k][u];
            }
        }

        if (u == v) return u;

        for (int k = maxK; k >= 0; k--) {
            if (parent[k][u] != parent[k][v]) {
                u = parent[k][u];
                v = parent[k][v];
            }
        }

        return parent[0][u];
    }
}

