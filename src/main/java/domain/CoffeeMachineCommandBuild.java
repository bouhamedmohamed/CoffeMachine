package domain;

public class CoffeeMachineCommandBuild {
    final CoffeeMachineCommandType preparedDrink;
    final int sugar;
    final boolean stick;
    final boolean hotDrink;

    private CoffeeMachineCommandBuild(CoffeeMachineCommandType preparedDrink, int sugar, boolean stick, boolean hotDrink) {
        this.preparedDrink = preparedDrink;
        this.sugar = sugar;
        this.stick = stick;
        this.hotDrink = hotDrink;
    }

    public String buildCommand() {
        String drinkSymbol = preparedDrink.getKeyCommand();
        String sugarQuantity = sugar == 0 ? "" : String.valueOf(this.sugar);
        String stickState = stick ? "0" : "";
        if (this.hotDrink) {
            drinkSymbol += preparedDrink.getKeyCommand() + "h";
        }
        if (drinkSymbol.equals("O")) {
            sugarQuantity = "";
            stickState = "";
        }
        return drinkSymbol + ":" + sugarQuantity + ":" + stickState;
    }


    public static final class CoffeeMachineCommandBuildBuilder {
        CoffeeMachineCommandType preparedDrink;
        int sugar;
        boolean stick;
        boolean hotDrink;

        private CoffeeMachineCommandBuildBuilder() {
        }

        public static CoffeeMachineCommandBuildBuilder aCoffeeMachineCommandBuild() {
            return new CoffeeMachineCommandBuildBuilder();
        }

        public CoffeeMachineCommandBuildBuilder withPreparedDrink(CoffeeMachineCommandType preparedDrink) {
            this.preparedDrink = preparedDrink;
            return this;
        }

        public CoffeeMachineCommandBuildBuilder withSugar(int sugar) {
            this.sugar = sugar;
            return this;
        }

        public CoffeeMachineCommandBuildBuilder withStick(boolean stick) {
            this.stick = stick;
            return this;
        }

        public CoffeeMachineCommandBuildBuilder withHotDrink(boolean hotDrink) {
            this.hotDrink = hotDrink;
            return this;
        }

        public CoffeeMachineCommandBuild build() {
            CoffeeMachineCommandBuild coffeeMachineCommandBuild = new CoffeeMachineCommandBuild(preparedDrink, sugar, stick, hotDrink);
            return coffeeMachineCommandBuild;
        }
    }
}
