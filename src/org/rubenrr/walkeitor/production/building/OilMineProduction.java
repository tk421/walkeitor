package org.rubenrr.walkeitor.production.building;

import android.util.Log;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.element.consumable.CrudeOil;
import org.rubenrr.walkeitor.element.storage.Storage;
import org.rubenrr.walkeitor.production.ProductionBase;

/**
 * User: Ruben Rubio Rey
 * Date: 26/05/13
 * Time: 6:02 PM
 */
public class OilMineProduction extends ProductionBase {


    private TimerHandler productionTimeHandler;
    // TODO get the size of the Consumables available in the Resource from some configuration.
    // TODO started default to 50.000 units
    private CrudeOil availableCrudeOil = new CrudeOil(50000);

    public OilMineProduction(Storage storage) {
        super(storage);
    }

    @Override
    public TimerHandler getProductionHandler () {
        if (this.productionTimeHandler == null ) {
            float interval = ElementConfig.MINE_OIL.getTimeElapsed();
            this.productionTimeHandler = new TimerHandler(interval, true, new ITimerCallback() {
                @Override
                public void onTimePassed(final TimerHandler pTimerHandler) {
                    OilMineProduction.this.generateProduction();
                }
            });
        }
        return this.productionTimeHandler;
    }

    /**
     * Generate the production.
     *
     * It should check if we have everything needed (power, requiredConsumables, etc)
     */
    public void generateProduction() {

        boolean success = false;

        // consumable that we intend to produce
        CrudeOil crudeOilProduced = new CrudeOil(ElementConfig.MINE_OIL.getConsumableProducedUnit());
        Log.d("Mine/Production", "Generating Production " + crudeOilProduced.getAmountToString());

        // remove consumable from the source
        if ( this.availableCrudeOil.removeAmount(crudeOilProduced.getAmount()) ) {
            Log.d("Mine/Production", "Enough resources int he mine. Available: " + this.availableCrudeOil.getAmount());

            // It looks like we have enough in the time. Therefore let's move the
            // production to the Mine
            if ( this.updateConsumable(crudeOilProduced)) {
                // all good, the oil has been extracted
                Log.d("Mine/Production", "Enough space in the storage. Current stored: " + this.getStorage().toString());
                success = true;
            } else {
                Log.d("Mine/Production", "NOT Enough space in the storage. Current stored: " + this.getStorage().toString());

                //TODO UI Advise that the storage is full.

                // return the amount to the Mine
                this.availableCrudeOil.addAmount(crudeOilProduced.getAmount());

                this.stopProduction();
            }
        } else {
            Log.d("Mine/Production", "Not enough production in the mine. Stopping production. : " + this.availableCrudeOil.getAmount());
            this.stopProduction();
        }

        if ( success ) {
            super.generateProduction(crudeOilProduced);
        }
    }


}
