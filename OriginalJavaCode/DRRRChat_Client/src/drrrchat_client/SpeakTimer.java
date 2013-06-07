// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:08
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SpeakTimer.java

package drrrchat_client;

import java.io.PrintStream;

// Referenced classes of package drrrchat_client:
//            Main

class SpeakTimer extends Thread
{

    public SpeakTimer(Main cc)
    {
        this.cc = cc;
    }

    public void run()
    {
        System.out.println(cc.allowspeak);
        try
        {
            Thread.sleep(2000L);
        }
        catch(Exception e) { }
        cc.allowspeak = true;
        System.out.println(cc.allowspeak);
    }

    Main cc;
}