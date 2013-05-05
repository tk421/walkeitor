package org.rubenrr.walkeitor.config.element;

/**
 * User: Ruben Rubio Rey
 * Date: 1/05/13
 * Time: 8:05 PM
 */
public enum ProductionConfig {

    MINE_OIL("Production Mine Oil", null, ConsumableConfig.CRUDE_OIL, 1f, 0, 1f, 1),
    REFINERY_OIL("Refinery Crude Oil to Fuel", ConsumableConfig.CRUDE_OIL, ConsumableConfig.FUEL, 30f, 3f, 1f, 7);

    // Internal code name
    private final String name;

    // What consumable is required for this to produce
    private final ConsumableConfig consumableRequire;

    // WHat consumable will be produced
    private final ConsumableConfig consumableProduce;

    // How often this exchange will happen
    private final float timeElapsed;

    // In that elapsed time,how many consumables will be used
    private final float consumableRequiredUnit;

    // In that elapsed time, how many consumables will be produced
    private final float consumableProducedUnit;

    //in that elapsed time, how much energy will be used
    // TODO insert energy in the system
    private final int energyNeededKwh;

    private ProductionConfig(String name, ConsumableConfig consumableRequire, ConsumableConfig consumableProduce, float timeElapsed, float consumableRequiredUnit, float consumableProducedUnit, int energyConsumedKw) {
        this.name = name;
        this.consumableRequire = consumableRequire;
        this.consumableProduce = consumableProduce;
        this.timeElapsed = timeElapsed;
        this.consumableRequiredUnit = consumableRequiredUnit;
        this.consumableProducedUnit = consumableProducedUnit;
        this.energyNeededKwh = energyConsumedKw;
    }

    @Override
    public String toString() {
        return "ProductionConfig{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public ConsumableConfig getConsumableRequire() {
        return consumableRequire;
    }

    public ConsumableConfig getConsumableProduce() {
        return consumableProduce;
    }

    public float getTimeElapsed() {
        return timeElapsed;
    }

    public float getConsumableRequiredUnit() {
        return consumableRequiredUnit;
    }

    public float getConsumableProducedUnit() {
        return consumableProducedUnit;
    }

    public int getEnergyNeededKwh() {
        return energyNeededKwh;
    }
}
