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

        final String commandDrinkType = getPartFromCommand(command, DRINK_TYPE);
        final String commandSugar = getPartFromCommand(command, SUGAR_QUANTITY);
        final String commandStick = getPartFromCommand(command, STICK_STATE);
        if(!commandDrinkType.equals("O"))
        return makeInstantProduct(commandDrinkType, commandSugar, commandStick);
        return makePreparedProduct(commandDrinkType, commandSugar, commandStick);
    }

    private String makePreparedProduct(String commandDrinkType, String commandSugar, String commandStick) {
        return "M:Drink maker will make one " + getSymbol(commandDrinkType, DRINK_TYPE);
    }

    private String makeInstantProduct(String commandDrinkType, String commandSugar, String commandStick) {
        return "M:Drink maker makes 1 " + getSymbol(commandDrinkType, DRINK_TYPE)
                + " with " + getSymbol(commandSugar, SUGAR_QUANTITY)
                + " sugar and " + getSymbol(commandStick, STICK_STATE)
                + " stick";
    }

    private String getPartFromCommand(String command, int indice) {
        final String[] commandParts = command.split(":");
        String commandPart = "";
        final boolean isElementExist = commandParts.length > indice;
        if (isElementExist)
            commandPart = commandParts[indice];
        return commandPart;
    }

    private String getSymbol(String commandPart, int position) {
        String symbol = commandPart;
        final Optional<CoffeeMachineCommandType> commandPartType = CoffeeMachineCommandType.getCommandType(commandPart);
        if (commandPartType.isPresent())
            symbol = commandPartType.get().getSymbolCommand()[position];
        return symbol;
    }


    public String prepareCommand(String command, double amount) {
        double moneyBack = buyCommand(command, amount);
        final boolean isNotEnoughMoney = moneyBack < 0;

        if (isNotEnoughMoney) {
            return "M: Enough money please add " + ((Math.abs(moneyBack) * 100) / 100);
        } else
            return sendCommand(command);
    }

    private double buyCommand(String command, double amount) {
        final String commandDrinkType = getPartFromCommand(command, DRINK_TYPE);
        final Optional<CoffeeMachineCommandType> commandType = CoffeeMachineCommandType.getCommandType(commandDrinkType);
        if (commandType.isPresent()) {
            return commandType.get().buyAndGetMoneyBack(amount);
        }
        throw new RuntimeException("Product not found");
    }

}
