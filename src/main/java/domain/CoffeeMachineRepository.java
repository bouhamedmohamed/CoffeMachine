package domain;

import java.time.LocalDate;
import java.util.Map;

public interface CoffeeMachineRepository {
    void addCoffeeMachineCommand(String drinkType);

    Map<String, CommandCoffeeMachine> getCoffeeMachineCommandAtDay(LocalDate commandDay);

    double getCoffeeMachineStatCommandAtDay(LocalDate commandDay);
}
