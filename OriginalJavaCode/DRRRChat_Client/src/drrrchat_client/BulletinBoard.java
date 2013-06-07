
package drrrchat_client;

import java.awt.*;
import java.io.FileInputStream;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class BulletinBoard extends JLayeredPane
    implements Runnable
{

    public BulletinBoard(boolean playsound)
    {
        this.playsound = playsound;
        setLayout(null);
        tf = new JTextField();
        tf.setBounds(450, 3, (int)tf.getPreferredSize().getWidth(), 20);
        tf.setEditable(false);
        tf.setOpaque(false);
        tf.setBorder(null);
        tf.setFont(new Font("", 0, 20));
        tf.setForeground(Color.WHITE);
        bottom = new JPanel();
        bottom.setBackground(new Color(0, 0, 0, 180));
        bottom.setBounds(0, 0, 460, 30);
        add(bottom, new Integer(0));
        add(tf, new Integer(300));
        setBounds(0, 640, 460, 30);
        setVisible(false);
    }

    public void run()
    {
        try
        {
            setVisible(true);
            if(playsound)
            {
                fis = new FileInputStream("broadcast.wav");
                as = new AudioStream(fis);
                AudioPlayer.player.start(as);
            }
            Thread.sleep(1000L);
            tf.setBounds(450, 3, (int)tf.getPreferredSize().getWidth(), 20);
        }
        catch(Exception ex) { }
        while(tf.getLocation().getX() > -tf.getPreferredSize().getWidth()) 
            try
            {
                Thread.sleep(50L);
                tf.setLocation((int)tf.getLocation().getX() - 3, (int)tf.getLocation().getY());
            }
            catch(Exception e) { }
        try
        {
            Thread.sleep(1000L);
        }
        catch(Exception e) { }
        setVisible(false);
    }

    JTextField tf;
    JPanel bottom;
    FileInputStream fis;
    AudioStream as;
    boolean playsound;
}