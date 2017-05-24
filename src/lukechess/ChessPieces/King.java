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
    public King(COLOR color, int x, int y)
    {
        Position position = new Position(x, y);
        this.color = color;
        this.position = position;
        this.alive = true;
        this.acsiiCode = "A";
    }
    
    /**
     * Populate all kings on board in starting position 
     * @param board
     */
    public static void populate(ChessPiece[][] board)
    {
        //top left
        board[0][4] = new King(ChessPiece.COLOR.WHITE, 0,4);
        //bottom right
        board[7][4] = new King(ChessPiece.COLOR.BLACK, 7,4); //7,4
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
    
}
