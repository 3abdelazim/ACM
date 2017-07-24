import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Stack;

class dfs_adjacency_matrix {
    public static int nodes, edges;

    public static int[][] adjaceny_matrix;

    public static boolean DFS(int start, int end) {
        Stack <Integer> s = new Stack <Integer>();
        
        boolean[] visited = new boolean[nodes];
        for (int i = 0; i < nodes; i++)
            visited[i] = false;

        s.add(start);
        visited[start] = true;

        while(!s.isEmpty()) {
            int current_node = s.pop();

            if (current_node == end) {
                return true;
            }

            for (int i = 0; i < nodes; i++) {
                if (adjaceny_matrix[current_node][i] == 1 && !visited[i]) {
                    s.add(i);
                    visited[i] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        nodes = Integer.parseInt(input[0]);
        
        adjaceny_matrix = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                adjaceny_matrix[i][j] = 0;
            }
        }

        edges = Integer.parseInt(input[1]);
        for (int i = 0; i < edges; i++) {
            input = br.readLine().split(" ");
            int v1 = Integer.parseInt(input[0]) - 1;
            int v2 = Integer.parseInt(input[1]) - 1;

            adjaceny_matrix[v1][v2] = 1;
            adjaceny_matrix[v2][v1] = 1;
        }

        int test_cases = Integer.parseInt(br.readLine());
        while (test_cases > 0) {
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]) - 1;
            int end = Integer.parseInt(input[1]) - 1;
            System.out.println(DFS(start, end));
            test_cases--;
        }
    }
}

/*

1 --- 2
 \  /  \
  3 --- 5
  
  4 --- 6
   \  /
    7


Sample input:
7 8
1 2
2 3
1 3
3 5
2 5
4 6
6 7
4 7
4
1 2
1 4
1 5
4 7

Sample output:
true
false
true
true

*/
