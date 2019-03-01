import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AS {
    private final Data data;
    int heuristic[][];
  //  Path path;
    
    private class ElementComparator implements Comparator<Element>{ 
            @Override
            public int compare(Element e1, Element e2) { 
                if (e1.pathCost + e1.h > e2.pathCost + e2.h) 
                    return 1; 
                else if (e1.pathCost + e1.h < e2.pathCost + e2.h) 
                    return -1; 
                return 0; 
            } 
    } 
    
    private class Element {
        Dimensions dimensions;
        int value; // value in the map matrix
        int pathCost;
        int h;
        
        public Element(Dimensions dimensions, int value, int pathCost){
            this.dimensions = dimensions;
            this.value = value;
            this.pathCost = pathCost;
            h = heuristic[dimensions.row][dimensions.column];
        }
        
        public void printElement(){
            dimensions.print();
            System.out.println(" , value: " + value + ", path cost: " + pathCost);
        }
    }
    
    public AS(Data data){
        this.data = data;
        countHeuristic();
    }
    
    private void countHeuristic(){
        heuristic = new int[data.dimensionRow][data.dimensionColumn];
        for(int i = 0; i < data.dimensionRow; i++){
            for(int j = 0; j < data.dimensionColumn; j++){
                //count manhattan distance from the goal
                heuristic[i][j] = (abs(data.goal.row - i) + abs(data.goal.column -j));
            }
        }
    }
    
    private boolean dimensionValid(Dimensions dimensions, ArrayList<Element> visited){
        if(dimensions.row < 0 || dimensions.column < 0 || dimensions.row >= data.dimensionRow || dimensions.column >= data.dimensionColumn)
            return false;
        if(data.map[dimensions.row][dimensions.column] == 0)
            return false;
        for(int i = 0; i < visited.size(); i++){
            if(dimensions.isEqual(visited.get(i).dimensions)){
          //      if(visited.get(i).pathCost > pathCost)
                return false;
            }
        }
        return true;
    }
    
    private void printPath(Dimensions path[][]){
        Dimensions currentDim = new Dimensions(data.goal.row, data.goal.column);
        int cost = 0;
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
    
    public void search(){
        PriorityQueue<Element> searchQ = new PriorityQueue<>(data.dimensionColumn*data.dimensionRow, new ElementComparator());
        searchQ.add(new Element(data.start, data.map[data.start.row][data.start.column], 0));
        
        ArrayList<Element> visited = new ArrayList<>();
        
        Dimensions path[][] = new Dimensions[data.dimensionRow][data.dimensionColumn];         //create path map
       
        for(int i = 0; i < data.dimensionRow; i++)
            for(int j = 0; j < data.dimensionColumn; j++)
                path[i][j] = new Dimensions(-1,-1);
        
        int expanded = 0;
        int mem = 0;
        
        
        while(!searchQ.isEmpty()){
            if(searchQ.size() > mem)
                mem = searchQ.size();
            
            Element current = searchQ.remove();
            visited.add(current);
            expanded++;
           
            System.out.print("Visiting: " );
            current.dimensions.print();
            System.out.println();
           
           //get children
            ArrayList<Dimensions> children = new ArrayList<>();
            children.add(new Dimensions((current.dimensions.row)-1, current.dimensions.column));
            children.add(new Dimensions(current.dimensions.row, (current.dimensions.column)-1));
            children.add(new Dimensions((current.dimensions.row)+1, current.dimensions.column));
            children.add(new Dimensions(current.dimensions.row, (current.dimensions.column)+1));
            
            for(int i = 0; i < 4; i++){
                if(dimensionValid(children.get(i), visited)){ //validate children
                    path[children.get(i).row][children.get(i).column] = new Dimensions(current.dimensions.row, current.dimensions.column);
                    int pathCost = current.pathCost + data.map[children.get(i).row][children.get(i).column];
                    if(children.get(i).isEqual(data.goal)){
                        printPath(path);
                        System.out.println("Number of nodes expanded: " + expanded);
                        System.out.println("Maximum number of nodes in memory: " + mem);
                        return;
                    }
                    Element el = new Element(children.get(i), data.map[children.get(i).row][children.get(i).column], pathCost );                   
                    visited.add(el);
                    searchQ.add(el);
                    System.out.print("New node: ");
                    el.printElement();
                }
            }
        }
    }  
}
