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
    public boolean addConsumable(Consumable consumable);
    public Consumable takeConsumable(Consumable consumable);
}
