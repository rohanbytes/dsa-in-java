/*
DFS is a graph traversal algorithm to visit all the vertices in a graph given a starting vertex.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class DepthFirstSearch {

public static ArrayList<ArrayList<Integer>> buildAdjacencyList(ArrayList<Integer> vertices, ArrayList<Integer[]> edges) {
    ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(vertices.size());
    for(int i=0; i<vertices.size(); i++){
        adjacencyList.add(new ArrayList<Integer>());
    }
    for(int i=0; i<edges.size(); i++){
        Integer[] edge = edges.get(i);
        adjacencyList.get(edge[0]).add(edge[1]);
        adjacencyList.get(edge[1]).add(edge[0]);
    }
    return adjacencyList;
}

public static void dfs(int source, ArrayList<ArrayList<Integer>> adjacencyList, ArrayList<Boolean> visited, ArrayList<Integer> traversedVertices) {
    if(visited.get(source) == Boolean.TRUE)
        return;
    visited.set(source, Boolean.TRUE);
    traversedVertices.add(source);
    ArrayList<Integer> neighbours = adjacencyList.get(source);
    for(int i=0; i<neighbours.size(); i++){
        dfs(neighbours.get(i), adjacencyList, visited, traversedVertices);
    }
}

public static void main(String[] args) throws FileNotFoundException, IOException {
    String fileName = "test-cases/DepthFirstSearch.csv";
    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
    int numOfTestCases = Integer.parseInt(bufferedReader.readLine());
    for(int i=0; i<numOfTestCases; i++){
        ArrayList<Integer> vertices;
        ArrayList<Integer[]> edges = new ArrayList<>();
        vertices = Arrays.stream(bufferedReader.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
        int numOfEdges = Integer.parseInt(bufferedReader.readLine());
        for(int j=0; j<numOfEdges; j++){
            edges.add(Arrays.stream(bufferedReader.readLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new));  
        }
        int source = Integer.parseInt(bufferedReader.readLine());
        ArrayList<Integer> traversedVertices = new ArrayList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        for(int k=0;k<vertices.size(); k++){
            visited.add(Boolean.FALSE);
        }
        ArrayList<ArrayList<Integer>> adjacencyList = buildAdjacencyList(vertices, edges);
        dfs(source, adjacencyList, visited, traversedVertices);
        System.out.println(traversedVertices.toString());
    }
}
}