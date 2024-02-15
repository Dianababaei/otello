package model;
import java.util.ArrayList;
public class GameModel {
    public static class Piece {
        private final int x;
        private final int y;
        private boolean color;
        private boolean playerTurn = false;

        public Piece(int x, int y, boolean color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean getColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }

    public static class Board {
        private final int rowCount;
        private final int colCount;
        private ArrayList<Piece> pieces;
        private boolean playerTurn = false;

        public Board(int rowCount, int colCount) {
            this.rowCount = rowCount;
            this.colCount = colCount;
            this.pieces = new ArrayList<>();
        }

        public void addPiece(int x, int y, boolean color) {
            pieces.add(new Piece(x, y, color));
        }

        public boolean hasPiece(int x, int y) {
            for (Piece piece : pieces) {
                if (piece.getX() == x && piece.getY() == y) {
                    return true;
                }
            }
            return false;
        }

        public boolean getPieceColor(int x, int y) {
            for (Piece piece : pieces) {
                if (piece.getX() == x && piece.getY() == y) {
                    return piece.getColor();
                }
            }
            return false;
        }

        public void flipPieces(int x, int y) {
            boolean doesFlip = false;
            Piece[][] board = getCellState();
            if (board[x][y] != null) {
                return;
            }
            int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
            int[] dy = {-1, 1, 1, -1, 1, -1, 0, 0};
            mainLoop:
            for (int k = 0; k < dx.length; k++) {
                ArrayList<Piece> flipRow = new ArrayList<>();
                for (int i = 1; i <= Math.max(rowCount, colCount); i++) {
                    int nx = x + i * dx[k], ny = y + i * dy[k];
                    if (nx >= colCount || nx < 0 || ny >= rowCount || ny < 0 || board[nx][ny] == null)
                        continue mainLoop;
                    if (board[nx][ny].getColor() == playerTurn)
                        break;
                    flipRow.add(board[nx][ny]);
                }
                for (Piece piece : flipRow) {
                    doesFlip = true;
                    piece.setColor(playerTurn);
                }
            }
            if (doesFlip) {
                pieces.add(new Piece(x, y, playerTurn));
            }
            if (!doesFlip)
                return;
            playerTurn = !playerTurn;
        }

        public Piece[][] getCellState() {
            Piece[][] board = new Piece[rowCount][colCount];
            for (Piece piece : this.pieces) {
                board[piece.getX()][piece.getY()] = piece;
            }
            return board;
        }

        public boolean hasMove(boolean player) {
            for (int i = 0; i < rowCount; i++)
                for (int j = 0; j < colCount; j++) {
                    Piece[][] board = this.getCellState();
                    if (board[i][j] != null) {
                        continue;
                    }
                    int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
                    int[] dy = {-1, 1, 1, -1, 1, -1, 0, 0};
                    boolean doesFlip = false;
                    mainLoop:
                    for (int k = 0; k < dx.length; k++) {
                        ArrayList<Piece> flipRow = new ArrayList<>();
                        for (int l = 1; l <= Math.max(rowCount, colCount); l++) {
                            int nx = i + l * dx[k], ny = j + l * dy[k];
                            if (nx >= colCount || nx < 0 || ny >= rowCount || ny < 0 || board[nx][ny] == null)
                                continue mainLoop;
                            if (board[nx][ny].getColor() == player)flipRow.add(board[nx][ny]);
                            doesFlip = !flipRow.isEmpty();
                        }
                        if (doesFlip)
                            return true;
                    }
                }
            return false;
        }

        public int getRowCount() {
            return rowCount;
        }

        public int getColCount() {
            return colCount;
        }

        public ArrayList<Piece> getPieces() {
            return pieces;
        }

        public void setPieces(ArrayList<Piece> pieces) {
            this.pieces = pieces;
        }

        public boolean isPlayerTurn() {
            return playerTurn;
        }
        public boolean getPlayerTurn() {
            this.playerTurn = playerTurn;
            return playerTurn;
        }

        public void setPlayerTurn(boolean playerTurn) {
            this.playerTurn = playerTurn;
        }

    }


}
