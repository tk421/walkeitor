package org.rubenrr.walkeitor.menu.building;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.element.building.Mine;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.action.OccupiedTiles;
import org.rubenrr.walkeitor.menu.MenuBase;
import org.rubenrr.walkeitor.menu.MenuExtendable;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.List;

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

        this.clear();

        Log.d("OilMineMenu", "Display with events " + statusConfig.toString() + " " + touchEvent.toString());

        // we are creating this building
        if (statusConfig.isSetLocation() && touchEvent.isActionUp()) {

            Mine mine = (Mine) this.getSprite();

            if (mine.isBuildableInResource()) {
                this.display_accept();
            } else {
                this.display_cancel();
            }

        }

    }

    /**
     * Accept the location and build the mine
     * TODO Remember to unset the DragAndDrop
     */
    private void display_accept() {

        String optionText = "Start construction";

        final Text option1 = new Text(this.getSprite().getWidth() + 10, 0, FontLoadManager.getInstance().get(FontConfig.MENU_STANDARD),
                optionText, optionText.length(), SceneManager.getInstance().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    Log.d("OilMineMenu", "Construction started");
                    OilMineMenu.this.clear();
                }
                return true;
            }
        };
        this.addEntity(option1);
        SceneManager.getInstance().getScene().registerTouchArea(option1);

    }


    /**
     * User does not want to build this any more
     */
    private void display_cancel() {

        String optionText = "Cancel construction";

        final Sprite mine = this.getSprite();

        final Text option1 = new Text(this.getSprite().getWidth() + 10, 0, FontLoadManager.getInstance().get(FontConfig.MENU_STANDARD),
                optionText, optionText.length(), SceneManager.getInstance().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    GameManager.getInstance().removeBuilding((Building)mine);
                }
                return true;
            }
        };
        this.addEntity(option1);
        SceneManager.getInstance().getScene().registerTouchArea(option1);

    }



}
