package graph.minimum_spanning_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {
    private int[] parent;
    private int[] rank;  // 랭크 배열 추가 (유니온 최적화)
    private PriorityQueue<Edge> queue;
    private int numVertices;

    // 생성자: 정점 개수를 입력받아 초기화
    public MinimumSpanningTree(int numVertices) {
        this.numVertices = numVertices;
        this.queue = new PriorityQueue<>();
        this.parent = new int[numVertices];
        this.rank = new int[numVertices];

        // 유니온 파인드 초기화
        for (int i = 0; i < numVertices; i++) {
            parent[i] = i;
            rank[i] = 1; // 랭크 초기값 1
        }
    }

    // 간선 추가 메서드
    public void addEdge(int start, int end, int weight) {
        queue.add(new Edge(start, end, weight));
    }

    // 크루스칼 알고리즘을 이용한 최소 신장 트리 계산
    public List<Edge> getMinimumSpanningTree() {
        List<Edge> mstEdges = new ArrayList<>();
        int useEdge = 0;

        while (!queue.isEmpty() && useEdge < numVertices - 1) {
            Edge now = queue.poll();

            if (find(now.start) != find(now.end)) {
                union(now.start, now.end);
                mstEdges.add(now);
                useEdge++;
            }
        }

        // 그래프가 연결되지 않은 경우(모든 간선을 사용했는데도 MST 완성 안됨)
        if (useEdge != numVertices - 1) {
            throw new IllegalStateException("최소 신장 트리를 만들 수 없습니다. (그래프가 연결되지 않음)");
        }

        return mstEdges;
    }

    // 유니온 연산 (랭크 기반 최적화 적용)
    private void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (rank[a] > rank[b]) {
                parent[b] = a;
            } else if (rank[a] < rank[b]) {
                parent[a] = b;
            } else {
                parent[b] = a;
                rank[a]++;
            }
        }
    }

    // 경로 압축을 적용한 루트 찾기 (Find 연산)
    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    // 내부 클래스: 간선 정보 저장
    public static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "(" + start + " - " + end + ", " + weight + ")";
        }
    }
}
