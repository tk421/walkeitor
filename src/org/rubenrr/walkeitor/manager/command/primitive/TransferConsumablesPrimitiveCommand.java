package org.rubenrr.walkeitor.manager.command.primitive;

import android.util.Log;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.element.unit.Unit;
import org.rubenrr.walkeitor.manager.command.Commandable;
import org.rubenrr.walkeitor.manager.util.Storage;

/**
 * This command takes a consumable from a building and stores it in a unit
 *
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 5:42 PM
 */
public class TransferConsumablesPrimitiveCommand implements PrimitiveCommand  {

    // Storage that will be the destination
    Storage storageTo;

    // Building that we are taking the resources from. Please note that the resources might be gone.
    Storage storageFrom;

    // Consumable that will be transferred
    Consumable consumable;

    // Unit that is moving
    Commandable unit;

    public TransferConsumablesPrimitiveCommand(Storage storageFrom, Storage storageTo, Consumable consumable, Commandable unit) {
        this.storageFrom = storageFrom;
        this.storageTo = storageTo;
        this.consumable = consumable;
        this.unit = unit;
    }

    @Override
    public void execute() {

        Log.d("TransferConsumablesPrimitiveCommand", "Before moving anything StorageFrom: " + this.storageFrom.toString());
        Log.d("TransferConsumablesPrimitiveCommand", "Before moving anything StorageTo: " + this.storageTo.toString());

        // how much space is available in the unit
        float spaceAvailable = this.storageTo.getAvailable();
        Consumable transferRequest = this.consumable.getConsumableConfig().factory();
        if (this.consumable.getAmount() > spaceAvailable) {
            transferRequest.setAmount(spaceAvailable);
        } else {
            transferRequest = this.consumable;
        }

        Log.d("TransferConsumablesPrimitiveCommand", "Transfer request: " + transferRequest.toString());
        Consumable consumable = this.storageFrom.takeConsumable(transferRequest);
        Log.d("TransferConsumablesPrimitiveCommand", "Actually moved: " + consumable.toString());

        this.storageTo.addConsumable(consumable);

        Log.d("TransferConsumablesPrimitiveCommand", "After moving StorageFrom: " + this.storageFrom.toString());
        Log.d("TransferConsumablesPrimitiveCommand", "After moving StorageTo: " + this.storageTo.toString());

        this.unit.nextCommand();

    }
}
