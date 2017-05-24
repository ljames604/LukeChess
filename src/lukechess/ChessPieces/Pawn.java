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
        return new ArrayList<Position>();
    }
}
