import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.PriorityQueue;
import java.util.Queue;

class floyd_warshall {
    public static int nodes, edges;
    public static int MAX_INT = 1000000007;

    public static int[][] adjaceny_matrix;

    public static int[][] distance;

    public static boolean floyd() {
        // returns true if a negative cycle exists

        distance = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                distance[i][j] = adjaceny_matrix[i][j];
            }
        }
        for (int i = 0; i < nodes; i++) {
            distance[i][i] = 0;
        }

        // We run the algorithm once to compute shortest paths
        for (int k = 0; k < nodes; k++) {
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    if ( distance[i][k] != MAX_INT &&
                         distance[k][j] != MAX_INT &&
                         distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        // We run it again to detect negative cycles
        for (int k = 0; k < nodes; k++) {
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    if ( distance[i][k] != MAX_INT &&
                         distance[k][j] != MAX_INT &&
                         distance[i][j] > distance[i][k] + distance[k][j]) {
                        return true;
                    }
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
                adjaceny_matrix[i][j] = MAX_INT;
            }
        }

        edges = Integer.parseInt(input[1]);
        for (int i = 0; i < edges; i++) {
            input = br.readLine().split(" ");
            int v1 = Integer.parseInt(input[0]) - 1;
            int v2 = Integer.parseInt(input[1]) - 1;
            int distance = Integer.parseInt(input[2]);

            adjaceny_matrix[v1][v2] = distance;
        }

        boolean negative_cycles = floyd();

        int test_cases = Integer.parseInt(br.readLine());
        while (test_cases > 0) {
            input = br.readLine().split(" ");
            if (negative_cycles) {
                System.out.println("A negative cycle exists");
            } else {
                int start = Integer.parseInt(input[0]) - 1;
                int end = Integer.parseInt(input[1]) - 1;
                if (distance[start][end] == MAX_INT) {
                    System.out.println("No path");
                } else {
                    System.out.println(distance[start][end]);
                }
            }
            test_cases--;
        }
    }
}

/*

1 <----- 2 -----> 4
|        ^
|        |
v        |
3 -----> 5


Sample input:
5 5
1 3 4
3 5 -5
5 2 2
2 1 1
2 4 -2
3
1 4
3 1
4 1

Sample output:
-1
-2
No path


Sample input:
5 5
1 3 4
3 5 -5
5 2 2
2 1 -4
2 4 -2
3
1 4
3 1
4 1

Sample output:
A negative cycle exists
A negative cycle exists
A negative cycle exists

*/
