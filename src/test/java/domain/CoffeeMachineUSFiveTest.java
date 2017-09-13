package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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


    @Test
    public void should_raise_an_error_message_and_send_email_when_quantity_of_resource_end() throws CommandException {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.TEA)
                .build();

        when(stockMachine.isEmpty("T")).thenReturn(false);
        final String resultCommand = coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 1);
        Assert.assertEquals("Not enough resource to serve the drink we sent email to fix that ASAP", resultCommand);
    }

    @Test
    public void should_send_email_to_notify_that_we_dont_have_enough_resource() throws CommandException {
        CoffeeMachineCommandBuild drinkToCommand = CoffeeMachineCommandBuild
                .CoffeeMachineCommandBuildBuilder
                .aCoffeeMachineCommandBuild()
                .withPreparedDrink(CoffeeMachineCommandType.TEA)
                .build();

        when(stockMachine.isEmpty("T")).thenReturn(false);
        coffeeMachine.checkCommandBeforePreparation(drinkToCommand.buildCommand(), 1);
        verify(email).sendNotification("T");

    }


}
