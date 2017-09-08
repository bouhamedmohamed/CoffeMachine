package domain;

import org.junit.Assert;
import org.junit.Test;

public class CoffeeMachineUSThreeTest {
    @Test
    public void should_make_one_orange_juice() {

        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        Assert.assertEquals("M:Drink maker will make one orange juice", coffeeMachine.prepareCommand("O::",0.6));
    }
}
