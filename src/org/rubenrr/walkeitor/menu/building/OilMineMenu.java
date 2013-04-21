package org.rubenrr.walkeitor.menu.building;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.building.Mine;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.menu.MenuBase;
import org.rubenrr.walkeitor.menu.MenuExtendable;
import org.rubenrr.walkeitor.util.TileLocatable;

/**
 * User: Ruben Rubio Rey
 * Date: 14/04/13
 * Time: 6:50 PM
 */
public class OilMineMenu extends MenuBase {

    @Override
    public void display() {
        // no implementation yet
    }

    @Override
    public void display(StatusConfig statusConfig, TouchEvent touchEvent) {

        Log.d("OilMineMenu", "Display with events " + statusConfig.toString() + " " + touchEvent.toString());

        // we are creating this building
        if (statusConfig.isSetLocation() && touchEvent.isActionUp()) {

            if (GameManager.getInstance().isInFreeTiles(this.getSpriteLocatable())) {
                Log.d("OilMineMenu", "Is in free tiles");
            } else {
                Log.d("OilMineMenu", "Is NOT in free tiles");
            }

        }

    }
}
