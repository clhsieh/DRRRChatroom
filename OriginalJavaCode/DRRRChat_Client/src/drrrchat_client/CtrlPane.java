// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:38
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CtrlPane.java

package drrrchat_client;

import java.awt.*;
import javax.swing.*;

public class CtrlPane extends JLayeredPane
{

    public CtrlPane()
    {
        bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setBounds(0, 0, 460, 65);
        Font f = new Font("Time New Roman", 1, 15);
        usernum = 0;
        messagefield = new JTextField();
        messagefield.setBounds(25, 7, 400, 20);
        messagefield.setActionCommand("send");
        messagefield.requestFocus();
        messagefield.setFont(f);
        messagefield.setBorder(null);
        sendbt = new JButton("POST!");
        sendbt.setBounds(170, 30, 90, 20);
        sendbt.setActionCommand("send");
        usernumlabel = new JLabel((new StringBuilder()).append(usernum).append("/50").toString());
        usernumlabel.setBounds(395, 40, 50, 20);
        msgline = new JLabel();
        msgline.setBounds(12, 2, 430, 30);
        msgline.setIcon(new ImageIcon(CtrlPane.class.getResource("img/msg.png")));
        post = new JLabel();
        post.setBounds(150, 30, 150, 30);
        post.setIcon(new ImageIcon(CtrlPane.class.getResource("img/post.png")));
        showusers = new JLabel();
        showusers.setBounds(430, 40, 20, 20);
        showusers.setIcon(new ImageIcon(CtrlPane.class.getResource("img/showusers.png")));
        post.setCursor(Cursor.getPredefinedCursor(12));
        setLayout(null);
        add(msgline, new Integer(100));
        add(messagefield, new Integer(200));
        add(post, new Integer(100));
        add(showusers, new Integer(100));
        add(usernumlabel, new Integer(100));
        add(bottom, new Integer(50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBounds(0, 5, 460, 65);
    }

    JTextField messagefield;
    JButton sendbt;
    JLabel usernumlabel;
    JLabel msgline;
    JLabel post;
    JLabel showusers;
    JLabel notify;
    Font f;
    int usernum;
    JPanel bottom;
}