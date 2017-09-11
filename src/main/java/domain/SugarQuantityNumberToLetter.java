package domain;

import java.util.Arrays;
import java.util.Optional;

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
        String quantitySymbol = "";
        final Optional<SugarQuantityNumberToLetter> sugarQuanity = Arrays.stream(values()).
                filter(quanity -> quanity.getKeyNumber().equals(quantity)).
                findAny();
        if (sugarQuanity.isPresent())
            quantitySymbol = sugarQuanity.get().getSymbol();
        return quantitySymbol;
    }
}

