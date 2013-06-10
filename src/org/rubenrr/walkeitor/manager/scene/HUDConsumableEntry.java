package org.rubenrr.walkeitor.manager.scene;

import android.util.Log;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.SceneManager;

/**
 * User: Ruben Rubio Rey
 * Date: 5/05/13
 * Time: 8:31 PM
 */
public class HUDConsumableEntry implements ConsumableUpdatable {
    private Text label;
    private Text value;
    private ConsumableConfig consumableConfig;
    private Consumable consumable;

    public HUDConsumableEntry(ConsumableConfig consumableConfig, HUD hud, int pX) {
        this.consumableConfig = consumableConfig;

        String labelText = consumableConfig.getName() + ":";
        this.label = new Text(pX, 1, FontLoadManager.getInstance().get(FontConfig.HUD),
                labelText, labelText.length(), SceneManager.getInstance().getVertexBufferObjectManager());
        hud.attachChild(this.label);

        this.value = new Text(pX + this.label.getWidth() + 5, 1, FontLoadManager.getInstance().get(FontConfig.HUD),
                "", "0000".length(), SceneManager.getInstance().getVertexBufferObjectManager());
        hud.attachChild(this.value);

        this.consumable = this.consumableConfig.factory();
    }

    @Override
    public boolean addConsumable(Consumable consumable) {
        boolean success = false;
        if( consumable.getConsumableConfig().equals(this.consumableConfig) ) {
            Log.d("HUDConsumableEntry/updateConsumable", "SAME consumableConfig");
            this.consumable.addAmount(consumable.getRoundAmount());
            this.updateHUDValue();
            success = true;
        } else {
            Log.d("HUDConsumableEntry/updateConsumable", "Different consumableConfig");
        }
        return success;
    }

    @Override
    public Consumable takeConsumable(Consumable consumable) {
        // null implementation
        Log.w("HUDManager/takeConsumable", "Executing null implementation");
        Consumable retrieved = consumable.getConsumableConfig().factory();
        return retrieved;
    }

    private void updateHUDValue() {
        Log.d("HUDConsumableEntry/updateConsumable", "HUD Updated to " + this.consumable.getAmountToString());
        this.value.setText(this.consumable.getAmountToString());
    }
}
