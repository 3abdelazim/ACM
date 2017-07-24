import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;

class dfs_recursion {
    public static int nodes, edges;

    public static ArrayList < ArrayList <Integer> > adjaceny_list;

    public static boolean[] visited;
    public static int destination;

    public static boolean DFS(int current_node) {
        if (current_node == destination) {
            return true;
        }

        for (int i = 0; i < adjaceny_list.get(current_node).size(); i++) {
            int node = adjaceny_list.get(current_node).get(i);
            if (!visited[node]) {
                visited[node] = true;
                boolean destination_found = DFS(node);
                if (destination_found) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        nodes = Integer.parseInt(input[0]);
        
        adjaceny_list = new ArrayList <ArrayList <Integer> >();
        for (int i = 0; i < nodes; i++) {
            adjaceny_list.add(new ArrayList <Integer>());
        }

        edges = Integer.parseInt(input[1]);
        for (int i = 0; i < edges; i++) {
            input = br.readLine().split(" ");
            int v1 = Integer.parseInt(input[0]) - 1;
            int v2 = Integer.parseInt(input[1]) - 1;

            adjaceny_list.get(v1).add(v2);
            adjaceny_list.get(v2).add(v1);
        }

        int test_cases = Integer.parseInt(br.readLine());
        while (test_cases > 0) {
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]) - 1;
            int end = Integer.parseInt(input[1]) - 1;

            visited = new boolean[nodes];
            for (int i = 0; i < nodes; i++)
                visited[i] = false;
            visited[start] = true;
            destination = end;

            System.out.println(DFS(start));
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
