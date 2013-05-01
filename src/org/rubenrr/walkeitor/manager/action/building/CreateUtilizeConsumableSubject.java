package org.rubenrr.walkeitor.manager.action.building;

/**
 * This manages the subject:
 *  - Every time that there is a new consumable available to the player
 *  - Every time the user utilizes an existing consumable
 *
 * User: Ruben Rubio Rey
 * Date: 30/04/13
 * Time: 8:04 PM
 */
public interface CreateUtilizeConsumableSubject {

    public void registerObserver(CreateUtilizeConsumableObserver createUtilizeConsumableObserver);
    public void removeObserver(CreateUtilizeConsumableObserver createUtilizeConsumableObserver);

    public void notifyCreateUtilizeObservers();

    // get methods ...

}
