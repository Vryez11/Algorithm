package search.dfs;

import java.util.ArrayList;

public class DFS {

    public static void DFS (ArrayList<Integer>[] lists, boolean[] visited, int startNode) {
        if (visited[startNode]) return;

        visited[startNode] = true;
        for (Integer integer : lists[startNode]) {
            if (!visited[integer])
                DFS(lists, visited, integer);
        }
    }
}
