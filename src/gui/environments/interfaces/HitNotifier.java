package gui.environments.interfaces;


/**
 * The HitNotifier interface represents an object that can notify listeners of hit events.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public interface HitNotifier {
    /**
     * Adds the specified listener to the list of hit event listeners.
     *
     * @param hl The listener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes the specified listener from the list of hit event listeners.
     *
     * @param hl The listener to be removed.
     */
    void removeHitListener(HitListener hl);
}