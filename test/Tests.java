import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {
    AI_BFS search;
    Data data;
    
    public Tests(){  
        search = new AI_BFS();
    }
    
    private void setVariables(String Filename) throws FileNotFoundException{
        search.readFile(Filename);
        search.printGivenData();
        data = new Data(search.map, search.dimensionRow, search.dimensionColumn, search.start, search.goal);
    }
    
            
    @Test
    public void test_example() throws FileNotFoundException{
        setVariables("map.txt");
        
        //BFS test
        BFS bfs = new BFS(data);
        bfs.BFS();      
        ArrayList<Dimensions> path = new ArrayList<>();
        Collections.addAll(path,new Dimensions(4,3),new Dimensions(4,2), new Dimensions(3,2), new Dimensions(2,2), new Dimensions(1,2));
        results res = new results(14,7,14, path);

        assertEquals(res, bfs.getResults());    
        
        //AS test
        setVariables("map.txt");
        AS as = new AS(data);
        as.search();
        ArrayList<Dimensions> path_as = new ArrayList<>();
        Collections.addAll(path_as,new Dimensions(4,3),new Dimensions(3,3), new Dimensions(2,3), new Dimensions(1,3), new Dimensions(1,2));
        results res_AS = new results(11,8,12, path_as);
        assertEquals(res_AS, as.getResults());    
    }
    
   /* @Test
    public void no_solution_BFS() throws FileNotFoundException{
        setVariables("noSolution.txt");
        BFS bfs = new BFS(data);
        bfs.BFS();      
        results res = new results(19,6,-1, null);
        assertEquals(res, bfs.getResults());  
        
        setVariables("noSolution.txt");
        AS as = new AS(data);
        as.search();    
        results res_as = new results(19,6,-1, null);
        assertEquals(res_as, as.getResults());       
    }
    
    @Test
    public void BFS_same_start_goal() throws FileNotFoundException{
        setVariables("same_start_goal.txt");
        BFS bfs = new BFS(data);
        bfs.BFS();      
        ArrayList<Dimensions> path = new ArrayList<>();
        path.add(new Dimensions(1,1));
        results res = new results(0,0,0,path );
        assertEquals(res, bfs.getResults());  
        
        setVariables("same_start_goal.txt");
        AS as = new AS(data);
        as.search();     
        assertEquals(res, as.getResults());  
    }
    
    @Test
    public void x_5x5() throws FileNotFoundException{
        //BFS test
        setVariables("5x5.txt");
        BFS bfs = new BFS(data);
        bfs.BFS();      
        ArrayList<Dimensions> path = new ArrayList<>();
        Collections.addAll(path,new Dimensions(4,1),new Dimensions(4,0), new Dimensions(3,0),new Dimensions(2,0),
                new Dimensions(1,0),new Dimensions(1,1),new Dimensions(1,2), new Dimensions(1,3), new Dimensions(0,3));
        results res = new results(17,4,23, path);
        assertEquals(res, bfs.getResults());    
        
        //AS test
        setVariables("5x5.txt");
        AS as = new AS(data);
        as.search();
        ArrayList<Dimensions> path_as = new ArrayList<>();
        Collections.addAll(path_as,new Dimensions(4,1),new Dimensions(4,2), new Dimensions(4,3),new Dimensions(4,4),
                new Dimensions(3,4),new Dimensions(2,4),new Dimensions(2,3), new Dimensions(1,3), new Dimensions(0,3));
        results res_as = new results(13,4,16,path_as);
        assertEquals(res_as,as.getResults());
    }
    
    @Test
    public void BFS_5x5_without0() throws FileNotFoundException{
        setVariables("5x5_new.txt");
        BFS bfs = new BFS(data);
        bfs.BFS();      
        ArrayList<Dimensions> path = new ArrayList<>();
        Collections.addAll(path,new Dimensions(4,1),new Dimensions(3,1), new Dimensions(2,1),new Dimensions(1,1),
                new Dimensions(0,1),new Dimensions(0,2),new Dimensions(0,3));
        results res = new results(20,6,15, path);
        assertEquals(res, bfs.getResults());       
        
        setVariables("5x5_new.txt");
        AS as = new AS(data);
        as.search();      
        ArrayList<Dimensions> path_as = new ArrayList<>();
        Collections.addAll(path_as,new Dimensions(4,1),new Dimensions(3,1), new Dimensions(3,2),new Dimensions(3,3),
                new Dimensions(2,3),new Dimensions(1,3),new Dimensions(0,3));
        results res_as = new results(11,12,10, path_as);
        assertEquals(res_as, as.getResults());  
    }
    
    @Test
    public void x_5x5_next() throws FileNotFoundException{
        setVariables("5x5_next.txt");
        BFS bfs = new BFS(data);
        bfs.BFS();      
        ArrayList<Dimensions> path = new ArrayList<>();
        Collections.addAll(path, new Dimensions(1,3), new Dimensions(0,3));
        results res = new results(1,1,2, path);
        assertEquals(res, bfs.getResults()); 
        
        setVariables("5x5_next.txt");
        AS as = new AS(data);
        as.search();      
        results res_as = new results(1,3,2, path);
        assertEquals(res_as, as.getResults()); 
    }
    
    @Test
    public void x_10x10() throws FileNotFoundException{
        setVariables("10x10.txt");
        BFS bfs = new BFS(data);
        bfs.BFS();      
        ArrayList<Dimensions> path = new ArrayList<>();
        Collections.addAll(path,new Dimensions(2,7),new Dimensions(3,7), new Dimensions(4,7),new Dimensions(5,7),
                new Dimensions(6,7),new Dimensions(7,7),new Dimensions(7,8));
        results res = new results(22,9,13, path);
        assertEquals(res, bfs.getResults());   
        
        setVariables("10x10.txt");
        AS as = new AS(data);
        as.search();      
        results res_as = new results(12,9,13, path);
        assertEquals(res_as, as.getResults());   
    }
    
    @Test
    public void BFS_start_0() throws FileNotFoundException{
        setVariables("start_is_0.txt");
        BFS bfs = new BFS(data);
        bfs.BFS();      
        results res = new results(0,0,-1, null);
        assertEquals(res, bfs.getResults());    
        
        setVariables("start_is_0.txt");
        AS as = new AS(data);
        as.search();      
        assertEquals(res, as.getResults());       
    }*/
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
