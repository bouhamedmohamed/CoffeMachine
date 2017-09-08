package domain;

import java.util.Optional;

public class CoffeeMachine {
    public String sendCommand(String command) {
        if (command.equals(""))
            return "";
        String dringType = "";
        final String[] commandParts = command.split(":");
        final String commandDrinkType = commandParts[0];
        final Optional<CoffeeMachineCommandType> commandType = CoffeeMachineCommandType.getCommandType(commandDrinkType);
        if (commandType.isPresent())
            dringType = commandType.get().getSymbolCommand();

        return "Drink maker makes 1 " + dringType + " with no sugar therefore no stick";
    }
}
