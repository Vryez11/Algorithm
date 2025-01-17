package search.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void BFS( ArrayList<Integer>[] lists, boolean[] visited, int startNode) {
        if (!visited[startNode]) return;
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (Integer num : lists[currentNode]) {
                if (!visited[num]) {
                    visited[num] = true;
                    queue.add(num);
                }
            }
        }
    }
}
