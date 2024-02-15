package view;
import model.GameModel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

public class GameView {

    public static class MyPanel extends JPanel {
        private static MyPanel instance;
        private ResourceBundle config;
        private GameModel.Board board;
        private int rowCount, colCount;

        private MyPanel(GameModel.Board board, int rowCount, int colCount) {
            this.board = board;
            this.rowCount = rowCount;
            this.colCount = colCount;
            setLayout(null);
            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    int windowWidth = getWidth() - 5;
                    int windowHeight = getHeight() - 20;
                    int x = me.getX() / (windowWidth / rowCount);
                    int y = me.getY() / (windowHeight / colCount);
                    if (x >= rowCount || y >= rowCount) {
                        return;
                    }
                    board.flipPieces(x, y);
                    repaint();
                    revalidate();
                }
            });
        }

        public static MyPanel getInstance(GameModel.Board board, int rowCount, int colCount) {
            if (instance == null) {
                instance = new MyPanel(board, rowCount, colCount);
            }
            return instance;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(new Color(0, 106, 65));
            g.setColor(Color.WHITE);
            int windowHeight = getHeight() - 20;
            if (!(board.hasMove(false) || board.hasMove(true))) {
                int p1Score = 0;
                int p2Score = 0;
                for (int i = 0; i < rowCount; i++) {
                    for (int j = 0; j < colCount; j++) {
                        if (board.getPieceColor(i, j) == false) {
                            p1Score += 1;
                        } else {
                            p2Score += 1;
                        }
                    }
                }
                String gameEndStr = "";
                if (p1Score > p2Score)
                    gameEndStr = "Winner: Player 1";
                else if (p2Score > p1Score)
                    gameEndStr = "Winner: Player 2";
                else
                    gameEndStr = "Tie";
                g.drawString(gameEndStr, 0, windowHeight + 15);
            } /*else {
                g.drawString("Player turn: " + (board.getPlayerTurn() ? "white" : "black"), 0, windowHeight + 15);
            } */
            int windowWidth = getWidth() - 5;
            for (int i = 0; i <= rowCount; i++) {
                g.drawLine(0, i * windowHeight / rowCount, windowWidth, i * windowHeight / rowCount);
                g.drawLine(i * windowWidth / colCount, 0, i * windowWidth / rowCount, windowHeight);
            }
            int pieceSize = Math.min(windowWidth / colCount, windowHeight / rowCount) / 2;
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    if (board.hasPiece(i, j)) {
                        int x = i * (windowWidth / colCount) + (windowWidth / colCount) / 2 - pieceSize / 2;
                        int y = j * (windowHeight / rowCount) + (windowHeight / rowCount) / 2 - pieceSize / 2;
                        g.setColor(board.getPieceColor(i, j) ? Color.WHITE : Color.BLACK);
                        g.drawOval(x, y, pieceSize, pieceSize);
                        g.fillOval(x, y, pieceSize, pieceSize);
                    }
                }
            }
        }
    }

}
