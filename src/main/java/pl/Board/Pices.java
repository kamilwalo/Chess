package pl.Board;

import pl.pieces.*;

import java.awt.*;

/*
Thic class creating pices
 */


public class Pices {
    private MainPice[] white_pieces = new MainPice[16];
    private MainPice[] black_pieces = new MainPice[16];

    public Pices(){
        /*
        * creating pices is easy
        * by abstract class MainPice[] I created tab which contains ALL the pices.
        * in the next step Im adding other pices by giving the Point (where it should by on the board)
        * and I'm giving them information what color they are
        * */
        for (int i = 0; i < 8; i++) {
            white_pieces[i]= new Pawn(new Point(i,6),true);
            black_pieces[i]= new Pawn(new Point(i,1),false);
        }

        white_pieces[8] = new Rook(new Point(0,7),true);
        black_pieces[8] = new Rook(new Point(0,0),false);
        white_pieces[9] = new Rook(new Point(7,7),true);
        black_pieces[9] = new Rook(new Point(7,0),false);

        white_pieces[10] = new Knight(new Point(1,7),true);
        black_pieces[10] = new Knight(new Point(1,0),false);
        white_pieces[11] = new Knight(new Point(6,7),true);
        black_pieces[11] = new Knight(new Point(6,0),false);

        white_pieces[12] = new Bishop(new Point(2,7),true);
        black_pieces[12] = new Bishop(new Point(2,0),false);
        white_pieces[13] = new Bishop(new Point(5,7),true);
        black_pieces[13] = new Bishop(new Point(5,0),false);

        white_pieces[14] = new Queen(new Point(3,7),true);
        black_pieces[14] = new Queen(new Point(3,0),false);

        white_pieces[15] = new King(new Point(4,7),true);
        black_pieces[15] = new King(new Point(4,0),false);
    }

    public MainPice[] getWhite_pieces() {
        return white_pieces;
    }

    public MainPice[] getBlack_pieces() {
        return black_pieces;
    }
}
