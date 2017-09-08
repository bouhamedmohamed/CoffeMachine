package domain;

import java.util.Optional;

public class CoffeeMachine {
    public String sendCommand(String command) {
        if (command.equals(""))
            return "";
        return buildCommand(command);
    }

    private String buildCommand(String command) {

        final String[] commandParts = command.split(":");
        final String commandDrinkType = getCommandPart(commandParts, 0);
        final String commandSugar = getCommandPart(commandParts, 1);
        final String commandStick = getCommandPart(commandParts, 2);

        return "M:Drink maker makes 1 " + getDrinkType(commandDrinkType) + " with " + calculateSugar(commandSugar) + " sugar and " + addStick(commandStick) + " stick";
    }

    private String getCommandPart(String[] commandParts, int indice) {
        final boolean isElementExist = commandParts.length > indice;
        if (isElementExist)
            return commandParts[indice];
        return "";
    }

    private String getDrinkType(String commandDrinkType) {
        String drinkType = "";

        final Optional<CoffeeMachineCommandType> commandType = CoffeeMachineCommandType.getCommandType(commandDrinkType);
        if (commandType.isPresent())
            drinkType = commandType.get().getSymbolCommand();
        return drinkType;
    }

    private String addStick(String commandStick) {

        String stickState = "therefore no";
        if (!commandStick.equals(""))
            stickState = "a";
        return stickState;
    }

    private String calculateSugar(String commandSugar) {
        String sugarQuantity = "no";
        if (!commandSugar.equals(""))
            sugarQuantity = commandSugar;
        return sugarQuantity;
    }
}
