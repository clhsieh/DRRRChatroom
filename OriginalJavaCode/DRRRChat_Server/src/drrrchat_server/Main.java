// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 1/13/2013 8:26:09 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package drrrchat_server;

import java.awt.TextField;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.Properties;

// Referenced classes of package drrrchat_server:
//            UserThread, AdminConsole, ConnTester

public class Main
{

    public Main()
    {
    }

    public static void main(String args[])
    {
        Properties prop;
        ServerSocket ss;
        UserThread userlist[];
        AdminConsole console;
        prop = new Properties();
		
		try{
			FileInputStream in = new FileInputStream("config.ini");
    		prop.load(in);
	        
        	in.close();
		
	        
	        int port = Integer.parseInt(prop.getProperty("port"));
	        int buffersize = Integer.parseInt(prop.getProperty("buffersize"));
	        String adminname = prop.getProperty("defaultname");
	        ss = new ServerSocket(port);
	        System.out.println((new StringBuilder()).append("use port:").append(port).toString());
	        System.out.println("Server started");
	        userlist = new UserThread[51];
	        console = new AdminConsole(userlist, buffersize);
	        console.namefield.setText(adminname);
	
			
	        Socket s;
	        String ipadd;
	        String blackip;
	        
	        while(true){
	        
	        	s = ss.accept();
		        ipadd = s.getInetAddress().toString();
		        System.out.println((new StringBuilder()).append("connection from ").append(ipadd).toString());
		        
		        blackip = prop.getProperty("blackip");
		        if(!blackip.contains(ipadd.substring(1))){
					for(int i=0;i<userlist.length;i++){
						System.out.println("i="+i);
						
						if(userlist[i] == null){
							
							
							userlist[i] = new UserThread(s, i, userlist, ipadd, console);
							Thread ut = new Thread(userlist[i]);
							ut.start();
							Thread ct = new Thread(new ConnTester(userlist[i]));
							ct.start();
							
							if(i == 50){
								s.close();
								userlist[50] = null;
							}
							
							System.out.println("thread created i="+i);

                            i++;

							break;
						}
						
						
					}
				}else{
					System.out.println("match blackip!");
					s.close();
				}
			}
			
	        
        }catch(Exception e){
			e.printStackTrace();
		} 
    }
}