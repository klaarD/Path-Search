public class Data {
    int map[][];
    int dimensionRow;
    int dimensionColumn;
    Dimensions start;
    Dimensions goal;
    
    public Data(int map[][], int dimensionRow, int dimensionColumn, Dimensions start, Dimensions goal){
        this.dimensionColumn = dimensionColumn;
        this.dimensionRow = dimensionRow;
        this.start = start;
        this.goal = goal;
        this.map = new int[dimensionRow][dimensionColumn];
        this.map = map;
    }
    
}
