package pl.window.mainWindow;

import pl.Board.Board;
import pl.Board.ImagesPieces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessPanel extends JPanel implements MouseListener {
    private final int WINDOW_WIDTH= (int) (Toolkit.getDefaultToolkit().getScreenSize().height-(Toolkit.getDefaultToolkit().getScreenSize().height*0.2));
    private final int WINDOW_HEIGHT=(int) (Toolkit.getDefaultToolkit().getScreenSize().height-(Toolkit.getDefaultToolkit().getScreenSize().height*0.2));
    private final int SQUARES = 8;
    private final Color FIRST_SQARE_COLOR = Color.getHSBColor(131,158,99);
    private final Color SECOND_SQARE_COLOR = Color.getHSBColor(235,236,208);
    private final int REFRESH =100;
    private Point pointClicked;
    private Board board = new Board();
//    private Timer timer;

    public ChessPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        setSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        setFocusable(true);
        addMouseListener( this);

//        timer = new Timer(REFRESH, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                repaint();
//            }
//        });
//        timer.start();
    }

    public int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }

    public int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        draw(g);
    }

    private void draw(Graphics g) {
        drawChessBoard(g);
        if(Board.tempPiece!=null){
            drawChosePiceBackground(g);
        }
        drawPieces(g);
        drawPossibleMoves(g);
        if(!board.isGameStillGoing()) {
            drawEndText(g);
        }
    }

    private void drawChosePiceBackground(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(
                Board.tempPiece.getPointWhereIsPiece().x*(WINDOW_WIDTH/SQUARES),
                Board.tempPiece.getPointWhereIsPiece().y*(WINDOW_HEIGHT/SQUARES),
                WINDOW_WIDTH/SQUARES,
                WINDOW_HEIGHT/SQUARES
                );
    }

    private void drawEndText(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 50));
        g.drawString(board.getTextOnTheEndGame(),WINDOW_WIDTH/2-(g.getFontMetrics().stringWidth(board.getTextOnTheEndGame())/2),WINDOW_HEIGHT/2);
    }

    private void drawPossibleMoves(Graphics g) {
        if(Board.tempPiece!=null)
        for (int Y = 0; Y < Board.tempPiece.getPossibleMoves().length; Y++) {
            for (int X = 0; X < Board.tempPiece.getPossibleMoves().length; X++) {
                if (Board.tempPiece.getPossibleMoves()[X][Y]=="Y" || Board.tempPiece.getPossibleMoves()[X][Y]=="E"){
                    g.drawImage(ImagesPieces.getCircle().getScaledInstance(WINDOW_WIDTH/SQUARES,WINDOW_HEIGHT/SQUARES,5), X*(WINDOW_WIDTH/SQUARES),Y*(WINDOW_HEIGHT/SQUARES),this);
                }
                if (Board.tempPiece.getPossibleMoves()[X][Y]=="K"){
                    g.drawImage(ImagesPieces.getRedCircle().getScaledInstance(WINDOW_WIDTH/SQUARES,WINDOW_HEIGHT/SQUARES,5), X*(WINDOW_WIDTH/SQUARES),Y*(WINDOW_HEIGHT/SQUARES),this);
                }
            }
        }
    }

    private void drawPieces(Graphics g) {
        for (int i = 0; i < Board.black_pieces.length; i++) {
            if(Board.black_pieces[i].getStatus()==true)
                g.drawImage(Board.black_pieces[i].imagePiece().getScaledInstance
                        (WINDOW_WIDTH/SQUARES,WINDOW_HEIGHT/SQUARES,5),
                        Board.black_pieces[i].getPointWhereIsPiece().x*(WINDOW_WIDTH/SQUARES),
                        Board.black_pieces[i].getPointWhereIsPiece().y*(WINDOW_HEIGHT/SQUARES),this);
            if(Board.white_pieces[i].getStatus()==true)
                g.drawImage(Board.white_pieces[i].imagePiece().
                        getScaledInstance(WINDOW_WIDTH/SQUARES,WINDOW_HEIGHT/SQUARES,5),
                        Board.white_pieces[i].getPointWhereIsPiece().x*(WINDOW_WIDTH/SQUARES),
                        Board.white_pieces[i].getPointWhereIsPiece().y*(WINDOW_HEIGHT/SQUARES),this);
        }
    }

    private void drawChessBoard(Graphics g) {
        for(int i = 0; i<SQUARES; i++){
            for (int j = 0; j < SQUARES; j++) {
                if ((i+j)%2==0) g.setColor(SECOND_SQARE_COLOR);
                else g.setColor(FIRST_SQARE_COLOR);
                g.fillRect(j*(WINDOW_WIDTH/SQUARES),i*(WINDOW_HEIGHT/SQUARES),WINDOW_WIDTH/SQUARES,WINDOW_HEIGHT/SQUARES);
            }
        }
    }

    private void makeAction(MouseEvent e){
        pointClicked = new Point((e.getX()/(WINDOW_WIDTH/SQUARES)),(e.getY()/(WINDOW_HEIGHT/SQUARES)));
        try {
            board.setField(pointClicked);
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            cloneNotSupportedException.printStackTrace();
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       makeAction(e);
        for (int X = 0; X < board.getBoard().length; X++) {
            for (int Y = 0; Y < board.getBoard().length; Y++) {
                System.out.print(board.getBoard()[Y][X]);
            }
            System.out.println();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        makeAction(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Board getBoard() {
        return board;
    }
}