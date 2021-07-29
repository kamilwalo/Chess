package pl.pieces;

import pl.Board.Board;
import pl.Board.ImagesPieces;

import java.awt.*;
import java.util.Objects;

public class Knight extends MainPiece {
    public Knight(Point placeOfPiece, boolean isItWhite) {
        super(placeOfPiece, isItWhite,"L  ");
    }

    @Override
    public Image imagePiece() {
        if (super.getStatus()){
            if (isItWhite) return ImagesPieces.getWhiteKnightImage();
            else return ImagesPieces.getBlackKnightImage();
        }return null;
    }

    public void possibleMoves() {
        for (int Y = 0; Y < possibleMoves.length; Y++) {
            for (int X = 0; X < possibleMoves.length; X++) {
                possibleMoves[X][Y] = null;
            }
        }

        if(isItWhite){
            possibleBeat(placeOfPiece.x+1, placeOfPiece.y+2, Board.black_pieces);
            possibleBeat(placeOfPiece.x+1, placeOfPiece.y-2, Board.black_pieces);
            possibleBeat(placeOfPiece.x-1, placeOfPiece.y+2, Board.black_pieces);
            possibleBeat(placeOfPiece.x-1, placeOfPiece.y-2, Board.black_pieces);
            possibleBeat(placeOfPiece.x-2, placeOfPiece.y+1, Board.black_pieces);
            possibleBeat(placeOfPiece.x-2, placeOfPiece.y-1, Board.black_pieces);
            possibleBeat(placeOfPiece.x+2, placeOfPiece.y-1, Board.black_pieces);
            possibleBeat(placeOfPiece.x+2, placeOfPiece.y+1, Board.black_pieces);
        }else {
            possibleBeat(placeOfPiece.x+1, placeOfPiece.y+2, Board.white_pieces);
            possibleBeat(placeOfPiece.x+1, placeOfPiece.y-2, Board.white_pieces);
            possibleBeat(placeOfPiece.x-1, placeOfPiece.y+2, Board.white_pieces);
            possibleBeat(placeOfPiece.x-1, placeOfPiece.y-2, Board.white_pieces);
            possibleBeat(placeOfPiece.x-2, placeOfPiece.y+1, Board.white_pieces);
            possibleBeat(placeOfPiece.x-2, placeOfPiece.y-1, Board.white_pieces);
            possibleBeat(placeOfPiece.x+2, placeOfPiece.y-1, Board.white_pieces);
            possibleBeat(placeOfPiece.x+2, placeOfPiece.y+1, Board.white_pieces);
        }
        if(chosePiece){
            try {
                moveChecker();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    private void possibleBeat(int tempX, int tempY, MainPiece[] pieces) {
         if( tempX>=0 && tempX<=7 && tempY>=0 && tempY<=7)
             if (Objects.equals(board[tempX][tempY], ".  ") && !Objects.equals(board[tempX][tempY], "e"))
        possibleMoves[tempX][tempY]="Y";else {
                 for (MainPiece piece : pieces) {
                     if (piece.getPointWhereIsPiece().equals(new Point(tempX, tempY))) {
                         possibleMoves[tempX][tempY] = "K";
                         break;
                     }
                 }
             }
    }
}