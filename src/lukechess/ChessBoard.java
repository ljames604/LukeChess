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
     * These two intergers range from 0-64 (because 8x8=64).
     * To get the X position, divide X by 8. To get the Y position,
     * monoluse blackKingPos by 8... Example, board[blackKingPos/8][whiteKingPos%8
     * This will save time opposed to iterating through the whole 
     * board to find the king every time.
     */
     int  blackKingPos, whiteKingPos;
    
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
    
    public HashMap<ChessPiece, Position> getAvailableMoves(boolean side)
    {
        HashMap<ChessPiece, Position> moves = new HashMap();
        for(int i=0; i<63; i++)
        {
            int x = i/8;
            int y = i%8;
            //try
            //{
                if(side)
                {
                    if(board[x][y] != null && board[x][y].getColor() == ChessPiece.COLOR.BLACK)
                    {
                        //ChessPiece deleteForDebug = board[x][y];
                        List<Position> positions = board[x][y].getAvailableMoves(board);
                        for (Position pos : positions) 
                        {
                            moves.put(board[x][y], pos);
                        }
                    }
                }
                else
                {
                    if(board[x][y] != null && board[x][y].getColor() == ChessPiece.COLOR.WHITE)
                    {
                        List<Position> positions = board[x][y].getAvailableMoves(board);
                        for (Position pos : positions) 
                        {
                            moves.put(board[x][y], pos);
                        }
                    }
                }
            //}catch(IndexOutOfBoundsException e) {}
        }
        
        return moves;
    }
    
//    public void miniMax(int depth, boolean maximizer)
//    {
//        maxi(depth, maximizer);
//    }
//    
//    public int mini(int depth, boolean maximizer)
//    {
//        if(depth == 0) { return 123; }
//        Map<ChessPiece, Position> availableMoves = getAvailableMoves(maximizer);
//        int max = 0;
//        for(Map.Entry<ChessPiece, Position> entry : availableMoves.entrySet()) 
//        {
//                ChessPiece piece = entry.getKey();
//                Position pos = entry.getValue();
//                printBoard();
//                piece.move(board, pos.getX(), pos.getY());
//                maximizer = !maximizer;
//                //countMoves++;
//                printBoard();
//                maxi(depth-1, maximizer);
//        }
//        return 123;
//    }
//    public int maxi(int depth, boolean maximizer)
//    {
//        if(depth == 0) { return 123; }
//        Map<ChessPiece, Position> availableMoves = getAvailableMoves(maximizer);
//        int min = 0;
//        for(Map.Entry<ChessPiece, Position> entry : availableMoves.entrySet()) 
//        {
//                ChessPiece piece = entry.getKey();
//                Position pos = entry.getValue();
//                printBoard();
//                piece.move(board, pos.getX(), pos.getY());
//                maximizer = !maximizer;
//                //countMoves++;
//                printBoard();
//                mini(depth-1, maximizer);
//        }
//        return 123;
//    }
    
    public int miniMax(int depth, boolean maximizer)
    {
        if(depth == 0) { return 123; }
        int countMoves = 0;
        //while(depth > 0)
        //{
            Map<ChessPiece, Position> availableMoves = getAvailableMoves(maximizer);
            printBoard();
            int bestMove = 0;
            for(Map.Entry<ChessPiece, Position> entry : availableMoves.entrySet()) 
            {
                
                ChessPiece piece = entry.getKey();
                Position pos = entry.getValue();
                Position oldMove = new Position(piece.getPosition().getX(),piece.getPosition().getY());
                printBoard();
                ChessPiece captured = piece.move(board, pos.getX(), pos.getY());
                countMoves++;
                printBoard();
                miniMax(depth-1, !maximizer);
                //piece.resetPositions(this.board, oldMove, pos, captured);
            }
       // }
        
        return 234;
    }
    
//    public void miniMax(ChessPiece node, int depth,  boolean maximisingPlayer)
//    {         
//        while(depth > 0)
//        {
//            if(maximisingPlayer)
//            {
//                array<Positions> moves = this.getAvailableMoves(maximisingPlayer);
//                int bestMove = 0;
//                foreach(moves as move)
//                {
//                    piece.makeMove(move);
//                    maximisingPlayer ^= maximisingPlayer;
//                    HashMap leafNodes = miniMax(move, depth-1, maximisingPlayer);
//                    int topEvaluation = max(evaluateBoards(leafNodes));
//                    piece.undoMove(move);
//                    return topEvaluation
//                }
//            }
//            else
//            {
//                array<Positions> moves = this.getAvailableMoves(maximisingPlayer);
//                int bestMove = 0;
//                foreach(moves as move)
//                {
//                    piece.makeMove(move);
//                    maximisingPlayer ^= maximisingPlayer;
//                    HashMap leafNodes = miniMax(move, depth-1, maximisingPlayer);
//                    int bottomEvaluation = min(evaluateBoards(leafNodes));
//                    piece.undoMove(move);
//                    return bottomEvaluation;
//                }
//            }
//        }
//    }
}
