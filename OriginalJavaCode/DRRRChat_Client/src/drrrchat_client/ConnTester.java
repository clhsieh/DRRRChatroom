// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:41
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ConnTester.java

package drrrchat_client;

import java.io.*;
import java.net.Socket;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

// Referenced classes of package drrrchat_client:
//            Main, ConnFail

class ConnTester
    implements Runnable
{

    public ConnTester(Main client)
    {
        this.client = client;
    }

    public void run()
    {
        try
        {
            while(true) 
            {
                Thread.sleep(15000L);
                client.dos.writeUTF(" ");
                client.dos.flush();
                System.out.println("send ct");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            client.fis = new FileInputStream("userout.wav");
            client.as = new AudioStream(client.fis);
            AudioPlayer.player.start(client.as);
            client.s.close();
        }
        catch(Exception e2)
        {
            e2.printStackTrace();
        }
        client.cf.setVisible(true);
    }

    Main client;
}