/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukechess;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Luke
 */
public class UserInterface extends JPanel implements MouseListener, MouseMotionListener
{
    static int  mouseX;
    static int mouseY;
            
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.addMouseListener(this);
        g.setColor(Color.ORANGE);
        g.drawRect(mouseX, mouseY, 40, 40);
    }
    
    @Override
    public void mouseMoved(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseClicked(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        this.repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
    @Override
    public void mouseDragged(MouseEvent e){}
}
