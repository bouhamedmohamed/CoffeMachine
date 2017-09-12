package domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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
}
