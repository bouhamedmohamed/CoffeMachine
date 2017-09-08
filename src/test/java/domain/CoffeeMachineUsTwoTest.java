package domain;

import org.junit.Assert;
import org.junit.Test;

public class CoffeeMachineUsTwoTest {
    @Test

    public void should_print_error_message_when_we_dont_put_enough_money () {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        Assert.assertEquals("M: Enough money please add 0.2", coffeeMachine.prepareCommand("C::",0.2));
    }
}
