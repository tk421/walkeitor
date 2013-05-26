package org.rubenrr.walkeitor.production;

import org.andengine.engine.handler.timer.TimerHandler;
import org.rubenrr.walkeitor.element.storage.Storage;

/**
 * User: Ruben Rubio Rey
 * Date: 2/05/13
 * Time: 8:40 PM
 */
public interface ProductionStrategy {

    // created the timeHandler that will be used interval the product generation if everything that is needed
    // is available
    TimerHandler getProductionHandler();

    // checks that everything needed to generate the Consumables are available and reduces such quantities
    void generateProduction();

    // starts the production process
    void startProduction();

    // stops the production process
    void stopProduction();

    // all production needs to have an storage associated
    void setStorage(Storage storage);

    Storage getStorage();
}
