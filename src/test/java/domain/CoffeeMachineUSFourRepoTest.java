package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineUSFourRepoTest {

    CoffeeMachineRepository coffeeMachineRepository;
    @Mock
    StockMachine stockMachine;
    CoffeeMachine coffeeMachine;
    @Mock
    private EmailNotification email;

    @Before
    public void setUp() throws Exception {
        coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        coffeeMachine = new CoffeeMachine(coffeeMachineRepository, stockMachine, email);
    }

    @Test
    public void should_return_one_command_when_we_add_one_command_on_repo() throws CommandException {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.TEA)
                .withSugar(1)
                .withStick(true)
                .build();

        Mockito.when(stockMachine.isEmpty("T")).thenReturn(true);
        coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 1);
        Assert.assertEquals(1, coffeeMachineRepository.getCoffeeMachineCommandAtDay(LocalDate.now()).size());
    }

    @Test
    public void should_return_two_commands_when_we_add_two_commands_on_repo() throws CommandException {
        CoffeeMachineCommandBuild drinkToCommandOne = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.TEA)
                .build();
        CoffeeMachineCommandBuild drinkToCommandTwo = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.CHOCOLATE)
                .build();

        Mockito.when(stockMachine.isEmpty("T")).thenReturn(true);
        Mockito.when(stockMachine.isEmpty("H")).thenReturn(true);
        coffeeMachine.checkCommandBeforePreparation(drinkToCommandOne.buildCommand(), 1);
        coffeeMachine.checkCommandBeforePreparation(drinkToCommandTwo.buildCommand(), 1);
        Assert.assertEquals(2, coffeeMachineRepository.getCoffeeMachineCommandAtDay(LocalDate.now()).size());
    }

    @Test
    public void should_return_total_price() throws Exception {
        CoffeeMachineCommandBuild drinkToCommandOne = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.TEA)
                .build();
        CoffeeMachineCommandBuild drinkToCommandTwo = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.COFFEE)
                .build();
        Mockito.when(stockMachine.isEmpty("T")).thenReturn(true);
        Mockito.when(stockMachine.isEmpty("C")).thenReturn(true);
        coffeeMachine.checkCommandBeforePreparation(drinkToCommandOne.buildCommand(), 1);
        coffeeMachine.checkCommandBeforePreparation(drinkToCommandTwo.buildCommand(), 1);
        Assert.assertEquals(1.0, coffeeMachine.getStatisticCommand(), 0.0001);

    }
}
