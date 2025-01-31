package graph.unionfind;

public class UnionFind {
    private int[] parent;
    private int[] rank; // (선택적) 랭크 배열 추가

    // 생성자: 노드 개수를 입력받아 parent 배열 초기화
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size]; // (선택적) 랭크 초기화

        for (int i = 0; i < size; i++) {
            parent[i] = i; // 자기 자신을 부모로 초기화
            rank[i] = 1;   // (선택적) 모든 노드의 초기 랭크를 1로 설정
        }
    }

    // 특정 원소의 대표(루트) 찾기 (경로 압축 적용)
    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]); // 경로 압축 최적화
        }
        return parent[x];
    }

    // 두 집합을 합치는 연산 (랭크 기반 최적화 추가)
    public void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            // (선택적) 랭크 기반 최적화: 더 낮은 랭크를 높은 랭크에 연결
            if (rank[a] > rank[b]) {
                parent[b] = a;
            } else if (rank[a] < rank[b]) {
                parent[a] = b;
            } else {
                parent[b] = a;
                rank[a]++; // 같은 랭크라면 a의 랭크 증가
            }
        }
    }

    // 두 원소가 같은 집합에 속해 있는지 확인
    public boolean isConnected(int a, int b) {
        return find(a) == find(b);
    }
}
