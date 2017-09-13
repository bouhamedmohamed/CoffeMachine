package domain;

import java.util.Arrays;

public enum CoffeeMachineCommandType {
    ORANGE("O", 0.6, new String[]{"one orange juice"}, 0.0, 0.0),
    COFFEE("C", 0.4, new String[]{"1 coffee"}, 0.1, 0.20),
    TEA("T", 0.6, new String[]{"1 tea"}, 0.3, 0.0),
    CHOCOLATE("H", 0.5, new String[]{"1 chocolate"}, 0.3, 0.2),
    HOTCOFFE("Ch", 0.4, new String[]{"an extra hot coffee"}, 0.3, 0.2),
    HOTCHOCOLATE("Hh", 0.5, new String[]{"an extra hot chocolate"}, 0.3, 0.2),
    HOTTEA("Th", 0.6, new String[]{"an extra hot tea"}, 0.3, 0.0),
    EMPTY("", 0.0, new String[]{"", "no", "therefore no"}, 0.0, 0.0),
    ZERO("0", 0.0, new String[]{"", "0", "a"}, 0.0, 0.0),
    NUMBER("ANY", 0.0, new String[]{""}, 0.0, 0.0);

    static final String HOT_DRINK_SYMBOL = "h";
    private String keyCommand;
    private Double price;
    private String[] symbolCommand;
    private Double waterQuantity;
    private Double milkQuantity;


    CoffeeMachineCommandType(String keyCommand, Double price, String[] symbolCommand, Double waterQuantity, Double milkQuantity) {
        this.keyCommand = keyCommand;
        this.price = price;
        this.symbolCommand = symbolCommand;
        this.waterQuantity = waterQuantity;
        this.milkQuantity = milkQuantity;
    }

    public Double getWaterQuantity() {
        return waterQuantity;
    }

    public Double getMilkQuantity() {
        return milkQuantity;
    }

    public String getKeyCommand() {
        return keyCommand;
    }

    public String[] getSymbolCommand() {
        return symbolCommand;
    }

    public static CoffeeMachineCommandType getCommandType(String command) throws CommandException {
        return Arrays.stream(values())
                .filter(commandType -> commandType.getKeyCommand().equals(command))
                .findFirst()
                .orElse(NUMBER);


    }


    public static String getSymbolCommand(String commandPart, int position) throws CommandException {
        String symbol = commandPart;
        final CoffeeMachineCommandType commandPartType = getCommandType(commandPart);
        if (!commandPartType.keyCommand.equals("ANY"))
            symbol = commandPartType.getSymbolCommand()[position];

        return symbol;

    }

    public double buyAndGetMoneyBack(double amount) {
        return amount - price;
    }

    public static boolean isHotDrink(String commandDrinkKey) throws CommandException {

        final CoffeeMachineCommandType commandType = getCommandType(commandDrinkKey);
        return commandDrinkKey.contains(HOT_DRINK_SYMBOL);


    }

    public static boolean isOrangeJuce(String commandDrinkType) {
        return commandDrinkType.equals("O");
    }

    public static double getTotalPrice(String commandDrinkType, int commandNumber) throws CommandException {
        final CoffeeMachineCommandType commandType = getCommandType(commandDrinkType);
        return commandType.price * commandNumber;

    }


}
