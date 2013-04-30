package org.rubenrr.walkeitor.manager.action.building;

/**
 * Interface in case that the Element generates a Consumable
 *
 * User: Ruben Rubio Rey
 * Date: 30/04/13
 * Time: 8:04 PM
 */
public interface ConsumableSubject {

    public void registerObserver(ConsumableObserver consumableObserver);
    public void removeObserver(ConsumableObserver consumableObserver);

    public void notifyObservers();

    // get methods ...

}
