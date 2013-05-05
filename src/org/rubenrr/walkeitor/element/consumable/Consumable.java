package org.rubenrr.walkeitor.element.consumable;

import android.util.Log;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;

/**
 * User: Ruben Rubio Rey
 * Date: 30/04/13
 * Time: 8:14 PM
 */
public abstract class Consumable {

    private float value;
    protected ConsumableConfig consumableConfig;

    Consumable(ConsumableConfig consumableConfig) {
        this(consumableConfig, 0f);
    }

    Consumable(ConsumableConfig consumableConfig, float value) {
        this.consumableConfig = consumableConfig;
        this.value = value;
    }

    public float getAmount() {
        return this.value;
    }

    public int getRoundAmount() {
        return Math.round(this.value);
    }

    public boolean isAdding() {
        if (this.value > 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public String getAmountToString() {
        int value = this.getRoundAmount();
        String valueString;
        if ( value <= 1000000 ) {
            valueString = "999M";
        } else if ( value > 1000000 ) {
            valueString = String.valueOf(Math.round(value/1000000 - 0.5)) + 'M';
        } else if ( value > 1000 ) {
            valueString = String.valueOf(Math.round(value/1000 - 0.5)) + 'k';
        } else {
            valueString = String.valueOf(this.getRoundAmount());
        }

        return valueString;
    }

    public void addAmount(float value) {
        this.value = this.value + value;
    }

    public boolean removeAmount(float value) throws Exception {
        float newValue = this.value - value;
        boolean success = true;
        if ( newValue < 0) {
            Log.w("Consumable", "Trying to remove more amount than the one that is available");
            success = false;
        } else {
            this.value = this.value - value;
        }
        return success;
    }

    abstract public boolean equals(Object o);

    abstract public int hashCode();

    public String getName() {
        return consumableConfig.getName();
    }

    public ConsumableConfig getConsumableConfig() {
        return consumableConfig;
    }
}
