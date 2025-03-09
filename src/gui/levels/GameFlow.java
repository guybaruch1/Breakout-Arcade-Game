package gui.levels;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import gui.animation.AnimationRunner;
import gui.animation.EndScreen;
import gui.animation.GameLevel;
import gui.animation.KeyPressStoppableAnimation;
import gui.environments.hitting.Counter;
import gui.environments.interfaces.LevelInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The GameFlow class manages the flow of the game, including creating levels, running levels, and displaying
 * the end screen.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private Counter currentScore;
    private KeyboardSensor keyboardSensor;
    private List<LevelInformation> levels;

    /**
     * Constructs a new GameFlow object.
     */
    public GameFlow() {
        this.animationRunner = new AnimationRunner(60, new Sleeper());
        this.keyboardSensor = this.animationRunner.getGui().getKeyboardSensor();
        this.currentScore = new Counter();
    }

    /**
     * Creates a list of level information based on the command line arguments.
     *
     * @param args the command line arguments
     */
    public void createLevelList(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0 || Objects.equals(args[0], "${args}")) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
        } else {
            for (String arg : args) {
                if (arg.equals("1")) {
                    levels.add(new Level1());
                } else if (arg.equals("2")) {
                    levels.add(new Level2());
                } else if (arg.equals("3")) {
                    levels.add(new Level3());
                }
            }
        }
        this.levels = levels;
    }

    /**
     * Runs the game levels.
     */
    public void runLevels() {
        // ...
        for (LevelInformation levelInfo : this.levels) {
            GameLevel level = new GameLevel(levelInfo, this.currentScore, this.keyboardSensor, this.animationRunner);
            level.initialize();

            while (level.getRemainingBalls().getValue() > 0 && level.getRemainingBlocks().getValue() > 0) {
                level.run();
            }

            if (level.getRemainingBalls().getValue() == 0) {
                break;
            }
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(true, this.currentScore.getValue())));
        this.animationRunner.getGui().close();
    }
}