package pl.window.mainWindow;


import pl.Board.ImagesPieces;
import pl.window.infoWindow.InfoFrame;

import javax.swing.*;
import java.awt.*;

public class ChessFrame extends JFrame {
    public ChessFrame(){
        ChessPanel chessPanel = new ChessPanel();

        //under this comment we are adding a bar.
        JMenuBar jMenuBar = new JMenuBar();

        JMenu jGameMenu = new JMenu("Game");
        JMenuItem restartBar = new JMenuItem("Restart");
        JMenuItem minimizeWindow = new JMenuItem("Minimize");
        JMenuItem exit = new JMenuItem("Exit");
        jGameMenu.add(restartBar);
        jGameMenu.add(minimizeWindow);
        jGameMenu.add(exit);
        jMenuBar.add(jGameMenu);
        setJMenuBar(jMenuBar);

        JMenu jInfoMenu = new JMenu("Info");
        JMenuItem info = new JMenuItem("Info");
        jInfoMenu.add(info);
        jMenuBar.add(jInfoMenu);


        restartBar.addActionListener(e -> {
            chessPanel.getBoard().restartGame();
            chessPanel.repaint();
        });

        exit.addActionListener(e -> System.exit(0));

        minimizeWindow.addActionListener(e -> setExtendedState(JFrame.ICONIFIED));

        info.addActionListener(e -> new InfoFrame());


        setTitle("Chess made by - Kamil Walo");
        setIconImage(ImagesPieces.getBlackKnightImage());
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width/2 - chessPanel.getWINDOW_WIDTH()/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-chessPanel.getWINDOW_HEIGHT()/2);
        setResizable(false);
        this.add(chessPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}