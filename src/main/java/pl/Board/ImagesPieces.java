package pl.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/*
 * The point for this class is to contain images for pieces and other elements
 * each method of this class is static
 * */

public class ImagesPieces {
    public static Image getBlackPawnImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\black_pawn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Image getWhitePawnImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\white_pawn.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getWhiteRookImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\white_rook.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getBlackRookImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\black_rook.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getBlackKnightImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\black_knight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Image getWhiteKnightImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\white_knight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getWhiteBishopImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\white_bishop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getBlackBishopImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\black_bishop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getBlackQueenImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\black_queen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getWhiteQueenImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\white_queen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getWhiteKingImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\white_king.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getBlackKingImage(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\black_king.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Image getCircle(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public static Image getRedCircle(){
        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\java\\Chess\\src\\main\\resources\\red_circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
