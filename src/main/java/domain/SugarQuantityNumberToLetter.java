package domain;

import java.util.Arrays;

public enum SugarQuantityNumberToLetter {
    ZERO("no", "no"),
    ONE("1", "one"),
    TWO("2", "two");

    private String keyNumber;
    private String Symbol;

    SugarQuantityNumberToLetter(String keyNumber, String symbol) {
        this.keyNumber = keyNumber;
        Symbol = symbol;
    }

    public String getKeyNumber() {
        return keyNumber;
    }

    public String getSymbol() {
        return Symbol;
    }

    public static String getQuantitySymbol(String quantity) {

        return Arrays.stream(values()).
                filter(quanity -> quanity.getKeyNumber().equals(quantity))
                .map(sugar -> sugar.getSymbol())
                .findAny()
                .orElse("");

    }
}

