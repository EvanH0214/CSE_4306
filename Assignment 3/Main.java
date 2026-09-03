import java.util.*;

public class Main {

    public static int count = 0;     // count variable to track the dfs order of the vertices

    public static void main(String[] args) {
        int[][] adjacencyMatrix = new int[][] {             // hardcoded adjacency matrix for testing
                {0, 1, 0, 0, 1, 1, 0, 0},                   // vertex 0
                {0, 0, 0, 0, 0, 1, 1, 0},                   // vertex 1
                {0, 0, 0, 1, 0, 0, 1, 0},                   // vertex 2
                {0, 0, 0, 0, 0, 0, 0, 1},                   // vertex 3
                {0, 0, 0, 0, 0, 1, 0, 0},                   // vertex 4
                {0, 0, 0, 0, 0, 0, 0, 0},                   // vertex 5
                {0, 0, 0, 0, 0, 0, 0, 1},                   // vertex 6
                {0, 0, 0, 0, 0, 0, 0, 0}                    // vertex 7
        };

        ArrayList<Vertex> graph = createGraph(adjacencyMatrix);     // create a new graph based on the adjacency matrix
        DFS(graph);                                                 // complete a depth-first-search based on the graph

        for (int i = 0; i <= graph.size(); i++) {                   //loop through each vertex in the graph to print the id's in the correct order
            for (int j = 0; j < graph.size(); j++) {                //loop through each vertex in the graph to check id's
                if (graph.get(j).getOrder() == i) {                 // check if the id of the current vertex is i
                    System.out.print(graph.get(j).getID() + ", ");  //print out the id of the vertex
                }
            }
        }
    }

    public static void DFS(ArrayList<Vertex> graph) {             // main depth-first-search method to loop through each vertex in the graph
        for (int i = 0; i < graph.size(); i++) {                  // loop through each vertex in the graph
            if (graph.get(i).getOrder() == 0) {                   // check if the current vertex has been visited yet
                dfs(graph.get(i));                                // call to the recursive depth-first-search method
            }
        }
    }

    public static void dfs(Vertex vertex) {                             // recursive dfs method to check vertex adjacency
        count++;                                                        // increment count variable
        vertex.setOrder(count);                                         // set the order attribute of the current vertex to the value of count
        for (Vertex adjacentVertex : vertex.getAdjacentVertices()) {    // loop through each vertex in the adjacentVertices list of the current vertex
            if (adjacentVertex.getOrder() == 0) {                       // check if the adjacent vertex has been visited
                dfs(adjacentVertex);                                    // recursively call the dfs method on each unvisited vertex
            }
        }

    }

    public static ArrayList<Vertex> createGraph(int[][] adjacencyMatrix) {     // create a graph of vertices based on a given adjacency matrix
        ArrayList<Vertex> graph = new ArrayList<Vertex>();                     // initialize a new array list to store all vertices in the graph
        for (int i = 0; i < adjacencyMatrix.length; i++) {                     // create vertices out of each row in the adjacency matrix
            ArrayList<Vertex> adjacentVertices = new ArrayList<Vertex>();      // create a new arrayList for each adjacent vertex for each vertex in the graph
            Vertex vertex = new Vertex(i, 0, adjacentVertices);          // create a new vertex
            graph.add(vertex);                                                 // add new vertex to the graph
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {                     // loop through each row in the adjacency matrix
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {              // loop through each column in the adjacency matrix
                if (adjacencyMatrix[i][j] == 1) {                              // check if there is an edge at [i][j]
                    for (Vertex vertices : graph) {                            // cycle through each vertex in the graph
                        if (vertices.getID() == j) {                           // check if the id of the vertex is equal to j
                            graph.get(i).addAdjacentVertex(vertices);          // add the vertex in column j to the adjacency list of vertex i
                        }

                    }
                }
            }
        }

        return graph;
    }
}