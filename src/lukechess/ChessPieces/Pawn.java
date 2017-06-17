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
     * Implementation of parent abstract method.
     * Even though the acsiiCode is a public abstract class variable,
     * We need to be able to get the asciiCode statically as well as
     * through the instantiated object. That is why the acsiiCode is
     * declared in the inherited class not the abstract parent. Declaring 
     * it in the parent CHessPiece class would force all implemented classes
     * to have the same acsii code.
     * @return 
     */
    @Override
    public String getAcsiiCode()
    {
        return this.acsiiCode;
    }
    
    /**
     * Populate all pawns on board in starting position
     */
    public static void populate(ChessPiece[][] board)
    {
        //white
        for(int i=0; i<board.length; i++)
        {
            board[1][i] = new Pawn(COLOR.WHITE, 1, i);
        }
        
        //black
        for(int i=0; i<board[6].length; i++)
        {
            board[6][i] = new Pawn(COLOR.BLACK, 6, i);
        }
    }

    /**
     * Get the available moves for the pawn
     * @param board
     * @return 
     */
    @Override
    public List<Position> getAvailableMoves(ChessPiece[][] board)
    {
        //array to store the available move postions
        ArrayList<Position> available = new ArrayList();
        
        //check forward move
        if(this.color == COLOR.BLACK)
        {
            if(board[this.position.getX()-1][this.position.getY()] == null)
            {
                //add to available positions
                Position moveTo = new Position(this.position.getX()-1, this.position.getY());
                available.add(moveTo);
            }
        }
        else
        {
            if(board[this.position.getX()+1][this.position.getY()] == null)
            {
                //add to available positions
                Position moveTo = new Position(this.position.getX()+1, this.position.getY());
                available.add(moveTo);
            }
        }
        //check for capture moves
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
                        //add to available positions
                        Position moveTo = new Position(this.position.getX()+1, this.position.getY());
                        available.add(moveTo);
                    }
                }
                else
                {
                    int nextX = this.position.getX()+1;
                    int nextY = this.position.getY()+horizontal;
                    if(board[nextX][nextY] != null && board[nextX][nextY].color != this.color)
                    {
                        //add to available positions
                        Position moveTo = new Position(this.position.getX()+1, this.position.getY());
                        available.add(moveTo);
                    }
                }
            }catch(IndexOutOfBoundsException e) {}
        }
        
        return available;
    }
    
    /**
     * We override the "move" method in abstract ChessPiece class to check for
     * queen promotion once the pawn gets the the opposite side of the board.
     * The pawn promotion will take effect after the move has been completed from
     * the parent class.
     * 
     * @param board
     * @param newPosition
     * @return ChessPiece
     */
    @Override
    public  ChessPiece move(ChessPiece[][] board, Position newPosition)
    {
        //first move the piece
        ChessPiece captured = super.move(board, newPosition);
        
        //then check for queen promotion
        int x = this.position.getX();
        int y = this.position.getY();
        
        //if black, check if Y position equals board (top of board).
        if(this.color == COLOR.BLACK)
        {
            if(x == 0)
            {
                Queen newQueen = new Queen(this.color, x, y);
                board[x][y] = newQueen;
            }
        }
        //if white, check if Y position is 0 (bottom of board).
        if(this.color == COLOR.WHITE)
        {
            if(x == board.length-1)
            {
                Queen newQueen = new Queen(this.color, x, y);
                board[x][y] = newQueen;
            }
        }
        
        //if conditions are met, change the pawn to a queen.
        return captured;
    }
}
