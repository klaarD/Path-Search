import java.util.ArrayList;

public class IDS {
    private boolean foundSolution;
    private final Data data;
    results res;
    int mem;
    int expanded;
    int limit;
    
    public IDS(Data data){
        this.data = data;
    }
    
    public void IDS(){
        limit = data.dimensionRow*data.dimensionColumn;
        mem = 0;
        expanded = 0;
    }
    
    public void search(){
        for(int i = 0; i < limit; i++){
            DFS(i);
            if(foundSolution == true) {
                System.out.println("Solution found in the depth: " + i);
                return;
            }           
        }
        res = new results(expanded,mem,-1, null);
    }
    
    public boolean dimensionValid(Dimensions dimensions, ArrayList<Dimensions> visited){
        if(dimensions.row < 0 || dimensions.column < 0 || dimensions.row >= data.dimensionRow || dimensions.column >= data.dimensionColumn)
            return false;
        if(data.map[dimensions.row][dimensions.column] == 0 || data.path[dimensions.row][dimensions.column].row != -1/*|| visited.contains(dimensions)*/)
            return false;
        return true;
    }

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
    
    private void DFSUtil(Dimensions current, ArrayList<Dimensions> visited, Dimensions path[][], int depth){
        //check if the childs have been visited
        if(depth < 0) return;
        if(depth == 0){
            if(current.isEqual(data.goal)){
                System.out.println(current.row + " " + current.column);
                //System.out.println("Solution found in the depth: " + depth);
                foundSolution = true;
            }
            return;
        }
        visited.add(current);
        expanded++;
        System.out.println("Visiting: " + current.row + " " + current.column);
        ArrayList<Dimensions> children = new ArrayList<>();
        children.add(new Dimensions((current.row)-1, current.column));
        children.add(new Dimensions(current.row, (current.column)-1));
        children.add(new Dimensions((current.row)+1, current.column));
        children.add(new Dimensions(current.row, (current.column)+1));
                   
            //check validity of the children and get rid of the invalid ones
            for(int i = 0; i < 4; i++){
                if(dimensionValid(children.get(i), visited)){
                    data.path[children.get(i).row][children.get(i).column] = new Dimensions(current.row, current.column); //add to path
                    if(children.get(i).isEqual(data.goal)){
                        System.out.println("Solution found");
                        printInfo();
                        foundSolution = true;
                        return;
                    }
                   // System.out.println("New node found: " + children.get(i).row + " " + children.get(i).column);
                    visited.add(children.get(i));
                    DFSUtil(children.get(i), visited, path, depth-1);
                }
            } 
    }

    private void DFS(int limit){
        ArrayList<Dimensions> visited = new ArrayList<>();
        
        Dimensions path[][] = new Dimensions[data.dimensionRow][data.dimensionColumn];
        for(int i = 0; i < data.dimensionRow; i++){
            for(int j = 0; j < data.dimensionColumn; j++){
                data.path[i][j] = new Dimensions(-1,-1);
            }
        }
        System.out.println("limit = " + limit);
        DFSUtil(data.start, visited, path, limit);
    }
}
