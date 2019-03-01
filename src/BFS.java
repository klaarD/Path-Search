import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private final Data data;
    
    public BFS(Data data){
        this.data = data;
    }
    
    private void printInfo(int inMem, Dimensions path[][], ArrayList<Dimensions> visited){
        int cost = 0;
        Dimensions currentDim = data.goal;
        
        System.out.print("Expanded nodes: ");
        for(int k = 0; k < visited.size(); k++){
            System.out.print("(" + visited.get(k).row+ "," + visited.get(k).column + ")");
        }
        System.out.println();
        System.out.println("Number of expanded nodes: " + visited.size());
                      
        System.out.println("Maximum number of nodes held in memory: " + inMem);
                      
        System.out.print("Path: ");
        System.out.print("(" + currentDim.row + "," + currentDim.column + ")");
        while(!currentDim.isEqual(data.start)){
            cost += data.map[currentDim.row][currentDim.column];
            currentDim = path[currentDim.row][currentDim.column];
            System.out.print("(" + currentDim.row + "," + currentDim.column + ")");
        }
        System.out.println();
        System.out.println("cost = " + cost);
    }
       
    public boolean dimensionValid(Dimensions dimensions, ArrayList<Dimensions> visited, Queue<Dimensions> SearchQ){
        if(dimensions.row < 0 || dimensions.column < 0 || dimensions.row >= data.dimensionRow || dimensions.column >= data.dimensionColumn)
            return false;
        if(data.map[dimensions.row][dimensions.column] == 0 || SearchQ.contains(dimensions) || visited.contains(dimensions))
            return false;
        return true;     
    }
        
    public void BFS(){
        Queue<Dimensions> SearchQueue = new LinkedList<>();         //create empty queue for opened nodes
        Dimensions path[][] = new Dimensions[data.dimensionRow][data.dimensionColumn];         //create path map
        
        for(int i = 0; i < data.dimensionRow; i++)
            for(int j = 0; j < data.dimensionColumn; j++)
                path[i][j] = new Dimensions(-1,-1);
          
        ArrayList<Dimensions> visited = new ArrayList<>(); //array for already visited nodes
        SearchQueue.add(data.start); // add start node to opened nodes
        int mem = 1;
        
        //run while there is an opened node or until the solution is found
        while(SearchQueue.size() > 0){
            //search all the neighbourhs of the first element of the queue
            // if the node is not already opened or visited, add it to the queue
            Dimensions current = SearchQueue.remove();
            visited.add(current);
            System.out.println("Visiting: " + current.row + " " + current.column);
            ArrayList<Dimensions> children = new ArrayList<>();
            children.add(new Dimensions((current.row)-1, current.column));
            children.add(new Dimensions(current.row, (current.column)-1));
            children.add(new Dimensions((current.row)+1, current.column));
            children.add(new Dimensions(current.row, (current.column)+1));
            
            //check validity of the children and get rid of the invalid ones
            for(int i = 0; i < 4; i++){
                if(dimensionValid(children.get(i), visited, SearchQueue)){
                    //add to the search queue and opened list
                    path[children.get(i).row][children.get(i).column] = new Dimensions(current.row, current.column);
                    if(children.get(i).isEqual(data.goal)){ //goal found
                        System.out.println(children.get(i).row + " " + children.get(i).column);
                        printInfo(mem, path, visited);
                        return;
                    }
                    System.out.println("New node found: " + children.get(i).row + " " + children.get(i).column);
                     SearchQueue.add(children.get(i));
                    if(SearchQueue.size() > mem) mem = SearchQueue.size();
                }
            }
        }   
    }   
}
