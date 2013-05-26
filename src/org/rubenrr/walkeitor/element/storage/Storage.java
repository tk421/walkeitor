package org.rubenrr.walkeitor.element.storage;

import android.util.Log;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.manager.scene.ConsumableUpdatable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Storage object has the ability to storage consumables in
 * buildings, resources and units.
 *
 * User: Ruben Rubio Rey
 * Date: 26/05/13
 * Time: 1:46 PM
 */
public class Storage implements ConsumableUpdatable {

    // evaluates how much space is available to storage
    private int size;

    // storage contains the given consumables
    private Map<ConsumableConfig, Consumable> consumables = new HashMap<ConsumableConfig, Consumable>();

    public Storage(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("An storage should have the capability to store. (size cannot be 0)");
        }
        this.size = size;
    }


    /**
     * Store the consumable if there is space to do so
     *
     * TODO we allow to store an excess of resources from time to time
     *
     * @param consumableVariation
     */
    public boolean updateConsumable( Consumable consumableVariation ) {

        if (this.isFull()) {
            return false;
        }

        boolean success = false;
        Iterator it = this.consumables.entrySet().iterator();

        // if the consumable exists we update it
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            ConsumableConfig consumableConfig = (ConsumableConfig)pairs.getKey();
            Consumable consumable = (Consumable)pairs.getValue();
            if (consumableVariation.getConsumableConfig().equals(consumable.getConsumableConfig())) {
                Log.d("Storage", "Updating value to consumable " + consumableVariation.getConsumableConfig() + " " + consumableVariation.getAmount());
                consumable.addAmount(consumableVariation.getAmount());
                success = true;
                break; // only one
            }
        }

        // if the consumable does not exist then we add it
        if (!success) {
            Log.d("Storage", "Creating new consumable " + consumableVariation.getConsumableConfig());
            this.consumables.put(consumableVariation.getConsumableConfig(), consumableVariation);
            success = true;
        }

        return success;
    }


    /**
     * Check if this storage is full
     *
     * @return
     */
    private boolean isFull() {
        boolean success = false;
        int usedSize = 0;
        Iterator it = this.consumables.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            ConsumableConfig consumableConfig = (ConsumableConfig)pairs.getKey();
            Consumable consumable = (Consumable)pairs.getValue();
            usedSize = usedSize + consumable.getSize();
        }

        return (usedSize >= this.size);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "size=" + size +
                ", consumables=" + consumables +
                '}';
    }
}
