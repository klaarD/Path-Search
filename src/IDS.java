import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 * Implements iterative deepening search
 * Iteratively runs depth limited search
 * checks if the node has already been visited - can cause that sometimes
 * doesnt find the solution at the level where is the solution, but finds it later
 */
public class IDS {
    private boolean foundSolution; // boolean value to check if the solution has already been found
    private final Data data; //data from the given file
    results res; //objet to store the result data
    int expanded; //number of expanded nodes
    int limit; //maximum possible number of the nodes to expand
    boolean over; //indicates if the time limit (3 minutes) is over
    
    public IDS(Data data){
        this.data = data;
        limit = data.dimensionRow*data.dimensionColumn;
        expanded = 0;
        over = false;
    }

    /**
     * Iterative search
     * runs for cycle with different iterating depth limit
     * runs DFS inside of the cycle
     * @param startTime starting time of the program
     */
    public void search(long startTime){
        if(data.map[data.start.row][data.start.column] == 0){ //check if the start is reachable
            res = new results(expanded,0, -1, null);
            res.printResults();
            return;
        }
        if(data.start.isEqual(data.goal)){ // check if the start and goal arent the same nodes
            ArrayList<Dimensions> finalPath = new ArrayList<>();
            finalPath.add(new Dimensions(data.start.row,data.goal.column));
            res = new results(expanded, 0, 0, finalPath);
        }
        int init_limit = abs(data.start.row - data.goal.row) + abs(data.start.column - data.goal.column); //Manhattan distance -> minimal depth where the solution can be found
        for(int i = init_limit; i < limit; i++){ //iteration of depth limited search
            if(over)break;
            DFS(i, startTime);
            if(foundSolution == true) return;       
        }  
        res = new results(expanded,1,-1, null);
        res.printResults();
    }
    
    /**
     * Validation that the dimensions are on the map and that the node hasnt been visited yet
     * @param dimensions
     * @param visited
     * @return 
     */
    public boolean dimensionValid(Dimensions dimensions, ArrayList<Dimensions> visited){
        if(dimensions.row < 0 || dimensions.column < 0 || dimensions.row >= data.dimensionRow || dimensions.column >= data.dimensionColumn)
            return false;
        return !(data.map[dimensions.row][dimensions.column] == 0 || visited.contains(dimensions));
    }

    /**
     * Print final information about the results
     */
    private void printInfo(){
        int cost = 0;
        Dimensions currentDim = data.goal;
        ArrayList<Dimensions> finalPath = new ArrayList<>();
        finalPath.add(currentDim);
        while(!currentDim.isEqual(data.start)){
            cost += data.map[currentDim.row][currentDim.column];
            currentDim = data.path[currentDim.row][currentDim.column];
            finalPath.add(currentDim);
        }
        res = new results(expanded,1, cost,finalPath);
        res.printResults();
        
    }
    
    public results getResults(){
        return res;
    }
    
    /**
     * Checks if the program is running for more than 3 minutes
     * @param startTime starting time of the search
     * @return 
     */
    public boolean overtime(long startTime){
        long currentTime = System.currentTimeMillis();
        long estimatedTime = currentTime - startTime;
        return (estimatedTime > 180000);
    }
    
    /**
     * Recursive depth limited search
     * @param current dimensions of the current node
     * @param visited list of dimensions of already visited nodes
     * @param depth to check if it didnt go over the maximum depth
     * @param startTime starting time of the program
     * @return 
     */
    private boolean DFSUtil(Dimensions current, ArrayList<Dimensions> visited, int depth, long startTime){

       if(overtime(startTime)){             //save result and return
            res = new results(expanded,1, -1, null); //solution was not found, set the result accordingly
            over = true;
            return true;
        } 
        if(depth < 0) return false; //went too deep
        if(depth == 0){
            if(current.isEqual(data.goal)){
                System.out.println(current.row + " " + current.column);
                foundSolution = true;
                return true;
            }
            return false;
        }
        expanded++;
        ArrayList<Dimensions> children = new ArrayList<>(); //list for the successors
        visited.add(current);
        children.add(new Dimensions((current.row)-1, current.column));
        children.add(new Dimensions(current.row, (current.column)-1));
        children.add(new Dimensions((current.row)+1, current.column));
        children.add(new Dimensions(current.row, (current.column)+1));
        
        for(int i = 0; i < 4; i++){  //check validity of the children and get rid of the invalid ones
            if(dimensionValid(children.get(i), visited)){
                if(foundSolution == true) return true;
                data.path[children.get(i).row][children.get(i).column] = new Dimensions(current.row, current.column); //add to path
                if(children.get(i).isEqual(data.goal)){ //solution found
                    printInfo();
                    foundSolution = true;
                    return false;
                }
                 if(DFSUtil(children.get(i), visited,depth-1, startTime) == true) return true; //run until the solution is found
            }
        } 
        return false;
    }

    /**
     * Method to run the recursive DFSUtil method from the starting point with a given depth limit
     * @param limit depth limit
     * @param startTime starting time of the program
     */
    private void DFS(int limit, long startTime){
        ArrayList<Dimensions> visited = new ArrayList<>();      
        for(int i = 0; i < data.dimensionRow; i++){
            for(int j = 0; j < data.dimensionColumn; j++){
                data.path[i][j] = new Dimensions(-1,-1);
            }
        }
        DFSUtil(data.start, visited, limit, startTime);
    }
}
