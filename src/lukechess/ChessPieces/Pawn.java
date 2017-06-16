/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukechess.ChessPieces;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Luke
 */
public class Pawn extends ChessPiece
{
    public static String acsiiCode;
    
    public Pawn(COLOR color, int x, int y)
    {
        Position position = new Position(x, y);
        this.color = color;
        this.position = position;
        this.alive = true;
        this.acsiiCode = "P";
    }
    
    /**
     * Populate all pawns on board in starting position
     */
    public static void populate(ChessPiece[][] board)
    {
        //white
        for(int i=0; i<board.length; i++)
        {
            board[1][i] = new Pawn(ChessPiece.COLOR.WHITE, 1, i);
        }
        
        //black
        for(int i=0; i<board[6].length; i++)
        {
            board[6][i] = new Pawn(ChessPiece.COLOR.BLACK, 6, i);
        }
    }

    
    @Override
    public List<Position> getAvailableMoves(ChessPiece[][] board)
    {
        if(this.color == COLOR.BLACK)
        {
            if(board[this.position.getX()-1][this.position.getY()] == null)
            {
                //add to available positions
            }
        }
        else
        {
            if(board[this.position.getX()+1][this.position.getY()] == null)
            {
                //add to available positions
            }
        }
        for(int horizontal=-1; horizontal<=1; horizontal+=2)
        {  
            try
            {
                if(this.color == COLOR.BLACK)
                {
                    int nextX = this.position.getX()-1;
                    int nextY = this.position.getY()+horizontal;
                    if(board[nextX][nextY] != null && board[nextX][nextY].color != this.color)
                    {
                        //add to available position
                    }
                }
                else
                {
                    int nextX = this.position.getX()+1;
                    int nextY = this.position.getY()+horizontal;
                    if(board[nextX][nextY] != null && board[nextX][nextY].color != this.color)
                    {
                        //add to available position
                    }
                }
            }catch(IndexOutOfBoundsException e) {}
        }
        return new ArrayList<Position>();
    }
    
    //get the piece's ASCii code
    @Override
    public String getAcsiiCode()
    {
        return this.acsiiCode;
    }
}
