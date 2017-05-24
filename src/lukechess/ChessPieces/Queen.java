/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukechess.ChessPieces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luke
 */
public class Queen extends ChessPiece
{
    public Queen(COLOR color, int x, int y)
    {
        Position position = new Position(x, y);
        this.color = color;
        this.position = position;
        this.alive = true;
        this.acsiiCode = "Q";
    }

    /**
     * Populate all queens on board in starting position 
     */
    public static void populate(ChessPiece[][] board)
    {
        //top left
        board[0][3] = new Queen(ChessPiece.COLOR.WHITE, 0,3);
        //bottom right
        board[7][3] = new Queen(ChessPiece.COLOR.BLACK, 7,3); //should be 7,3
    }
    
    
    @Override
    public List<Position> getAvailableMoves(ChessPiece[][] board)
    {
        //array to store the available move postions
        ArrayList<Position> available = new ArrayList();
        int moveIndex = 1;
        for(int x=-1; x<=1; x++)
        {
            for(int y=-1; y<=1; y++)
            {
                int currentX = this.position.getX(); //+moveIndex*x;
                int currentY = this.position.getY(); //+moveIndex*y;
                
                if(x != 0 || y !=0)
                {
                    //array will go out of bounds so we catch the IndexOutOfBoundsException
                    try
                    {                        
                        while(board[currentX+moveIndex*x][currentY+moveIndex*y] == null)
                        {
                            int moveX = currentX+moveIndex*x;
                            int moveY = currentY+moveIndex*y;
                            Position moveTo = new Position(moveX, moveY);
                            if(isKingSafe(board, moveTo))
                            {
                                available.add(moveTo);
                            }
                            moveIndex++;
                        }
                        if(board[currentX+moveIndex*x][currentY+moveIndex*y].color != this.color)
                        {
                            int moveX = currentX+moveIndex*x;
                            int moveY = currentY+moveIndex*y;
                            Position moveTo = new Position(moveX, moveY);
                            if(isKingSafe(board, moveTo))
                            {
                                available.add(moveTo);
                            }
                        }
                    } catch(IndexOutOfBoundsException e) {}
                    moveIndex = 1;
                }
            }
        }
        return available;
    }
    
}
