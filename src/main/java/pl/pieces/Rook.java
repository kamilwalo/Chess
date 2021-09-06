package pl.pieces;

import pl.Board.Board;
import pl.Board.ImagesPieces;

import java.awt.*;
import java.util.Objects;

public class Rook extends MainPiece {
    public Rook(Point point, boolean isItWhite) {
        super(point,isItWhite,"R  ");
    }

    @Override
    public Image imagePiece() {
        if (super.getStatus()){
            if (isItWhite) return ImagesPieces.getWhiteRookImage();
            else return ImagesPieces.getBlackRookImage();
        }return null;
    }

    @Override
    public void possibleMoves() {
        for (int Y = 0; Y < possibleMoves.length; Y++) {
            for (int X = 0; X < possibleMoves.length; X++) {
                possibleMoves[X][Y] = null;
            }
        }

        possibleRook(1,0);
        possibleRook(-1,0);
        possibleRook(0,1);
        possibleRook(0,-1);
        if(chosePiece){
            try {
                moveChecker();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

    }

    public void possibleRook( int upDown, int leftRight){
        int tempX=placeOfPiece.x;
        int tempY= placeOfPiece.y;
        if(tempX+leftRight<8 &&  tempX+leftRight>-1 && tempY+upDown<8 && tempY+upDown>-1){
            while (Objects.equals(board[tempX + leftRight][tempY + upDown], ".  ") || Objects.equals(board[tempX + leftRight][tempY + upDown], "e")){
                possibleMoves[tempX+leftRight][tempY+upDown] = "Y";
                tempY+=upDown;
                tempX+=leftRight;
                if(tempX+leftRight==8 ||  tempX+leftRight==-1 || tempY+upDown==8 || tempY+upDown==-1) break;
            }
            if(tempX+leftRight<8 &&  tempX+leftRight>-1 && tempY+upDown<8 && tempY+upDown>-1)
            if (!Objects.equals(board[tempX + leftRight][tempY + upDown], ".  ")){
                if(isItWhite) {
                    possibleBeat(Board.black_pieces,tempX+leftRight,tempY+upDown);
                }else {
                    possibleBeat(Board.white_pieces,tempX+leftRight,tempY+upDown);
                }
            }
        }
    }

    private void possibleBeat(MainPiece[] pieces, int tempX, int tempY) {

        Point temPoint = new Point(tempX, tempY);
        for (MainPiece piece : pieces) {
            if (temPoint.equals(piece.getPointWhereIsPiece())) {
                possibleMoves[tempX][tempY] = "K";
                break;
            }
        }
    }
}
