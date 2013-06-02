package org.rubenrr.walkeitor.production;

import org.andengine.engine.handler.timer.TimerHandler;
import org.rubenrr.walkeitor.element.storage.Storage;

/**
 * User: Ruben Rubio Rey
 * Date: 2/05/13
 * Time: 8:40 PM
 */
public interface ProductionStrategy {

    /**
     * This is the method which is executed every time we can make a production
     *  - checks if we have everything we need to generate a production
     *  - removes the consumables needed to make the production
     *  - stores the new produces consumables
     *  - if the consumables needed are not in the storage, triggers the search for those consumables.
     *
     */
    void generateProduction();

    // starts the production process
    void startProduction();

    // stops the production process
    void stopProduction();

    // all production needs to have an storage associated
    void setStorage(Storage storage);

    Storage getStorage();
}
