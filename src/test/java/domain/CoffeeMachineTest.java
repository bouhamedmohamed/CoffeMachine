package domain;

import org.junit.Assert;
import org.junit.Test;

public class CoffeeMachineTest {
    @Test
    public void should_send_empty_when_we_put_nothing() {
        Assert.assertEquals("",new CoffeeMachine().sendCommand(""));
    }

    @Test
    public void should_send_tea_when_we_put_T() {
        Assert.assertEquals("Drink maker makes 1 tea with no sugar therefore no stick",new CoffeeMachine().sendCommand("T::"));
    }

    @Test
    public void should_send_chocolate_when_we_put_H() {
        Assert.assertEquals("Drink maker makes 1 chocolate with no sugar therefore no stick",new CoffeeMachine().sendCommand("H::"));
    }
}
