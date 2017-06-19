/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukechess.ChessPieces;

import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Luke
 */
public class Position 
{
    /**
     * These are for user interfacing. 
     * Example : A user would select "A5" not board[0][4].
     **/
    public static final Map<String, Integer> alpha = new HashMap<String, Integer>();
    public static final Map<Integer, Integer> numeric = new HashMap<Integer, Integer>(); ;
    
    //x and y coordenence 
    private int x, y;
    
    /**
     * Constructor
     * @param int x
     * @param int y 
     */
    public Position(int x, int y)
    {
        assignHashMap();
        this.x = x;
        this.y = y;
    }
    
    /**
     * Populate the alpha & numeric HashMaps
     */
    private void assignHashMap()
    {
        alpha.put("A", 0);
        alpha.put("B", 1);
        alpha.put("C", 2);
        alpha.put("D", 3);
        alpha.put("E", 4);
        alpha.put("F", 5);
        alpha.put("G", 6);
        alpha.put("H", 7);
        
        numeric.put(1,0);
        numeric.put(2,1);
        numeric.put(3,2);
        numeric.put(4,3);
        numeric.put(5,4);
        numeric.put(6,7);
        numeric.put(7,8);
        numeric.put(8,7);
        
    }
    
    /**
     * GETTERS & SETTERS
     */
    
    public int getX()
    {
        return this.x;
    }
    
    public void setX(int xPosition)
    {
        this.x = xPosition;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setY(int yPosition)
    {
        this.y = yPosition;
    }
    
    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the variables by actual human board rules; letter and number.
     * @param y
     * @param x 
     */
    public void setAlphaNumeric(String y, int x)
    {
        this.y = numeric.get(x);
        this.x = alpha.get(y);
    }
        
        
}
