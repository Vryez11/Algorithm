package tree.lca;

import java.util.*;

// 일반적인 LCA (DFS를 사용하여 부모와 깊이를 저장하고 한 단계씩 올라가면서 탐색)
class NaiveLCA {
    private int[] parent;
    private int[] depth;
    private List<Integer>[] tree;
    private int n;

    public NaiveLCA(int n) {
        this.n = n;
        parent = new int[n];
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
        parent[node] = par;
        depth[node] = d;
        for (int child : tree[node]) {
            if (child != par) {
                dfs(child, node, d + 1);
            }
        }
    }

    public void preprocess(int root) {
        dfs(root, -1, 0);
    }

    public int findLCA(int u, int v) {
        while (u != v) {
            if (depth[u] > depth[v]) {
                u = parent[u];
            } else {
                v = parent[v];
            }
        }
        return u;
    }
}