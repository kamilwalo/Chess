package pl.Board;

import pl.pieces.MainPiece;
/*
* The point of this class is checking if is checked or checkmate ;)
*/

public class Check {
    /*
    The point of this method is to return
    @return true- what's mean " Yes, its check"
    @return false - No, it's not check ;)
    */

    public boolean isItCheck(MainPiece[] firstPieces, MainPiece queen) throws CloneNotSupportedException {
        MainPiece queenCopy = queen.clone();
        MainPiece[] firstPiecesCopy = new MainPiece[16];

        for (int i = 0; i < firstPiecesCopy.length; i++)
            firstPiecesCopy[i] = firstPieces[i].clone();

        /*
        if this loop find "K" on the point where is king, that's mean we got check
        */
        for (int i = 0; i < firstPiecesCopy.length; i++) {
            firstPiecesCopy[i].possibleMoves();
                    if (firstPiecesCopy[i].getPossibleMoves()[queenCopy.getPointWhereIsPiece().x][queenCopy.getPointWhereIsPiece().y]=="K"){
                        return true;
                    }
        }return false;
    }

    public boolean isItCheckmate( MainPiece[] piece ) throws CloneNotSupportedException {
        /*
        The point of this method is to return
         true - what's mean - Yes, we got checkmate
         false - the other answer ;)

         at the beginning the program generate possible moves first piece and
         checking if its can move somewhere. If he finds piece which can move somewhere return true
         and end the loop by 'return false'.
         */
        for (int i = 0; i < piece.length; i++) {
            piece[i].possibleMoves();
            piece[i].moveChecker();
            for (int X = 0; X < piece[i].getPossibleMoves().length; X++) {
                for (int Y = 0; Y < piece[i].getPossibleMoves().length; Y++) {
                    if(piece[i].getPossibleMoves()[X][Y]!=null) {
                        //if this loop find something other than null its mean that piece can move somewhere
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
