package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoffeeMachineUSThreeTest {
    private CoffeeMachine coffeeMachine;

    @Before
    public void setUp() throws Exception {
        coffeeMachine = new CoffeeMachine();
    }

    @Test
    public void should_make_one_orange_juice() {
        Assert.assertEquals("M:Drink maker will make one orange juice", coffeeMachine.prepareCommand("O::", 0.6));
    }

    @Test
    public void should_make_an_extra_hot_coffee_with_no_sugar() {
        Assert.assertEquals("M:Drink maker will make an extra hot coffee with no sugar", coffeeMachine.prepareCommand("Ch::", 0.6));
    }
}
