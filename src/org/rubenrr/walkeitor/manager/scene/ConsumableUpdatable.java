package org.rubenrr.walkeitor.manager.scene;

import org.rubenrr.walkeitor.element.consumable.Consumable;

/**
 * If object needs to be notified about consumable updates
 *
 *
 * User: Ruben Rubio Rey
 * Date: 4/05/13
 * Time: 7:31 PM
 */
public interface ConsumableUpdatable {

    // create a consumable
    public boolean addConsumable(Consumable consumable);

    // remove a consumable from the scene
    public void removeConsumable(Consumable consumable);

    // move a consumable to somewhere else
    public Consumable takeConsumable(Consumable consumable);

}
