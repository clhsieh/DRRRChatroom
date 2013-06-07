// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:35
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MsgBlock.java

package drrrchat_client;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

class IconPanel extends JPanel
{

    public IconPanel(String file)
    {
        t = Toolkit.getDefaultToolkit();
        i = t.getImage(IconPanel.class.getResource("img/"+file));
        setBounds(0, 0, 80, 80);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void paint(Graphics g)
    {
        g.drawImage(i, 0, 0, this);
    }

    Toolkit t;
    Image i;
}