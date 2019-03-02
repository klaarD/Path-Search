/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Clara
 */
public class Element {
        Dimensions dimensions;
        int value; // value in the map matrix
        int pathCost;
        int h;
        
        public Element(Dimensions dimensions, int value, int pathCost, int heuristic){
            this.dimensions = dimensions;
            this.value = value;
            this.pathCost = pathCost;
        //    h = heuristic[dimensions.row][dimensions.column];
            h = heuristic;
        }
        
        public void printElement(){
            dimensions.print();
            System.out.println(" , value: " + value + ", path cost: " + pathCost);
        }
        
        @Override
        public boolean equals(Object o){
            if(o == this) return true;
            if(!(o instanceof Element)) return false;
            Element e = (Element) o;
            return (e.dimensions.row == this.dimensions.row && e.dimensions.column == this.dimensions.column);  
        }    
}
