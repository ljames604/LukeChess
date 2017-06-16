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
public class Bishop extends ChessPiece
{
    public static String acsiiCode;
    
    public Bishop(COLOR color, int x, int y)
    {
        Position position = new Position(x, y);
        this.color = color;
        this.position = position;
        this.alive = true;
        this.acsiiCode = "B";
    }
    
    /**
     * Populate all rooks on board in starting position 
     */
    public static void populate(ChessPiece[][] board)
    {
        //top left
        board[0][2] = new Bishop(ChessPiece.COLOR.WHITE, 0,2);
        //top right
        board[0][5] = new Bishop(ChessPiece.COLOR.WHITE, 0,5); //0,5
        //bottom left
        board[7][2] = new Bishop(ChessPiece.COLOR.BLACK, 7,2);
        //bottom right
        board[7][5] = new Bishop(ChessPiece.COLOR.BLACK, 7,5); 
    }
    
    
    @Override
    public List<Position> getAvailableMoves(ChessPiece[][] board)
    {
        //array to store the available move postions
        ArrayList<Position> available = new ArrayList();
        
        int moveIndex = 1;
        
        for(int xIndex = -1; xIndex<=1; xIndex+=2)
        {
            for(int yIndex = -1; yIndex<=1; yIndex+=2)
            {
                if(xIndex!=0 || yIndex!=0)
                {
                    try
                    {
                        while(board[position.getX()+moveIndex*xIndex][position.getY()+moveIndex*yIndex] == null)
                        {
                            int moveX = position.getX()+moveIndex*xIndex;
                            int moveY = position.getY()+moveIndex*yIndex;
                            Position moveTo = new Position(moveX, moveY);
                            if(isKingSafe(board, moveTo))
                            {
                                available.add(moveTo);
                            }
                            moveIndex++;
                        }
                        if(board[position.getX()+moveIndex*xIndex][position.getY()+moveIndex*yIndex].color != this.color)
                        {
                            int moveX = position.getX()+moveIndex*xIndex;
                            int moveY = position.getY()+moveIndex*yIndex;
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
    
    //get the piece's ASCii code
    @Override
    public String getAcsiiCode()
    {
        return this.acsiiCode;
    }
}
