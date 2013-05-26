package org.rubenrr.walkeitor.production;

import org.andengine.engine.handler.timer.TimerHandler;
import org.rubenrr.walkeitor.element.storage.Storage;

/**
 * User: Ruben Rubio Rey
 * Date: 26/05/13
 * Time: 6:16 PM
 */
public class NoProduction extends ProductionBase {

    public NoProduction(Storage storage) {
        super(storage);
    }

    @Override
    public TimerHandler getProductionHandler() {
        // Empty
        return null;
    }

    @Override
    public void generateProduction() {
        // Empty
    }

}
