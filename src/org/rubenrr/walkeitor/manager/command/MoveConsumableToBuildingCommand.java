package org.rubenrr.walkeitor.manager.command;

import android.util.Log;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.element.unit.Unit;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.command.primitive.StartProductionPrimitiveCommand;
import org.rubenrr.walkeitor.manager.command.primitive.TransferConsumablesPrimitiveCommand;
import org.rubenrr.walkeitor.manager.command.primitive.MoveToPrimitiveCommand;

/**
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 4:29 PM
 */
public class MoveConsumableToBuildingCommand implements Command, Comparable<Command> {

    // This unit is the one who is going to give it`
    Unit unit;

    // This building is the destiny, is the one who contains the storage
    Building building;

    // How much of this consumable is needed
    Consumable consumable;

    public MoveConsumableToBuildingCommand(Building building, Consumable consumable) {
        this(null, building, consumable);
    }

    public MoveConsumableToBuildingCommand(Unit unit, Building building, Consumable consumable) {
        this.unit = unit;
        this.building = building;
        this.consumable = consumable;
    }

    @Override
    public boolean execute() {

        boolean success = false;

        if (unit == null) {
            Log.e("Command", "Can't execute command. We do not know who will be doing it!");
        } else {

            // First, we need to find out who can supply us the required consumable
            Log.d("MoveConsumableToBuildingCommand/Step1", "Searching for resources");
            Building buildingFrom = GameManager.getInstance().getBuildingThatContains(this.consumable);

            if (buildingFrom == null ) {

                Log.w("Command", "No building contains the required consumable " + this.consumable);

            } else {

                // Second, we have to move towards that building
                MoveToPrimitiveCommand movetoCommand = new MoveToPrimitiveCommand(unit, buildingFrom);
                this.unit.addCommand(movetoCommand);

                // Third, we should be able to get from the Storage that consumable
                TransferConsumablesPrimitiveCommand takeFromCommand = new TransferConsumablesPrimitiveCommand(buildingFrom.getStorage(), unit.getStorage(), this.consumable, unit);
                this.unit.addCommand(takeFromCommand);

                // Four, we need to move to the building
                MoveToPrimitiveCommand movetoDestinationCommand = new MoveToPrimitiveCommand(unit, this.building);
                this.unit.addCommand(movetoDestinationCommand);

                // Five, we give the consumable to the building
                TransferConsumablesPrimitiveCommand giveToCommand = new TransferConsumablesPrimitiveCommand(unit.getStorage(), this.building.getStorage(), this.consumable, unit);
                this.unit.addCommand(giveToCommand);

                // Six, moving resources to a building is to start production.
                StartProductionPrimitiveCommand startProduction = new StartProductionPrimitiveCommand(this.building.getProduction(), unit);
                this.unit.addCommand(startProduction);

                success = true;

                this.unit.startExecuteCommands();
            }



        }

        return success;

    }

    @Override
    public int compareTo(Command another) {
        // TODO for now, null implementation. Priorities needs to be set.
        return 0;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
