package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class CoffeeMachineRepositoryImplementation implements CoffeeMachineRepository {
    Map<String, List<LocalDateTime>> commandsCoffeeMachine = new HashMap<>();

    @Override
    public void addCoffeeMachineCommand(String drinkType) {
        List<LocalDateTime> commandsByCommandDrink = commandsCoffeeMachine.get(drinkType);
        if (commandsByCommandDrink == null)
            commandsByCommandDrink = new ArrayList<>();
        commandsByCommandDrink.add(LocalDateTime.now());
        commandsCoffeeMachine.put(drinkType, commandsByCommandDrink);
    }

    @Override
    public List<CommandCoffeeMachine> getCoffeeMachineCommandAtDay(LocalDate commandDay) {
        return Collections.singletonList(new CommandCoffeeMachine("T", 1, LocalDate.now()));
    }
}
