package domain;

import java.util.Arrays;
import java.util.Optional;

public enum CoffeeMachineCommandType {
    COFFEE("C", "coffee"),
    TEA("T", "tea"),
    CHOCOLATE("H", "chocolate"),
    EMPTY("", "", "no", "therefore no"),
    Zero("0", "", "0", "a");
    private String keyCommand;
    private String[] symbolCommand;

    CoffeeMachineCommandType(String keyCommand, String... symbolCommand) {
        this.keyCommand = keyCommand;
        this.symbolCommand = symbolCommand;
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
}
