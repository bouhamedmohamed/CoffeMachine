package domain;

public class CoffeeMachine {
    public String sendCommand(String command) {
        if (command.equals(""))
            return "";
        String dringType = "";
        final String[] commandParts = command.split(":");
        final String commandDrinkType = commandParts[0];
        if (commandDrinkType.equals("T"))
            dringType = "tea";
        else
            dringType = "chocolate";
        return "Drink maker makes 1 " + dringType + " with no sugar therefore no stick";
    }
}
