// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:16
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package drrrchat_client;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.*;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

// Referenced classes of package drrrchat_client:
//            Login, ConnFail, CtrlPane, MsgPane, 
//            WhisperMode, BulletinBoard, ConnTester, SpeakTimer, 
//            WhisperCtrl, WhisperPane

public class Main extends JFrame
    implements Runnable, ActionListener, MouseListener, WindowListener
{

    public Main()
    {
        menubar = new JMenuBar();
        statusmenu = new JMenu("test");
        item1 = new JMenuItem("test");
        statusmenu.add(item1);
        menubar.add(statusmenu);
        Properties prop = new Properties();
        try
        {
            FileInputStream in = new FileInputStream("config.ini");
            prop.load(in);
            in.close();
            String fontname = new String(prop.getProperty("font").getBytes("ISO8859-1"), "BIG5");
            if(prop.getProperty("bold").equals("y"))
                f = new Font(fontname, 1, 15);
            else
                f = new Font(fontname, 0, 15);
            String list = prop.getProperty("favorite");
            String configport = prop.getProperty("port");
            login = new Login(this, true, list, configport);
            if(prop.getProperty("keeprecord").equals("y"))
                keeprecord = true;
            else
                keeprecord = false;
            if(prop.getProperty("broadcast").equals("y"))
                broadcast = true;
            else
                broadcast = false;
            if(prop.getProperty("playsound").equals("y"))
                playsound = true;
            else
                playsound = false;
            if(prop.getProperty("minitotray").equals("y"))
                minitotray = true;
            else
                minitotray = false;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        userlist = new Vector();
        login.namefield.addActionListener(this);
        login.confirmbt.addActionListener(this);
        cf = new ConnFail(this, true, "\u9023\u7DDA\u5931\u6557!!");
        for(int i = 0; i < 11; i++)
            login.buttonlist[i].addActionListener(this);

        ctrlpane = new CtrlPane();
        msgpane = new MsgPane();
        msgscroll = new JScrollPane(msgpane);
        msgscroll.setBounds(0, 70, 455, 605);
        msgscroll.getVerticalScrollBar().setUnitIncrement(40);
        popuserlist = new JPopupMenu();
        popuserlist.setBackground(Color.BLACK);
        iconfile = "0";
        ctrlpane.sendbt.addActionListener(this);
        ctrlpane.messagefield.addActionListener(this);
        ctrlpane.showusers.addMouseListener(this);
        ctrlpane.post.addMouseListener(this);
        ctrlpane.addMouseListener(this);
        Toolkit t = Toolkit.getDefaultToolkit();
        setBounds((int)t.getScreenSize().getWidth() / 2 - 230, (int)t.getScreenSize().getHeight() / 2 - 350, 460, 700);
        whispermode = new WhisperMode();
        bb = new BulletinBoard(playsound);
        c = getLayeredPane();
        c.setLayout(null);
        c.add(ctrlpane, new Integer(100));
        c.add(msgscroll, new Integer(100));
        c.add(whispermode, new Integer(200));
        c.add(bb, new Integer(300));
        c.setBackground(Color.BLACK);
        setResizable(false);
        login.setVisible(true);
        addWindowListener(this);
        allowspeak = true;
        try
        {
            TrayIcon icon = new TrayIcon(getImage(), "DRRRChat", createPopupMenu());
            icon.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e)
                {
                    setVisible(true);
                    setState(0);
                    toFront();
                }

            
            
            });
            if(minitotray)
                SystemTray.getSystemTray().add(icon);
        }
        catch(Exception e) { }
    }

    private static Image getImage()
        throws HeadlessException
    {
        Icon defaultIcon = new ImageIcon(Main.class.getResource("img/tray.png"));
        Image img = new BufferedImage(defaultIcon.getIconWidth(), defaultIcon.getIconHeight(), 6);
        defaultIcon.paintIcon(new Panel(), img.getGraphics(), 0, 0);
        return img;
    }

    private static PopupMenu createPopupMenu()
        throws HeadlessException
    {
        PopupMenu menu = new PopupMenu();
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }

        });
        menu.add(exit);
        return menu;
    }

    public void run()
    {
        try
        {
            Thread.sleep(500L);
        }
        catch(Exception e) { }
        setVisible(true);
        msgscroll.getVerticalScrollBar().setValue(0);
        boolean justin = true;
        try
        {
            do
            {
                String msg = dis.readUTF();
                if(msg.equals(" "))
                    System.out.println("get ct");
                else
                if(msg.startsWith("[b]"))
                {
                    if(broadcast)
                    {
                        bb.tf.setText(msg.substring(3));
                        Thread t = new Thread(bb);
                        t.start();
                    }
                } else
                if(msg.startsWith("[u]"))
                {
                    String arruserlist[] = msg.substring(3).split(",");
                    java.util.List listuserlist = Arrays.asList(arruserlist);
                    userlist = new Vector(listuserlist);
                    for(int i = 0; i < userlist.size(); i++)
                    {
                        userlistitem = new JMenuItem(userlist.get(i).toString());
                        userlistitem.setFont(new Font("", 0, 12));
                        userlistitem.setBackground(Color.BLACK);
                        userlistitem.setForeground(Color.WHITE);
                        userlistitem.setActionCommand("whisper-" + userlist.get(i).toString());
                        userlistitem.addActionListener(this);
                        popuserlist.add(userlistitem);
                        whispermode.ctrl.userlist.addItem(userlist.get(i).toString());
                    }

                    ctrlpane.usernum = userlist.size();
                    ctrlpane.usernumlabel.setText(ctrlpane.usernum + "/50");
                } else
                if(msg.contains("[w:"))
                {
                    int width = 0;
                    String msgout = msg.split(":")[2];
                    msgout = msgout.substring(0, msgout.lastIndexOf("["));
                    if(width > 305)
                        width = 305;
                    System.out.println(width);
                    whispermode.pane.refresh(msg, f, keeprecord, playsound);
                    if(!whispermode.visible)
                    {
                        Thread t = new Thread(whispermode);
                        t.start();
                        whispermode.ctrl.userlist.setSelectedItem(msg.split(":")[0]);
                    }
                } else
                {
                    int width = 0;
                    if(msg.contains(":"))
                    {
                        String msgout = msg.split(":")[1];
                        msgout = msgout.substring(0, msgout.lastIndexOf("["));
                        if(width > 330)
                            width = 330;
                    }
                    msgpane.refresh(msg, f, keeprecord, playsound);
                }
                if(msg.startsWith("[j]"))
                {
                    if(!justin)
                    {
                        userlist.add(msg.substring(3));
                        userlistitem = new JMenuItem(msg.substring(3));
                        userlistitem.setFont(new Font("", 0, 12));
                        userlistitem.setBackground(Color.BLACK);
                        userlistitem.setForeground(Color.WHITE);
                        userlistitem.setActionCommand("whisper-" + msg.substring(3));
                        userlistitem.addActionListener(this);
                        popuserlist.add(userlistitem);
                        whispermode.ctrl.userlist.addItem(msg.substring(3));
                        ctrlpane.usernum++;
                        ctrlpane.usernumlabel.setText(ctrlpane.usernum + "/50");
                    } else
                    {
                        justin = false;
                    }
                } else
                if(msg.startsWith("[l]"))
                {
                    int i = 0;
                    do
                    {
                        if(i >= userlist.size())
                            break;
                        if(userlist.get(i).toString().equals(msg.substring(3)))
                        {
                            userlist.remove(i);
                            popuserlist.remove(i);
                            whispermode.ctrl.userlist.removeItemAt(i);
                            break;
                        }
                        i++;
                    } while(true);
                    ctrlpane.usernum--;
                    ctrlpane.usernumlabel.setText(ctrlpane.usernum + "/50");
                } else
                if(msg.startsWith("[k]"))
                {
                    int i = 0;
                    do
                    {
                        if(i >= userlist.size())
                            break;
                        if(userlist.get(i).toString().equals(msg.substring(3)))
                        {
                            userlist.remove(i);
                            popuserlist.remove(i);
                            whispermode.ctrl.userlist.removeItemAt(i);
                            break;
                        }
                        i++;
                    } while(true);
                    ctrlpane.usernum--;
                    ctrlpane.usernumlabel.setText(ctrlpane.usernum + "/50");
                } else
                if(msg.startsWith("[d]"))
                    cf = new ConnFail(this, true, "\u66B1\u7A31\u91CD\u8907!!");
            } while(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            fis = new FileInputStream("userout.wav");
            as = new AudioStream(fis);
            AudioPlayer.player.start(as);
        }
        catch(Exception e2) { }
        cf.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("login"))
        {
            String ip = "220.134.141.169";
            if(!login.namefield.getText().trim().equals("admin") && !login.namefield.getText().trim().equals("") && !login.namefield.getText().contains(":") && !login.namefield.getText().contains("[") && !login.namefield.getText().contains("]") && !login.namefield.getText().contains("#"))
            {
                if(!login.ipbox.getSelectedItem().toString().equals("Test Server"))
                    ip = login.ipbox.getSelectedItem().toString();
                try
                {
                    int port = Integer.parseInt(login.portfield.getText());
                    System.out.println(ip + ":" + port);
                    s = new Socket(ip, port);
                    bis = new BufferedInputStream(s.getInputStream());
                    bos = new BufferedOutputStream(s.getOutputStream());
                    dos = new DataOutputStream(bos);
                    dis = new DataInputStream(bis);
                    dos.writeUTF("20110129");
                    dos.flush();
                    String buffered = dis.readUTF();
                    if(!buffered.equals(""))
                    {
                        String bufferedarr[] = buffered.split("#");
                        for(int i = 0; i < bufferedarr.length; i++)
                            msgpane.refresh(bufferedarr[i], f, false, false);

                    }
                    dos.writeUTF("[j]" + login.namefield.getText());
                    dos.flush();
                    login.setVisible(false);
                    setTitle("Durarara!! Chatroom -- " + login.namefield.getText() + "@" + login.ipbox.getSelectedItem().toString());
                    Thread t = new Thread(this);
                    t.start();
                    Thread t2 = new Thread(new ConnTester(this));
                    t2.start();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    cf.setVisible(true);
                }
            }
        } else
        if(ae.getActionCommand().equals("send"))
        {
            if(!ctrlpane.messagefield.getText().equals("") && allowspeak)
                try
                {
                    dos.writeUTF(ctrlpane.messagefield.getText().replace("[", "\u3014").replace("]", "\u3015").replace(":", "\uFF1A").replace("#", "\uFF03") + "[" + iconfile + "]");
                    dos.flush();
                    ctrlpane.messagefield.setText("");
                    allowspeak = false;
                    (new Thread(new SpeakTimer(this))).start();
                }
                catch(Exception e)
                {
                    cf.setVisible(true);
                }
        } else
        if(ae.getActionCommand().startsWith("whisper"))
        {
            if(!whispermode.visible)
            {
                Thread t = new Thread(whispermode);
                t.start();
            }
            whispermode.ctrl.userlist.setSelectedItem(ae.getActionCommand().split("-")[1]);
        } else
        {
            iconfile = ae.getActionCommand();
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
        try
        {
            if(me.getModifiers() != 4)
                if(((Component)me.getSource()).getLocation().getX() == 430D)
                    popuserlist.show(me.getComponent(), me.getX(), me.getY());
                else
                if(((Component)me.getSource()).getLocation().getX() == 150D && !ctrlpane.messagefield.getText().equals("") && allowspeak)
                {
                    dos.writeUTF(ctrlpane.messagefield.getText().replace("[", "\u3014").replace("]", "\u3015").replace(":", "\uFF1A").replace("#", "\uFF03") + "[" + iconfile + "]");
                    dos.flush();
                    ctrlpane.messagefield.setText("");
                    allowspeak = false;
                    (new Thread(new SpeakTimer(this))).start();
                }
        }
        catch(Exception e)
        {
            System.out.println(e);
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

    public void windowDeactivated(WindowEvent windowevent)
    {
    }

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
    }

    public void windowIconified(WindowEvent we)
    {
        System.out.println("iconified");
        if(minitotray)
            setVisible(false);
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

    public static void main(String args[])
    {
        Main crc = new Main();
    }

    Socket s;
    BufferedInputStream bis;
    BufferedOutputStream bos;
    DataOutputStream dos;
    DataInputStream dis;
    MsgPane msgpane;
    Login login;
    ConnFail cf;
    CtrlPane ctrlpane;
    Container c;
    String iconfile;
    JPopupMenu popuserlist;
    JMenuItem userlistitem;
    JScrollPane msgscroll;
    Vector userlist;
    Font f;
    FileInputStream fis;
    AudioStream as;
    JMenuBar menubar;
    JMenu statusmenu;
    JMenuItem item1;
    WhisperMode whispermode;
    boolean keeprecord;
    boolean allowspeak;
    boolean broadcast;
    boolean playsound;
    boolean minitotray;
    BulletinBoard bb;
}