package domain;

import java.time.LocalDate;

public class CommandCoffeeMachine {
    private final String commandDrinkType;
    private final int commandNumber;
    private final LocalDate commandDay;

    public CommandCoffeeMachine(String commandDrinkType, int commandNumber, LocalDate commandDay) {
        this.commandDrinkType = commandDrinkType;
        this.commandNumber = commandNumber;
        this.commandDay = commandDay;
    }

    @Override
    public String toString() {
        return  commandDrinkType + "||" +
                "||" + commandNumber +
                "||" + commandDay+
                "||";
    }

    public double getTotalPrice() throws CommandException {
        return CoffeeMachineCommandType.getTotalPrice(commandDrinkType,commandNumber);
    }
}
