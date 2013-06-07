// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:03
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WhisperMode.java

package drrrchat_client;

import java.awt.*;
import javax.swing.*;

class WhisperCtrl extends JLayeredPane
{

    public WhisperCtrl()
    {
        msgfield = new JTextField();
        msgfield.setBounds(130, 32, 285, 20);
        msgfield.setActionCommand("sendw");
        msgfield.requestFocus();
        msgfield.setBorder(null);
        msgfield.setOpaque(false);
        msgfield.setForeground(Color.WHITE);
        post = new JLabel();
        post.setBounds(170, 65, 100, 18);
        post.setCursor(Cursor.getPredefinedCursor(12));
        hidepanel = new JLabel();
        hidepanel.setBounds(430, 10, 12, 12);
        hidepanel.setIcon(new ImageIcon(WhisperCtrl.class.getResource("img/close.png")));
        userlist = new JComboBox();
        userlist.setBounds(48, 32, 80, 21);
        userlist.setFont(new Font("", 0, 11));
        setLayout(null);
        setBounds(0, 0, 460, 95);
        add(msgfield);
        add(post);
        add(hidepanel);
        add(userlist);
    }

    JTextField msgfield;
    JLabel post;
    JLabel hidepanel;
    JComboBox userlist;
}