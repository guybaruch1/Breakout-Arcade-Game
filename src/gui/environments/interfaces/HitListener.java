package gui.environments.interfaces;

import gui.environments.objects.Ball;
import gui.environments.objects.Block;

/**
 * The HitListener interface represents a listener for hit events.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public interface HitListener {
    /**
     * Called whenever the beingHit object is hit by the specified hitter ball.
     *
     * @param beingHit The block that is being hit.
     * @param hitter   The ball that is hitting the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}