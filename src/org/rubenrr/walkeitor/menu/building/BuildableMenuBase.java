package org.rubenrr.walkeitor.menu.building;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.config.status.StatusConfig;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.util.Buildable;
import org.rubenrr.walkeitor.menu.MenuBase;

/**
 * User: Ruben Rubio Rey
 * Date: 27/04/13
 * Time: 6:53 PM
 */
abstract class BuildableMenuBase extends MenuBase {
    @Override
    public void display(StatusConfig statusConfig, TouchEvent touchEvent) {

        this.clear();

        Log.d("BuildableMenuBase", "Display with events " + statusConfig.toString() + " " + touchEvent.toString());

        // we are creating this building
        if (statusConfig.isSetLocation() && touchEvent.isActionUp()) {

            Buildable building = (Buildable) this.getSprite();

            if (building.isBuildableInResource()) {
                this.displayAcceptStartConstruction();
            } else {
                this.displayCancelStartConstruction();
            }

        }

    }

    /**
     * Display the menu if we locate the building in an area that we
     * CAN build it
     */
    abstract void displayAcceptStartConstruction();

    /**
     * User does not want to build this any more
     */
    void displayCancelStartConstruction() {

        String optionText = "Cancel construction";

        final Sprite building = this.getSprite();

        final Text option1 = new Text(this.getSprite().getWidth() + 10, 0, FontLoadManager.getInstance().get(FontConfig.MENU_STANDARD),
                optionText, optionText.length(), SceneManager.getInstance().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    GameManager.getInstance().removeBuilding((Building)building);
                }
                return true;
            }
        };
        this.addEntity(option1);
        SceneManager.getInstance().getScene().registerTouchArea(option1);

    }
}
