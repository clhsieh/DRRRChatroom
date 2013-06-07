// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:14
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MsgBlock.java

package drrrchat_client;

import java.awt.*;
import java.io.*;
import java.util.GregorianCalendar;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

// Referenced classes of package drrrchat_client:
//            IconPanel, BalloonPanel

public class MsgBlock extends JLayeredPane
{

    public MsgBlock(String msg, Font f, boolean keeprecord, boolean playsound)
    {
        this.f = f;
        setLayout(null);
        if(msg.startsWith("[j]"))
        {
            blockheight = 80;
            try
            {
                if(playsound)
                {
                    fis = new FileInputStream("userin.wav");
                    as = new AudioStream(fis);
                    AudioPlayer.player.start(as);
                }
                infolabel = new JLabel((new StringBuilder()).append("<html><font size=\"5\">\uFF0D\uFF0D").append(msg.substring(3, msg.length())).append("\u52A0\u5165</font></html>").toString());
                infolabel.setForeground(Color.WHITE);
                infolabel.setBounds(0, 0, 400, 30);
                add(infolabel);
            }
            catch(Exception ex) { }
        } else
        if(msg.startsWith("[l]"))
        {
            blockheight = 80;
            try
            {
                if(playsound)
                {
                    fis = new FileInputStream("userout.wav");
                    as = new AudioStream(fis);
                    AudioPlayer.player.start(as);
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
            infolabel = new JLabel((new StringBuilder()).append("<html><font size=\"5\">\uFF0D\uFF0D").append(msg.substring(3, msg.length())).append("\u96E2\u958B</font></html>").toString());
            infolabel.setForeground(Color.WHITE);
            infolabel.setBounds(0, 0, 400, 30);
            add(infolabel);
        } else
        if(msg.startsWith("[k]"))
        {
            blockheight = 80;
            try
            {
                if(playsound)
                {
                    fis = new FileInputStream("userout.wav");
                    as = new AudioStream(fis);
                    AudioPlayer.player.start(as);
                }
            }
            catch(Exception ex) { }
            infolabel = new JLabel((new StringBuilder()).append("<html><font size=\"5\">\uFF0D\uFF0D").append(msg.substring(3, msg.length())).append("\u88AB\u8E22</font></html>").toString());
            infolabel.setForeground(Color.WHITE);
            infolabel.setBounds(0, 0, 400, 30);
            add(infolabel);
        } else
        if(msg.contains("[w:"))
        {
            try
            {
                fis = new FileInputStream("msgin.wav");
                as = new AudioStream(fis);
                AudioPlayer.player.start(as);
            }
            catch(Exception ex) { }
            namelabel = new JLabel((new StringBuilder()).append(" ").append(msg.split(":")[0]).toString());
            namelabel.setBounds(0, 60, 400, 12);
            namelabel.setFont(new Font("", 0, 12));
            namelabel.setForeground(Color.WHITE);
            if(msg.endsWith("[0]"))
                icon = new IconPanel("0.png");
            else
            if(msg.endsWith("[1]"))
                icon = new IconPanel("1.png");
            else
            if(msg.endsWith("[2]"))
                icon = new IconPanel("2.png");
            else
            if(msg.endsWith("[3]"))
                icon = new IconPanel("3.png");
            else
            if(msg.endsWith("[4]"))
                icon = new IconPanel("4.png");
            else
            if(msg.endsWith("[5]"))
                icon = new IconPanel("5.png");
            else
            if(msg.endsWith("[6]"))
                icon = new IconPanel("6.png");
            else
            if(msg.endsWith("[7]"))
                icon = new IconPanel("7.png");
            else
            if(msg.endsWith("[8]"))
                icon = new IconPanel("8.png");
            else
            if(msg.endsWith("[9]"))
                icon = new IconPanel("9.png");
            else
            if(msg.endsWith("[10]"))
                icon = new IconPanel("10.png");
            else
            if(msg.endsWith("[a]"))
                icon = new IconPanel("a.png");
            String msgout = msg.substring(msg.indexOf("["));
            msgout = msgout.substring(msgout.indexOf("]") + 1, msgout.lastIndexOf("["));
            msgarea = new JTextPane();
            msgarea.setFont(f);
            msgarea.setBounds(85, 12, 305, 10);
            msgout = msgout.replace("\uFF1A", ":").replace("\u3014", "[").replace("\u3015", "]").replace("\uFF03", "#");
            msgarea.setText(msgout);
            msgarea.setSize((int)(msgarea.getPreferredSize().getWidth() <= 305D ? msgarea.getPreferredSize().getWidth() : 305D), (int)msgarea.getPreferredSize().getHeight());
            msgarea.setForeground(Color.BLACK);
            msgarea.setOpaque(false);
            msgarea.setEditable(false);
            blockheight = msgarea.getSize().getHeight() + 60D <= 100D ? 100 : (int)msgarea.getSize().getHeight() + 60;
            bp = new BalloonPanel(msg, msgarea, this);
            add(icon, new Integer(100));
            add(namelabel, new Integer(200));
            add(bp, new Integer(100));
            stretch();
        } else
        if(!msg.startsWith("[d]"))
        {
            try
            {
                if(playsound)
                {
                    fis = new FileInputStream("msgin.wav");
                    as = new AudioStream(fis);
                    AudioPlayer.player.start(as);
                }
            }
            catch(Exception ex) { }
            namelabel = new JLabel((new StringBuilder()).append(" ").append(msg.split(":")[0]).toString());
            namelabel.setBounds(0, 60, 150, 12);
            namelabel.setFont(new Font("", 0, 12));
            namelabel.setForeground(Color.WHITE);
            if(msg.endsWith("[0]"))
                icon = new IconPanel("0.png");
            else
            if(msg.endsWith("[1]"))
                icon = new IconPanel("1.png");
            else
            if(msg.endsWith("[2]"))
                icon = new IconPanel("2.png");
            else
            if(msg.endsWith("[3]"))
                icon = new IconPanel("3.png");
            else
            if(msg.endsWith("[4]"))
                icon = new IconPanel("4.png");
            else
            if(msg.endsWith("[5]"))
                icon = new IconPanel("5.png");
            else
            if(msg.endsWith("[6]"))
                icon = new IconPanel("6.png");
            else
            if(msg.endsWith("[7]"))
                icon = new IconPanel("7.png");
            else
            if(msg.endsWith("[8]"))
                icon = new IconPanel("8.png");
            else
            if(msg.endsWith("[9]"))
                icon = new IconPanel("9.png");
            else
            if(msg.endsWith("[10]"))
                icon = new IconPanel("10.png");
            else
            if(msg.endsWith("[a]"))
                icon = new IconPanel("a.png");
            String msgout = msg.split(":")[1];
            msgout = msgout.substring(0, msgout.lastIndexOf("["));
            msgarea = new JTextPane();
            msgarea.setFont(f);
            msgarea.setBounds(85, 12, 330, 10);
            msgout = msgout.replace("\uFF1A", ":").replace("\u3014", "[").replace("\u3015", "]").replace("\uFF03", "#");
            msgarea.setText(msgout);
            msgarea.setSize((int)(msgarea.getPreferredSize().getWidth() <= 330D ? msgarea.getPreferredSize().getWidth() : 330D), (int)msgarea.getPreferredSize().getHeight());
            msgarea.setForeground(Color.WHITE);
            msgarea.setOpaque(false);
            msgarea.setEditable(false);
            blockheight = msgarea.getSize().getHeight() + 60D <= 100D ? 100 : (int)msgarea.getSize().getHeight() + 60;
            bp = new BalloonPanel(msg, msgarea, this);
            add(icon, new Integer(100));
            add(namelabel, new Integer(200));
            add(bp, new Integer(100));
            stretch();
        }
        if(blockheight > 210)
            setBounds(10, -190, 500, 180);
        else
            setBounds(10, 30 - blockheight, 500, blockheight - 20);
        setBackground(Color.BLACK);
        if(keeprecord)
            try
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter("history", true));
                bw.write((new StringBuilder()).append((new GregorianCalendar()).getTime()).append(" - ").append(msg).append("\r\n").toString());
                bw.close();
            }
            catch(Exception e) { }
    }

    public void stretch()
    {
        Thread t = new Thread(bp);
        t.start();
    }

    JLabel namelabel;
    JLabel infolabel;
    IconPanel icon;
    BalloonPanel bp;
    FontMetrics fm;
    FileInputStream fis;
    AudioStream as;
    Font f;
    JTextPane msgarea;
    int blockheight;
}