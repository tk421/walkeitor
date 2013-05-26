package org.rubenrr.walkeitor.production;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.element.storage.Storage;
import org.rubenrr.walkeitor.manager.SceneManager;

/**
 * User: Ruben Rubio Rey
 * Date: 26/05/13
 * Time: 5:59 PM
 */
public abstract class ProductionBase implements ProductionStrategy {

    private Storage storage;

    public ProductionBase (Storage storage) {
        this.storage = storage;
    }

    /**
     * What to do after the production is generated
     */
    protected void generateProduction(Consumable consumableProduced) {
        // Notify the HUD
        SceneManager.getInstance().getHUD().updateConsumable(consumableProduced);
    }

    @Override
    public void startProduction() {
        TimerHandler timeHandler = this.getProductionHandler();
        if (timeHandler != null) {
            SceneManager.getInstance().registerUpdateHandler(this.getProductionHandler());
        }
    }

    @Override
    public void stopProduction() {
        TimerHandler timeHandler = this.getProductionHandler();
        if (timeHandler != null) {
            SceneManager.getInstance().unregisterUpdateHandler(this.getProductionHandler());
        }
    }

    @Override
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Updates the Consumables available in the storage
     *
     * @param consumableVariation
     * @return
     */
    protected boolean updateConsumable( Consumable consumableVariation ) {
        return this.storage.updateConsumable(consumableVariation);
    }
}
