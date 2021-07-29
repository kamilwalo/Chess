package pl.pieces;

import pl.Board.Board;
import pl.Board.Check;
import pl.Board.ImagesPieces;
import pl.window.Promotion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

/*
* This is abstract class.
* The point of this class is to creating pice in the same tab.
* @parm placeOfPice - contains place where is pice
* @parm possibleMoves - contains String[][] which have all possible moves
* */

public abstract class MainPice implements Cloneable{
    protected Point placeOfPiece;
    protected boolean isItWhite;
    protected static String[][] possibleMoves = new String[8][8];
    protected boolean firstMove = true;
    protected String[][] board = Board.board;
    protected int move=1;
    protected boolean isAlive= true;
    protected String nameOnBoard;
    protected boolean choosedPiece=false;
    private int idPice;
    private Check checkmate = Board.checkmate;

    public void setPlaceOfPiece(Point placeOfPiece) {
        this.placeOfPiece = placeOfPiece;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setChoosedPiece(boolean choosedPiece) {
        this.choosedPiece = choosedPiece;
    }

    public MainPice(Point placeOfPiece, boolean isItWhite, String nameOnBoard) {
        this.placeOfPiece = placeOfPiece;
        this.isItWhite = isItWhite;
        this.nameOnBoard = nameOnBoard;
        if(isItWhite) move *=-1;
    }

    public void beatThePiece(Point pointChoosedByUser,MainPice[] piece1) {
        for (int i = 0; i < piece1.length; i++) {
            if (piece1[i].getPointWhereIsPiece().equals(pointChoosedByUser)){
                board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                placeOfPiece = piece1[i].getPointWhereIsPiece();
                board[placeOfPiece.x][placeOfPiece.y] = nameOnBoard;
                piece1[i].killPiece();
                break;
            }
        }
    }

    public String getNameOnStringBoard() { return nameOnBoard; }

    public void killPiece() {
        placeOfPiece = new Point(7,8);
        isAlive=false;
    }

    public boolean getStatus() { return isAlive; }

    public Image imagePiece() { return null; }

    public void move(Point pointChoosedByUser) throws CloneNotSupportedException {


        //this  'if' let plaer to castle his pices
        if(pointChoosedByUser.equals(new Point(6,7)) && nameOnBoard=="K  " && firstMove && Board.white_pieces[9].isFirstMove() && isItWhite  ){
            castling(Board.white_pieces[9]);
        }else
        if(pointChoosedByUser.equals(new Point(6,0)) && nameOnBoard=="K  " && firstMove && Board.black_pieces[9].isFirstMove() && !isItWhite){
            castling(Board.black_pieces[9]);
        }
            //this  'ifs' let plaer to move pice, kill pice or en passant
        if (possibleMoves[pointChoosedByUser.x][pointChoosedByUser.y] == "Y" ) {
                    firstMove = false;
                    board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                    placeOfPiece = pointChoosedByUser;
                    board[pointChoosedByUser.x][pointChoosedByUser.y] = nameOnBoard;
                }else
        if (possibleMoves[pointChoosedByUser.x][pointChoosedByUser.y] == "E"){
                        firstMove = false;
                        board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                        placeOfPiece = pointChoosedByUser;
                        board[pointChoosedByUser.x][pointChoosedByUser.y] = nameOnBoard;
                    }else
        if(possibleMoves[pointChoosedByUser.x][pointChoosedByUser.y]=="K" && board[pointChoosedByUser.x][pointChoosedByUser.y]!="e")  {
                    if (isItWhite) {
                        beatThePiece(pointChoosedByUser, Board.black_pieces);
                    } else {
                        beatThePiece(pointChoosedByUser, Board.white_pieces);
                    }
                    firstMove = false;
                    board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                    placeOfPiece = pointChoosedByUser;
                    board[pointChoosedByUser.x][pointChoosedByUser.y] = nameOnBoard;
                }else
        if(possibleMoves[pointChoosedByUser.x][pointChoosedByUser.y]=="K" && board[pointChoosedByUser.x][pointChoosedByUser.y]=="e" && nameOnBoard=="P  "){
            if (isItWhite) {
                beatThePiece(new Point(pointChoosedByUser.x, pointChoosedByUser.y+1), Board.black_pieces);
            } else {
                beatThePiece(new Point(pointChoosedByUser.x, pointChoosedByUser.y-1), Board.white_pieces);
            }
            firstMove = false;
            board[placeOfPiece.x][placeOfPiece.y] = ".  ";
            placeOfPiece = pointChoosedByUser;
            board[pointChoosedByUser.x][pointChoosedByUser.y] = nameOnBoard;
        }
        if ((placeOfPiece.y==0 || placeOfPiece.y==7) && nameOnBoard=="P  "){
            JFrame frame = new JFrame();
            frame.setIconImage(ImagesPieces.getBlackKnightImage());
            Promotion promotion = new Promotion(frame);
            frame.setLocation(
                    Toolkit.getDefaultToolkit().getScreenSize().width/2 - promotion.getWIDTH_ICON()*4/2,
                    Toolkit.getDefaultToolkit().getScreenSize().height/2 - promotion.getHEIGHT_ICON()/2);
            frame.setTitle("Promotion");
            frame.setContentPane(promotion.getPanel());
            frame.pack();
            frame.setVisible(true);
        }
        Board.tempPiece = null;
    }

    public boolean isItWhite() {
        return isItWhite;
    }

    private void castling(MainPice rook) throws CloneNotSupportedException {
        if(isItWhite)rook.move(new Point(5,7));
        else rook.move(new Point(5,0));
    }

    public Point getPointWhereIsPiece() { return placeOfPiece; }

    public String[][] getPossibleMoves() {
        return possibleMoves;
    }


    public void moveChecker() throws CloneNotSupportedException {
    /*this method checing what moves is possible to move. If it is check the promam wouldnt let player to move
    * the pice another than stop check
    * */

            Point actualPositionOfPiece = new Point(placeOfPiece.x, placeOfPiece.y);
            String[][] possibleMovesCopy = new String[8][8];
            for (int X = 0; X < possibleMovesCopy.length; X++) {
                for (int Y = 0; Y < possibleMovesCopy.length; Y++) {
                    possibleMovesCopy[X][Y] = possibleMoves[X][Y];
                }
            }
            for (int X = 0; X < possibleMovesCopy.length; X++) {
                for (int Y = 0; Y < possibleMovesCopy.length; Y++) {
                    if (possibleMovesCopy[X][Y] == "Y"|| possibleMovesCopy[X][Y]=="E") {
                        board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                        placeOfPiece = new Point(X, Y);
                        board[X][Y] = nameOnBoard;
                        if(isItWhite) {
                            if (checkmate.isItCheck(Board.black_pieces, Board.white_pieces[15]))
                                possibleMovesCopy[X][Y] = null;
                        }else {
                            if (checkmate.isItCheck(Board.white_pieces, Board.black_pieces[15]))
                                possibleMovesCopy[X][Y] = null;
                        }
                        board[X][Y] = ".  ";
                        placeOfPiece = new Point(actualPositionOfPiece.x, actualPositionOfPiece.y);
                        board[actualPositionOfPiece.x][actualPositionOfPiece.y] = nameOnBoard;
                    }
                    if(possibleMovesCopy[X][Y]=="K" && board[X][Y]!="e"){
                        MainPice tempPice;

                        if(isItWhite) tempPice= lookForPice(Board.black_pieces,X,Y).clone();
                        else tempPice= lookForPice(Board.white_pieces,X,Y).clone();

                        if (isItWhite)   beatThePiece(tempPice.getPointWhereIsPiece(), Board.black_pieces);
                        else   beatThePiece(tempPice.getPointWhereIsPiece(), Board.white_pieces);

                        if(isItWhite) {
                            if (checkmate.isItCheck(Board.black_pieces, Board.white_pieces[15]))
                                possibleMovesCopy[X][Y] = null;
                        }else {
                            if (checkmate.isItCheck(Board.white_pieces, Board.black_pieces[15]))
                                possibleMovesCopy[X][Y] = null;
                        }

                        board[X][Y]=tempPice.getNameOnStringBoard();

                        if(isItWhite) Board.black_pieces[idPice]=tempPice;
                        else Board.white_pieces[idPice]=tempPice;

                        placeOfPiece = new Point(actualPositionOfPiece.x,actualPositionOfPiece.y);
                        board[actualPositionOfPiece.x][actualPositionOfPiece.y] = nameOnBoard;
                    }
                    if(possibleMovesCopy[X][Y]=="K" && board[X][Y]=="e"){
                        MainPice tempPice;

                        if(isItWhite) tempPice= lookForPice(Board.black_pieces,X,Y+1).clone();
                        else tempPice= lookForPice(Board.white_pieces,X,Y-1).clone();

                        if (isItWhite)   beatThePiece(tempPice.getPointWhereIsPiece(), Board.black_pieces);
                        else   beatThePiece(tempPice.getPointWhereIsPiece(), Board.white_pieces);

                        if(isItWhite) {
                            if (checkmate.isItCheck(Board.black_pieces, Board.white_pieces[15]))
                                possibleMovesCopy[X][Y] = null; //nie pozwoli ruszyć się białym jeżeli nadal będzie mat
                        }else {
                            if (checkmate.isItCheck(Board.white_pieces, Board.black_pieces[15]))
                                possibleMovesCopy[X][Y] = null;
                        }

                        board[X][Y]="e";

                        if(isItWhite) Board.black_pieces[idPice]=tempPice;
                        else Board.white_pieces[idPice]=tempPice;

                        if(isItWhite)tempPice.setPlaceOfPiece(new Point(tempPice.getPointWhereIsPiece().x,tempPice.getPointWhereIsPiece().y));
                        else tempPice.setPlaceOfPiece(new Point(tempPice.getPointWhereIsPiece().x,tempPice.getPointWhereIsPiece().y));

                        placeOfPiece = new Point(actualPositionOfPiece.x,actualPositionOfPiece.y);
                        board[actualPositionOfPiece.x][actualPositionOfPiece.y] = nameOnBoard;
                    }
                }
            }
            possibleMoves=possibleMovesCopy;
            board[placeOfPiece.x][placeOfPiece.y] = ".  ";
            placeOfPiece = actualPositionOfPiece;
            board[actualPositionOfPiece.x][actualPositionOfPiece.y] = nameOnBoard;
            choosedPiece=false;

    }

    private MainPice lookForPice(MainPice[] pices, int x, int y) {
        //lookForPice looking for pice with Point which playsOfPice is equal Point(x,y)
        for (int i = 0; i < pices.length; i++) {
            if(pices[i].getPointWhereIsPiece().equals(new Point(x,y))) {
                idPice=i;
                return pices[i];
            }
        }return null;
    }

    @Override
    public MainPice clone() throws CloneNotSupportedException {
        return (MainPice) super.clone();
    }

    public void possibleMoves() {// every pice has his own generator of possible moves
    }
}