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
public class CoffeeMachineUSThreeTest {
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
    public void should_make_one_orange_juice() {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.ORANGE)
                .build();
        Assert.assertEquals("M:Drink maker will make one orange juice", coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 0.6));
    }

    @Test
    public void should_make_an_extra_hot_coffee_with_no_sugar() {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.COFFEE)
                .withHotDrink(true)
                .build();
        Assert.assertEquals("M:Drink maker will make an extra hot coffee with no sugar", coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 0.6));
    }

    @Test
    public void should_make_an_extra_hot_chocolate_with_one_sugar_and_a_stick() {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.CHOCOLATE)
                .withHotDrink(true)
                .withSugar(1)
                .withStick(true)
                .build();
        Assert.assertEquals("M:Drink maker will make an extra hot chocolate with one sugar and a stick", coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 0.6));
    }

    @Test
    public void should_make_an_extra_hot_tea_with_two_sugar_and_a_stick() {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.TEA)
                .withHotDrink(true)
                .withSugar(2)
                .withStick(true)
                .build();
        Assert.assertEquals("M:Drink maker will make an extra hot tea with two sugar and a stick", coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 0.6));
    }
}
