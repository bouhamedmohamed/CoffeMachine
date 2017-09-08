package domain;

import java.util.Optional;

public class CoffeeMachine {
    final static int DRINK_TYPE = 0;
    final static int SUGAR_QUANTITY = 1;
    final static int STICK_STATE = 2;

    public String sendCommand(String command) {
        if (command.equals(""))
            return "";
        return buildCommand(command);
    }

    private String buildCommand(String command) {
        final String[] commandParts = command.split(":");
        final String commandDrinkType = getPartFromCommand(commandParts, DRINK_TYPE);
        final String commandSugar = getPartFromCommand(commandParts, SUGAR_QUANTITY);
        final String commandStick = getPartFromCommand(commandParts, STICK_STATE);
        return "M:Drink maker makes 1 " + commandDrinkType + " with " + commandSugar + " sugar and " + commandStick + " stick";
    }

    private String getPartFromCommand(String[] commandParts, int indice) {
        String commandPart = "";
        final boolean isElementExist = commandParts.length > indice;
        if (isElementExist)
            commandPart = commandParts[indice];
        return getSymbol(commandPart, indice);
    }

    private String getSymbol(String commandPart, int position) {
        String symbol = commandPart;
        final Optional<CoffeeMachineCommandType> commandPartType = CoffeeMachineCommandType.getCommandType(commandPart);
        if (commandPartType.isPresent())
            symbol = commandPartType.get().getSymbolCommand()[position];
        return symbol;
    }


    public String prepareCommand(String s, double v) {
        return "M: Enough money please add 0.2";
    }
}
