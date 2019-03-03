import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * class that gets arguments from the command line
 * loads data from given file
 * run given search
 * @author Klara Dvorakova
 */
public class Search {
    public int map[][]; //2d array to save the map
    public int dimensionRow; //number of rows
    public int dimensionColumn; //number of columns
    public Dimensions start; //coordinates of start position
    public Dimensions goal; //coordinates of goal position
    
    
    /**
     * Read given file, save its values
     * @param FileName name of the file
     * @throws FileNotFoundException 
     */
    public void readFile(String FileName) throws FileNotFoundException {
        File file = new File(FileName);
        Scanner sc = new Scanner(file);
        //read dimensions
        dimensionRow = sc.nextInt();
        dimensionColumn = sc.nextInt();
        //read starting and goal positions
        start = new Dimensions(sc.nextInt(), sc.nextInt());
        goal = new Dimensions(sc.nextInt(), sc.nextInt());           
        map = new int[dimensionRow][dimensionColumn];
        for(int i = 0; i< dimensionRow; i++){ //read map
            for(int j = 0; j < dimensionColumn; j++){
                map[i][j] = sc.nextInt();
            }   
        }  
    }
    
    /**
     * Print data given in the file
     */
    public void printGivenData() {
        System.out.println(dimensionRow + " " + dimensionColumn);
        System.out.println(start.row + " " + start.column);
        System.out.println(goal.row + " " + goal.column);
        for(int i = 0; i < dimensionRow; i++){
            for(int j = 0; j < dimensionColumn; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
        
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 2) {
            System.out.println("set right arguments");
            return;
        }
        
        String Filename = args[0];
        String alg = args[1];
        
        Search search = new Search();
        search.readFile(Filename);
        Data data = new Data(search.map, search.dimensionRow, search.dimensionColumn, search.start, search.goal);
        
       if(alg.equals("BFS")){
           BFS bfs = new BFS(data);
            long startTime = System.currentTimeMillis();
            bfs.BFS(startTime);
            long stopTime = System.currentTimeMillis();
            long estimatedTime = stopTime - startTime;
            System.out.println("Time: " + estimatedTime + "ms");
       }
       else if(alg.equals("IDS")){
            IDS ids = new IDS(data);
            long startTime = System.currentTimeMillis();
            ids.search(startTime);
            long stopTime = System.currentTimeMillis();
            long estimatedTime = stopTime - startTime;
            System.out.println("Time: " + estimatedTime + "ms");         
       }
       else if(alg.equals("AS")){
            AS as = new AS(data);
            long startTime = System.currentTimeMillis();
            as.search(startTime);
            long stopTime = System.currentTimeMillis();
            long estimatedTime = stopTime - startTime;
            System.out.println("Time: " + estimatedTime + "ms");             
       }
    }
    
}



