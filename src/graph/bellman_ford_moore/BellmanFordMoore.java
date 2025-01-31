package graph.bellman_ford_moore;

import java.util.ArrayList;
import java.util.List;

public class BellmanFordMoore {
    private List<Edge> edges;  // 에지 리스트
    private int[] distance;    // 거리 배열
    private int numVertices;   // 정점 개수
    private int startNode;     // 시작 정점

    // 생성자: 정점 개수와 시작 노드를 받아 초기화
    public BellmanFordMoore(int numVertices, int startNode) {
        this.numVertices = numVertices;
        this.startNode = startNode;
        this.edges = new ArrayList<>();
        this.distance = new int[numVertices];

        // 거리 배열 초기화 (INF)
        for (int i = 0; i < numVertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[startNode] = 0; // 시작 정점의 거리는 0
    }

    // 간선 추가 메서드
    public void addEdge(int start, int end, int weight) {
        edges.add(new Edge(start, end, weight));
    }

    // 벨만-포드 알고리즘 실행 (음수 사이클이 있으면 false 반환)
    public boolean bellmanFord() {
        // V-1번 반복하며 최단 거리 갱신
        for (int i = 0; i < numVertices - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.start] != Integer.MAX_VALUE &&
                        distance[edge.end] > distance[edge.start] + edge.weight) {
                    distance[edge.end] = distance[edge.start] + edge.weight;
                }
            }
        }

        // 음수 사이클 존재 여부 확인
        for (Edge edge : edges) {
            if (distance[edge.start] != Integer.MAX_VALUE &&
                    distance[edge.end] > distance[edge.start] + edge.weight) {
                return false; // 음수 사이클 존재
            }
        }

        return true; // 음수 사이클 없음
    }

    // 특정 노드까지의 최단 거리 반환
    public int getDistance(int node) {
        return distance[node];
    }

    // 최단 거리 배열 반환
    public int[] getDistances() {
        return distance.clone();
    }

    // 내부 클래스: 에지 정보 저장
    private static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
