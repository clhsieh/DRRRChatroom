// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:01
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WhisperMode.java

package drrrchat_client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

// Referenced classes of package drrrchat_client:
//            WhisperCtrl, WhisperPane, WhisperBorder, Main, 
//            WhisperTimer, ConnFail

public class WhisperMode extends JLayeredPane
    implements Runnable, MouseListener, ActionListener
{

    public WhisperMode()
    {
        ctrl = new WhisperCtrl();
        pane = new WhisperPane();
        border = new WhisperBorder();
        visible = false;
        setLayout(null);
        setBounds(5, 70, 0, 0);
        whisperscroll = new JScrollPane(pane);
        whisperscroll.setBounds(10, 90, 430, 500);
        whisperscroll.getVerticalScrollBar().setUnitIncrement(40);
        whisperscroll.setOpaque(false);
        whisperscroll.getViewport().setOpaque(false);
        whisperscroll.setBorder(null);
        add(ctrl, new Integer(100));
        add(whisperscroll);
        add(border, new Integer(0));
        ctrl.hidepanel.addMouseListener(this);
        ctrl.msgfield.addActionListener(this);
        ctrl.post.addMouseListener(this);
        allowspeak = true;
    }

    public void run()
    {
        try
        {
            if(visible)
            {
                for(int i = 0; i < 10; i++)
                {
                    setSize((int)getSize().getWidth() - 45, (int)getSize().getHeight() - 70);
                    setLocation(5, (i + 1) * 25 + 70);
                    Thread.sleep(10L);
                }

                visible = false;
            } else
            {
                fis = new FileInputStream("userin.wav");
                as = new AudioStream(fis);
                AudioPlayer.player.start(as);
                for(int i = 0; i < 10; i++)
                {
                    setSize((int)getSize().getWidth() + 45, (int)getSize().getHeight() + 70);
                    setLocation(5, 320 - (i + 1) * 25);
                    Thread.sleep(10L);
                }

                System.out.println("open");
                visible = true;
            }
        }
        catch(Exception e) { }
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(!ctrl.msgfield.getText().equals("") && ctrl.userlist.getSelectedItem() != null && allowspeak)
        {
            Main cc = (Main)getParent().getParent().getParent();
            try
            {
                cc.dos.writeUTF((new StringBuilder()).append("[w:").append(ctrl.userlist.getSelectedItem().toString()).append("]").append(ctrl.msgfield.getText().replace("[", "\u3014").replace("]", "\u3015").replace(":", "\uFF1A").replace("#", "\uFF03")).append("[").append(cc.iconfile).append("]").toString());
                cc.dos.flush();
                ctrl.msgfield.setText("");
                allowspeak = false;
                (new Thread(new WhisperTimer(this))).start();
            }
            catch(Exception e)
            {
                cc.cf.setVisible(true);
            }
        }
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
        if(((Component)me.getSource()).getLocation().getX() == 430D)
        {
            Thread t = new Thread(this);
            t.start();
        } else
        if(!ctrl.msgfield.getText().equals("") && ctrl.userlist.getSelectedItem() != null && allowspeak)
        {
            Main cc = (Main)getParent().getParent().getParent();
            try
            {
                cc.dos.writeUTF((new StringBuilder()).append("[w:").append(ctrl.userlist.getSelectedItem().toString()).append("]").append(ctrl.msgfield.getText().replace("[", "\u3014").replace("]", "\u3015").replace(":", "\uFF1A").replace("#", "\uFF03")).append("[").append(cc.iconfile).append("]").toString());
                cc.dos.flush();
                ctrl.msgfield.setText("");
                allowspeak = false;
                (new Thread(new WhisperTimer(this))).start();
            }
            catch(Exception e)
            {
                cc.cf.setVisible(true);
            }
        }
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

    WhisperCtrl ctrl;
    WhisperPane pane;
    WhisperBorder border;
    boolean visible;
    FileInputStream fis;
    AudioStream as;
    JScrollPane whisperscroll;
    boolean allowspeak;
}