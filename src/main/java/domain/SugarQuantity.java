package domain;

import java.util.Arrays;
import java.util.Optional;

public enum SugarQuantity {
    ZERO("no", "no"),
    ONE("1", "one");

    private String keyNumber;
    private String Symbol;

    SugarQuantity(String keyNumber, String symbol) {
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
        final Optional<SugarQuantity> sugarQuanity = Arrays.stream(values()).
                filter(quanity -> quanity.getKeyNumber().equals(quantity)).
                findAny();
        if (sugarQuanity.isPresent())
            quantitySymbol = sugarQuanity.get().getSymbol();
        return quantitySymbol;
    }
}

