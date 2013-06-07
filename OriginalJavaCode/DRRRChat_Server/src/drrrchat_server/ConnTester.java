// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 1/13/2013 8:25:59 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package drrrchat_server;

import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.Socket;

// Referenced classes of package drrrchat_server:
//            UserThread

class ConnTester extends Thread
{

    public ConnTester(UserThread ut)
    {
        this.ut = ut;
    }

    public void run()
    {
        try
        {
            while(true) 
            {
                sleep(15000L);
                ut.dos.writeUTF(" ");
                ut.dos.flush();
                System.out.println((new StringBuilder()).append("send ct to ").append(ut.id).toString());
            }
        }
        catch(Exception e)
        {
            try
            {
                e.printStackTrace();
                ut.s.close();
            }
            catch(Exception e2)
            {
                e.printStackTrace();
            }
        }
    }

    UserThread ut;
}