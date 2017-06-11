/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this indexlate file, choose Tools | Templates
 * and open the indexlate in the editor.
 */
package lukechess.ChessPieces;

import java.util.List;
/**
 *
 * @author Luke
 */
public abstract class ChessPiece
{
    public enum COLOR { BLACK, WHITE }
    protected String acsiiCode;
    protected Position position;
    protected ChessPiece.COLOR color;
    protected boolean alive;
    protected int value;
    
    public abstract List<Position> getAvailableMoves(ChessPiece[][] board);
    
     
    /**
     * Get the pieces color
     * @return enum
     */
    public COLOR getColor()
    {
        return this.color;
    }
    
    /**
     * Get the pieces ACSI code
     * @return String
     */
    public String getAcsiiCode()
    {
        return this.acsiiCode;
    }
    
    /**
     * Get this pieces location
     * @return Position
     */
    public Position getPosition()
    {
        return this.position;
    }
    
    /**
     * Check is a piece move causes the king to be in check
     * @param board
     * @param newPosition
     * @return Boolean
     */
    protected boolean isKingSafe(ChessPiece[][] board, Position newPosition)
    {    
        //find the king (needs to be changed)
        ChessPiece king = this.findKing(board); // TODO !! save the kings postion in a class vaiable in the ChessBoard to save time
        
        //save the original position
        Position originalPosition = new Position(position.getX(), position.getY());
        
        //move the peice to the new location and save whatever is in the new postion so we can replace it after
        ChessPiece captured = move(board, newPosition);
 
        /************************
         ***** BISHOP/QUEEN *****
         ************************/
        int index = 1;
        for(int x=-1; x<=1; x+=2)
        {
            for(int y=-1; y<=1; y+=2)
            {
                try
                {
                    //find the next occupied diagnal position
                    while(board[king.position.getX()+x*index][king.position.getY()+y*index] == null) { index++; }
                    int nextX = king.position.getX()+x*index;
                    int nextY = king.position.getY()+y*index;
                    //String aaa = board[nextX][nextY].getAcsiiCode();
                    if(board[nextX][nextY].color != this.color)
                    {
                        if("B".equals(board[nextX][nextY].getAcsiiCode()) || "Q".equals(board[nextX][nextY].getAcsiiCode()))
                        {
                            //the king is in jeopardy. reset piece locations
                            resetPositions(board, originalPosition, newPosition, captured);
                            return false;
                        }
                    }
                } catch(IndexOutOfBoundsException e) {}
            }
            index = 1;
        }
        
        /**********************
         ***** ROOK/QUEEN *****
         **********************/
        //check vertical
        index = 1;
        for(int x=-1; x<=1; x+=2)
        {
            //check vertical
            try
            {
                //find the next occupied vertical position
                while(board[king.position.getX()+x*index][king.position.getY()] == null) { index++; }
                int nextX = king.position.getX()+x*index;
                int nextY = king.position.getY();
                if(board[nextX][nextY].color != this.color)
                {
                    if("R".equals(board[nextX][nextY].getAcsiiCode()) || "Q".equals(board[nextX][nextY].getAcsiiCode()))
                    {
                        //the king is in jeopardy. reset piece locations
                        resetPositions(board, originalPosition, newPosition, captured);
                        return false;
                    }
                }
            }catch(IndexOutOfBoundsException e) {}
            index = 1;
        }
        //check horizontal
        index = 1;
        for(int y=-1; y<=1; y+=2)
        {
            try
            {
                //find the next occupied horizontal position
                while(board[king.position.getX()][king.position.getY()+y*index] == null) { index++; }
                int nextX = king.position.getX();
                int nextY = king.position.getY()+y*index;
                if(board[nextX][nextY].color != this.color)
                {
                    if("R".equals(board[nextX][nextY].getAcsiiCode()) || "Q".equals(board[nextX][nextY].getAcsiiCode()))
                    {
                        //the king is in jeopardy. reset piece locations
                        resetPositions(board, originalPosition, newPosition, captured);
                        return false;
                    }
                }
            }catch(IndexOutOfBoundsException e) {}
            index = 1;
        }
        
        /**********************
         ******* KNIGHT *******
         **********************/
        for(int x=-1; x<=1; x+=2)
        {
            for(int y=-1; y<=1; y+=2)
            {
                int nextX = king.position.getX()+x*2;
                int nextY = king.position.getY()+y;
                try
                {
                    if(board[nextX][nextY] != null)
                    {
                        if(board[nextX][nextY].color != this.color && "K".equals(board[nextX][nextY].getAcsiiCode()))
                        {
                            //the king is in jeopardy. reset piece locations
                            resetPositions(board, originalPosition, newPosition, captured);
                            return false;
                        }
                    }
                }catch(IndexOutOfBoundsException e) {}
                
                nextX = king.position.getX()+x;
                nextY = king.position.getY()+y*2;
                try
                {
                    if(board[nextX][nextY] != null)
                    {
                        if(board[nextX][nextY].color != king.color && "K".equals(board[nextX][nextY].getAcsiiCode()))
                        {
                            //the king is in jeopardy. reset piece locations
                            resetPositions(board, originalPosition, newPosition, captured);
                            return false;
                        }
                    }
                }catch(IndexOutOfBoundsException e) {}
            }
        }
        
        /**********************
         ******** PAWN ********
         **********************/
        for(int horizontal=-1; horizontal<=1; horizontal+=2)
        {
            if(king.color == COLOR.BLACK)
            {
                int nextX = king.position.getX()-1;
                int nextY = king.position.getY()+horizontal;
                if(board[nextX][nextY] != null && king.color != board[nextX][nextY].color)
                {
                    //the king is in jeopardy. reset piece locations
                    resetPositions(board, originalPosition, newPosition, captured);
                    return false;
                }
            }
            else
            {
                int nextX = king.position.getX()+1;
                int nextY = king.position.getY()+horizontal;
                if(board[nextX][nextY] != null && king.color != board[nextX][nextY].color)
                {
                    //the king is in jeopardy. reset piece locations
                    resetPositions(board, originalPosition, newPosition, captured);
                    return false;
                }
            }
        }

        
        //the king is safe. reset piece locations
        resetPositions(board, originalPosition, newPosition, captured);
        return true;
    }
    
    /**
     * Reset chess pieces to original locations after check has been preformed
     * @param board
     * @param oldPos
     * @param newPos
     * @param captured 
     */
    private void resetPositions(ChessPiece[][] board, Position oldPos, Position newPos, ChessPiece captured)
    {
        move(board, oldPos);
        board[newPos.getX()][newPos.getY()] = captured;
    }
    
    /**
     * Move the piece on the board
     * @param board
     * @param newPosition 
     */
    public ChessPiece move(ChessPiece[][] board, Position newPosition)
    {    
        //remove the piece from old location
        board[this.position.getX()][this.position.getY()] = null;
        
        //set the coordenence of new postion for the peice
        this.position.setX(newPosition.getX());
        this.position.setY(newPosition.getY());
        
        //place the piece in the new postion on the board
        ChessPiece captured = board[newPosition.getX()][newPosition.getY()];
        board[newPosition.getX()][newPosition.getY()] = this;
        
        return captured;
    }
    
    /**
     * Find this sides  //TODO THIS METHOD SHOULD NOT EXIST! THE POSITION OF THE KING SHOULD BE SAVED IN THE BOARD
     * @param board
     * @return ChessPiece
     */
    public ChessPiece findKing(ChessPiece[][] board)
    {
        ChessPiece king = null;
        for(int i=0; i<64; i++)
        {
            int x = i/8;
            int y = i%8;
            if(board[x][y] != null && "A".equals(board[x][y].getAcsiiCode()) && board[x][y].getColor() == this.color)
            {
                king = board[x][y];
                break;
            }
        }
        return king;
    }
    
    /**
     * Output the contents of the board for debugging
     * @param board 
     */
    public void printBoard(ChessPiece[][] board)
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
