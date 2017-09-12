package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeMachineRepositoryImplementation implements CoffeeMachineRepository {
    private Map<String, List<LocalDateTime>> commandsCoffeeMachine = new HashMap<>();

    @Override
    public void addCoffeeMachineCommand(String drinkType) {
        List<LocalDateTime> commandsByCommandDrink = commandsCoffeeMachine.get(drinkType);
        if (commandsByCommandDrink == null)
            commandsByCommandDrink = new ArrayList<>();
        commandsByCommandDrink.add(LocalDateTime.now());
        commandsCoffeeMachine.put(drinkType, commandsByCommandDrink);
    }

    @Override
    public Map<String, CommandCoffeeMachine> getCoffeeMachineCommandAtDay(LocalDate commandDay) {


        return commandsCoffeeMachine.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> new CommandCoffeeMachine(e.getKey(), Integer.parseInt("" + e.getValue()
                                .stream()
                                .map(commandOfDay -> commandOfDay.toLocalDate())
                                .filter(commandOfDay -> commandOfDay.isEqual(commandDay))
                                .count()), commandDay)
                ));


    }
}
