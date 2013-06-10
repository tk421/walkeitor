package org.rubenrr.walkeitor.manager.command.primitive;

import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.element.unit.Unit;

/**
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 5:42 PM
 */
public class GiveToPrimitiveCommand implements PrimitiveCommand {

    // Unit that will store that consumable
    Unit unit;

    // Building that we are taking the resources from. Please note that the resources might be gone.
    Building building;

    // Consumable that will be transferred
    Consumable consumable;

    @Override
    public void execute() {

        // how much space is available in the building
        // TODO check the limits in the building and check what are we going to do in case there is not enough space



    }
}
