package pl.pieces;

import pl.Board.Board;
import pl.Board.ImagesPieces;

import java.awt.*;
import java.util.Objects;

public class King extends MainPiece {
    public King(Point placeOfPiece, boolean isItWhite) {
        super(placeOfPiece, isItWhite,"K  ");
    }

    @Override
    public Image imagePiece() {
        if (super.getStatus()){
            if (isItWhite) return ImagesPieces.getWhiteKingImage();
            else return ImagesPieces.getBlackKingImage();
        }return null;
    }

    @Override
    public void possibleMoves() {
        for (int Y = 0; Y < possibleMoves.length; Y++) {
            for (int X = 0; X < possibleMoves.length; X++) {
                possibleMoves[X][Y] = null;
            }
        }

        if(placeOfPiece.x-1>-1 && placeOfPiece.y-1>-1 && Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y - 1], ".  ")) possibleMoves[placeOfPiece.x-1][placeOfPiece.y-1]="Y";
        if(placeOfPiece.x-1>-1 && placeOfPiece.y+1<8 && Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y + 1], ".  ")) possibleMoves[placeOfPiece.x-1][placeOfPiece.y+1]="Y";
        if(placeOfPiece.x+1<8 && placeOfPiece.y+1<8 && Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y + 1], ".  ")) possibleMoves[placeOfPiece.x+1][placeOfPiece.y+1]="Y";
        if(placeOfPiece.x+1<8 && placeOfPiece.y-1>-1 && Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y - 1], ".  ")) possibleMoves[placeOfPiece.x+1][placeOfPiece.y-1]="Y";
        if(placeOfPiece.x-1>-1 && Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y], ".  ")) possibleMoves[placeOfPiece.x-1][placeOfPiece.y]="Y";
        if(placeOfPiece.y-1>-1 && Objects.equals(board[placeOfPiece.x][placeOfPiece.y - 1], ".  ")) possibleMoves[placeOfPiece.x][placeOfPiece.y-1]="Y";
        if(placeOfPiece.y+1<8 && Objects.equals(board[placeOfPiece.x][placeOfPiece.y + 1], ".  ")) possibleMoves[placeOfPiece.x][placeOfPiece.y+1]="Y";
        if(placeOfPiece.x+1<8 && Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y], ".  ")) possibleMoves[placeOfPiece.x+1][placeOfPiece.y]="Y";

        if(isItWhite && firstMove && Board.white_pieces[9].isFirstMove() && Objects.equals(board[6][7], ".  ") && Objects.equals(board[5][7], ".  ")) possibleMoves[6][7]="Y";
        if(!isItWhite && firstMove && Board.black_pieces[9].isFirstMove() && Objects.equals(board[6][0], ".  ") && Objects.equals(board[5][0], ".  ")) possibleMoves[6][0]="Y";

       if(isItWhite) possibleBeat(Board.black_pieces);
       else possibleBeat(Board.white_pieces);

        if(chosePiece){
            try {
                moveChecker();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    private void possibleBeat(MainPiece[] pieces) {
        if(placeOfPiece.x-1>-1 && placeOfPiece.y-1>-1)if(!Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y - 1], ".  ") && !Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y - 1], "e")) possibleKing(pieces,placeOfPiece.x-1,placeOfPiece.y-1);
        if(placeOfPiece.x-1>-1 && placeOfPiece.y+1<8 )if(!Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y + 1], ".  ") && !Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y + 1], "e")) possibleKing(pieces,placeOfPiece.x-1,placeOfPiece.y+1);
        if(placeOfPiece.x+1<8 && placeOfPiece.y+1<8 )if(!Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y + 1], ".  ") && !Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y + 1], "e")) possibleKing(pieces,placeOfPiece.x+1,placeOfPiece.y+1);
        if(placeOfPiece.x+1<8 && placeOfPiece.y-1>-1 )if(!Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y - 1], ".  ") && !Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y - 1], "e")) possibleKing(pieces,placeOfPiece.x+1,placeOfPiece.y-1);
        if(placeOfPiece.x-1>-1 )if(!Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y], ".  ") && !Objects.equals(board[placeOfPiece.x - 1][placeOfPiece.y], "e")) possibleKing(pieces,placeOfPiece.x-1,placeOfPiece.y);
        if(placeOfPiece.y-1>-1 )if(!Objects.equals(board[placeOfPiece.x][placeOfPiece.y - 1], ".  ") && !Objects.equals(board[placeOfPiece.x][placeOfPiece.y - 1], "e")) possibleKing(pieces,placeOfPiece.x,placeOfPiece.y-1);
        if(placeOfPiece.y+1<8 )if(!Objects.equals(board[placeOfPiece.x][placeOfPiece.y + 1], ".  ") && !Objects.equals(board[placeOfPiece.x][placeOfPiece.y + 1], "e")) possibleKing(pieces,placeOfPiece.x,placeOfPiece.y+1);
        if(placeOfPiece.x+1<8 )if(!Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y], ".  ") && !Objects.equals(board[placeOfPiece.x + 1][placeOfPiece.y], "e")) possibleKing(pieces,placeOfPiece.x+1,placeOfPiece.y);
    }

    private void possibleKing(MainPiece[] pieces, int tempX, int tempY) {
        for (MainPiece piece : pieces) {
            if (piece.getPointWhereIsPiece().equals(new Point(tempX, tempY))) {
                possibleMoves[tempX][tempY] = "K";
                break;
            }
        }
    }
}