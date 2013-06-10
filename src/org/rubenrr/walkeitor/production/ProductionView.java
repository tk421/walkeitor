package org.rubenrr.walkeitor.production;

import android.util.Log;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.text.Text;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.util.SpriteAttachable;
import org.rubenrr.walkeitor.manager.util.Storage;

import java.util.Iterator;
import java.util.Map;

/**
 * User: Ruben Rubio Rey
 * Date: 10/06/13
 * Time: 5:16 PM
 */
public class ProductionView {

    private TimerHandler timerHandler;
    private Storage storage;
    private String productionStatus = "None";
    private Text text;
    private SpriteAttachable sprite;

    ProductionView(SpriteAttachable sprite, Storage storage) {
        this.storage = storage;
        this.sprite = sprite;
        this.text = new Text(0, this.sprite.getHeight() + 10, FontLoadManager.getInstance().get(FontConfig.PRODUCTION_SNAPSHOT),
                "", 1000, SceneManager.getInstance().getVertexBufferObjectManager());
        this.sprite.attachChild(this.text);
        this.timerHandler = this.getProductionViewHandler();
        SceneManager.getInstance().registerUpdateHandler(this.timerHandler);

    }

    public void setProductionStatus(final String productionStatus) {
        this.productionStatus = productionStatus;
        this.updateDisplay();
    }

    /**
     * How often the status will be displayed
     *
     * @return
     */
    private TimerHandler getProductionViewHandler () {
        if (this.timerHandler == null ) {
            float interval = 5;
            this.timerHandler = new TimerHandler(interval, true, new ITimerCallback() {
                @Override
                public void onTimePassed(final TimerHandler pTimerHandler) {
                    Log.d("ProductionView/TimeHandler", "Thread updated");
                    ProductionView.this.updateDisplay();
                }
            });
        }
        return this.timerHandler;
    }

    void updateDisplay() {
        StringBuffer optionText = new StringBuffer("Status: " + this.productionStatus);
        Map<ConsumableConfig, Consumable> consumables = this.storage.getConsumables();

        optionText.append("\nSize: " + storage.getSize());

        Iterator it = consumables.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            ConsumableConfig consumableConfig = (ConsumableConfig)pairs.getKey();
            Consumable consumable = (Consumable)pairs.getValue();
            optionText.append( "\n" + consumableConfig.getName() +  ": " + consumable.getAmountToString());
        }

        Log.d("ProductionView/TimeHandler", "Update Display: " + optionText);

        this.text.setText(optionText);

    }

}
