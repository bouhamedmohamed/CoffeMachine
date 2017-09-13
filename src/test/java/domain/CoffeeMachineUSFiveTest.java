package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineUSFiveTest {

    CoffeeMachineRepository coffeeMachineRepository;
    @Mock
    StockMachine stockMachine;
    CoffeeMachine coffeeMachine;

    @Before
    public void setUp() throws Exception {
        coffeeMachineRepository = new CoffeeMachineRepositoryImplementation();
        coffeeMachine = new CoffeeMachine(coffeeMachineRepository, stockMachine);
    }


    @Test(expected = CommandException.class)
    public void should_raise_an_exception_and_send_email_when_quantity_of_ressource_end() throws CommandException {
        when(stockMachine.hasEnoughRessource("T")).thenThrow(CommandException.class);
        given(stockMachine.hasEnoughRessource("T")).willThrow(new CommandException(""));
        coffeeMachine.checkCommandBeforePreparation("T:1:0", 1);
    }


}
