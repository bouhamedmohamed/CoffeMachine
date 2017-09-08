package domain;

import org.junit.Assert;
import org.junit.Test;

public class CoffeeMachineTest {
    @Test

    public void should_send_empty_when_we_send_nothing() {
        Assert.assertEquals("",new CoffeeMachine().sendCommand(""));
    }
}
