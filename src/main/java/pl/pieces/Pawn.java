package pl.pieces;

import pl.Board.Board;
import pl.Board.ImagesPieces;

import java.awt.*;
import java.util.Objects;

public class Pawn extends MainPiece {


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
                if (Objects.equals(board[placeOfPiece.x][placeOfPiece.y + move], ".  ")) {
                    possibleMoves[placeOfPiece.x][placeOfPiece.y + move] = "Y";
                    if (Objects.equals(board[placeOfPiece.x][placeOfPiece.y + (move * 2)], ".  ")) {
                        possibleMoves[placeOfPiece.x][placeOfPiece.y + (move * 2)] = "E";
                    }
                }
            } else {
                //every next move
                if (Objects.equals(board[placeOfPiece.x][placeOfPiece.y + move], ".  ")) {
                    possibleMoves[placeOfPiece.x][placeOfPiece.y + move] = "Y";
                }
            }

        if (isItWhite) {
            possibleBeat(Board.black_pieces);
        } else {
            possibleBeat(Board.white_pieces);
        }

        if (chosePiece) {
            try {
                moveChecker();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    private void possibleBeat(MainPiece[] pieces) {
        if (placeOfPiece.x - 1 >= 0 && placeOfPiece.y + move < 8 && placeOfPiece.y + move >= 0) {
            if (!Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y + move], "e")) {
                if (!Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y + move], ".  ")) {
                    for (MainPiece piece : pieces) {
                        if (new Point(placeOfPiece.x - 1, placeOfPiece.y + move).equals(piece.getPointWhereIsPiece())) {
                            possibleMoves[placeOfPiece.x - 1][placeOfPiece.y + move] = "K";
                        }
                    }
                }
            } else {
                for (MainPiece piece : pieces) {
                    if (new Point(placeOfPiece.x - 1, placeOfPiece.y).equals(piece.getPointWhereIsPiece())) {
                        possibleMoves[placeOfPiece.x - 1][placeOfPiece.y + move] = "K";
                    }
                }
            }
        }

        if (placeOfPiece.x + 1 < 8 && placeOfPiece.y + move < 8 && placeOfPiece.y + move > -1) {
            if (!Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y + move], ".  ")) {
                if (!Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y + move], "e")) {
                    for (MainPiece piece : pieces) {
                        if (new Point(placeOfPiece.x + 1, placeOfPiece.y + move).equals(piece.getPointWhereIsPiece())) {
                            possibleMoves[placeOfPiece.x + 1][placeOfPiece.y + move] = "K";
                        }
                    }
                } else {
                    for (MainPiece piece : pieces) {
                        if (new Point(placeOfPiece.x + 1, placeOfPiece.y).equals(piece.getPointWhereIsPiece())) {
                            possibleMoves[placeOfPiece.x + 1][placeOfPiece.y + move] = "K";
                        }
                    }
                }
            }
        }
    }
}