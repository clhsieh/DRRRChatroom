// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:45
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ConnFail.java

package drrrchat_client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConnFail extends JDialog
    implements ActionListener, WindowListener
{

    public ConnFail(JFrame f, boolean b, String labelmsg)
    {
        super(f, b);
        setTitle("");
        msg = new JLabel(labelmsg);
        msg.setFont(new Font("", 0, 16));
        msg.setBounds(65, 20, 100, 30);
        confirm = new JButton("\u96E2\u958B");
        confirm.setFont(new Font("", 0, 12));
        confirm.setBounds(70, 70, 60, 20);
        confirm.addActionListener(this);
        c = getContentPane();
        c.setLayout(null);
        Toolkit t = Toolkit.getDefaultToolkit();
        setBounds((int)t.getScreenSize().getWidth() / 2 - 100, (int)t.getScreenSize().getHeight() / 2 - 75, 200, 150);
        c.add(msg);
        c.add(confirm);
        addWindowListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        System.exit(1);
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
    }

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
    }

    public void windowIconified(WindowEvent windowevent)
    {
    }

    public void windowClosed(WindowEvent windowevent)
    {
    }

    public void windowClosing(WindowEvent we)
    {
        System.exit(1);
    }

    public void windowOpened(WindowEvent windowevent)
    {
    }

    JLabel msg;
    JButton confirm;
    Container c;
}