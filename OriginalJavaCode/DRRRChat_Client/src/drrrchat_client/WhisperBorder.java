// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2013/1/13 ¤U¤È 06:43:05
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   WhisperMode.java

package drrrchat_client;

import java.awt.*;
import javax.swing.JPanel;

class WhisperBorder extends JPanel
{

    public WhisperBorder()
    {
        setBounds(0, 5, 450, 600);
        setLayout(null);
    }

    public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(new Color(220, 220, 220, 220));
        g2D.setStroke(new BasicStroke(5F));
        g2D.drawRoundRect(2, 0, 440, 590, 20, 20);
        g2D.setStroke(new BasicStroke(2.0F));
        g2D.fillRoundRect(5, 3, 435, 80, 20, 0);
        g2D.setColor(new Color(255, 255, 255, 255));
        g2D.drawRoundRect(20, 25, 400, 25, 10, 10);
        g2D.drawRoundRect(170, 60, 100, 18, 10, 10);
        g2D.setColor(new Color(255, 255, 255, 150));
        g2D.fillRoundRect(171, 61, 98, 18, 10, 10);
        g2D.setColor(new Color(0, 0, 0, 120));
        g2D.fillRoundRect(22, 26, 397, 23, 10, 10);
        g2D.setFont(new Font("", 1, 12));
        g2D.setColor(new Color(255, 255, 255));
        g2D.drawString("secret mode", 180, 10);
        g2D.drawString("to:", 30, 41);
        g2D.drawString("POST!", 202, 73);
        g2D.setColor(new Color(0, 0, 0, 200));
        g2D.fillRect(5, 83, 435, 510);
        g2D.setColor(new Color(220, 220, 220, 220));
        g2D.setStroke(new BasicStroke(5F));
        g2D.drawLine(6, 587, 437, 587);
    }
}