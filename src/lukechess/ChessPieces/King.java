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
public class King extends ChessPiece
{
    public static String acsiiCode;
    
    public King(COLOR color, int x, int y)
    {
        Position position = new Position(x, y);
        this.color = color;
        this.position = position;
        this.alive = true;
        this.acsiiCode = "A";
    }
    
    /**
     * Implementation of parent abstract method.
     * Even though the acsiiCode is a public abstract class variable,
     * We need to be able to get the asciiCode statically as well as
     * through the instantiated object. That is why the acsiiCode is
     * declared in the inherited class not the abstract parent. Declaring 
     * it in the parent ChessPiece class would force all implemented classes
     * to have the same acsii code.
     * @return 
     */
    @Override
    public String getAcsiiCode()
    {
        return this.acsiiCode;
    }
    
    /**
     * Populate all kings on board in starting position 
     * @param board
     */
    public static void populate(ChessPiece[][] board)
    {
        //top left
        board[0][4] = new King(ChessPiece.COLOR.WHITE, 0,4); //0,4
        //bottom right
        board[4][4] = new King(ChessPiece.COLOR.BLACK, 4,4); //7,4
    }
    
    
    @Override
    public List<Position> getAvailableMoves(ChessPiece[][] board)
    {
        //array to store the available move postions
        ArrayList<Position> available = new ArrayList();
        
        //there is only 9 position that need to be checked becuase king can
        //only move one square at a time
        for(int index=0; index<9; index++)
        {
            //we skip the kings current postion
            if(index != 4)
            {
                try
                {
                    //these equations will get the position in the 2D array for the next move
                    int moveX = this.position.getX()-1+index/3;
                    int moveY = this.position.getY()-1+index%3;

                    //we have to check if the move is empty and not the same color
                    ChessPiece piece = board[moveX][moveY];
                    if(piece == null || piece.getColor() != this.color)
                    {
                        Position moveTo = new Position(moveX, moveY);
                        //we have to check if this move palces the king in check
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
    
    /**
     * TO DO!!! the ChessBoard should be an extension of an interface that has
     * methods available to get the position of the king. Every time the king moves
     * the class variable that stores its postions on the board should also be updated.
     * This will save valuable processing time.
     * 
     * @param board
     * @param x
     * @param y
     * @return ChessPiece
     */
    @Override
    public  ChessPiece move(ChessPiece[][] board, int x, int y)
    {
        //first move the piece
        ChessPiece captured = super.move(board, x, y);
        
        //TO DO! update the board's king position in the ChessBoard variable. TO DO!
        
        return captured;
    }
    
}
