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
        for(int i=0; i<64; i++)
        {
            if(board[i/8][i%8] != null && board[i/8][i%8].getColor() == ChessPiece.COLOR.BLACK)
            {
                ChessPiece piece = board[i/8][i%8];
                if(piece != null && "A".equals(piece.getAcsiiCode()) && piece.getColor() == ChessPiece.COLOR.BLACK)
                {
                    List<Position> p = piece.getAvailableMoves(board);
                    
                    for (int x=0; x<board.length; x++)
                    {
                       for (int y=0; y<board[0].length; y++)
                       {
                            boolean bingo = false;
                            for (Position pos : p)
                            {
                                if(pos.getX() == x && pos.getY() == y)
                                {
                                    bingo = true;
                                    if(board[x][y] != null)
                                    {
                                        System.out.print("$" + " ");
                                    }
                                    else
                                    {
                                        System.out.print("-" + " ");
                                    }
                                }
                            }
                            if(bingo)
                            {
                                continue;
                            }
                                
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
                }
//                Position p = board[i/8][i%8].getPosition();
//                System.out.print(p.getX());
//                System.out.print(p.getY());
                //String bingo = board[i/8][i%8].getAcsiiCode();
                //System.out.print(bingo);
                //System.out.print(board[i/8][i%8].getClass());
                //System.out.print("---");
                // bingo = board[i/8][i%8].getAcsiiCode();
                //System.out.print(bingo);
            }
        }
    }
    
}
