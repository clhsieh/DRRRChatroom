// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:42:59
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WhisperMode.java

package drrrchat_client;

import java.awt.*;
import java.io.PrintStream;
import java.util.Vector;
import javax.swing.JLayeredPane;

// Referenced classes of package drrrchat_client:
//            MsgBlock

class WhisperPane extends JLayeredPane
{

    public WhisperPane()
    {
        msgs = new Vector();
        setLayout(null);
        setPreferredSize(new Dimension(100, 0));
    }

    public void refresh(String msg, Font f, boolean keeprecord, boolean playsound)
    {
        newmsg = new MsgBlock(msg, f, keeprecord, playsound);
        add(newmsg);
        msgs.add(newmsg);
        if(msgs.size() > 60)
        {
            remove((MsgBlock)msgs.get(0));
            msgs.remove(0);
        }
        rearrange();
    }

    public void rearrange()
    {
        try
        {
            int blockheight = newmsg.blockheight;
            System.out.println((new StringBuilder()).append("block=").append(blockheight).toString());
            for(int i = 0; i < msgs.size(); i++)
            {
                MsgBlock msgblock = (MsgBlock)msgs.get(i);
                if(blockheight > 210)
                    msgblock.setLocation(10, (int)msgblock.getLocation().getY() + 200);
                else
                    msgblock.setLocation(10, ((int)msgblock.getLocation().getY() + blockheight) - 20);
            }

            repaint();
            if(getPreferredSize().getHeight() < 4000D)
            {
                if(blockheight > 210)
                    setPreferredSize(new Dimension(100, (int)getPreferredSize().getHeight() + 210));
                else
                    setPreferredSize(new Dimension(100, ((int)getPreferredSize().getHeight() + blockheight) - 20));
                revalidate();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    Vector msgs;
    MsgBlock newmsg;
}