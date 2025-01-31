package graph.floyd_warshall;

public class FloydWarshall {
    private int[][] distance;  // 최단 거리 행렬
    private int numVertices;   // 정점 개수

    // 생성자: 인접 행렬을 입력받아 초기화
    public FloydWarshall(int[][] graph) {
        this.numVertices = graph.length;
        this.distance = new int[numVertices][numVertices];

        // 그래프 복사 (원본 변경 방지)
        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(graph[i], 0, distance[i], 0, numVertices);
        }
    }

    // 플로이드-워셜 알고리즘 실행
    public void runFloydWarshall() {

        for (int k = 0; k < numVertices; k++) {       // 중간 노드
            for (int s = 0; s < numVertices; s++) {   // 시작 노드
                for (int e = 0; e < numVertices; e++) { // 도착 노드
                    // 오버플로우 방지: Integer.MAX_VALUE + x는 오버플로우 발생 가능
                    if (distance[s][k] != Integer.MAX_VALUE && distance[k][e] != Integer.MAX_VALUE) {
                        distance[s][e] = Math.min(distance[s][e], distance[s][k] + distance[k][e]);
                    }
                }
            }
        }
    }

    // 최단 거리 행렬 반환
    public int[][] getDistanceMatrix() {
        return distance;
    }

    // 특정 두 정점 간 최단 거리 반환
    public int getShortestDistance(int start, int end) {
        return distance[start][end];
    }
}
