package domain;

import java.util.Optional;

import static domain.CoffeeMachineCommandType.getCommandType;
import static domain.CoffeeMachineCommandType.getSymbolCommand;

public class CoffeeMachine {
    final static int DRINK_TYPE = 0;
    final static int SUGAR_QUANTITY = 1;
    final static int STICK_STATE = 2;

    public String sendCommand(String command) {
        final boolean isEmptyCommand = command.equals("");
        if (isEmptyCommand)
            return "";
        return buildCommand(command);
    }

    private String buildCommand(String command) {

        final String commandDrinkType = getPartFromCommand(command, DRINK_TYPE);
        final String commandSugar = getPartFromCommand(command, SUGAR_QUANTITY);
        final String commandStick = getPartFromCommand(command, STICK_STATE);

        return buildProductCommandToPrint(commandDrinkType, commandSugar, commandStick);
    }

    private String buildProductCommandToPrint(String commandDrinkType, String commandSugar, String commandStick) {

        final boolean preparedDrink = isPreparedDrink(commandDrinkType);
        String instantOrPreparedProduct = buildMessageProduct(commandDrinkType, preparedDrink);
        String sugarQuantity = prepareSugarQuantity(commandSugar, preparedDrink);
        StringBuffer messageToPrint = new StringBuffer();
        messageToPrint.append("M:Drink maker " + instantOrPreparedProduct + " " + getSymbol(commandDrinkType, DRINK_TYPE));
        if (!isOrangeJuce(commandDrinkType)) {

            messageToPrint.append(" with " + sugarQuantity + " sugar");
            if (!preparedDrink || (preparedDrink && !commandSugar.equals("")))
                messageToPrint.append(" and " + getSymbol(commandStick, STICK_STATE) + " stick");
        }
        return messageToPrint.toString();
    }


    public String prepareCommand(String command, double amount) {
        double moneyBack = buyAndGetBack(command, amount);
        final boolean isNotEnoughMoney = moneyBack < 0;

        if (isNotEnoughMoney) {
            return "M: Enough money please add " + ((Math.abs(moneyBack) * 100) / 100);
        } else
            return sendCommand(command);
    }

    private double buyAndGetBack(String command, double amount) {
        final String commandDrinkType = getPartFromCommand(command, DRINK_TYPE);
        final Optional<CoffeeMachineCommandType> commandType = getCommandType(commandDrinkType);
        if (commandType.isPresent()) {
            return commandType.get().buyAndGetMoneyBack(amount);
        }
        throw new RuntimeException("Product not found");
    }

    private String prepareSugarQuantity(String commandSugar, boolean preparedDrink) {
        String sugarQuantity = getSymbol(commandSugar, SUGAR_QUANTITY);
        if (preparedDrink)
            sugarQuantity = SugarQuantityNumberToLetter.getQuantitySymbol(sugarQuantity);
        return sugarQuantity;
    }
    private String buildMessageProduct(String commandDrinkType, boolean preparedDrink) {
        String instantOrPreparedProduct = "makes";
        if (preparedDrink || isOrangeJuce(commandDrinkType)) {
            instantOrPreparedProduct = "will make";
        }
        return instantOrPreparedProduct;
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
        return getSymbolCommand(commandPart, position);
    }


    private boolean isPreparedDrink(String commandDrinkType) {
        return isOrangeJuce(commandDrinkType) || isHotDrink(commandDrinkType);
    }

    private boolean isOrangeJuce(String commandDrinkType) {
        return CoffeeMachineCommandType.isOrangeJuce(commandDrinkType);
    }

    private boolean isHotDrink(String commandDrinkType) {
        return CoffeeMachineCommandType.isHotDrink(commandDrinkType);
    }
}
