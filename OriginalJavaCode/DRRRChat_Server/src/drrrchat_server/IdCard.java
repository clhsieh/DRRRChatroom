// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 1/13/2013 8:26:03 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package drrrchat_server;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

class IdCard extends JPanel
{

    public IdCard(int id, String name, String ipadd)
    {
        this.id = id;
        idlabel = new JLabel(String.valueOf(id + 1));
        idlabel.setBounds(2, 6, 20, 20);
        idlabel.setForeground(Color.WHITE);
        namelabel = new JLabel();
        namelabel.setBounds(18, 8, 75, 20);
        namelabel.setForeground(Color.WHITE);
        namelabel.setFont(new Font("", 0, 12));
        iplabel = new JLabel("");
        iplabel.setFont(new Font("", 2, 10));
        iplabel.setBounds(18, 28, 110, 15);
        iplabel.setForeground(Color.WHITE);
        lastspeak = new JLabel();
        lastspeak.setBounds(93, 8, 195, 20);
        lastspeak.setForeground(Color.WHITE);
        lastspeak.setFont(new Font("", 0, 12));
        kickout = new JButton("kick!");
        kickout.setActionCommand("kick");
        kickout.setBounds(290, 10, 60, 20);
        kickout.setFont(new Font("", 0, 12));
        kickout.setEnabled(false);
        setLayout(null);
        setBounds(0, id * 40, 355, 40);
        setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50)));
        setBackground(Color.BLACK);
        add(idlabel);
        add(namelabel);
        add(iplabel);
        add(lastspeak);
        add(kickout);
    }

    public void loaduser(String name, String ipadd)
    {
        this.name = name;
        namelabel.setText(name);
        iplabel.setText((new StringBuilder()).append("from:").append(ipadd.split("/")[1]).toString());
        kickout.setEnabled(true);
    }

    public void loadmsg(String msg)
    {
        lastspeak.setText((new StringBuilder()).append("\uFF1A").append(msg).toString());
    }

    public void clear()
    {
        name = null;
        namelabel.setText("");
        iplabel.setText("");
        lastspeak.setText("");
        kickout.setEnabled(false);
    }

    int id;
    String name;
    JLabel idlabel;
    JLabel namelabel;
    JLabel iplabel;
    JLabel lastspeak;
    JButton kickout;
}