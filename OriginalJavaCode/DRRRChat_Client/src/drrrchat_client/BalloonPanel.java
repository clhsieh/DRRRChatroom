// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:42:37
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MsgBlock.java

package drrrchat_client;

import java.awt.*;
import javax.swing.*;

// Referenced classes of package drrrchat_client:
//            MsgBlock

class BalloonPanel extends JPanel
    implements Runnable
{

    public BalloonPanel(String msgin, JTextPane msgarea, MsgBlock lp)
    {
        this.msgarea = msgarea;
        this.lp = lp;
        width = (int)msgarea.getSize().getWidth();
        height = (int)msgarea.getSize().getHeight() + 22;
        if(msgin.contains("[w:"))
            msg = msgin.substring(msgin.indexOf("]"));
        else
            msg = msgin.split(":")[1];
        tmpwidth = 0;
        tmpheight = 0;
        this.msgin = msgin;
        setLayout(null);
        setBounds(70, 5, 400, lp.blockheight);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void paint(Graphics g)
    {
        this.g = g;
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("", 1, 15));
        int xs[] = {
            0, 10, 10
        };
        int ys[] = {
            25, 20, 30
        };
        int xs2[] = {
            6, 13, 13
        };
        int ys2[] = {
            25, 21, 25
        };
        int xs3[] = {
            6, 13, 13
        };
        int ys3[] = {
            25, 25, 29
        };
        g2.setColor(Color.WHITE);
        g2.fillPolygon(xs, ys, 3);
        g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
        if(msgin.contains("[w:"))
        {
            if(msg.endsWith("[0]"))
            {
                g2.setColor(new Color(145, 192, 237));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[1]"))
            {
                g2.setColor(new Color(60, 98, 155));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[2]"))
            {
                g2.setColor(new Color(92, 121, 85));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[3]"))
            {
                g2.setColor(new Color(80, 80, 80));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[4]"))
            {
                g2.setColor(new Color(90, 53, 145));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[5]"))
            {
                g2.setColor(new Color(132, 227, 51));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[6]"))
            {
                g2.setColor(new Color(242, 166, 20));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[7]"))
            {
                g2.setColor(new Color(143, 143, 143));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[8]"))
            {
                g2.setColor(new Color(179, 31, 122));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[9]"))
            {
                g2.setColor(new Color(219, 103, 162));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            if(msg.endsWith("[10]"))
            {
                g2.setColor(new Color(179, 33, 39));
                g2.fillPolygon(xs, ys, 3);
                g2.fillRoundRect(10, 0, tmpwidth, tmpheight, 20, 20);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.fillPolygon(xs3, ys3, 3);
            } else
            {
                g2.setColor(new Color(220, 220, 220));
                g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs2, ys2, 3);
                g2.setColor(new Color(200, 200, 200));
                g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
                g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, tmpheight - 6, 20, 20);
                g2.fillPolygon(xs3, ys3, 3);
            }
        } else
        if(msg.endsWith("[0]"))
        {
            g2.setColor(new Color(145, 192, 237));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(115, 162, 207));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[1]"))
        {
            g2.setColor(new Color(60, 98, 155));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(30, 68, 125));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[2]"))
        {
            g2.setColor(new Color(92, 121, 85));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(61, 91, 55));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[3]"))
        {
            g2.setColor(new Color(120, 120, 120));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(90, 90, 90));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[4]"))
        {
            g2.setColor(new Color(90, 53, 145));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(60, 23, 115));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[5]"))
        {
            g2.setColor(new Color(132, 227, 51));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(102, 197, 21));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[6]"))
        {
            g2.setColor(new Color(242, 166, 20));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(212, 136, 0));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[7]"))
        {
            g2.setColor(new Color(143, 143, 143));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(113, 113, 113));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[8]"))
        {
            g2.setColor(new Color(179, 31, 122));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(149, 1, 92));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[9]"))
        {
            g2.setColor(new Color(219, 103, 162));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(189, 73, 132));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        if(msg.endsWith("[10]"))
        {
            g2.setColor(new Color(179, 33, 39));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(149, 3, 9));
            g2.fillRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, 5);
            g2.fillRoundRect(13, 3 + (tmpheight - 6) / 2, tmpwidth - 6, (tmpheight - 6) / 2, 20, 20);
            g2.fillPolygon(xs3, ys3, 3);
        } else
        {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRoundRect(13, 3, tmpwidth - 6, tmpheight - 6, 20, 20);
            g2.fillPolygon(xs2, ys2, 3);
            g2.setColor(new Color(0, 0, 0));
            g2.fillPolygon(xs3, ys3, 3);
        }
        g.setColor(Color.WHITE);
    }

    public void addmsg()
    {
        lp.add(msgarea, new Integer(200));
    }

    public void run()
    {
        try
        {
            Thread.sleep(50L);
            for(int i = 5; i < 11; i++)
            {
                tmpwidth = (width * i) / 10;
                tmpheight = (height * i) / 10;
                setLocation((70 + width / 2) - tmpwidth / 2, (5 + height / 2) - tmpheight / 2);
                repaint();
                lp.repaint();
                Thread.sleep(10L);
            }

            tmpwidth += 15;
            tmpheight += 10;
            setLocation((70 + width / 2) - tmpwidth / 2, (5 + height / 2) - tmpheight / 2);
            repaint();
            Thread.sleep(20L);
            tmpwidth -= 5;
            tmpheight -= 10;
            setLocation(70, 5);
            repaint();
            lp.repaint();
            Thread.sleep(100L);
            addmsg();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    int tmpwidth;
    int tmpheight;
    int width;
    int height;
    String msg;
    String msgin;
    Graphics g;
    JTextPane msgarea;
    JLayeredPane lp;
}