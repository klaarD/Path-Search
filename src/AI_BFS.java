import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class AI_BFS {
    int map[][];
    int dimensionRow;
    int dimensionColumn;
    Dimensions start;
    Dimensions goal;
    
    private void readFile(String FileName) throws FileNotFoundException {
        File file = new File(FileName);
        //try {
            Scanner sc = new Scanner(file);

            //read dimensions
            dimensionRow = sc.nextInt();
            dimensionColumn = sc.nextInt();

            //read starting and goal positions
            start = new Dimensions(sc.nextInt(), sc.nextInt());
            goal = new Dimensions(sc.nextInt(), sc.nextInt());
            
            map = new int[dimensionRow][dimensionColumn];

            //read map
            for(int i = 0; i< dimensionRow; i++){
                for(int j = 0; j < dimensionColumn; j++){
                    map[i][j] = sc.nextInt();
                }   
            }  
    }
    
    private void printGivenData() {
            System.out.println(dimensionRow + " " + dimensionColumn);
            System.out.println(start.row + " " + start.column);
            System.out.println(goal.row + " " + goal.column);
            
            //print map
            for(int i = 0; i < dimensionRow; i++){
                for(int j = 0; j < dimensionColumn; j++){
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        }
        
    public static void main(String[] args) throws FileNotFoundException{
        AI_BFS search = new AI_BFS();
        search.readFile("map.txt");
        search.printGivenData();
        Data data = new Data(search.map, search.dimensionRow, search.dimensionColumn, search.start, search.goal);
       
        
        BFS bfs = new BFS(data);
        long startTime = System.currentTimeMillis();
        bfs.BFS();
        long stopTime = System.currentTimeMillis();
        long estimatedTime = stopTime - startTime;
        double time = (double) estimatedTime / 1000;
        System.out.println("Time: " + time + "s");
       
      /*  IDS ids = new IDS(data);
        startTime = System.currentTimeMillis();
        ids.IDS();
        stopTime = System.currentTimeMillis();
        estimatedTime = stopTime - startTime;
        time = (double) estimatedTime / 1000;
        System.out.println("Time: " + time + "s");  */
   
     //   AS astar = new AS(data);
       // astar.search();
    }
}



