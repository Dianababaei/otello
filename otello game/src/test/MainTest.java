package test;
import org.junit.Test;
import static org.junit.Assert.*;

    public class MainTest {
        @Test
        public void testPlayerHasMove() {
            MyPanel panel = new MyPanel();
            panel.rowCount = 8;
            panel.colCount = 8;

            // Test case where player has move
            panel.pieces.add(new Piece(3, 3, false));
            panel.pieces.add(new Piece(4, 4, false));
            panel.pieces.add(new Piece(3, 4, true));
            assertTrue(panel.playerHasMove(true));

            // Test case where player does not have move
            panel.pieces.clear();
            panel.pieces.add(new Piece(0, 0, false));
            assertFalse(panel.playerHasMove(true));

            // Test case where board is full and game ends in a tie
            panel.pieces.clear();
            int rowCount = panel.rowCount;
            int colCount = panel.colCount;
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < colCount; j++) {
                    boolean color = (i + j) % 2 == 0;
                    panel.pieces.add(new Piece(i, j, color));
                }
            }
            assertFalse(panel.playerHasMove(true));
            assertFalse(panel.playerHasMove(false));

            // Test case where player 1 wins
            panel.pieces.clear();
            panel.pieces.add(new Piece(0, 0, false));
            panel.pieces.add(new Piece(1, 1, false));
            panel.pieces.add(new Piece(2, 2, false));
            panel.pieces.add(new Piece(3, 3, false));
            panel.pieces.add(new Piece(4, 4, false));
            panel.pieces.add(new Piece(5, 5, false));
            panel.pieces.add(new Piece(6, 6, false));
            panel.pieces.add(new Piece(7, 7, false));
            panel.pieces.add(new Piece(3, 4, true));
            assertTrue(panel.playerHasMove(true));
            assertFalse(panel.playerHasMove(false));

            // Test case where player 2 wins
            panel.pieces.clear();
            panel.pieces.add(new Piece(0, 1, true));
            panel.pieces.add(new Piece(1, 0, true));
            panel.pieces.add(new Piece(1, 1, false));
            assertTrue(panel.playerHasMove(false));
            assertFalse(panel.playerHasMove(true));
        }

        @Test
        public void testGetCellState() {
            MyPanel panel = new MyPanel();
            panel.rowCount = 8;
            panel.colCount = 8;

            // Test case with no pieces on the board
            Piece[][] expectedBoard = new Piece[8][8];
            assertArrayEquals(expectedBoard, panel.getCellState());

            // Test case with pieces on the board
            panel.pieces.add(new Piece(2, 2, true));
            panel.pieces.add(new Piece(4, 5, false));
            expectedBoard[2][2] = new Piece(2, 2, true);
            expectedBoard[4][5] = new Piece(4, 5, false);
            assertArrayEquals(expectedBoard, panel.getCellState());
        }
    }
