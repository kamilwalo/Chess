package pl.Board;

import pl.pieces.MainPice;

import java.awt.*;

/*
* @parm board - Its String[][] which contains a board game.
* @parm black_pices and white_pices - its contains the pices
* @parm tempPice - contains choosen pice by user
* @parm choosedPice - this boolean inform if the pice is choosen or not.
* @parm textOntheEndGame - actually its return String which inform the players who won
*/

public class Board {
    public static String[][] board = new String[8][9];
    public static MainPice[] black_pieces = new MainPice[16];
    public static MainPice[] white_pieces = new MainPice[16];
    public static MainPice tempPiece = null;
    private boolean chosePiece = false;
    private boolean isTurnWhite;
    private boolean isGameStillGoing = true;
    public static Check checkmate = new Check();
    private String textOnTheEndGame;

    public Board() {
        //This construstor run the game by method restartGame().
        restartGame();
        System.out.println("gra uruchomiona");
    }

    public static MainPice getTempPiece() {
        return tempPiece;
    }

    private  void deleteEFromBoard(){
        /*after move pawn 2 fields ahead its leave "e" on the String[][] board
        if the opponent wouldn't move his pawn on this side (where is "e")
        this method 'delete' this "e" from board.*/
        for (int X = 0; X < board.length; X++) {
            for (int Y = 0; Y < board.length; Y++) {
                if(board[X][Y]=="e") {
                    board[X][Y]=".  ";
                    break;
                }
            }
        }
    }


    public void setField(Point pointOfField) throws CloneNotSupportedException {
        /*
        * this method is run by class ChessPanel
        * if user chose pice this method use other method ;)
        * */

        if(chosePiece ==false){
            if (board[pointOfField.x][pointOfField.y] != ".  ") {
                for (int i = 0; i < Board.white_pieces.length; i++) {
                    if (white_pieces[i].getPointWhereIsPiece().equals(pointOfField) && isTurnWhite==true) {
                        tempPiece = white_pieces[i];
                        chosePiece = true;
                        white_pieces[i].setChoosedPiece(true);
                        break;
                    }
                    if (black_pieces[i].getPointWhereIsPiece().equals(pointOfField) && isTurnWhite==false) {
                        tempPiece = black_pieces[i];
                        chosePiece = true;
                        black_pieces[i].setChoosedPiece(true);
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
        this method runs only if the player chose pice
        Y && E- means  -> yes, u can move here but you wouldn't beat the pice
        K means  -> yes, u can move here but you wouldn't beat the pice
        */
        chosePiece =false;
        if(tempPiece.getPossibleMoves()[pointClicked.x][pointClicked.y]!=null)
        switch (tempPiece.getPossibleMoves()[pointClicked.x][pointClicked.y]){
            case "Y":
            case "K":
                tempPiece.move(pointClicked);
                deleteEFromBoard();
                if (isTurnWhite) {
                    isTurnWhite = false;
                } else {
                    isTurnWhite = true;
                }
                break;
            case "E":
                deleteEFromBoard();
                if(tempPiece.isItWhite())board[tempPiece.getPointWhereIsPiece().x][tempPiece.getPointWhereIsPiece().y-1]="e";
                else board[tempPiece.getPointWhereIsPiece().x][tempPiece.getPointWhereIsPiece().y+1]="e";
                tempPiece.move(pointClicked);

                if (isTurnWhite) {
                    isTurnWhite = false;
                } else {
                    isTurnWhite = true;
                }
                break;
            default:
                tempPiece=null;
                break;
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public void isItEndGame() throws CloneNotSupportedException {
        if(isTurnWhite) {
            if (checkmate.isItCheckmate(white_pieces)) endGame("wygrały czarne");
        } else {
            if (checkmate.isItCheckmate(black_pieces)) endGame("Wygrały białe");
        }
    }

    public void restartGame() {
        //this methoed at the begining runs game. And if players want to restart game, this program use it as well.
        isGameStillGoing=true;
        isTurnWhite=true;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                board[x][y] = ".  ";
            }
        }
        Pices pices = new Pices(); // Creating new pices and assign them to parm
        black_pieces=pices.getBlack_pieces();
        white_pieces=pices.getWhite_pieces();

        /*
        * This loop assign the pices to board
        * */
        for (int Y = 0; Y < white_pieces.length; Y++) {
            board[black_pieces[Y].getPointWhereIsPiece().x][black_pieces[Y].getPointWhereIsPiece().y]=black_pieces[Y].getNameOnStringBoard();
            board[white_pieces[Y].getPointWhereIsPiece().x][white_pieces[Y].getPointWhereIsPiece().y]=white_pieces[Y].getNameOnStringBoard();
        }
    }
}