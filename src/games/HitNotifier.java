// 211604343 Shaked Gitta

package games;

/**
 * The HitNotifier interface represents an object that can notify hit events to registered listeners.
 */
public interface HitNotifier {
    /**
     * Adds a `HitListener` object as a listener to hit events.
     *
     * @param hl the `HitListener` to add.
     */
    void addHitListener(HitListener hl);
    /**
     * Removes a `HitListener` object from the list of listeners to hit events.
     *
     * @param hl the `HitListener` to remove.
     */
    void removeHitListener(HitListener hl);
}
