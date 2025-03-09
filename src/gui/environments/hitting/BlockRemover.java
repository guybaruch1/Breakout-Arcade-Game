package gui.environments.hitting;

import gui.environments.interfaces.HitListener;
import gui.environments.objects.Ball;
import gui.environments.objects.Block;
import gui.animation.GameLevel;

/**
 * The BlockRemover class is responsible for removing blocks from the game and keeping count of the remaining blocks.
 * It implements the HitListener interface.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructs a BlockRemover object with the specified game and remaining block counter.
     *
     * @param game            The game from which blocks will be removed.
     * @param remainingBlocks The counter to keep track of the remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the hit event when a block is hit by a ball.
     * Decreases the remaining block count, removes the block from the game's collidables,
     * and removes the block from the game itself.
     *
     * @param beingHit The block that is being hit.
     * @param hitter   The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBlocks.decrease(1);
        this.game.removeCollidable(beingHit);
        beingHit.removeFromGame(this.game);
    }
}