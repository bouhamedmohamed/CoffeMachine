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

    @Before
    public void setUp() throws Exception {
        coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        coffeeMachine = new CoffeeMachine(coffeeMachineRepository, stockMachine);
    }

    @Test
    public void should_return_one_command_when_we_add_one_command_on_repo() throws CommandException {
        Mockito.when(stockMachine.hasEnoughRessource("T")).thenReturn(true);
        coffeeMachine.checkCommandBeforePreparation("T:1:0", 1);
        Assert.assertEquals(1, coffeeMachineRepository.getCoffeeMachineCommandAtDay(LocalDate.now()).size());
    }

    @Test
    public void should_return_two_commands_when_we_add_two_commands_on_repo() throws CommandException {
        Mockito.when(stockMachine.hasEnoughRessource("T")).thenReturn(true);
        Mockito.when(stockMachine.hasEnoughRessource("H")).thenReturn(true);
        coffeeMachine.checkCommandBeforePreparation("T:1:0", 1);
        coffeeMachine.checkCommandBeforePreparation("H:1:0", 1);
        Assert.assertEquals(2, coffeeMachineRepository.getCoffeeMachineCommandAtDay(LocalDate.now()).size());
    }

    @Test
    public void should_return_total_price() throws Exception {
        Mockito.when(stockMachine.hasEnoughRessource("T")).thenReturn(true);
        Mockito.when(stockMachine.hasEnoughRessource("C")).thenReturn(true);
        coffeeMachine.checkCommandBeforePreparation("T:1:0", 1);
        coffeeMachine.checkCommandBeforePreparation("C:1:0", 1);
        Assert.assertEquals(1.0, coffeeMachine.getStatisticCommand(), 0.0001);

    }
}
