import java.util.*;

class Cell{

    // Time Complexity: O(V + E)

    public int row;
    public int column;

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o){

        if(this == o)
            return true;

        if(o == null || getClass() != o.getClass()){
            return false;
        }

        Cell that = (Cell)o;
        if(this.row != that.row || this.column != that.column){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.row, this.column);
    }

    @Override
    public String toString(){
        return Integer.toString(row) + " " + Integer.toString(column);
    }
}

public class SearchMazeUsingDFS {

    private static Map<Cell, Set<Cell>> createGraph(List<List<Integer>> maze){
        Map<Cell, Set<Cell>> graph = new HashMap<>();
        int numRow = maze.size();
        int numCol = maze.get(0).size(); //Assuming maze contains at least one cell
        for(int i=0; i<numRow; i++){
            for(int j=0; j<numCol; j++){
                Cell currentCell = new Cell(i, j);
                if(maze.get(i).get(j) == 0){
                    Set<Cell> cellSet = new HashSet<>();
                    if( i-1>= 0 && maze.get(i-1).get(j) == 0){
                        Cell topCell = new Cell(i-1, j);
                        cellSet.add(topCell);
                        graph.get(topCell).add(currentCell);
                    }
                    if( j-1>= 0 && maze.get(i).get(j-1) == 0){
                        Cell leftCell = new Cell(i, j-1);
                        cellSet.add(leftCell);
                        graph.get(leftCell).add(currentCell);
                    }
                    graph.put(currentCell, cellSet);
                }
            }
        }
        return graph;
    }

    private static boolean depthFirstSearch(Map<Cell, Set<Cell>> graph, Cell start, Cell end, Set<Cell> visited,
                                        List<Cell> path){
        if(start.equals(end)){
            return true;
        }
        visited.add(start);
        Set<Cell> adjacencyList = graph.get(start);
        for(Cell c: adjacencyList){
            if(visited.contains(c))
                continue;
            path.add(c);
            if(depthFirstSearch(graph, c, end, visited, path)){
                return true;
            }
            else{
                path.remove(path.size() -  1);
            }
        }
        return false;
    }

    public static void main(String[] args){
        Integer[] row1 = { 1, 1, 1, 0 };
        Integer[] row2 = { 1, 0, 0, 0 };
        Integer[] row3 = { 1, 1, 0, 1 };
        Integer[] row4 = { 1, 0, 0, 1 };

        List<Integer> rowList1 = Arrays.asList(row1);
        List<Integer> rowList2 = Arrays.asList(row2);
        List<Integer> rowList3 = Arrays.asList(row3);
        List<Integer> rowList4 = Arrays.asList(row4);

        List<List<Integer>> maze = new ArrayList<>();
        maze.add(rowList1);
        maze.add(rowList2);
        maze.add(rowList3);
        maze.add(rowList4);

        Cell start = new Cell(1, 1);
        Cell end = new Cell(0, 3);
        List<Cell> path = new ArrayList<>();
        path.add(start);

        Map<Cell, Set<Cell>> graph = createGraph(maze);
        System.out.println(graph);
        depthFirstSearch(graph, start, end, new HashSet<>(), path);

        for(Cell c: path){
            System.out.println(Integer.toString(c.row) + " " + Integer.toString(c.column));
        }
    }
}
