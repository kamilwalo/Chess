package pl.pieces;

import pl.Board.Board;
import pl.Board.Check;
import pl.Board.ImagesPieces;
import pl.window.Promotion;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/*
* This is abstract class.
* The point of this class is creating piece in the same tab.
* @param placeOfPiece - contains place where is piece
* @param possibleMoves - contains String[][] which have all possible moves
* */

public abstract class MainPiece implements Cloneable{
    protected Point placeOfPiece;
    protected boolean isItWhite;
    protected static String[][] possibleMoves = new String[8][8];
    protected boolean firstMove = true;
    protected String[][] board = Board.board;
    protected int move=1;
    protected boolean isAlive= true;
    protected String nameOnBoard;
    protected boolean chosePiece =false;
    private int idPiece;
    private final Check checkmate = Board.checkmate;

    public void setPlaceOfPiece(Point placeOfPiece) {
        this.placeOfPiece = placeOfPiece;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setChosePiece(boolean chosePiece) {
        this.chosePiece = chosePiece;
    }

    public MainPiece(Point placeOfPiece, boolean isItWhite, String nameOnBoard) {
        this.placeOfPiece = placeOfPiece;
        this.isItWhite = isItWhite;
        this.nameOnBoard = nameOnBoard;
        if(isItWhite) move *=-1;
    }

    public void beatThePiece(Point pointChosenByUser, MainPiece[] piece1) {
        for (MainPiece mainPiece : piece1) {
            if (mainPiece.getPointWhereIsPiece().equals(pointChosenByUser)) {
                board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                placeOfPiece = mainPiece.getPointWhereIsPiece();
                board[placeOfPiece.x][placeOfPiece.y] = nameOnBoard;
                mainPiece.killPiece();
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

    public void move(Point pointChosenByUser) throws CloneNotSupportedException {


        //this  'if' let player castle his pieces
        if(pointChosenByUser.equals(new Point(6,7)) && Objects.equals(nameOnBoard, "K  ") && firstMove && Board.white_pieces[9].isFirstMove() && isItWhite  ){
            castling(Board.white_pieces[9]);
        }else
        if(pointChosenByUser.equals(new Point(6,0)) && Objects.equals(nameOnBoard, "K  ") && firstMove && Board.black_pieces[9].isFirstMove() && !isItWhite){
            castling(Board.black_pieces[9]);
        }
            //this  'ifs' let player move piece, kill piece or en passant
        if (Objects.equals(possibleMoves[pointChosenByUser.x][pointChosenByUser.y], "Y")) {
                    firstMove = false;
                    board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                    placeOfPiece = pointChosenByUser;
                    board[pointChosenByUser.x][pointChosenByUser.y] = nameOnBoard;
                }else
        if (Objects.equals(possibleMoves[pointChosenByUser.x][pointChosenByUser.y], "E")){
                        firstMove = false;
                        board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                        placeOfPiece = pointChosenByUser;
                        board[pointChosenByUser.x][pointChosenByUser.y] = nameOnBoard;
                    }else
        if(Objects.equals(possibleMoves[pointChosenByUser.x][pointChosenByUser.y], "K") && !Objects.equals(board[pointChosenByUser.x][pointChosenByUser.y], "e"))  {
                    if (isItWhite) {
                        beatThePiece(pointChosenByUser, Board.black_pieces);
                    } else {
                        beatThePiece(pointChosenByUser, Board.white_pieces);
                    }
                    firstMove = false;
                    board[placeOfPiece.x][placeOfPiece.y] = ".  ";
                    placeOfPiece = pointChosenByUser;
                    board[pointChosenByUser.x][pointChosenByUser.y] = nameOnBoard;
                }else
        if(Objects.equals(possibleMoves[pointChosenByUser.x][pointChosenByUser.y], "K") && Objects.equals(board[pointChosenByUser.x][pointChosenByUser.y], "e") && Objects.equals(nameOnBoard, "P  ")){
            if (isItWhite) {
                beatThePiece(new Point(pointChosenByUser.x, pointChosenByUser.y+1), Board.black_pieces);
            } else {
                beatThePiece(new Point(pointChosenByUser.x, pointChosenByUser.y-1), Board.white_pieces);
            }
            firstMove = false;
            board[placeOfPiece.x][placeOfPiece.y] = ".  ";
            placeOfPiece = pointChosenByUser;
            board[pointChosenByUser.x][pointChosenByUser.y] = nameOnBoard;
        }
        if ((placeOfPiece.y==0 || placeOfPiece.y==7) && Objects.equals(nameOnBoard, "P  ")){
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

    private void castling(MainPiece rook) throws CloneNotSupportedException {
        if(isItWhite)rook.move(new Point(5,7));
        else rook.move(new Point(5,0));
    }

    public Point getPointWhereIsPiece() { return placeOfPiece; }

    public String[][] getPossibleMoves() {
        return possibleMoves;
    }


    public void moveChecker() throws CloneNotSupportedException {
    /*this method checking what moves are possible to move. If it is checked the program wouldn't let player move
    * the piece another than stop check
    * */

            Point actualPositionOfPiece = new Point(placeOfPiece.x, placeOfPiece.y);
            String[][] possibleMovesCopy = new String[8][8];
            for (int X = 0; X < possibleMovesCopy.length; X++) {
                System.arraycopy(possibleMoves[X], 0, possibleMovesCopy[X], 0, possibleMovesCopy.length);
            }
            for (int X = 0; X < possibleMovesCopy.length; X++) {
                for (int Y = 0; Y < possibleMovesCopy.length; Y++) {
                    if (Objects.equals(possibleMovesCopy[X][Y], "Y") || Objects.equals(possibleMovesCopy[X][Y], "E")) {
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
                    if(Objects.equals(possibleMovesCopy[X][Y], "K") && !Objects.equals(board[X][Y], "e")){
                        MainPiece tempPiece;

                        if(isItWhite) tempPiece= Objects.requireNonNull(lookForPiece(Board.black_pieces, X, Y)).clone();
                        else tempPiece= Objects.requireNonNull(lookForPiece(Board.white_pieces, X, Y)).clone();

                        if (isItWhite)   beatThePiece(tempPiece.getPointWhereIsPiece(), Board.black_pieces);
                        else   beatThePiece(tempPiece.getPointWhereIsPiece(), Board.white_pieces);

                        if(isItWhite) {
                            if (checkmate.isItCheck(Board.black_pieces, Board.white_pieces[15]))
                                possibleMovesCopy[X][Y] = null;
                        }else {
                            if (checkmate.isItCheck(Board.white_pieces, Board.black_pieces[15]))
                                possibleMovesCopy[X][Y] = null;
                        }

                        board[X][Y]=tempPiece.getNameOnStringBoard();

                        if(isItWhite) Board.black_pieces[idPiece]=tempPiece;
                        else Board.white_pieces[idPiece]=tempPiece;

                        placeOfPiece = new Point(actualPositionOfPiece.x,actualPositionOfPiece.y);
                        board[actualPositionOfPiece.x][actualPositionOfPiece.y] = nameOnBoard;
                    }
                    if(Objects.equals(possibleMovesCopy[X][Y], "K") && Objects.equals(board[X][Y], "e")){
                        MainPiece tempPiece;

                        if(isItWhite) tempPiece= Objects.requireNonNull(lookForPiece(Board.black_pieces, X, Y + 1)).clone();
                        else tempPiece= Objects.requireNonNull(lookForPiece(Board.white_pieces, X, Y - 1)).clone();

                        if (isItWhite)   beatThePiece(tempPiece.getPointWhereIsPiece(), Board.black_pieces);
                        else   beatThePiece(tempPiece.getPointWhereIsPiece(), Board.white_pieces);

                        if(isItWhite) {
                            if (checkmate.isItCheck(Board.black_pieces, Board.white_pieces[15]))
                                possibleMovesCopy[X][Y] = null; //It wouldn't let move piece if it's mate
                        }else {
                            if (checkmate.isItCheck(Board.white_pieces, Board.black_pieces[15]))
                                possibleMovesCopy[X][Y] = null;
                        }

                        board[X][Y]="e";

                        if(isItWhite) Board.black_pieces[idPiece]=tempPiece;
                        else Board.white_pieces[idPiece]=tempPiece;

                        tempPiece.setPlaceOfPiece(new Point(tempPiece.getPointWhereIsPiece().x,tempPiece.getPointWhereIsPiece().y));

                        placeOfPiece = new Point(actualPositionOfPiece.x,actualPositionOfPiece.y);
                        board[actualPositionOfPiece.x][actualPositionOfPiece.y] = nameOnBoard;
                    }
                }
            }
            possibleMoves=possibleMovesCopy;
            board[placeOfPiece.x][placeOfPiece.y] = ".  ";
            placeOfPiece = actualPositionOfPiece;
            board[actualPositionOfPiece.x][actualPositionOfPiece.y] = nameOnBoard;
            chosePiece =false;

    }

    private MainPiece lookForPiece(MainPiece[] pieces, int x, int y) {
        //lookForPiece looking for piece with Point which playsOfPiece is equal Point(x,y)
        for (int i = 0; i < pieces.length; i++) {
            if(pieces[i].getPointWhereIsPiece().equals(new Point(x,y))) {
                idPiece =i;
                return pieces[i];
            }
        }return null;
    }

    @Override
    public MainPiece clone() throws CloneNotSupportedException {
        return (MainPiece) super.clone();
    }

    public void possibleMoves() {// every piece has his own generator of possible moves
    }
}