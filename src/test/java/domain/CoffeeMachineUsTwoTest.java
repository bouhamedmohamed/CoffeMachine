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
    @Mock
    private EmailNotification email;

    @Before
    public void setUp() throws Exception {
        when(stockMachine.isEmpty(any())).thenReturn(true);
        final CoffeeMachineRepository coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        coffeeMachine = new CoffeeMachine(coffeeMachineRepository, stockMachine, email);
    }

    @Test
    public void should_print_error_message_when_we_dont_put_enough_money_for_coffee() {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.COFFEE)
                .build();
        Assert.assertEquals("M: Enough money please add 0.2", coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 0.2));
    }

    @Test
    public void should_print_error_message_when_we_dont_put_enough_money_for_tea() {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.TEA)
                .build();
        Assert.assertEquals("M: Enough money please add 0.4", coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 0.2));
    }

    @Test
    public void should_send_coffee_with_two_sugar_and_a_stick_when_we_put_T20_when_we_have_enough_money() {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.TEA)
                .withSugar(2)
                .withStick(true)
                .build();
        Assert.assertEquals("M:Drink maker makes 1 tea with 2 sugar and a stick", coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 0.8));
    }
}
