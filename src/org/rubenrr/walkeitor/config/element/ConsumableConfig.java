package org.rubenrr.walkeitor.config.element;

import android.util.Log;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.element.consumable.CrudeOil;
import org.rubenrr.walkeitor.element.consumable.Fuel;

/**
 * User: Ruben Rubio Rey
 * Date: 30/04/13
 * Time: 8:39 PM
 */
public enum ConsumableConfig {
    CRUDE_OIL("Crude Oil", 1),
    FUEL("Fuel", 1);

    private String name;

    // marks the size that 1 unit of this Consumable occupies
    private int size;

    private ConsumableConfig(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public Consumable createInstance() {
        Consumable instance = null;

        if ( this.equals(ConsumableConfig.CRUDE_OIL)) {
            instance = new CrudeOil();
        } else if ( this.equals(ConsumableConfig.FUEL)) {
            instance = new Fuel();
        } else {
            Log.e("ConsumableConfig", "ConsumableConfig/getConsumableInstance " + this.toString() +  " not implemented");
        }

        return instance;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Consumable factory() {
        Consumable consumable;
        if (this.equals(ConsumableConfig.CRUDE_OIL)) {
            consumable = new CrudeOil();
        } else if (this.equals(ConsumableConfig.FUEL)) {
            consumable = new Fuel();
        } else {
            consumable = null;
        }
        return consumable;
    }

    @Override
    public String toString() {
        return "ConsumableConfig{" +
                "name='" + name + '\'' +
                '}';
    }

}
