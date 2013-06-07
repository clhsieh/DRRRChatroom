

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class HistoryViewer extends JFrame
{

    public HistoryViewer()
    {
        ta = new JTextArea();
        ta.setEditable(false);
        ta.setBackground(Color.BLACK);
        ta.setForeground(Color.WHITE);
        try
        {
            br = new BufferedReader(new FileReader("history"));
            String s = "";
            String msg = "";
            while((s = br.readLine()) != null) 
            {
                msg = s.substring(31);
                if(msg.startsWith("[j]"))
                    msg = msg.substring(3) + "\u52A0\u5165";
                else
                if(msg.startsWith("[l]"))
                    msg = msg.substring(3) + "\u96E2\u958B";
                else
                if(msg.startsWith("[k]"))
                    msg = msg.substring(3) + "\u88AB\u8E22";
                else
                    msg = msg.substring(0, msg.lastIndexOf("["));
                System.out.println(msg);
                ta.append(s.substring(24, 29) + s.substring(4, 20) + " - " + msg + "\r\n");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        Toolkit t = Toolkit.getDefaultToolkit();
        setBounds((int)t.getScreenSize().getWidth() / 2 - 250, (int)t.getScreenSize().getHeight() / 2 - 200, 500, 400);
        c = getContentPane();
        JScrollPane msgscroll = new JScrollPane(ta);
        c.add(msgscroll);
        setVisible(true);
        msgscroll.getVerticalScrollBar().setValue(msgscroll.getVerticalScrollBar().getMaximum());
        setDefaultCloseOperation(3);
    }

    public static void main(String args[])
    {
        new HistoryViewer();
    }

    JTextArea ta;
    BufferedReader br;
    Container c;
}