package pl.Board;

import pl.pieces.MainPiece;

import java.awt.*;

/*
* @param board - Its String[][] which contains a board game.
* @param black_pieces and white_pieces - its contains the pieces
* @param tempPiece - contains chosen piece by user
* @param chosePiece - this boolean inform if the piece is chosen or not.
* @param textOnTheEndGame - actually its return String which inform the players who won
*/

public class Board {
    public static String[][] board = new String[8][9];
    public static MainPiece[] black_pieces = new MainPiece[16];
    public static MainPiece[] white_pieces = new MainPiece[16];
    public static MainPiece tempPiece = null;
    private boolean chosePiece = false;
    private boolean isTurnWhite;
    private boolean isGameStillGoing = true;
    public static Check checkmate = new Check();
    private String textOnTheEndGame;

    public Board() {
        //This constructor run the game by method restartGame().
        restartGame();
    }

    private  void deleteEFromBoard(){
        /*after move pawn 2 fields ahead its leave "e" on the String[][] board
        if the opponent wouldn't move his pawn on this side (where is "e")
        this method 'delete' this "e" from board.*/
        for (int X = 0; X < board.length; X++) {
            for (int Y = 0; Y < board.length; Y++) {
                if(board[X][Y].equals("e")) {
                    board[X][Y]=".  ";
                    break;
                }
            }
        }
    }


    public void setField(Point pointOfField) throws CloneNotSupportedException {
        /*
        * this method is run by class ChessPanel
        * if user chose piece this method use other method ;)
        * */

        if(!chosePiece){
            if (!board[pointOfField.x][pointOfField.y].equals(".  ")) {
                for (int i = 0; i < Board.white_pieces.length; i++) {
                    if (white_pieces[i].getPointWhereIsPiece().equals(pointOfField) && isTurnWhite) {
                        tempPiece = white_pieces[i];
                        chosePiece = true;
                        white_pieces[i].setChosePiece(true);
                        break;
                    }
                    if (black_pieces[i].getPointWhereIsPiece().equals(pointOfField) && !isTurnWhite) {
                        tempPiece = black_pieces[i];
                        chosePiece = true;
                        black_pieces[i].setChosePiece(true);
                        break;
                    }
                }if(tempPiece!=null) {
                    tempPiece.possibleMoves();
                }
            }
        }
        else {
            if(tempPiece.getPossibleMoves()[pointOfField.x][pointOfField.y]!=null) {
                movePiece(pointOfField);
                isItEndGame();
            }else {
                chosePiece =false;
                tempPiece=null;
                setField(pointOfField);
            }
        }
        }

    public String getTextOnTheEndGame() {
        return textOnTheEndGame;
    }

    public boolean isGameStillGoing() {
        return isGameStillGoing;
    }

    private void endGame(String string) {
        isGameStillGoing=false;
        textOnTheEndGame=string;
    }


    public void movePiece(Point pointClicked) throws CloneNotSupportedException {
        /*
        this method runs only if the player chose piece
        Y && E- means  -> yes, u can move here, but you wouldn't beat the piece
        K means  -> yes, u can move here, but you wouldn't beat the piece
        */
        chosePiece =false;
        if(tempPiece.getPossibleMoves()[pointClicked.x][pointClicked.y]!=null)
            switch (tempPiece.getPossibleMoves()[pointClicked.x][pointClicked.y]) {
                case "Y", "K" -> {
                    tempPiece.move(pointClicked);
                    deleteEFromBoard();
                    isTurnWhite = !isTurnWhite;
                }
                case "E" -> {
                    deleteEFromBoard();
                    if (tempPiece.isItWhite())
                        board[tempPiece.getPointWhereIsPiece().x][tempPiece.getPointWhereIsPiece().y - 1] = "e";
                    else board[tempPiece.getPointWhereIsPiece().x][tempPiece.getPointWhereIsPiece().y + 1] = "e";
                    tempPiece.move(pointClicked);
                    isTurnWhite = !isTurnWhite;
                }
                default -> tempPiece = null;
            }
    }

    public String[][] getBoard() {
        return board;
    }

    public void isItEndGame() throws CloneNotSupportedException {
        if(isTurnWhite) {
            if (checkmate.isItCheckmate(white_pieces)) endGame("black won");
        } else {
            if (checkmate.isItCheckmate(black_pieces)) endGame("white won");
        }
    }

    public void restartGame() {
        //this method at the beginning runs game. And if players want to restart game, this program use it as well.
        isGameStillGoing=true;
        isTurnWhite=true;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                board[x][y] = ".  ";
            }
        }
        Pieces pieces = new Pieces(); // Creating new pieces and assign them to param
        black_pieces=pieces.getBlack_pieces();
        white_pieces=pieces.getWhite_pieces();

        /*
        * This loop assign the pieces to board
        * */
        for (int Y = 0; Y < white_pieces.length; Y++) {
            board[black_pieces[Y].getPointWhereIsPiece().x][black_pieces[Y].getPointWhereIsPiece().y]=black_pieces[Y].getNameOnStringBoard();
            board[white_pieces[Y].getPointWhereIsPiece().x][white_pieces[Y].getPointWhereIsPiece().y]=white_pieces[Y].getNameOnStringBoard();
        }
    }
}