/**
 * Class to store coordinates of one node
 */
public class Dimensions {
        public int row;
        public int column;
        
        public Dimensions(int row, int column){
            this.row = row;
            this.column = column;
        }
        
        public boolean isEqual(Dimensions dimensions){
            return this.row == dimensions.row && this.column == dimensions.column;
        } 
        
        @Override
        public boolean equals(Object o){
            if (o == this) { 
                return true; 
            }
            if (!(o instanceof Dimensions)) { 
                return false; 
            } 
            Dimensions c = (Dimensions) o; 
            return ( c.row == this.row && c.column == this.column);      
        }
        public void print(){
            System.out.print("(" + row + "," + column + ")");
        }
}
