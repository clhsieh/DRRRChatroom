// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:06
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TestFrame.java

package drrrchat_client;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

// Referenced classes of package drrrchat_client:
//            WhisperMode, WhisperCtrl

public class TestFrame extends JFrame
    implements ActionListener, MouseListener
{

    public TestFrame()
    {
        bt = new JButton();
        bt.setBounds(10, 10, 100, 30);
        bt.addActionListener(this);
        wm = new WhisperMode();
        setBounds(200, 100, 460, 750);
        c = getLayeredPane();
        c.setLayout(null);
        c.add(wm);
        c.add(bt);
        wm.ctrl.hidepanel.addMouseListener(this);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public void actionPerformed(ActionEvent ae)
    {
        Thread t = new Thread(wm);
        t.start();
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent me)
    {
        Thread t = new Thread(wm);
        t.start();
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
    }

    public static void main(String args[])
    {
        new TestFrame();
    }

    JButton bt;
    WhisperMode wm;
    Container c;
}