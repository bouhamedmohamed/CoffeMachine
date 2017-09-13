package domain;

import java.util.Arrays;

public enum CoffeeMachineCommandType {
    ORANGE("O", 0.6, new String[]{"one orange juice"}),
    COFFEE("C", 0.4, new String[]{"1 coffee"}),
    TEA("T", 0.6, new String[]{"1 tea"}),
    CHOCOLATE("H", 0.5, new String[]{"1 chocolate"}),

    EMPTY("", 0.0, new String[]{"", "no", "therefore no"}),
    ZERO("0", 0.0, new String[]{"", "0", "a"}),
    NUMBER("ANY", 0.0, new String[]{""});

    static final String HOT_DRINK_SYMBOL = "h";
    static final String HOT_DRINK_MESSAGE = "an extra hot";
    private String keyCommand;
    private Double price;
    private String[] symbolCommand;


    CoffeeMachineCommandType(String keyCommand, Double price, String[] symbolCommand) {
        this.keyCommand = keyCommand;
        this.price = price;
        this.symbolCommand = symbolCommand;
    }

    public String getKeyCommand() {
        return keyCommand;
    }

    public String[] getSymbolCommand() {
        return symbolCommand;
    }

    public static CoffeeMachineCommandType getCommandType(String command) throws CommandException {
        String finalDrinkType = checkIsHotDrink(command);
        return Arrays.stream(values())
                .filter(commandType -> commandType.getKeyCommand().equals(finalDrinkType))
                .findFirst()
                .orElse(NUMBER);


    }

    private static String checkIsHotDrink(String command) throws CommandException {
        String drinkType = command;
        if (isHotDrink(command)) {
            final String firstCharFromCommand = command.substring(0, 1);
            drinkType = firstCharFromCommand;
        }
        return drinkType;
    }


    public static String getSymbolCommand(String commandPart, int position) throws CommandException {
        String symbol = commandPart;
        final CoffeeMachineCommandType commandPartType = getCommandType(commandPart);
        if (!commandPartType.keyCommand.equals("ANY"))
            symbol = getSymbolFromDrink(commandPart, position, symbol, commandPartType);

        return symbol;

    }

    private static String getSymbolFromDrink(String commandPart, int position, String symbol, CoffeeMachineCommandType commandPartType) throws CommandException {
        symbol = commandPartType.getSymbolCommand()[position];
        if (isHotDrink(commandPart))
            symbol = commandPartType.getSymbolCommand()[position].replace("1", HOT_DRINK_MESSAGE);
        return symbol;
    }

    public double buyAndGetMoneyBack(double amount) {
        return amount - price;
    }

    public static boolean isHotDrink(String commandDrinkKey) throws CommandException {
        return commandDrinkKey.contains(HOT_DRINK_SYMBOL);
    }

    public static boolean isOrangeJuice(String commandDrinkType) {
        return commandDrinkType.equals("O");
    }

    public static double getTotalPrice(String commandDrinkType, int commandNumber) throws CommandException {
        final CoffeeMachineCommandType commandType = getCommandType(commandDrinkType);
        return commandType.price * commandNumber;

    }


}
