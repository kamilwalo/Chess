package pl.Board;

import pl.pieces.MainPice;

import java.awt.*;
/*
* The point of this class is to checking if is check or checkmate ;)
*/

public class Check {
    /*
    The point of this method is to return
    @return true- wahats mean " Yes, its check"
    @return false - No, itst not check ;)
    */

    public boolean isItCheck(MainPice[] firstPices, MainPice queeen) throws CloneNotSupportedException {
        MainPice queenCopy = queeen.clone();
        MainPice[] firstPicesCopy = new MainPice[16];

        for (int i = 0; i < firstPicesCopy.length; i++)
            firstPicesCopy[i] = firstPices[i].clone();

        /*
        if this loop find "K" on the point where is king, thats mean we got check
        */
        for (int i = 0; i < firstPicesCopy.length; i++) {
            firstPicesCopy[i].possibleMoves();
                    if (firstPicesCopy[i].getPossibleMoves()[queenCopy.getPointWhereIsPiece().x][queenCopy.getPointWhereIsPiece().y]=="K"){
                        return true;
                    }
        }return false;
    }

    public boolean isItCheckmate( MainPice[] pice ) throws CloneNotSupportedException {
        /*
        The point of this method is to return
         true - whats mean - Yes, we got checkmate
         false - the other answer ;)

         at the begining the program generate possible moves first pice and
         checking if its can move somwhere. If he find pice which can move somewhere return true
         and end the loop by 'return false'.
         */
        for (int i = 0; i < pice.length; i++) {
            pice[i].possibleMoves();
            pice[i].moveChecker();
            for (int X = 0; X < pice[i].getPossibleMoves().length; X++) {
                for (int Y = 0; Y < pice[i].getPossibleMoves().length; Y++) {
                    if(pice[i].getPossibleMoves()[X][Y]!=null) {
                        //if this loop find something other than null its mean that pice can move somewhere
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
