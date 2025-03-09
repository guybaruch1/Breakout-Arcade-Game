package gui.environments.hitting;

import gui.environments.interfaces.HitListener;
import gui.environments.objects.Ball;
import gui.environments.objects.Block;

/**
 * The ScoreTrackingListener class is responsible for tracking and updating the score based on block hits.
 * It implements the HitListener interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private Counter remainingBlocks;

    /**
     * Constructs a ScoreTrackingListener object with the specified score counter and remaining blocks counter.
     *
     * @param scoreCounter    The counter representing the current score.
     * @param remainingBlocks The counter representing the remaining blocks.
     */
    public ScoreTrackingListener(Counter scoreCounter, Counter remainingBlocks) {
        this.currentScore = scoreCounter;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the hit event when a block is hit by a ball.
     * Updates the score based on the number of remaining blocks.
     *
     * @param beingHit The block that is being hit.
     * @param hitter   The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (remainingBlocks.getValue() == 0) {
            this.currentScore.increase(100);
        } else {
            this.currentScore.increase(5);
        }
    }
}