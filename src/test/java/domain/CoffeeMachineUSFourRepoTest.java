package domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineUSFourRepoTest {
    @Mock
    CoffeeMachineRepository coffeeMachineRepository;

    @Test
    public void should_add_command_on_repo() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeMachineRepository);
        coffeeMachine.prepareCommand("T:1:0", 1);
        Mockito.verify(coffeeMachineRepository).addCoffeeMachineCommand("T");
    }

    @Test
    public void should_return_one_command_when_we_add_one_command_on_repo() {
        final CoffeeMachineRepository coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeMachineRepository);
        coffeeMachine.prepareCommand("T:1:0", 1);
        Assert.assertEquals(1, coffeeMachineRepository.getCoffeeMachineCommandAtDay(LocalDate.now()).size());
    }

    @Test
    public void should_return_two_commands_when_we_add_two_commands_on_repo() {
        final CoffeeMachineRepository coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeMachineRepository);
        coffeeMachine.prepareCommand("T:1:0", 1);
        coffeeMachine.prepareCommand("H:1:0", 1);
        Assert.assertEquals(2, coffeeMachineRepository.getCoffeeMachineCommandAtDay(LocalDate.now()).size());
    }

    @Test
    public void should_return_total_price() throws Exception {
        final CoffeeMachineRepository coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeMachineRepository);
        coffeeMachine.prepareCommand("T:1:0", 1);
        coffeeMachine.prepareCommand("C:1:0", 1);
        Assert.assertEquals(1.0, coffeeMachine.getStatisticCommand(), 0.0001);

    }
}
