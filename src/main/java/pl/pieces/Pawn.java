package pl.pieces;

import pl.Board.Board;
import pl.Board.ImagesPieces;

import java.awt.*;

public class Pawn extends MainPice {


    public Pawn(Point point, boolean isItWhite) {
        super(point, isItWhite, "P  ");
    }

    @Override
    public Image imagePiece() {
        if (super.getStatus()) {
            if (isItWhite) return ImagesPieces.getWhitePawnImage();
            else return ImagesPieces.getBlackPawnImage();
        }
        return null;
    }

    @Override
    public void possibleMoves() {
        for (int Y = 0; Y < possibleMoves.length; Y++) {
            for (int X = 0; X < possibleMoves.length; X++) {
                possibleMoves[X][Y] = null;
            }
        }
        if (placeOfPiece.y + move < 8 && placeOfPiece.y + move > -1)
            if (firstMove) {
                if (board[placeOfPiece.x][placeOfPiece.y + move] == ".  ") {
                    possibleMoves[placeOfPiece.x][placeOfPiece.y + move] = "Y";
                    if (board[placeOfPiece.x][placeOfPiece.y + (move * 2)] == ".  ") {
                        possibleMoves[placeOfPiece.x][placeOfPiece.y + (move * 2)] = "E";
                    }
                }
            } else {
                //każdy kolejny ruch
                if (board[placeOfPiece.x][placeOfPiece.y + move] == ".  ") {
                    possibleMoves[placeOfPiece.x][placeOfPiece.y + move] = "Y";
                }
            }

        if (isItWhite) {
            possibleBeat(Board.black_pieces);
        } else {
            possibleBeat(Board.white_pieces);
        }

        if (choosedPiece) {
            try {
                moveChecker();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    private void possibleBeat(MainPice[] pieces) {
        if (placeOfPiece.x - 1 >= 0 && placeOfPiece.y + move < 8 && placeOfPiece.y + move >= 0) {
            if (board[placeOfPiece.x - 1][placeOfPiece.y + move] != "e") {
                if (board[placeOfPiece.x - 1][placeOfPiece.y + move] != ".  ") {
                    for (int i = 0; i < pieces.length; i++) {
                        if (new Point(placeOfPiece.x - 1, placeOfPiece.y + move).equals(pieces[i].getPointWhereIsPiece())) {
                            possibleMoves[placeOfPiece.x - 1][placeOfPiece.y + move] = "K";
                        }
                    }
                }
            } else {
                for (int i = 0; i < pieces.length; i++) {
                    if (new Point(placeOfPiece.x - 1, placeOfPiece.y).equals(pieces[i].getPointWhereIsPiece())) {
                        possibleMoves[placeOfPiece.x - 1][placeOfPiece.y + move] = "K";
                    }
                }
            }
        }

        if (placeOfPiece.x + 1 < 8 && placeOfPiece.y + move < 8 && placeOfPiece.y + move > -1) {
            if (board[placeOfPiece.x + 1][placeOfPiece.y + move] != ".  ") {
                if (board[placeOfPiece.x + 1][placeOfPiece.y + move] != "e") {
                    for (int i = 0; i < pieces.length; i++) {
                        if (new Point(placeOfPiece.x + 1, placeOfPiece.y + move).equals(pieces[i].getPointWhereIsPiece())) {
                            possibleMoves[placeOfPiece.x + 1][placeOfPiece.y + move] = "K";
                        }
                    }
                } else {
                    for (int i = 0; i < pieces.length; i++) {
                        if (new Point(placeOfPiece.x + 1, placeOfPiece.y).equals(pieces[i].getPointWhereIsPiece())) {
                            possibleMoves[placeOfPiece.x + 1][placeOfPiece.y + move] = "K";
                        }
                    }
                }
            }
        }
    }
}