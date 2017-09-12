package domain;

import java.util.Arrays;
import java.util.Optional;

public enum CoffeeMachineCommandType {
    ORANGE("O", 0.6, new String[]{"one orange juice"}, 0.0, 0.0),
    COFFEE("C", 0.4, new String[]{"1 coffee"}, 0.1, 0.20),
    TEA("T", 0.6, new String[]{"1 tea"}, 0.3, 0.0),
    CHOCOLATE("H", 0.5, new String[]{"1 chocolate"}, 0.3, 0.2),
    HOTCOFFE("Ch", 0.4, new String[]{"an extra hot coffee"}, 0.3, 0.2),
    HOTCHOCOLATE("Hh", 0.5, new String[]{"an extra hot chocolate"}, 0.3, 0.2),
    HOTTEA("Th", 0.6, new String[]{"an extra hot tea"}, 0.3, 0.0),
    EMPTY("", 0.0, new String[]{"", "no", "therefore no"}, 0.0, 0.0),
    Zero("0", 0.0, new String[]{"", "0", "a"}, 0.0, 0.0);

    static final String HOT_DRINK_SYMBOL = "h";
    private static StockMachine stockMachine = new StockMachineImplementation();
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

    public static Optional<CoffeeMachineCommandType> getCommandType(String command) {
        return Arrays.stream(values())
                .filter(commandType -> commandType.getKeyCommand().equals(command))
                .findFirst();

    }


    public static String getSymbolCommand(String commandPart, int position) {
        String symbol = commandPart;
        final Optional<CoffeeMachineCommandType> commandPartType = getCommandType(commandPart);
        if (commandPartType.isPresent())
            symbol = commandPartType.get().getSymbolCommand()[position];
        return symbol;
    }

    public double buyAndGetMoneyBack(double amount) {
        return amount - price;
    }

    public static boolean isHotDrink(String commandDrinkKey) {

        final Optional<CoffeeMachineCommandType> commandType = getCommandType(commandDrinkKey);
        if (commandType.isPresent())
            return commandDrinkKey.contains(HOT_DRINK_SYMBOL);
        return false;

    }

    public static boolean isOrangeJuce(String commandDrinkType) {
        return commandDrinkType.equals("O");
    }

    public static double getTotalPrice(String commandDrinkType, int commandNumber) {
        final Optional<CoffeeMachineCommandType> commandType = getCommandType(commandDrinkType);
        if (commandType.isPresent())
            return commandType.get().price * commandNumber;
        return 0;
    }

    public static boolean haveEnoughQuantityToserveTheDrink(String commandDrinkType) {
        final Optional<CoffeeMachineCommandType> commandType = getCommandType(commandDrinkType);
        if (commandType.isPresent()) {
            final CoffeeMachineCommandType coffeeMachineCommandType = commandType.get();
            return stockMachine.hasEnoughRessource(coffeeMachineCommandType.getMilkQuantity(), coffeeMachineCommandType.getWaterQuantity());
        }
        return false;
    }
}
