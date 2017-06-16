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
public class Knight extends ChessPiece
{
    public static String acsiiCode;
    
    public Knight(COLOR color, int x, int y)
    {
        Position position = new Position(x, y);
        this.color = color;
        this.position = position;
        this.alive = true;
        this.acsiiCode = "K";
    }
    
    /**
     * Populate all knights on board in starting position 
     */
    public static void populate(ChessPiece[][] board)
    {
        //top left
        board[0][1] = new Knight(ChessPiece.COLOR.WHITE, 0,1); //0,1
        //top right
        board[0][6] = new Knight(ChessPiece.COLOR.WHITE, 0,6); //0,6
        //bottom left
        board[7][1] = new Knight(ChessPiece.COLOR.BLACK, 4,4); //7,1
        //bottom right
        board[7][6] = new Knight(ChessPiece.COLOR.BLACK, 7,6); //7,6
    }
    
    
    @Override
    public List<Position> getAvailableMoves(ChessPiece[][] board)
    {
        //array to store the available move postions
        ArrayList<Position> available = new ArrayList();
        
        for(int xIndex = -1; xIndex<=1; xIndex+=2)
        {
            for(int yIndex = -1; yIndex<=1; yIndex+=2)
            {
                try
                {
                    if(board[position.getX()+xIndex][position.getY()+yIndex*2] == null || board[position.getX()+xIndex][position.getY()+yIndex*2].color != this.color)
                    {
                        int moveX = position.getX()+xIndex;
                        int moveY = position.getY()+yIndex*2;
                        Position moveTo = new Position(moveX, moveY);
                        if(isKingSafe(board, moveTo))
                        {
                            available.add(moveTo);
                        }
                    }
                } catch(IndexOutOfBoundsException e) {}
                try
                {
                    if(board[position.getX()+xIndex*2][position.getY()+yIndex] == null || board[position.getX()+xIndex*2][position.getY()+yIndex].color != this.color)
                    {
                        int moveX = position.getX()+xIndex*2;
                        int moveY = position.getY()+yIndex;
                        Position moveTo = new Position(moveX, moveY);
                        if(isKingSafe(board, moveTo))
                        {
                            available.add(moveTo);
                        }
                    }
                } catch(IndexOutOfBoundsException e) {}
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
