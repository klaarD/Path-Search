import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class AS {
    private final Data data;
    int heuristic[][];
    private results res;
    ArrayList<Element> visited;
    PriorityQueue<Element> searchQ;
    
    private class ElementComparator implements Comparator<Element>{ 
            @Override
            public int compare(Element e1, Element e2) { 
                int cost1 = e1.pathCost + e1.h;
                int cost2 = e2.pathCost + e2.h;
                if (cost1 > cost2 )
                    return 1; 
                else if (cost1 <= cost2){
                    if(cost1 == cost2){
                        if(e1.h >= e2.h) return 1;
                        return -1;
                    }
                    return -1; 
                }
                return 0; 
            } 
    } 
    
    public AS(Data data){
        this.data = data;
        countHeuristic();
        visited = new ArrayList<>(); //list of visited nodes
        searchQ = new PriorityQueue<>(data.dimensionColumn*data.dimensionRow, new ElementComparator()); //priority queue for opened elements
    }
    
    private void countHeuristic(){
        heuristic = new int[data.dimensionRow][data.dimensionColumn];
        for(int i = 0; i < data.dimensionRow; i++){
            for(int j = 0; j < data.dimensionColumn; j++){
                heuristic[i][j] = (abs(data.goal.row - i) + abs(data.goal.column -j));
            }
        }
    }
    
    private boolean dimensionValid(Dimensions dimensions){
        if(dimensions.row < 0 || dimensions.column < 0 || dimensions.row >= data.dimensionRow || dimensions.column >= data.dimensionColumn)
            return false;
        return data.map[dimensions.row][dimensions.column] != 0;             
    }
    
    private boolean visitedNode(Element el){
        boolean found = false;
        if(data.path[el.dimensions.row][el.dimensions.column].row != -1){
            for (Element tmp :searchQ) {
                if(tmp.dimensions.isEqual(el.dimensions) && (el.pathCost + el.h) < (tmp.pathCost + tmp.h)){
                    searchQ.remove(tmp);
                    return false;
                }
            }
            found = true;
            } 
        return found;
    }
    
    public results getResults(){
        return res;
    }
    
    private void printInfo(int inMem, int expanded){
        int cost = 0;
        Dimensions currentDim = data.goal;
        ArrayList<Dimensions> finalPath = new ArrayList<>();
        finalPath.add(currentDim);
        while(!currentDim.isEqual(data.start)){
            cost += data.map[currentDim.row][currentDim.column];
            currentDim = data.path[currentDim.row][currentDim.column];
            finalPath.add(currentDim);
        }
        res = new results(expanded,inMem, cost,finalPath);
        res.printResults();
    }
    
    public void search(){
        int expanded = 0; //number of expanded nodes
        if(data.map[data.start.row][data.start.column] == 0 || data.map[data.goal.row][data.goal.column] == 0){
                res = new results(expanded,0, -1, null); //solution was not found, set the result accordingly
                res.printResults(); // print the result   
                return;
        }
        if(data.goal.isEqual(data.start)){ //check if the start is the same as the goal
            printInfo(0,expanded);   //print result
            return; //solution found, return from the method
        }
        searchQ.add(new Element(data.start, data.map[data.start.row][data.start.column], 0, heuristic[data.start.row][data.start.column]));   //add start element     
        int mem = 0;    //maximum number of nodes in memory    
        while(!searchQ.isEmpty()){ //search queue while there is smth inside or until the solution is found
           if(searchQ.size() > mem)  mem = searchQ.size();    //set maximum number of nodes in the        
           Element current = searchQ.remove(); //get first element of the queue
           if(current.dimensions.isEqual(data.goal)){
                printInfo(mem,expanded);
                return;              
           }
           visited.add(current); //add current node to the visited list
           expanded++; //add this node to expanded
         /*  System.out.print("Visiting: ");
           current.dimensions.print();
           System.out.println(" " + current.pathCost + current.h);*/
           
           //print queue
        /*   for (Element tmp : searchQ) {
                System.out.print("    "); 
                
                tmp.dimensions.print();
                System.out.print(" " + tmp.pathCost + " " + tmp.h);
            }
                     System.out.println();
      */
            ArrayList<Dimensions> children = new ArrayList<>(); //get children
            children.add(new Dimensions((current.dimensions.row)-1, current.dimensions.column)); //up
            children.add(new Dimensions(current.dimensions.row, (current.dimensions.column)-1)); //left
            children.add(new Dimensions((current.dimensions.row)+1, current.dimensions.column)); //down
            children.add(new Dimensions(current.dimensions.row, (current.dimensions.column)+1)); //right
            
            for(int i = 0; i < 4; i++){ // check validity of each node
                if(dimensionValid(children.get(i))){ //validate children
                    int pathCost = current.pathCost + data.map[children.get(i).row][children.get(i).column];
                    Element el = new Element(children.get(i), data.map[children.get(i).row][children.get(i).column], pathCost,heuristic[children.get(i).row][children.get(i).column] );                  
                    if(!visitedNode(el)){
                        data.path[children.get(i).row][children.get(i).column] = new Dimensions(current.dimensions.row, current.dimensions.column);
                      /*  System.out.print("   New node: ");
                        children.get(i).print();
                        System.out.println();*/
                        visited.add(el);
                        searchQ.add(el);
                    }
                }
            }
        }
        res = new results(expanded,mem, -1, null); //solution was not found, set the result accordingly
        res.printResults(); // print the result
    }  
}
