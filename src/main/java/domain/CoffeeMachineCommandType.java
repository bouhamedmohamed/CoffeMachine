package domain;

import java.util.Arrays;
import java.util.Optional;

public enum CoffeeMachineCommandType {
    ORANGE("O", 0.6, new String[]{"one orange juice"}),
    COFFEE("C", 0.4, new String[]{"1 coffee"}),
    TEA("T", 0.6, new String[]{"1 tea"}),
    CHOCOLATE("H", 0.5, new String[]{"1 chocolate"}),
    HOTCOFFE("Ch", 0.4, new String[]{"an extra hot coffee"}),
    HOTCHOCOLATE("Hh", 0.5, new String[]{"an extra hot chocolate"}),
    EMPTY("", 0.0, new String[]{"", "no", "therefore no"}),
    Zero("0", 0.0, new String[]{"", "0", "a"});
    private String keyCommand;
    private Double price;
    private String[] symbolCommand;

    CoffeeMachineCommandType(String keyCommand, Double price, String[] symbolCommand) {
        this.keyCommand = keyCommand;
        this.price = price;
        this.symbolCommand = symbolCommand;
    }

    public Double getPrice() {
        return price;
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

    public double buyAndGetMoneyBack(double amount) {
        return amount - price;
    }
}
