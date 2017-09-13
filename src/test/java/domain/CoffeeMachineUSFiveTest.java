package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineUSFiveTest {

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


    @Test(expected = CommandException.class)
    public void should_raise_an_exception_and_send_email_when_quantity_of_resource_end() throws CommandException {
        when(stockMachine.isEmpty("T")).thenThrow(CommandException.class);
        given(stockMachine.isEmpty("T")).willThrow(new CommandException(""));
        coffeeMachine.checkCommandBeforePreparation("T:1:0", 1);
    }

    @Test
    public void should_send_email_to_notify_that_we_dont_have_enough_resource() throws CommandException {
        when(stockMachine.isEmpty("T")).thenReturn(false);
        coffeeMachine.checkCommandBeforePreparation("T:1:0", 1);
        verify(email).sendNotification("T");

    }


}
