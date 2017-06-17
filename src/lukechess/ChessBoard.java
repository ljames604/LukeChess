/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukechess;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import lukechess.ChessPieces.*;

/**
 *
 * @author Luke
 */
public class ChessBoard 
{
    //two dimentional representation of the chess board
    public static ChessPiece[][] board;
    
    /**
     * ChessBoard constructor
     */
    public ChessBoard()
    {
        board = new ChessPiece[8][8];
        Pawn.populate(board);
        Rook.populate(board);
        Knight.populate(board);
        Bishop.populate(board);
        Queen.populate(board);
        King.populate(board);
    }
    
    /**
     * Return a list of possible moves for each black piece on the board
     * @return Map<Integer, Position>
     */
    public static Map<ChessPiece, Position> possibleMoves()
    {
        Map<ChessPiece, Position> allMoves = new HashMap();
        
        //iterate the chess board (there are 63)
        for(int i=0; i<64; i++)
        {
            ChessPiece piece = board[i/8][i%8];
            if(piece != null && piece.getColor() == ChessPiece.COLOR.BLACK)
            {
                List<Position> pieceMoves = piece.getAvailableMoves(board);
                for(Position position : pieceMoves)
                {
                    allMoves.put(piece,position);
                }
            }
        }
        
        return allMoves;
    }
    
    /**
     * Output the contents of the board for debugging
     * @param board 
     */
    public void printBoard()
    {                        
        for (int x=0; x<board.length; x++)
        {
           for (int y=0; y<board[0].length; y++)
           {
                if(board[x][y] == null)
                {
                    System.out.print("*" + " ");
                }

                else
                {
                    System.out.print(board[x][y].getAcsiiCode() + " ");
                }
           }
           System.out.println();
        }
        
        System.out.println();System.out.println();System.out.println();System.out.println();
    }
}
