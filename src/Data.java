public class Data {
    int map[][];
    int dimensionRow;
    int dimensionColumn;
    Dimensions start;
    Dimensions goal;
    Dimensions path[][];
    
    public Data(int map[][], int dimensionRow, int dimensionColumn, Dimensions start, Dimensions goal){
        this.dimensionColumn = dimensionColumn;
        this.dimensionRow = dimensionRow;
        this.start = start;
        this.goal = goal;
        this.map = new int[dimensionRow][dimensionColumn];
        this.map = map;
        
        path = new Dimensions[dimensionRow][dimensionColumn];         //create path map
        
        for(int i = 0; i < dimensionRow; i++)
            for(int j = 0; j < dimensionColumn; j++)
                path[i][j] = new Dimensions(-1,-1);
        path[start.row][start.column] = start;
    }
    
}
