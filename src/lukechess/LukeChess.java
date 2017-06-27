package lukechess;
        
import javax.swing.*;
import java.util.List;
import lukechess.ChessPieces.*;
/**
 *
 * @author Luke
 */
public class LukeChess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Luke's first Chess engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserInterface ui = new UserInterface();
        frame.add(ui);
        frame.setSize(500,500);
        frame.setVisible(true);
        
        ChessBoard b = new ChessBoard();
        ChessPiece[][] board = b.board;
        
        //[6][4].move(b.board, 7, 4);
        
        b.miniMax(2, true);
        
        
        
        
        
        
        ChessPiece king = findBlackKing(board);
        List<Position> availablePositions = king.getAvailableMoves(board);
    
        for(int i=0; i<64; i++)
        {
            if(i%8 ==0)
                System.out.println();
                            
            int x = i/8;
            int y = i%8;
               
            boolean bingo = false;
            for (Position pos : availablePositions)
            {
                if(pos.getX() == x && pos.getY() == y)
                {
                    bingo = true;
                    if(board[pos.getX()][pos.getY()] != null)
                    {
                        System.out.print("$" + " ");
                        break;
                    }
                    else
                    {
                        System.out.print("-" + " ");
                        break;
                    }
                }
            }
            if(!bingo)
            {
                if(board[i/8][i%8] == null)
                {
                    System.out.print("*" + " ");
                    //continue;
                }
                else
                {
                    System.out.print(board[i/8][i%8].getAcsiiCode() + " ");
                }
            }
        }
    }
    
    
    /**
     * Find this sides  //TODO THIS METHOD SHOULD NOT EXIST! THE POSITION OF THE KING SHOULD BE SAVED IN THE BOARD
     * @param board
     * @return ChessPiece
     */
    public static ChessPiece findBlackKing(ChessPiece[][] board)
    {
        ChessPiece king = null;
        for(int i=0; i<64; i++)
        {
            int x = i/8;
            int y = i%8;
            if(board[x][y] != null && "A".equals(board[x][y].getAcsiiCode()) && board[x][y].getColor() == ChessPiece.COLOR.BLACK)
            {
                king = board[x][y];
                break;
            }
        }
        return king;
    }
}
