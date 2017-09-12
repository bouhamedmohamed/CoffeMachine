package domain;

import java.time.LocalDate;
import java.util.List;

public interface CoffeeMachineRepository {
    void addCoffeeMachineCommand(String drinkType);

    List<CommandCoffeeMachine> getCoffeeMachineCommandAtDay(LocalDate commandDay);
}
