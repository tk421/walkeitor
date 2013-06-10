package org.rubenrr.walkeitor.manager.command.primitive;

import android.util.Log;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.element.unit.Unit;

/**
 * This command takes a consumable from a building and stores it in a unit
 *
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 5:42 PM
 */
public class TakeFromPrimitiveCommand implements PrimitiveCommand {

    // Unit that will store that consumable
    Unit unit;

    // Building that we are taking the resources from. Please note that the resources might be gone.
    Building building;

    // Consumable that will be transferred
    Consumable consumable;

    public TakeFromPrimitiveCommand(Unit unit, Building building, Consumable consumable) {
        this.unit = unit;
        this.building = building;
        this.consumable = consumable;
    }

    @Override
    public void execute() {

        Log.d("TakeFromPrimitiveCommand", "Before moving anything Unit: " + this.unit.getStorage().toString());
        Log.d("TakeFromPrimitiveCommand", "Before moving anything Building: " + this.building.getStorage().toString());

        // how much space is available in the unit
        float spaceAvailable = this.unit.getStorage().getAvailable();
        Consumable transferRequest = this.consumable.getConsumableConfig().factory();
        if (this.consumable.getAmount() > spaceAvailable) {
            transferRequest.setAmount(spaceAvailable);
        } else {
            transferRequest = this.consumable;
        }

        Log.d("TakeFromPrimitiveCommand", "Transfer request: " + transferRequest.toString());
        Consumable consumable = this.building.getStorage().takeConsumable(transferRequest);
        Log.d("TakeFromPrimitiveCommand", "Actually moved: " + consumable.toString());

        Log.d("TakeFromPrimitiveCommand", "Moved consumable " + consumable.toString() + " from building " +
                this.building.toString() + " to unit " + this.unit.toString());

        this.unit.getStorage().addConsumable(consumable);

        Log.d("TakeFromPrimitiveCommand", "After moving Unit: " + this.unit.getStorage().toString());
        Log.d("TakeFromPrimitiveCommand", "After moving Building: " + this.building.getStorage().toString());


    }
}
