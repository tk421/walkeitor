package org.rubenrr.walkeitor.production;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.manager.util.Storage;
import org.rubenrr.walkeitor.manager.SceneManager;

/**
 * User: Ruben Rubio Rey
 * Date: 26/05/13
 * Time: 5:59 PM
 */
public abstract class ProductionBase implements ProductionStrategy {

    private Storage storage;
    private float timeElapsed; // every time this production will be triggered
    private TimerHandler productionTimeHandler;

    public ProductionBase (Storage storage, float timeElapsed) {
        this.storage = storage;
        this.timeElapsed = timeElapsed;
    }

    /**
     * Marks how often the production will happen
     * based in the configuration.
     *
     * Every time we are able to generate a production the method
     * generateProduction() will be called.
     *
     * @return
     */
    private TimerHandler getProductionHandler () {
        if (this.productionTimeHandler == null ) {
            float interval = this.timeElapsed;
            this.productionTimeHandler = new TimerHandler(interval, true, new ITimerCallback() {
                @Override
                public void onTimePassed(final TimerHandler pTimerHandler) {
                    ProductionBase.this.generateProduction();
                }
            });
        }
        return this.productionTimeHandler;
    }

    abstract public void generateProduction();

    /**
     * What to do after the production is generated
     */
    protected void generateProduction(Consumable consumableProduced) {
        // Notify the HUD
        SceneManager.getInstance().getHUD().addConsumable(consumableProduced);
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
        return this.storage.addConsumable(consumableVariation);
    }
}
