package domain;

public class StockMachineImplementation implements StockMachine {

    private double milkQuantity = 2;
    private double waterQuantity = 2;
    private EmailNotification emailNotification = new EmailNotificationImplementation();

    @Override
    public boolean hasEnoughRessource(Double milkQuantity, Double waterQuantity) {
        boolean enoughWater = haveEnoughWater(waterQuantity);
        boolean enoughMilk = haveEnoughMilk(milkQuantity);
        prepareNotification(enoughMilk, enoughWater);
        updateStock(milkQuantity, waterQuantity, enoughMilk, enoughWater);

        return enoughMilk && enoughWater;
    }

    private void updateStock(Double milkQuantity, Double waterQuantity, boolean enoughMilk, boolean enoughWater) {
        if (enoughMilk)
            consumeMilk(milkQuantity);
        if (enoughWater)
            consumeWater(waterQuantity);
    }

    private void consumeWater(Double waterQuantity) {
        this.waterQuantity -= waterQuantity;
    }

    private void consumeMilk(Double milkQuantity) {
        this.milkQuantity -= milkQuantity;
    }

    private void prepareNotification(boolean enoughMilk, boolean enoughWater) {
        if (!enoughMilk)
            emailNotification.send("Milk");

        if (!enoughWater)
            emailNotification.send("Water");
    }

    private boolean haveEnoughMilk(Double milkQuantity) {
        return this.milkQuantity >= milkQuantity;
    }

    private boolean haveEnoughWater(Double waterQuantity) {
        return this.waterQuantity >= waterQuantity;
    }

}
