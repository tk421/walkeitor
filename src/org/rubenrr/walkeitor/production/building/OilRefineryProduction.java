package org.rubenrr.walkeitor.production.building;

import android.util.Log;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.element.building.Refinery;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.element.consumable.CrudeOil;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.command.MoveConsumableToBuildingCommand;
import org.rubenrr.walkeitor.manager.util.Storage;
import org.rubenrr.walkeitor.production.ProductionBase;

/**
 * The oil refinery received crude and generates Fuel.
 *
 * User: Ruben Rubio Rey
 * Date: 26/05/13
 * Time: 6:02 PM
 */
public class OilRefineryProduction extends ProductionBase {

    Refinery refinery;

    public OilRefineryProduction(Refinery refinery, Storage storage, float timeElapsed) {
        super(storage, timeElapsed);
        this.refinery = refinery;
    }

    /**
     * Generate the production, if we have everything that we need in order to do so.
     *
     * It should check if we have everything needed (power, requiredConsumables, etc)
     */
    public void generateProduction() {

        boolean success = false;

        // Oil refinery. First thing we need is a defined amount of crude oil
        // in the storage
        Consumable consumableRequired = ElementConfig.REFINERY_OIL.getConsumableRequired();
        Consumable consumableProduced = ElementConfig.REFINERY_OIL.getConsumableProduced();

        Log.d("Refinery/Production", "Starting Production ");
        if ( this.getStorage().takeConsumable(consumableRequired).getAmount() > 0 ) {

            Log.d("Refinery/Production", "Requirements are met " + this.getStorage().toString());

            // consumable is available and removed from storage
            // al requirements has been met. Lets produce.
            if (this.getStorage().addConsumable(consumableProduced)) {
                Log.d("Refinery/Production", "Production happened "+ this.getStorage().toString());

            } else {
                // As we cannot use the consumable required, lets return it.
                this.getStorage().addConsumable(consumableRequired);

                Log.d("Refinery/Production", "Production cannot happen. Not enough space ? "+ this.getStorage().toString());
                // TODO we need to call a worker to fix up this issue
            }

        } else {
            Log.d("Refinery/Production", "Requirements are not met");

            // It will fill it up the refinery with Crude Oil
            Consumable consumableNeeded = new CrudeOil(this.getStorage().getAvailable());

            // How to get where which object contains the storage ??
            MoveConsumableToBuildingCommand giveCommand = new MoveConsumableToBuildingCommand(this.refinery, consumableNeeded);

            //Let's call a worker to fix up the problem
            GameManager.getInstance().addTask(giveCommand);


            this.stopProduction(); // stop production until this issue is fixed
        }

        //if (this.getStorage().addConsumable())



        if ( success ) {
            //super.generateProduction(consumable);
        }
    }


}
