package domain;

public class CoffeeMachine {
    public String sendCommand(String command) {
        if (command.equals(""))
            return "";
        return "Drink maker makes 1 tea with no sugar therefore no stick";
    }
}
