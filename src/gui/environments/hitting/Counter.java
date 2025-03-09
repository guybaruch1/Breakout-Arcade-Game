package gui.environments.hitting;

/**
 * The Counter class represents a simple counter that can be increased or decreased.
 *
 * @author Guy Baruch ID 207707472 EX6
 */
public class Counter {
    private int count = 0;

    /**
     * Increases the counter by the specified number.
     *
     * @param number The number to increase the counter by.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number The number to decrease the counter by.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Retrieves the current value of the counter.
     *
     * @return The current count value.
     */
    public int getValue() {
        return this.count;
    }
}