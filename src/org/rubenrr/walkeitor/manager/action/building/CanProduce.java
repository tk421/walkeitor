package org.rubenrr.walkeitor.manager.action.building;

import org.andengine.engine.handler.timer.TimerHandler;

/**
 * User: Ruben Rubio Rey
 * Date: 2/05/13
 * Time: 8:40 PM
 */
public interface CanProduce {

    // created the timeHandler that will be used interval the product generation if everything that is needed
    // is available
    TimerHandler getProduction();

    // checks that everything needed to generate the Consumables are available and reduces such quantities
    void generateProduction();
}
