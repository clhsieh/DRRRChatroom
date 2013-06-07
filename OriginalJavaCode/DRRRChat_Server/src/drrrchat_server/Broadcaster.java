// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 1/13/2013 8:25:56 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package drrrchat_server;

import java.io.*;
import java.util.Properties;

// Referenced classes of package drrrchat_server:
//            UserThread

class Broadcaster
    implements Runnable
{

    public Broadcaster(UserThread userlist[])
    {
        this.userlist = userlist;
    }

    public void run()
    {
        do
            try
            {
                Properties prop;
                do
                {
                    prop = new Properties();
                    FileInputStream in = new FileInputStream("config.ini");
                    prop.load(in);
                    in.close();
                    int frequency = Integer.parseInt(prop.getProperty("frequency")) * 1000;
                    Thread.sleep(frequency);
                } while(!prop.getProperty("broadcast").equals("y"));
                br = new BufferedReader(new FileReader("broadcast.txt"));
                int totalmsg = Integer.parseInt(br.readLine());
                int pick = totalmsg != 0 ? (int)(Math.random() * (double)totalmsg) + 1 : 0;
                String broadcast = "";
                int i;
                for(i = 0; i < pick; i++)
                    broadcast = br.readLine();

                i = 0;
                while(i < 50) 
                {
                    if(userlist[i] != null)
                    {
                        userlist[i].dos.writeUTF((new StringBuilder()).append("[b]").append(broadcast).toString());
                        userlist[i].dos.flush();
                    }
                    i++;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        while(true);
    }

    UserThread userlist[];
    BufferedReader br;
}