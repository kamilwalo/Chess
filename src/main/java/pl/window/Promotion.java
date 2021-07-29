package pl.window;

import pl.Board.Board;
import pl.Board.ImagesPieces;
import pl.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Promotion extends JFrame {

    private final int WIDTH_ICON =50;
    private final int HEIGHT_ICON =50;

    private JButton rookButton;
    private JButton queenButton;
    private JButton bishopButton;
    private JButton knightButton;
    private JPanel panel;
    private int idPice;
    private boolean colorOfPice;
    private Point point;

    public JPanel getPanel() {
        return panel;
    }

    public int getWIDTH_ICON() {
        return WIDTH_ICON;
    }

    public int getHEIGHT_ICON() {
        return HEIGHT_ICON;
    }

    public Promotion(JFrame frame) {
        setSize(new Dimension(WIDTH_ICON, HEIGHT_ICON));
        if(Board.tempPiece.isItWhite())lookForPice(Board.white_pieces,Board.tempPiece.getPointWhereIsPiece());
        else lookForPice(Board.black_pieces,Board.tempPiece.getPointWhereIsPiece());

        colorOfPice= Board.tempPiece.isItWhite();
        point=Board.tempPiece.getPointWhereIsPiece();

        if(Board.tempPiece.isItWhite()){
            Icon iconQueen = new ImageIcon(ImagesPieces.getWhiteQueenImage().getScaledInstance(WIDTH_ICON, HEIGHT_ICON, 5));
            queenButton.setIcon(iconQueen);
            Icon iconBishop = new ImageIcon(ImagesPieces.getWhiteBishopImage().getScaledInstance(WIDTH_ICON, HEIGHT_ICON, 5));
            bishopButton.setIcon(iconBishop);
            Icon iconKnight = new ImageIcon(ImagesPieces.getWhiteKnightImage().getScaledInstance(WIDTH_ICON, HEIGHT_ICON, 5));
            knightButton.setIcon(iconKnight);
            Icon iconRook = new ImageIcon(ImagesPieces.getWhiteRookImage().getScaledInstance(WIDTH_ICON, HEIGHT_ICON, 5));
            rookButton.setIcon(iconRook);
        }else {
            Icon iconQueen = new ImageIcon(ImagesPieces.getBlackQueenImage().getScaledInstance(WIDTH_ICON, HEIGHT_ICON, 5));
            queenButton.setIcon(iconQueen);
            Icon iconBishop = new ImageIcon(ImagesPieces.getBlackBishopImage().getScaledInstance(WIDTH_ICON, HEIGHT_ICON, 5));
            bishopButton.setIcon(iconBishop);
            Icon iconKnight = new ImageIcon(ImagesPieces.getBlackKnightImage().getScaledInstance(WIDTH_ICON, HEIGHT_ICON, 5));
            knightButton.setIcon(iconKnight);
            Icon iconRook = new ImageIcon(ImagesPieces.getBlackRookImage().getScaledInstance(WIDTH_ICON, HEIGHT_ICON, 5));
            rookButton.setIcon(iconRook);
        }

        queenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(colorOfPice) Board.white_pieces[idPice] = new Queen(point, colorOfPice);
                else Board.black_pieces[idPice] = new Queen(point, colorOfPice);
                Board.board[point.x][point.y] = "Q  ";
                frame.dispose();
            }
        });
        bishopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(colorOfPice) Board.white_pieces[idPice] = new Bishop(point, colorOfPice);
                else Board.black_pieces[idPice] = new Bishop(point, colorOfPice);
                Board.board[point.x][point.y] = "B  ";
                frame.dispose();
            }
        });
        knightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(colorOfPice) Board.white_pieces[idPice] = new Knight(point, colorOfPice);
                else Board.black_pieces[idPice] = new Knight(point, colorOfPice);
                Board.board[point.x][point.y] = "L  ";
                frame.dispose();
            }
        });
        rookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(colorOfPice) Board.white_pieces[idPice] = new Rook(point, colorOfPice);
                else Board.black_pieces[idPice] = new Rook(point, colorOfPice);
                Board.board[point.x][point.y] = "R  ";
                frame.dispose();
            }
        });
    }

    private void lookForPice(MainPice[] pices, Point point) {
        //lookForPice looking for pice with Point which playsOfPice is equal Point(x,y)
        for (int i = 0; i < pices.length; i++) {
            if(pices[i].getPointWhereIsPiece().equals(point)) {
                idPice=i;
            }
        }
    }
}