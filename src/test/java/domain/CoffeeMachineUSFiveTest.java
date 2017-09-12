package domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineUSFiveTest {
    @Mock
    CoffeeMachineRepository coffeeMachineRepository;

    @Test
    public void should_check_resource_milk_and_water_before_preparing_command() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeMachineRepository);
        coffeeMachine.CheckCommandBeforePreparation("T:1:0", 1);
        Mockito.verify(coffeeMachineRepository).addCoffeeMachineCommand("T");
    }

    @Test(expected = RuntimeException.class)
    public void should_raise_an_exception_and_send_email_when_quantity_of_ressource_end() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeMachineRepository);
        for (int indice = 0; indice < 100; indice++)
            coffeeMachine.CheckCommandBeforePreparation("T:1:0", 1);
    }
}
