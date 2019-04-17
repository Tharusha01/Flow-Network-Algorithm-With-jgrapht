import java.util.*;

/*
 * w1697753 - Ishan Tharusha Wijayabahu
 */

public class MaxFlow {

    /*
     *Generating random numbers between 6 and 12 for 2d array index
     */
    Random rand = new Random();
    public int nodes = rand.nextInt((12 - 6) + 1) + 6;
    /*
     *Creating two dimensional array. which is hold edges capacities
     */
    int[] arr1 = new int[nodes];
    int[][] twoDim = new int[nodes][nodes];

    /*
    * Array list.which holds paths of edges.
    */
    public ArrayList<String> weight = new ArrayList<String>();

    public ArrayList<String> pathCapacitiy = new ArrayList<String>();
    /*
    * ArrayList.which holds path and path flows
    */
    public ArrayList<String> finalPathFlow = new ArrayList<>();
    /*
     * ArrayList.which holds paths between two vertexes.
     */
    public ArrayList<String> path = new ArrayList<>();

    public int lastIndex = nodes - 1; //sink
     final int V = nodes;    //Number of vertices in graph

    int rGraph[][] = new int[V][V];

    /* Returns true if there is a weight from source 's' to sink
      't' in residual graph. Also fills parent[] to store the
      weight */

    boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }

    // Returns tne maximum flow from s to t in the given graph
    int fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)


        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store weight
        int parent[] = new int[V];

        int max_flow = 0;  // There is no flow initially

        // Augment the flow while tere is weight from source
        // to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the weight filled by BFS. Or we can say
            // find the maximum flow through the weight found.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the weight
            ArrayList<String> pathFlow = new ArrayList<>();
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;

                pathFlow.add(u + " to " + v);
                Collections.sort(pathFlow);
            }

            finalPathFlow.add(pathFlow + " path flow : "+path_flow);
            //System.out.println(pathFlow + " path flow : "+path_flow);

            // Add weight flow to overall flow
            max_flow += path_flow;
        }
        // Return the overall flow
        return max_flow;
    }

    /*
    * Method. Which generate 2D Array values and return int 2D Array.
    */
    public int[][] generateNodes() {

        /*
         * Declaring variables. Which is holds capacities of nodes.
         */
        int capacity;
        int check;
        lastIndex = (arr1.length) - 1;

        for (int i = 0; i < arr1.length; i++) {

            for (int j = 0; j < twoDim.length; j++) {
                /*
                 * Randomly generate nodes capacities between 5 and 20.
                 */
                capacity = (int) (Math.random() * (20 - 5) + 1) + 5;
                check = (int) (Math.random() * 11);
                /*
                 * Checking 1st column of 2D array.
                 */
                if (j == 0) {
                    arr1[j] = 0;

                }
                /*
                 * Checking last column of 2D array.
                 */
                else if (j == lastIndex) {
                    if (i == lastIndex) {
                        arr1[j] = 0;
                    } else if (i > (lastIndex - 3)) {
                        arr1[j] = capacity;
                        String path1 = i + " to " + j + " weight : " + capacity;
                        path.add(path1);
                        //System.out.println(i + " to " + j + " weight : " + capacity);
                        weight.add(i+","+j);
                        pathCapacitiy.add(Integer.toString(capacity));
                    } else {
                        arr1[j] = 0;
                    }

                }
                /*
                 * Checking 1st row of the 2D array.
                 */
                else if (i == 0) {
                    if (j <= 2) {
                        arr1[j] = capacity;
                        String path1 = i + " to " + j + " weight : " + capacity;
                        path.add(path1);
                        pathCapacitiy.add(Integer.toString(capacity));
                        //System.out.println(i + " to " + j + " weight : " + capacity);
                        weight.add(i+","+j);
                    }
                }
                /*
                 * Checking  other indexes of 2D array.
                 */
                else {
                    if (j == i) {
                        arr1[j] = 0;
                    } else if (i == lastIndex) {
                        arr1[j] = 0;
                    } else {
                        if (check < 5) {
                            arr1[j] = 0;
                        } else {
                            arr1[j] = capacity;
                            String path1 = i + " to " + j + " weight : " + capacity;
                            path.add(path1);
                            pathCapacitiy.add(Integer.toString(capacity));
                            //System.out.println(i + " to " + j + " weight : " + capacity);
                            weight.add(i+","+j);
                        }
                    }
                }
            }
            /*
             * Filling 2D array.
             */
            twoDim[i] = arr1.clone();
        }
        /*
        * Returning 2D array
        */
        return twoDim;
    }
}
