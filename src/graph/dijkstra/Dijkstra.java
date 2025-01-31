package graph.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    private List<Edge>[] list;
    private int[] distance;
    private boolean[] visited;
    private int startNode;

    // 생성자를 통해 필요한 데이터를 받음
    public Dijkstra(List<Edge>[] list, int startNode) {
        this.list = list;
        this.startNode = startNode;
        this.distance = new int[list.length];
        this.visited = new boolean[list.length];

        // 거리 배열을 무한대로 초기화
        for (int i = 0; i < list.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
    }

    public int[] dijkstra() {
        PriorityQueue<Edge> q = new PriorityQueue<>();
        distance[startNode] = 0;
        q.offer(new Edge(startNode, 0));

        while (!q.isEmpty()) {
            Edge now = q.poll();
            int currentVertex = now.vertex;

            if (visited[currentVertex]) continue;
            visited[currentVertex] = true;

            for (Edge next : list[currentVertex]) {
                int nextVertex = next.vertex;
                int nextValue = next.value;

                if (distance[nextVertex] > distance[currentVertex] + nextValue) {
                    distance[nextVertex] = distance[currentVertex] + nextValue;
                    q.offer(new Edge(nextVertex, distance[nextVertex]));
                }
            }
        }

        return distance;
    }

    // Edge 클래스 (우선순위 큐 정렬을 위해 Comparable 구현)
    static class Edge implements Comparable<Edge> {
        int vertex;
        int value;

        public Edge(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.value, o.value); // 안전한 정렬 방식
        }
    }
}
