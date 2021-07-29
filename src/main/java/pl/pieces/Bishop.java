package pl.pieces;

import pl.Board.Board;
import pl.Board.ImagesPieces;

import java.awt.*;

public class Bishop extends  MainPice{
    public Bishop(Point placeOfPiece, boolean isItWhite) {
        super(placeOfPiece, isItWhite, "B  ");
    }

    @Override
    public Image imagePiece() {
        if (super.getStatus()){
            if (isItWhite) return ImagesPieces.getWhiteBishopImage();
            else return ImagesPieces.getBlackBishopImage();
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

        possibleBeat(1,1);
        possibleBeat(-1,1);
        possibleBeat(1,-1);
        possibleBeat(-1,-1);

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
            while (board[tempX+leftRight][tempY+upDown]==".  " ||board[tempX+leftRight][tempY+upDown]=="e"){

                possibleMoves[tempX + leftRight][tempY + upDown] = "Y";
                tempY += upDown;
                tempX += leftRight;
                if(tempX+leftRight>=8 ||  tempX+leftRight<=-1 || tempY+upDown>=8 || tempY+upDown<=-1) break;
            }
            if(tempX+leftRight<8 &&  tempX+leftRight>-1 && tempY+upDown<8 && tempY+upDown>-1)
                if (board[tempX+leftRight][tempY+upDown]!=".  " && board[tempX+leftRight][tempY+upDown]!="e" ){
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
        if(board[tempX][tempY]=="e") {
            possibleMoves[tempX][tempY] = "K";
        }
        else
        for (int i = 0; i < pieces.length; i++) {
            if(temPoint.equals(pieces[i].getPointWhereIsPiece())){
                if(possibleMoves[tempX][tempY] != "A")possibleMoves[tempX][tempY]="K";
            }
        }
    }

}
