package controller;
import model.BoardGenerator;
import model.GameModel;
import view.GameView;
import javax.swing.*;
import java.io.IOException;

public class Controller {
    public static class GameController {
        private static final int ROW_COUNT = 8;
        private static final int COL_COUNT = 8;
        private static JFrame frame;
        private GameModel.Board board;
        private GameView.MyPanel panel;

        public void startGame() throws InterruptedException, IOException {
            board = BoardGenerator.generateBoard();

            frame = new JFrame("Reversi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = GameView.MyPanel.getInstance(board, ROW_COUNT, COL_COUNT);
            panel.setBounds(0, 0, 800, 800);
            frame.add(panel);
            frame.setSize(800, 800);
            frame.setVisible(true);

            while (board.hasMove(false) || board.hasMove(true)) {
                Thread.sleep(50);
                if (!board.hasMove(board.getPlayerTurn())) {
                    board.setPlayerTurn(!board.getPlayerTurn());
                }
                panel.repaint();
                panel.revalidate();
            }
            panel.repaint();
            panel.revalidate();
        }

    }}
