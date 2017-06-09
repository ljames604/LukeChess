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
public class Rook extends ChessPiece
{
    public Rook(COLOR color, int x, int y)
    {
        Position position = new Position(x, y);
        this.color = color;
        this.position = position;
        this.alive = true;
        this.acsiiCode = "R";
    }
    
    /**
     * Populate all castles on board in starting position
     */
    public static void populate(ChessPiece[][] board)
    {
        //top left
        board[0][0] = new Rook(ChessPiece.COLOR.WHITE, 0,0); //0,0
        //top right
        board[0][7] = new Rook(ChessPiece.COLOR.WHITE, 0,7); //0,7
        //bottom left
        board[7][0] = new Rook(ChessPiece.COLOR.BLACK, 7,0); //7,0
        //bottom right
        board[7][7] = new Rook(ChessPiece.COLOR.BLACK, 7,7); //7,7
    }

    
    @Override
    public List<Position> getAvailableMoves(ChessPiece[][] board)
    {
        //array to store the available move postions
        ArrayList<Position> available = new ArrayList();
        
        int moveIndex = 1;
        
        for(int index = -1; index<=1; index+=2)
        {
            try
            {
                while(board[position.getX()][position.getY()+moveIndex*index] == null)
                {
                    int moveX = position.getX();
                    int moveY = position.getY()+moveIndex*index;
                    Position moveTo = new Position(moveX, moveY);
                    if(isKingSafe(board, moveTo))
                    { 
                        available.add(moveTo);
                    }
                    moveIndex++;
                }
                if(board[position.getX()][position.getY()+moveIndex*index].color != this.color)
                {
                    int moveX = position.getX();
                    int moveY = position.getY()+moveIndex*index;
                    Position moveTo = new Position(moveX, moveY); 
                    if(isKingSafe(board, moveTo))
                    {
                        available.add(moveTo);
                    }
                }
            } catch(IndexOutOfBoundsException e) {}
            moveIndex=1;
            try
            {
                while(board[position.getX()+moveIndex*index][position.getY()] == null)
                {
                    int moveX = position.getX()+moveIndex*index;
                    int moveY = position.getY();
                    Position moveTo = new Position(moveX, moveY);
                    if(isKingSafe(board, moveTo))
                    {
                        available.add(moveTo);
                    }
                    moveIndex++;
                }
                if(board[position.getX()+moveIndex*index][position.getY()].color != this.color)
                {
                    int moveX = position.getX()+moveIndex*index;
                    int moveY = position.getY();
                    Position moveTo = new Position(moveX, moveY);
                    if(isKingSafe(board, moveTo))
                    {
                        available.add(moveTo);
                    }
                }
            } catch(IndexOutOfBoundsException e) {}
            moveIndex=1;
        }
        return available;
    }
    
        
}
