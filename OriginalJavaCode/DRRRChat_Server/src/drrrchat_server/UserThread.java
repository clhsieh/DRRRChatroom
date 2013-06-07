// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 1/13/2013 8:26:12 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package drrrchat_server;

import java.io.*;
import java.net.Socket;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.*;
import javax.swing.text.Document;

// Referenced classes of package drrrchat_server:
//            AdminConsole, IdCardList, IdCard

class UserThread
    implements Runnable
{

    public UserThread(Socket s, int id, UserThread userlist[], String ipadd, AdminConsole console)
    {
        try
        {
            name = "";
            kickflag = false;
            this.id = id;
            this.s = s;
            this.userlist = userlist;
            this.console = console;
            this.ipadd = ipadd;
            bis = new BufferedInputStream(s.getInputStream());
            bos = new BufferedOutputStream(s.getOutputStream());
            dos = new DataOutputStream(bos);
            dis = new DataInputStream(bis);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                userlist[id] = null;
                s.close();
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
    }

    public void sendtoall(String msg)
    {
        try
        {
            if(msg.startsWith("[w:"))
            {
                String to = msg.substring(3, msg.indexOf("]"));
                for(int i = 0; i < 50; i++)
                    if(userlist[i] != null && userlist[i].name.equals(to))
                    {
                        userlist[i].dos.writeUTF((new StringBuilder()).append(!msg.startsWith("[j]") && !msg.startsWith("[l]") ? (new StringBuilder()).append(name).append(":").toString() : "").append(msg).toString());
                        userlist[i].dos.flush();
                    }

                msg = (new StringBuffer(msg)).insert(msg.indexOf("]") + 1, (new StringBuilder()).append("(").append(to).append(")").toString()).toString();
                dos.writeUTF((new StringBuilder()).append(!msg.startsWith("[j]") && !msg.startsWith("[l]") && !msg.startsWith("[k]") ? (new StringBuilder()).append(name).append(":").toString() : "").append(msg).toString());
                dos.flush();
            } else
            {
                for(int i = 0; i < 50; i++)
                    if(userlist[i] != null)
                    {
                        userlist[i].dos.writeUTF((new StringBuilder()).append(!msg.startsWith("[j]") && !msg.startsWith("[l]") ? (new StringBuilder()).append(name).append(":").toString() : "").append(msg).toString());
                        userlist[i].dos.flush();
                    }

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
            try
            {
                userlist[id] = null;
                s.close();
            }
            catch(Exception e2)
            {
                e2.printStackTrace();
            }
        }
    }

    public void run()
    {
        boolean duplicate = false;
        try
        {
            String clientversion = dis.readUTF();
            if(!clientversion.equals("20110129"))
            {
                s.close();
                userlist[id] = null;
            } else
            {
                String buffered = "";
                for(int i = 0; i < console.msgbuffer.size(); i++)
                    buffered = (new StringBuilder()).append(buffered).append(console.msgbuffer.get(i).toString()).append("#").toString();

                dos.writeUTF(buffered);
                dos.flush();
                String namein = dis.readUTF();
                String tmpname = namein.substring(3, namein.length());
                System.out.println("checking if exists");
                for(int i = 0; i < userlist.length; i++)
                    if(userlist[i] != null && userlist[i].name != null && tmpname.equals(userlist[i].name))
                        duplicate = true;

                if(duplicate)
                {
                    userlist[id] = null;
                    dos.writeUTF("[d]");
                    dos.flush();
                    s.close();
                }
                name = namein.substring(3, namein.length());
                if(!duplicate)
                {
                    sendtoall(namein);
                    console.updatebuffer(namein);
                }
                String users = "[u]";
                for(int i = 0; i < 50; i++)
                    if(userlist[i] != null && userlist[i].name != null)
                        users = (new StringBuilder()).append(users).append(userlist[i].name).append(",").toString();

                dos.writeUTF(users);
                dos.flush();
                console.list.cards[id].loaduser(name, ipadd);
                if(!duplicate)
                    console.recordarea.append((new StringBuilder()).append((new GregorianCalendar()).getTime()).append(" - ").append(name).append(" \u52A0\u5165\r\n").toString());
                if(console.currentrow == 100)
                    try
                    {
                        console.recordarea.replaceRange("", console.recordarea.getLineStartOffset(0), console.recordarea.getLineStartOffset(1));
                        console.recordarea.setCaretPosition(console.recordarea.getDocument().getLength());
                    }
                    catch(Exception e2) { }
                else
                    console.currentrow++;
                console.recordscroll.getVerticalScrollBar().setValue(console.recordscroll.getVerticalScrollBar().getMaximum());
                do
                {
                    String msg = dis.readUTF();
                    if(!msg.equals(" "))
                    {
                        console.recordarea.append((new StringBuilder()).append((new GregorianCalendar()).getTime()).append(" - ").append(name).append(":").append(msg).append("\r\n").toString());
                        if(console.currentrow == 100)
                            try
                            {
                                console.recordarea.replaceRange("", console.recordarea.getLineStartOffset(0), console.recordarea.getLineStartOffset(1));
                                console.recordarea.setCaretPosition(console.recordarea.getDocument().getLength());
                            }
                            catch(Exception e2) { }
                        else
                            console.currentrow++;
                        console.recordscroll.getVerticalScrollBar().setValue(console.recordscroll.getVerticalScrollBar().getMaximum());
                        sendtoall(msg);
                        if(!msg.startsWith("[w:"))
                            console.updatebuffer((new StringBuilder()).append(name).append(":").append(msg).toString());
                        console.list.cards[id].loadmsg(msg);
                    } else
                    {
                        System.out.println((new StringBuilder()).append("get ct from ").append(id).toString());
                    }
                } while(true);
            }
        }
        catch(Exception e)
        {
            userlist[id] = null;
            if(!kickflag)
            {
                try
                {
                    s.close();
                }
                catch(Exception e2)
                {
                    System.out.println(e2);
                }
                if(!duplicate)
                    console.recordarea.append((new StringBuilder()).append((new GregorianCalendar()).getTime()).append(" - ").append(name).append(" \u96E2\u958B\r\n").toString());
                if(console.currentrow == 100)
                    try
                    {
                        console.recordarea.replaceRange("", console.recordarea.getLineStartOffset(0), console.recordarea.getLineStartOffset(1));
                        console.recordarea.setCaretPosition(console.recordarea.getDocument().getLength());
                    }
                    catch(Exception e2) { }
                else
                    console.currentrow++;
                console.recordscroll.getVerticalScrollBar().setValue(console.recordscroll.getVerticalScrollBar().getMaximum());
                if(!duplicate)
                {
                    console.sendtoall((new StringBuilder()).append("[l]").append(name).toString());
                    console.updatebuffer((new StringBuilder()).append("[l]").append(name).toString());
                }
            }
            e.printStackTrace();
            console.list.cards[id].clear();
        }
    }

    int id;
    String name;
    Socket s;
    String ipadd;
    BufferedInputStream bis;
    BufferedOutputStream bos;
    DataOutputStream dos;
    DataInputStream dis;
    AdminConsole console;
    UserThread userlist[];
    boolean kickflag;
}