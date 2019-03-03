
import java.util.ArrayList;

//for test purposes
public class results {
        
    private final int expandedNodes;
    private final int MaxMemNodes;
    private final int finalPathCost;
    private ArrayList<Dimensions> path;
    
    results(int expandedNodes, int MaxMemNodes, int finalPathCost, ArrayList<Dimensions> path){
        this.expandedNodes = expandedNodes;
        this.MaxMemNodes = MaxMemNodes;
        this.finalPathCost = finalPathCost;
        this.path = new ArrayList<>();
        this.path = path;
    }
    
    public int getExpandedNodes(){
        return expandedNodes;
    }
    
    public int getMaxMemNodes(){
        return MaxMemNodes;
    }
    
    public int getFinalPathCost(){
        return finalPathCost;
    }
    
    public ArrayList<Dimensions> getPath(){
        return path;
    }
    
    public void printResults(){
        System.out.println("Number of expanded nodes: " + expandedNodes);                      
        System.out.println("Maximum number of nodes held in memory: " + MaxMemNodes);
        System.out.println("Path cost: " + finalPathCost);
        if(path != null){
            System.out.print("Path: ");
            for(int i = path.size()-1; i>= 0; i--){
                if(i != path.size()-1) System.out.print(",");
                path.get(i).print();
            }
            System.out.println();
        }
    }
    
    public boolean isEqual(results r){
        if(expandedNodes != r.expandedNodes || MaxMemNodes != r.MaxMemNodes || finalPathCost != r.finalPathCost)
            return false;
        if(path != null){
            for(int i = 0; i < path.size(); i++){
                if(path.get(i).row != r.path.get(i).row || path.get(i).column != r.path.get(i).column)
                    return false;       
            }
        }
        return true;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof results)) return false;
            results e = (results) o;
            return this.isEqual(e);          
    }
           
    
}
