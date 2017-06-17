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
    protected Position position;
    protected ChessPiece.COLOR color;
    protected boolean alive;
    protected int value;
    
    public abstract List<Position> getAvailableMoves(ChessPiece[][] board);
    public abstract String getAcsiiCode();
     
    /**
     * Get the pieces color
     * @return enum
     */
    public COLOR getColor()
    {
        return this.color;
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
     * Check if a piece move causes jeopardy to their king.
     * There are two ways to do this but only one right way.
     * 
     * Firstly, we could reuse each piece's "getAvailablePositions" method, 
     * from the opposite color, and see if any of them intersect with the king. 
     * This means each piece has to check all it's available moves and report 
     * back to the king. The king then determines if the move will put him in 
     * jeopardy based on his position. The individual piece's move algorithm would 
     * be reusable but would cause many more unnecessary calculations.
     * 
     * Secondly, we could have the king itself check for moves that would put him himself 
     * in jeopardy. This means the king would delegate to the pieces where they are 
     * allowed to move instead of the pieces reporting all their available positions 
     * back to the king. This method greatly decreases valuable processing time.
     * 
     * TODO! each section of this method should be separated into its own method.
     * 
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
        
        int kingX = king.position.getX();
        int kingY = king.position.getY();
 
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
                    while(board[kingX+x*index][kingY+y*index] == null) { index++; }
                    int nextX = kingX+x*index;
                    int nextY = kingY+y*index;
                    //String aaa = board[nextX][nextY].getAcsiiCode();
                    if(board[nextX][nextY].color != this.color)
                    {
                        if(Bishop.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()) || Queen.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()))
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
                while(board[kingX+x*index][kingY] == null) { index++; }
                int nextX = kingX+x*index;
                int nextY = kingY;
                if(board[nextX][nextY].color != this.color)
                {
                    if(Rook.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()) || Queen.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()))
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
                while(board[kingX][kingY+y*index] == null) { index++; }
                int nextX = kingX;
                int nextY = kingY+y*index;
                if(board[nextX][nextY].color != this.color)
                {
                    if(Rook.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()) || Queen.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()))
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
                int nextX = kingX+x*2;
                int nextY = kingY+y;
                try
                {
                    if(board[nextX][nextY] != null)
                    {
                        if(board[nextX][nextY].color != this.color && Knight.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()))
                        {
                            //the king is in jeopardy. reset piece locations
                            resetPositions(board, originalPosition, newPosition, captured);
                            return false;
                        }
                    }
                }catch(IndexOutOfBoundsException e) {}
                
                nextX = kingX+x;
                nextY = kingY+y*2;
                try
                {
                    if(board[nextX][nextY] != null)
                    {
                        if(board[nextX][nextY].color != king.color && Knight.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()))
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
            try
            {
                if(king.color == COLOR.BLACK)
                {
                    int nextX = kingX-1;
                    int nextY = kingY+horizontal;
                    if(board[nextX][nextY] != null && king.color != board[nextX][nextY].color)
                    {
                        if(Pawn.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()))
                        {
                            //the king is in jeopardy. reset piece locations
                            resetPositions(board, originalPosition, newPosition, captured);
                            return false;
                        }
                    }
                }
                else
                {
                    int nextX = kingX+1;
                    int nextY = kingY+horizontal;
                    if(board[nextX][nextY] != null && king.color != board[nextX][nextY].color)
                    {
                        if(Pawn.acsiiCode.equals(board[nextX][nextY].getAcsiiCode()))
                        {
                            //the king is in jeopardy. reset piece locations
                            resetPositions(board, originalPosition, newPosition, captured);
                            return false;
                        }
                    }
                }
            }catch(IndexOutOfBoundsException e) {}
        }
        
        /**********************
         ******** KING ********
         **********************/
        for(int x=kingX-1; x<=kingX+1; x++)
        {
            for(int y=kingY-1; y<=kingY+1; y++)
            {
                try
                {
                    if(board[x][y] != null && this.color != board[x][y].color)
                    {
                        if(King.acsiiCode.equals(board[x][y].getAcsiiCode()))
                        {
                            //the king is in jeopardy. reset piece locations
                            resetPositions(board, originalPosition, newPosition, captured);
                            return false;
                        }
                    }
                }catch(IndexOutOfBoundsException e) {}
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
     * @return ChessPiece (any captured piece from the move)
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

}
