package pl.window.infoWindow;

import pl.Board.ImagesPieces;

import javax.swing.*;
import java.awt.*;

public class InfoFrame extends JFrame {
    public InfoFrame()  {
        InfoPanel infoPanel = new InfoPanel();
        setIconImage(ImagesPieces.getBlackKnightImage());
        setBackground(Color.WHITE);
        this.add(infoPanel);
        setTitle("Info");
        pack();
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width/2 - infoPanel.getWINDOW_WIDTH()/2 ,
                Toolkit.getDefaultToolkit().getScreenSize().height/2 - infoPanel.getWINDOW_HEIGHT()/2
        );
        setResizable(false);
        setVisible(true);
    }
}
