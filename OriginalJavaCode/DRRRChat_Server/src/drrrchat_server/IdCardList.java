// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 1/13/2013 8:26:06 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Main.java

package drrrchat_server;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

// Referenced classes of package drrrchat_server:
//            IdCard, UserThread

class IdCardList extends JPanel
{

    public IdCardList(UserThread userlist[])
    {
        cards = new IdCard[50];
        setBounds(10, 10, 365, 530);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for(int i = 0; i < 50; i++)
        {
            cards[i] = new IdCard(i, null, null);
            add(cards[i]);
        }

        setPreferredSize(new Dimension(300, 2000));
        revalidate();
    }

    IdCard cards[];
}