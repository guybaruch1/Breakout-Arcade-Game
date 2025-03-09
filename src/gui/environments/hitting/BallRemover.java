package gui.environments.hitting;

import gui.environments.interfaces.HitListener;
import gui.environments.objects.Ball;
import gui.environments.objects.Block;
import gui.animation.GameLevel;

/**
 * The BallRemover class is responsible for removing balls from the game and keeping count of the remaining balls.
 * It implements the HitListener interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBall;

    /**
     * Constructs a BallRemover object with the specified game and remaining ball counter.
     *
     * @param game          The game from which balls will be removed.
     * @param remainingBall The counter to keep track of the remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBall) {
        this.game = game;
        this.remainingBall = remainingBall;
    }

    /**
     * Handles the hit event when a block is hit by a ball.
     * Decreases the remaining ball count, removes the ball from the game,
     * and removes the ball from the game's sprites.
     *
     * @param beingHit The block that is being hit.
     * @param hitter   The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBall.decrease(1);
        this.game.removeSprite(hitter);
        hitter.removeFromGame(this.game);
    }
}