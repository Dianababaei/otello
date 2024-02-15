package model;
import view.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


    public class BoardGenerator {
        private static final String CONFIG_FILE_PATH = "config.properties";
        public static GameModel.Board generateBoard() throws IOException {
            Config config = Config.getConfig("main");
            int rowCount = Integer.parseInt(config.getProperty("rowCount"));
            int colCount = Integer.parseInt(config.getProperty("colCount"));

            GameModel.Board board = new GameModel.Board(rowCount, colCount);
            for (int i = 1; i < 5; i++) {
                int x = Integer.parseInt(config.getProperty("p"+i+"_x"));
                int y = Integer.parseInt(config.getProperty("p"+i+"_y"));
                boolean color = Boolean.parseBoolean(config.getProperty("p"+i+"_c"));
                board.addPiece(x, y, color);
            }
            return board;
        }
    }

