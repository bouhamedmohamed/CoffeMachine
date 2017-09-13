package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineUSOneTest {
    private CoffeeMachine coffeeMachine;
    @Mock
    StockMachine stockMachine;
    @Mock
    private EmailNotification email;

    @Before
    public void setUp() throws Exception {
        when(stockMachine.isEmpty(any())).thenReturn(true);
        final CoffeeMachineRepository coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        coffeeMachine = new CoffeeMachine(coffeeMachineRepository, stockMachine, email);
    }

    @Test
    public void should_send_empty_when_we_put_nothing() {
        Assert.assertEquals("", coffeeMachine.checkCommandBeforePreparation("", 0));
    }

    @Test
    public void should_send_tea_when_we_put_T() {
        Assert.assertEquals("M:Drink maker makes 1 tea with no sugar and therefore no stick", coffeeMachine.checkCommandBeforePreparation("T::", 1));
    }

    @Test
    public void should_send_chocolate_when_we_put_H() {
        Assert.assertEquals("M:Drink maker makes 1 chocolate with no sugar and therefore no stick", coffeeMachine.checkCommandBeforePreparation("H::", 1));
    }

    @Test
    public void should_send_coffee_when_we_put_C() {
        Assert.assertEquals("M:Drink maker makes 1 coffee with no sugar and therefore no stick", coffeeMachine.checkCommandBeforePreparation("C::", 1));
    }

    @Test
    public void should_send_coffee_with_one_sugar_and_a_stick_when_we_put_T10() {
        Assert.assertEquals("M:Drink maker makes 1 tea with 1 sugar and a stick", coffeeMachine.checkCommandBeforePreparation("T:1:0", 1));
    }

    @Test
    public void should_send_coffee_with_two_sugar_and_a_stick_when_we_put_T20() {
        Assert.assertEquals("M:Drink maker makes 1 tea with 2 sugar and a stick", coffeeMachine.checkCommandBeforePreparation("T:2:0", 1));
    }

    @Test
    public void should_send_coffee_with_two_sugar_and_a_stick_when_we_put_C1() {
        Assert.assertEquals("M:Drink maker makes 1 coffee with 1 sugar and therefore no stick", coffeeMachine.checkCommandBeforePreparation("C:1:", 1));
    }

}
