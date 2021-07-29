package pl.window;

import pl.Board.Board;
import pl.Board.ImagesPieces;
import pl.pieces.*;

import javax.swing.*;
import java.awt.*;

public class Promotion extends JFrame {

    private final int WIDTH_ICON =50;
    private final int HEIGHT_ICON =50;

    private JButton rookButton;
    private JButton queenButton;
    private JButton bishopButton;
    private JButton knightButton;
    private JPanel panel;
    private int idPiece;
    private final boolean colorOfPiece;
    private final Point point;

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
        if(Board.tempPiece.isItWhite()) lookForPiece(Board.white_pieces,Board.tempPiece.getPointWhereIsPiece());
        else lookForPiece(Board.black_pieces,Board.tempPiece.getPointWhereIsPiece());

        colorOfPiece = Board.tempPiece.isItWhite();
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

        queenButton.addActionListener(e -> {
            if(colorOfPiece) Board.white_pieces[idPiece] = new Queen(point, true);
            else Board.black_pieces[idPiece] = new Queen(point, false);
            Board.board[point.x][point.y] = "Q  ";
            frame.dispose();
        });

        bishopButton.addActionListener(e -> {
            if(colorOfPiece) Board.white_pieces[idPiece] = new Bishop(point, true);
            else Board.black_pieces[idPiece] = new Bishop(point, false);
            Board.board[point.x][point.y] = "B  ";
            frame.dispose();
        });

        knightButton.addActionListener(e -> {
            if(colorOfPiece) Board.white_pieces[idPiece] = new Knight(point, true);
            else Board.black_pieces[idPiece] = new Knight(point, false);
            Board.board[point.x][point.y] = "L  ";
            frame.dispose();
        });

        rookButton.addActionListener(e -> {
            if(colorOfPiece) Board.white_pieces[idPiece] = new Rook(point, true);
            else Board.black_pieces[idPiece] = new Rook(point, false);
            Board.board[point.x][point.y] = "R  ";
            frame.dispose();
        });

    }

    private void lookForPiece(MainPiece[] pieces, Point point) {
        //lookForPiece looking for piece with Point which playsOfPiece is equal Point(x,y)
        for (int i = 0; i < pieces.length; i++) {
            if(pieces[i].getPointWhereIsPiece().equals(point)) {
                idPiece =i;
            }
        }
    }
}