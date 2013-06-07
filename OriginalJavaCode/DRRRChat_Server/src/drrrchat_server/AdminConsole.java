// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 1/13/2013 8:25:53 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package drrrchat_server;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.Document;

// Referenced classes of package drrrchat_server:
//            IdCardList, Broadcaster, IdCard, UserThread

class AdminConsole extends JFrame
    implements ActionListener
{

    public AdminConsole(UserThread userlist[], int buffersize)
    {
        super("admin console");
        this.userlist = userlist;
        this.buffersize = buffersize;
        namefield = new TextField();
        namefield.setBounds(10, 420, 60, 20);
        namefield.setFont(new Font("", 0, 16));
        msgfield = new TextField();
        msgfield.setBounds(75, 420, 310, 20);
        msgfield.setFont(new Font("", 0, 16));
        msgfield.addActionListener(this);
        list = new IdCardList(userlist);
        for(int i = 0; i < 50; i++)
            list.cards[i].kickout.addActionListener(this);

        Toolkit t = Toolkit.getDefaultToolkit();
        setBounds((int)t.getScreenSize().getWidth() / 2 - 400, (int)t.getScreenSize().getHeight() / 2 - 240, 800, 480);
        c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.BLACK);
        listscroll = new JScrollPane(list);
        listscroll.setBounds(10, 10, 375, 400);
        listscroll.getVerticalScrollBar().setUnitIncrement(30);
        recordarea = new JTextArea();
        recordarea.setEditable(false);
        recordarea.setLineWrap(true);
        recordarea.setBackground(Color.BLACK);
        recordarea.setForeground(Color.WHITE);
        recordarea.append("server started\r\n");
        recordscroll = new JScrollPane(recordarea);
        recordscroll.setBounds(395, 10, 390, 435);
        recordscroll.getVerticalScrollBar().setUnitIncrement(40);
        currentrow = 1;
        c.add(msgfield);
        c.add(namefield);
        c.add(listscroll);
        c.add(recordscroll);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(3);
        msgbuffer = new Vector();
        Thread bt = new Thread(new Broadcaster(userlist));
        bt.start();
    }

    public void sendtoall(String msg)
    {
        try
        {
            for(int i = 0; i < 50; i++)
                if(userlist[i] != null)
                {
                    userlist[i].dos.writeUTF(msg);
                    userlist[i].dos.flush();
                }

        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("kick"))
        {
            userlist[((IdCard)(IdCard)((Component)ae.getSource()).getParent()).id].kickflag = true;
            sendtoall((new StringBuilder()).append("[k]").append(((IdCard)(IdCard)((Component)ae.getSource()).getParent()).name).toString());
            updatebuffer((new StringBuilder()).append("[k]").append(((IdCard)(IdCard)((Component)ae.getSource()).getParent()).name).toString());
            recordarea.append((new StringBuilder()).append((new GregorianCalendar()).getTime()).append(" - ").append(((IdCard)(IdCard)((Component)ae.getSource()).getParent()).name).append("\u88AB\u8E22\r\n").toString());
            if(currentrow == 100)
                try
                {
                    recordarea.replaceRange("", recordarea.getLineStartOffset(0), recordarea.getLineStartOffset(1));
                    recordarea.setCaretPosition(recordarea.getDocument().getLength());
                }
                catch(Exception e) { }
            else
                currentrow++;
            recordscroll.getVerticalScrollBar().setValue(recordscroll.getVerticalScrollBar().getMaximum());
            try
            {
                userlist[((IdCard)(IdCard)((Component)ae.getSource()).getParent()).id].s.close();
                userlist[((IdCard)(IdCard)((Component)ae.getSource()).getParent()).id] = null;
            }
            catch(Exception e) { }
            ((IdCard)(IdCard)((Component)ae.getSource()).getParent()).clear();
        } else
        if(!msgfield.getText().equals(""))
        {
            System.out.println((new StringBuilder()).append((new GregorianCalendar()).getTime()).append(" - ").append(namefield.getText()).append(":").append(msgfield.getText().replace(":", "\uFF1A").replace("[", "\u3014").replace("]", "\u3015").replace("#", "\uFF03")).append("[a]\r\n").toString());
            recordarea.append((new StringBuilder()).append((new GregorianCalendar()).getTime()).append(" - ").append(namefield.getText()).append(":").append(msgfield.getText().replace(":", "\uFF1A").replace("[", "\u3014").replace("]", "\u3015").replace("#", "\uFF03")).append("[a]\r\n").toString());
            if(currentrow == 100)
                try
                {
                    recordarea.replaceRange("", recordarea.getLineStartOffset(0), recordarea.getLineStartOffset(1));
                    recordarea.setCaretPosition(recordarea.getDocument().getLength());
                }
                catch(Exception e) { }
            else
                currentrow++;
            recordscroll.getVerticalScrollBar().setValue(recordscroll.getVerticalScrollBar().getMaximum());
            sendtoall((new StringBuilder()).append(namefield.getText()).append(":").append(msgfield.getText().replace(":", "\uFF1A").replace("[", "\u3014").replace("]", "\u3015").replace("#", "\uFF03")).append("[a]").toString());
            updatebuffer((new StringBuilder()).append(namefield.getText()).append(":").append(msgfield.getText().replace(":", "\uFF1A").replace("[", "\u3014").replace("]", "\u3015").replace("#", "\uFF03")).append("[a]").toString());
            msgfield.setText("");
        }
    }

    public void updatebuffer(String msg)
    {
        msgbuffer.add(msg);
        if(msgbuffer.size() > buffersize)
            msgbuffer.remove(0);
    }

    UserThread userlist[];
    Container c;
    TextField msgfield;
    TextField namefield;
    IdCardList list;
    JTextArea recordarea;
    int currentrow;
    JScrollPane listscroll;
    JScrollPane recordscroll;
    JLabel info;
    Vector msgbuffer;
    int buffersize;
}