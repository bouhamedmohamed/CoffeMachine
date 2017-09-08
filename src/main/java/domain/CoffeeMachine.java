package domain;

import java.util.Optional;

public class CoffeeMachine {
    public String sendCommand(String command) {
        if (command.equals(""))
            return "";
        String dringType = "";
        String sugarQuantity = "no";
        String stickState = "therefore no";
        final String[] commandParts = command.split(":");
        final String commandDrinkType = commandParts.length > 0 ? commandParts[0] : "";
        final String commandSugar = commandParts.length > 1 ? commandParts[1] : "";
        final String commandStick = commandParts.length > 2 ? commandParts[2] : "";
        final Optional<CoffeeMachineCommandType> commandType = CoffeeMachineCommandType.getCommandType(commandDrinkType);
        if (commandType.isPresent())
            dringType = commandType.get().getSymbolCommand();
        if (!commandSugar.equals(""))
            sugarQuantity = commandSugar;
        if (!commandStick.equals(""))
            stickState = "a";


        return "Drink maker makes 1 " + dringType + " with " + sugarQuantity + " sugar and " + stickState + " stick";
    }
}
