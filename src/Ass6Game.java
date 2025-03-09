import gui.environments.interfaces.LevelInformation;
import gui.levels.GameFlow;
import gui.levels.Level1;
import gui.levels.Level2;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 6 game.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Ass6Game {

    /**
     * The assignment 6 game.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LevelInformation level1 = new Level1();
        LevelInformation level2 = new Level2();
        List<LevelInformation> list = new ArrayList<LevelInformation>();
        list.add(level1);
        list.add(level2);
        GameFlow newGame = new GameFlow();
        newGame.createLevelList(args);
        newGame.runLevels();
        /*GameLevel newGame = new GameLevel(level1);
        newGame.run();
        LevelInformation level2 = new Level2();
        newGame.newGame(level2);
        newGame.run();*/
    }
}
