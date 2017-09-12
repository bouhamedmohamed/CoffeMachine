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
        Assert.assertEquals("M:Drink maker will make one orange juice", coffeeMachine.CheckCommandBeforePreparation("O::", 0.6));
    }

    @Test
    public void should_make_an_extra_hot_coffee_with_no_sugar() {
        Assert.assertEquals("M:Drink maker will make an extra hot coffee with no sugar", coffeeMachine.CheckCommandBeforePreparation("Ch::", 0.6));
    }

    @Test
    public void should_make_an_extra_hot_chocolate_with_one_sugar_and_a_stick() {
        Assert.assertEquals("M:Drink maker will make an extra hot chocolate with one sugar and a stick", coffeeMachine.CheckCommandBeforePreparation("Hh:1:0", 0.6));
    }
    @Test
    public void should_make_an_extra_hot_tea_with_two_sugar_and_a_stick() {
        Assert.assertEquals("M:Drink maker will make an extra hot tea with two sugar and a stick", coffeeMachine.CheckCommandBeforePreparation("Th:2:0", 0.6));
    }
}
