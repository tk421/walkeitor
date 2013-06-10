package org.rubenrr.walkeitor.manager.util;

import android.util.Log;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.config.status.StorageStatusConfig;
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
        this.size = size;
    }


    /**
     * Store the consumable if there is space to do so
     *
     * TODO we allow to store an excess of resources from time to time
     *
     * @param consumableVariation
     */
    public boolean addConsumable(Consumable consumableVariation) {

        if (this.isFull()) {
            return false;
        }

        boolean success = this.modifyConsumable(consumableVariation, StorageStatusConfig.ADD);

        return success;
    }


    /**
     * Add or remove a consumable
     *
     * @param consumableVariation the consumable type and amount that will be added or removed
     * @param storageStatus the action that will happen
     * @return
     */
    private boolean modifyConsumable( Consumable consumableVariation, StorageStatusConfig storageStatus) {

        boolean success = false;
        Iterator it = this.consumables.entrySet().iterator();

        // if the consumable exists we update it
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            ConsumableConfig consumableConfig = (ConsumableConfig)pairs.getKey();
            Consumable consumable = (Consumable)pairs.getValue();
            if (consumableVariation.getConsumableConfig().equals(consumable.getConsumableConfig())) {
                Log.d("Storage", "Updating value to consumable " + consumableVariation.getConsumableConfig() + " " + consumableVariation.getAmount());
                if (storageStatus.equals(StorageStatusConfig.ADD)) {
                    consumable.addAmount(consumableVariation.getAmount());
                } else {
                    consumable.removeAmount(consumableVariation.getAmount());
                }
                success = true;
                break; // only one
            }
        }

        // if the consumable does not exist then we add it
        if (!success) {
            if (storageStatus.equals(StorageStatusConfig.ADD)) {
                Log.d("Storage", "Creating new consumable " + consumableVariation.getConsumableConfig());
                this.consumables.put(consumableVariation.getConsumableConfig(), consumableVariation);
                success = true;
            }
        }
        return success;
    }

    /**
     * Take from the storage the required consumable.
     * If the full amount cannot be met, we will return as much as possible.
     *
     * @param consumable
     * @return
     */
    @Override
    public Consumable takeConsumable(Consumable consumable) {

        Consumable retrievedConsumable = consumable.getConsumableConfig().factory();

        Log.d("Storage/takeConsumable", "Transfer request: " + consumable.toString());

        // how much is available in the building
        float amountAvailable = this.getAmountOfMatchedConsumable(consumable);

        if (amountAvailable > consumable.getAmount()) {
            retrievedConsumable.setAmount(consumable.getAmount());
        } else {
            retrievedConsumable.setAmount(amountAvailable);
        }

        Log.d("Storage/takeConsumable", "Available: " + retrievedConsumable.toString());

        boolean success = this.modifyConsumable(retrievedConsumable, StorageStatusConfig.REMOVE);

        if (!success) {
            retrievedConsumable.setAmount(0);
            Log.w("Storage/takeConsumable", "Cannot take required consumable " + consumable.toString() + " as this " +
                    "does not have it");
        }

        return retrievedConsumable;
    }

    @Override
    public void removeConsumable(Consumable consumable) {
        this.modifyConsumable(consumable, StorageStatusConfig.REMOVE);
    }

    /**
     * Remove the consumable from the storage
     *
     */
    private void removeConsumable() {

    }

    /**
     * Check if this storage is full
     *
     * @return
     */
    private boolean isFull() {
        int usedSize = this.getUsedSpace();
        return (usedSize >= this.size);
    }

    private int getUsedSpace() {
        int usedSize = 0;
        Iterator it = this.consumables.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            ConsumableConfig consumableConfig = (ConsumableConfig)pairs.getKey();
            Consumable consumable = (Consumable)pairs.getValue();
            usedSize = usedSize + consumable.getSize();
        }
        return usedSize;
    }

    public int getAvailable() {
        int usedSize = this.getUsedSpace();
        return this.size - usedSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "size=" + size +
                ", consumables=" + consumables +
                '}';
    }

    /**
     * Return which percentage of consumable contains this storage
     *
     * @param consumableSearched
     * @return
     */
    public int getPercentageOfMatchedConsumable(Consumable consumableSearched) {
        int percentage = 0;
        Consumable consumableAvailable = this.consumables.get(consumableSearched.getConsumableConfig());
        if ( consumableAvailable != null ) {
            percentage = Math.round( consumableAvailable.getAmount() * 100 / consumableSearched.getAmount()  );
        }
        return percentage;
    }

    /**
     * Return how many units of this consumable contains this storage
     *
     * @param consumableSearched
     * @return
     */
    public float getAmountOfMatchedConsumable(Consumable consumableSearched) {
        float amount = 0;
        Consumable consumableAvailable = this.consumables.get(consumableSearched.getConsumableConfig());
        if ( consumableAvailable != null ) {
            amount = consumableAvailable.getAmount();
        }
        return amount;
    }
}
