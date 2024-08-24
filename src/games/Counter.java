// 211604343 Shaked Gitta

package games;
/**
 * Counter represents a simple counter that can be incremented or decremented.
 */
public class Counter {
    private int count;
    /**
     * Constructs a new Counter with an initial count of 0.
     */
    public Counter() {
        count = 0;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number the number to increase the counter by
     */    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the number to decrease the counter by
     */    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return this.count;
    }
}