package org.rubenrr.walkeitor.manager.scene;

import android.util.Log;
import org.andengine.engine.camera.hud.HUD;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.config.status.StorageStatusConfig;
import org.rubenrr.walkeitor.element.consumable.Consumable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * THIS CLASS IS NOT GOOD!!!
 * I AM GOING TO NEED TO CREATE ANOTHER CLASS THAT COMPOSES THE
 * CONSUMABLE AND THE TEXT, and then instanciate this to have the values
 *
 * User: Ruben Rubio Rey
 * Date: 30/04/13
 * Time: 7:23 PM
 */
public class HUDManager implements ConsumableUpdatable {

    private HUD hud;

    private Map<ConsumableConfig, HUDConsumableEntry> consumableEntries = new HashMap<ConsumableConfig, HUDConsumableEntry>();

    public HUDManager(HUD hud) {
        this.hud = hud;
        this.loadConsumables();
    }


    private void loadConsumables() {

        //Takes care of the position in the screen TODO rotate
        int x = 1;

        for (ConsumableConfig consumableConfig : ConsumableConfig.values()) {

            HUDConsumableEntry hudConsumableEntry = new HUDConsumableEntry(consumableConfig, this.hud, x);
            consumableEntries.put(consumableConfig, hudConsumableEntry);

            x = x + 70 + 35;

        }
    }

    /**
     * Something has used or has created Consumables
     *
     * @param consumableVariation
     */
    public boolean addConsumable(Consumable consumableVariation) {
        boolean success = this.addOrRemoveConsumable(consumableVariation, StorageStatusConfig.ADD);
        return success;
    }

    private boolean addOrRemoveConsumable(Consumable consumableVariation, StorageStatusConfig storageStatusConfig) {
        boolean success = false;
        Log.d("HUDManager/updateConsumable", "New update");
        Iterator it = this.consumableEntries.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            ConsumableConfig consumableConfig = (ConsumableConfig)pairs.getKey();
            HUDConsumableEntry hudConsumableEntry = (HUDConsumableEntry)pairs.getValue();
            if ( storageStatusConfig.equals(StorageStatusConfig.ADD)  ) {
                if (hudConsumableEntry.addConsumable(consumableVariation)) {
                    Log.d("HUDManager/updateConsumable", "UPDATE SUCCESSFUL!");
                    success = true;
                    break; // only one
                }
            } else {
                hudConsumableEntry.removeConsumable(consumableVariation);
            }
        }
        return success;
    }

    @Override
    public Consumable takeConsumable(Consumable consumable) {
        // null implementation
        Log.w("HUDManager/takeConsumable", "Executing null implementation");
        Consumable retrieved = consumable.getConsumableConfig().factory();
        return retrieved;
    }

    @Override
    public void removeConsumable(Consumable consumableVariation) {
        this.addOrRemoveConsumable(consumableVariation, StorageStatusConfig.REMOVE);
    }
}
