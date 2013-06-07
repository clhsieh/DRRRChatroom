// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:32
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Login.java

package drrrchat_client;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class Login extends JDialog
    implements WindowListener
{

    public Login(JFrame f, boolean b, String list, String port)
    {
        super(f, b);
        namefield = new JTextField();
        namefield.setBounds(150, 230, 120, 20);
        namefield.setActionCommand("login");
        namelabel = new JLabel("Name:");
        namelabel.setBounds(100, 230, 50, 20);
        namelabel.setForeground(Color.WHITE);
        iplabel = new JLabel("Server IP:");
        iplabel.setBounds(75, 200, 100, 20);
        iplabel.setForeground(Color.WHITE);
        portlabel = new JLabel("Port:");
        portlabel.setBounds(270, 200, 50, 20);
        portlabel.setForeground(Color.WHITE);
        ipbox = new JComboBox();
        ipbox.setBounds(140, 200, 120, 20);
        ipbox.setFont(new Font("", 0, 12));
        String listarr[] = list.split(",");
        for(int i = 0; i < listarr.length; i++)
            ipbox.addItem(listarr[i]);

        portfield = new JTextField(port);
        portfield.setBounds(302, 200, 60, 20);
        ipbox.setEditable(true);
        confirmbt = new JButton("join");
        confirmbt.setBounds(282, 230, 60, 20);
        confirmbt.setActionCommand("login");
        iconlist = new JLabel[11];
        for(int i = 0; i < 11; i++)
        {
            iconlist[i] = new JLabel();
            iconlist[i].setIcon(new ImageIcon(     Login.class.getResource("img/"+(new StringBuilder()).append(i).append(".png").toString())      ));
        }

        for(int i = 0; i < 6; i++)
            iconlist[i].setBounds(i * 65 + 20, 20, 60, 60);

        for(int i = 6; i < 11; i++)
            iconlist[i].setBounds((i - 6) * 65 + 50, 100, 60, 60);

        bg = new ButtonGroup();
        buttonlist = new JRadioButton[11];
        for(int i = 0; i < 11; i++)
        {
            buttonlist[i] = new JRadioButton();
            buttonlist[i].setActionCommand(String.valueOf(i));
            buttonlist[i].setBackground(Color.BLACK);
            bg.add(buttonlist[i]);
        }

        buttonlist[0].setSelected(true);
        for(int i = 0; i < 6; i++)
            buttonlist[i].setBounds(i * 65 + 40, 80, 20, 20);

        for(int i = 6; i < 11; i++)
            buttonlist[i].setBounds((i - 6) * 65 + 70, 160, 20, 20);

        Container c = getContentPane();
        c.setLayout(null);
        c.add(namefield);
        c.add(namelabel);
        c.add(ipbox);
        c.add(portfield);
        c.add(iplabel);
        c.add(confirmbt);
        c.add(portlabel);
        for(int i = 0; i < 11; i++)
        {
            c.add(iconlist[i]);
            c.add(buttonlist[i]);
        }

        c.setBackground(Color.BLACK);
        Toolkit t = Toolkit.getDefaultToolkit();
        setBounds((int)t.getScreenSize().getWidth() / 2 - 215, (int)t.getScreenSize().getHeight() / 2 - 150, 430, 300);
        setResizable(false);
        addWindowListener(this);
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
        System.exit(0);
    }

    public void windowOpened(WindowEvent windowevent)
    {
    }

    JTextField namefield;
    JButton confirmbt;
    JLabel iconlist[];
    JLabel iplabel;
    JLabel namelabel;
    JComboBox ipbox;
    JTextField portfield;
    JRadioButton buttonlist[];
    JLabel portlabel;
    ButtonGroup bg;
    String list;
}