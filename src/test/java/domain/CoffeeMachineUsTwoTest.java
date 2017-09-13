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
public class CoffeeMachineUSTwoTest {
    CoffeeMachine coffeeMachine;
    @Mock
    StockMachine stockMachine;

    @Before
    public void setUp() throws Exception {
        when(stockMachine.hasEnoughRessource(any())).thenReturn(true);
        final CoffeeMachineRepository coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        coffeeMachine = new CoffeeMachine(coffeeMachineRepository, stockMachine);
    }

    @Test
    public void should_print_error_message_when_we_dont_put_enough_money_for_coffee() {
        Assert.assertEquals("M: Enough money please add 0.2", coffeeMachine.checkCommandBeforePreparation("C::", 0.2));
    }

    @Test
    public void should_print_error_message_when_we_dont_put_enough_money_for_tea() {
        Assert.assertEquals("M: Enough money please add 0.4", coffeeMachine.checkCommandBeforePreparation("T::", 0.2));
    }

    @Test
    public void should_send_coffee_with_two_sugar_and_a_stick_when_we_put_T20_when_we_have_enough_money() {
        Assert.assertEquals("M:Drink maker makes 1 tea with 2 sugar and a stick", coffeeMachine.checkCommandBeforePreparation("T:2:0", 0.8));
    }
}
