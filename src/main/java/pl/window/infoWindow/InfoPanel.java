package pl.window.infoWindow;

import pl.Board.ImagesPieces;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final int WINDOW_WIDTH= 150;
    private final int WINDOW_HEIGHT=135;

    public int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }

    public int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

    public InfoPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        setSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }

    private void draw(Graphics g) {
        drawString(g);
        drawImage(g);
    }

    private void drawString(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD,12));
        g.drawString("Author:",0,10);
        g.setFont(new Font("Times New Roman", Font.ITALIC,12));
        g.drawString("Kamil Walo",50,10);
        g.setFont(new Font("Times New Roman", Font.BOLD,12));
        g.drawString("Version:",0,22);
        g.setFont(new Font("Times New Roman", Font.ITALIC,12));
        g.drawString("v1.0",50,22);
        g.setFont(new Font("Times New Roman", Font.BOLD,12));
        g.drawString("Data:",0,34);
        g.setFont(new Font("Times New Roman", Font.ITALIC,12));
        g.drawString("29.07.2021",50,34);
    }

    private void drawImage(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,34,110,100);
        g.drawImage(ImagesPieces.getBlackKnightImage().getScaledInstance(100,100,5),0,34,this);
    }
}
