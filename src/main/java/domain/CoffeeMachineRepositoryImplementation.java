package domain;

import domain.CoffeeMachineRepository;
import domain.CommandCoffeeMachine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeMachineRepositoryImplementation implements CoffeeMachineRepository {
    private Map<String, List<LocalDateTime>> commandsCoffeeMachine = new HashMap<>();
    private double sommeTotalCommand = 0;

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

    @Override
    public double getCoffeeMachineStatCommandAtDay(LocalDate commandDay) {
        sommeTotalCommand = 0;
        final Map<String, CommandCoffeeMachine> coffeeMachineCommandAtDay = getCoffeeMachineCommandAtDay(commandDay);
        coffeeMachineCommandAtDay.values().stream().forEach(commandCoffeeMachine -> getCommandCoffeeMachineConsumer(commandCoffeeMachine));
        return sommeTotalCommand;
    }

    private String getCommandCoffeeMachineConsumer(CommandCoffeeMachine commandCoffeeMachine) {
        sommeTotalCommand += commandCoffeeMachine.getTotalPrice();
        return commandCoffeeMachine.toString() + " " + commandCoffeeMachine.getTotalPrice();
    }
}
