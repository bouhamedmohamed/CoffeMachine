package domain;

public interface StockMachine {
    boolean hasEnoughRessource(String drinkType) throws CommandException;
}
