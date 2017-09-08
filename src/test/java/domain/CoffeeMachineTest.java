package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoffeeMachineTest {
    private CoffeeMachine coffeeMachine;

    @Before
    public void setUp() throws Exception {
        coffeeMachine = new CoffeeMachine();
    }

    @Test
    public void should_send_empty_when_we_put_nothing() {
        Assert.assertEquals("", new CoffeeMachine().sendCommand(""));
    }

    @Test
    public void should_send_tea_when_we_put_T() {
        Assert.assertEquals("M:Drink maker makes 1 tea with no sugar and therefore no stick", coffeeMachine.sendCommand("T::"));
    }

    @Test
    public void should_send_chocolate_when_we_put_H() {
        Assert.assertEquals("M:Drink maker makes 1 chocolate with no sugar and therefore no stick", coffeeMachine.sendCommand("H::"));
    }

    @Test
    public void should_send_coffee_when_we_put_C() {
        Assert.assertEquals("M:Drink maker makes 1 coffee with no sugar and therefore no stick", coffeeMachine.sendCommand("C::"));
    }

    @Test
    public void should_send_coffee_with_one_sugar_and_a_stick_when_we_put_T10() {
        Assert.assertEquals("M:Drink maker makes 1 tea with 1 sugar and a stick", coffeeMachine.sendCommand("T:1:0"));
    }

    @Test
    public void should_send_coffee_with_two_sugar_and_a_stick_when_we_put_T10() {
        Assert.assertEquals("M:Drink maker makes 1 tea with 2 sugar and a stick", coffeeMachine.sendCommand("T:2:0"));
    }

    @Test
    public void should_send_coffee_with_two_sugar_and_a_stick_when_we_put_C1() {
        Assert.assertEquals("M:Drink maker makes 1 coffee with 1 sugar and therefore no stick", coffeeMachine.sendCommand("C:1:"));
    }

}
