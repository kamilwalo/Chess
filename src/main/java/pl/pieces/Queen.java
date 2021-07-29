package pl.pieces;

import pl.Board.Board;
import pl.Board.ImagesPieces;

import java.awt.*;

public class Queen extends MainPice {
    public Queen(Point placeOfPiece, boolean isItWhite ) {
        super(placeOfPiece, isItWhite, "Q  ");
    }

    @Override
    public Image imagePiece() {
        if (super.getStatus()){
            if (isItWhite) return ImagesPieces.getWhiteQueenImage();
            else return ImagesPieces.getBlackQueenImage();
        }return null;
    }

    @Override
    public void possibleMoves() {
        for (int Y = 0; Y < possibleMoves.length; Y++) {
            for (int X = 0; X < possibleMoves.length; X++) {
                possibleMoves[X][Y] = null;
            }
        }

        possibleBeat(1,1);
        possibleBeat(-1,1);
        possibleBeat(1,-1);
        possibleBeat(-1,-1);
        possibleBeat(-1,0);
        possibleBeat(1,0);
        possibleBeat(0,1);
        possibleBeat(0,-1);

        if(choosedPiece){
            try {
                moveChecker();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public void possibleBeat(int upDown, int leftRight){
        int tempX=placeOfPiece.x;
        int tempY= placeOfPiece.y;
        if(tempX+leftRight<8 &&  tempX+leftRight>-1 && tempY+upDown<8 && tempY+upDown>-1){
            while (board[tempX+leftRight][tempY+upDown]==".  "||board[tempX+leftRight][tempY+upDown]=="e"){
                possibleMoves[tempX+leftRight][tempY+upDown] = "Y";
                tempY+=upDown;
                tempX+=leftRight;
                if(tempX+leftRight==8 ||  tempX+leftRight==-1 || tempY+upDown==8 || tempY+upDown==-1) break;
            }
            if(tempX+leftRight<8 &&  tempX+leftRight>-1 && tempY+upDown<8 && tempY+upDown>-1)
                if (board[tempX+leftRight][tempY+upDown]!=".  "){
                    if(isItWhite) {
                        possibleBeat(Board.black_pieces,tempX+leftRight,tempY+upDown);
                    }else {
                        possibleBeat(Board.white_pieces,tempX+leftRight,tempY+upDown);
                    }
                }
        }
    }

    private void possibleBeat(MainPice[] pieces, int tempX, int tempY) {

        Point temPoint = new Point(tempX, tempY);
        for (int i = 0; i < pieces.length; i++) {
            if(temPoint.equals(pieces[i].getPointWhereIsPiece())){
                possibleMoves[tempX][tempY]="K";
            }
        }
    }
}
