package domain;

import java.time.LocalDate;

import static domain.CoffeeMachineCommandType.getCommandType;
import static domain.CoffeeMachineCommandType.getSymbolCommand;

public class CoffeeMachine {
    final static int DRINK_TYPE = 0;
    final static int SUGAR_QUANTITY = 1;
    final static int STICK_STATE = 2;
    private final CoffeeMachineRepository coffeeMachineRepository;
    private final StockMachine stockMachine;
    private EmailNotification email;


    public CoffeeMachine(CoffeeMachineRepository coffeeMachineRepository, StockMachine stockMachine, EmailNotification email) {

        this.coffeeMachineRepository = coffeeMachineRepository;
        this.stockMachine = stockMachine;
        this.email = email;
    }

    public String checkCommandBeforePreparation(String command, double amount) {

        final String commandDrinkType = getPartFromCommand(command, DRINK_TYPE);


        final boolean haveEnoughResourceToPrepareDrink;

        haveEnoughResourceToPrepareDrink = stockMachine.isEmpty(commandDrinkType);

        if (haveEnoughResourceToPrepareDrink) {
            try {
                return prepareCommand(command, amount, commandDrinkType);
            } catch (CommandException e) {
                e.printStackTrace();
            }
        } else
            email.sendNotification(commandDrinkType);
        return "Not enough resource to serve the drink we sent email to fix that ASAP";
    }

    public double getStatisticCommand() {
        return coffeeMachineRepository.getCoffeeMachineStatCommandAtDay(LocalDate.now());
    }

    private String sendCommand(String command) throws CommandException {
        final boolean isEmptyCommand = command.equals("");
        if (isEmptyCommand)
            return "";
        return buildCommand(command);
    }


    private String buildCommand(String command) throws CommandException {

        final String commandDrinkType = getPartFromCommand(command, DRINK_TYPE);
        final String commandSugar = getPartFromCommand(command, SUGAR_QUANTITY);
        final String commandStick = getPartFromCommand(command, STICK_STATE);
        coffeeMachineRepository.addCoffeeMachineCommand(commandDrinkType);
        return buildProductCommandToPrint(commandDrinkType, commandSugar, commandStick);
    }


    private String buildProductCommandToPrint(String commandDrinkType, String commandSugar, String commandStick) throws CommandException {

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


    private String prepareCommand(String command, double amount, String commandDrinkType) throws CommandException {
        double moneyBack = buyAndGetBack(commandDrinkType, amount);
        final boolean isNotEnoughMoney = moneyBack < 0;

        if (isNotEnoughMoney) {
            return "M: Enough money please add " + ((Math.abs(moneyBack) * 100) / 100);
        } else
            return sendCommand(command);
    }

    private double buyAndGetBack(String commandDrinkType, double amount) throws CommandException {

        final CoffeeMachineCommandType commandType = getCommandType(commandDrinkType);
        return commandType.buyAndGetMoneyBack(amount);

    }

    private String prepareSugarQuantity(String commandSugar, boolean preparedDrink) throws CommandException {
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

    private String getSymbol(String commandPart, int position) throws CommandException {
        return getSymbolCommand(commandPart, position);
    }


    private boolean isPreparedDrink(String commandDrinkType) throws CommandException {
        return isOrangeJuce(commandDrinkType) || isHotDrink(commandDrinkType);
    }

    private boolean isOrangeJuce(String commandDrinkType) {
        return CoffeeMachineCommandType.isOrangeJuice(commandDrinkType);
    }

    private boolean isHotDrink(String commandDrinkType) throws CommandException {
        return CoffeeMachineCommandType.isHotDrink(commandDrinkType);
    }
}
