import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private final Data data;
    private results res;
 
    public BFS(Data data){
        this.data = data;
    }
    
    private void printInfo(int inMem, int visited){
        int cost = 0;
        Dimensions currentDim = data.goal;
        ArrayList<Dimensions> finalPath = new ArrayList<>();
        finalPath.add(currentDim);
        while(!currentDim.isEqual(data.start)){
            cost += data.map[currentDim.row][currentDim.column];
            currentDim = data.path[currentDim.row][currentDim.column];
            finalPath.add(currentDim);
        }
        res = new results(visited,inMem, cost,finalPath);
        res.printResults();
    }
       
    public results getResults(){
        return res;
    }
    
    public boolean dimensionValid(Dimensions dimensions){
        if(dimensions.row < 0 || dimensions.column < 0 || dimensions.row >= data.dimensionRow || dimensions.column >= data.dimensionColumn) //check if dimensions are at the map
            return false;
        return !(data.path[dimensions.row][dimensions.column].row != -1  || data.map[dimensions.row][dimensions.column] == 0); //check if the node has already been visited and if can be visited
    }
        
    public void BFS(){
        int expanded = 0; //number of expanded nodes   
        if(data.map[data.start.row][data.start.column] == 0 || data.map[data.goal.row][data.goal.column] == 0){
                res = new results(expanded,0, -1, null); //solution was not found, set the result accordingly
                res.printResults(); // print the result   
                return;
        }
        if(data.goal.isEqual(data.start)){ //check if the start is the same as the goal
            printInfo(0, expanded);   //print result
            return; //solution found, return from the method
        }
        Queue<Dimensions> SearchQueue = new LinkedList<>(); //create empty queue for opened nodes
        SearchQueue.add(data.start); // add start node to opened nodes
        int mem = 1; // maximum number of nodes in memory at one time
        while(SearchQueue.size() > 0){ //expand nodes from the queue, run while there is an opened node or until the solution is found
            Dimensions current = SearchQueue.remove(); //expand first node in the queue
            expanded++; //new node is being expanded
            ArrayList<Dimensions> children = new ArrayList<>(); // list of all the possible children of the node
            children.add(0,new Dimensions((current.row)-1, current.column)); //up
            children.add(1,new Dimensions(current.row, (current.column)-1)); //left
            children.add(2,new Dimensions((current.row)+1, current.column)); //down
            children.add(3,new Dimensions(current.row, (current.column)+1)); //right

            for(int i = 0; i < 4; i++){  //check validity of the children and get rid of the invalid ones
                if(dimensionValid(children.get(i))){ //node is valid, not visited yet -> can be opened
                    data.path[children.get(i).row][children.get(i).column] = new Dimensions(current.row, current.column); //save parent of the node to the path
                    if(children.get(i).isEqual(data.goal)){ //goal found
                        printInfo(mem,expanded); //print information
                        return; //return from the function
                    }
                    SearchQueue.add(children.get(i)); //add to search queue
                    if(SearchQueue.size() > mem) mem = SearchQueue.size(); // check the maximum number of nodes in memory
                }
            }
        } 
        res = new results(expanded,mem, -1, null); //solution was not found, set the result accordingly
        res.printResults(); // print the result
    }   
}
