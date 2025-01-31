package graph.topology;

import java.util.*;

public class TopologySort {
    private List<Integer>[] graph;
    private int[] indegree;
    private int numVertices;

    // 생성자: 그래프 초기화 및 진입 차수 배열 설정
    public TopologySort(int numVertices) {
        this.numVertices = numVertices;
        this.graph = new ArrayList[numVertices];
        this.indegree = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    // 간선 추가 메서드
    public void addEdge(int from, int to) {
        graph[from].add(to);
        indegree[to]++;
    }

    // 위상 정렬 실행 (결과를 리스트로 반환)
    public List<Integer> topologySort() {
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        // 진입 차수가 0인 노드를 큐에 삽입
        for (int i = 0; i < numVertices; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            result.add(now);

            for (int next : graph[now]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        // DAG(유향 비순환 그래프)가 아닐 경우 예외 처리 (사이클 존재)
        if (result.size() != numVertices) {
            throw new IllegalStateException("사이클이 존재하여 위상 정렬이 불가능합니다.");
        }

        return result;
    }
}
