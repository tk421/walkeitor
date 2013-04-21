package org.rubenrr.walkeitor.menu.person;

import android.util.Log;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.building.Mine;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.action.OccupiedTiles;
import org.rubenrr.walkeitor.menu.MenuBase;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.List;

/**
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 4:54 PM
 */
public class WorkerMenu extends MenuBase  {

    /**
     * If the city received one touch, a menu will be
     * - Construct worker: Will create a class person
     */
    public void display() {

        String optionText = "Build a oil mine";

        final Text option1 = new Text(this.getSprite().getWidth() + 10, 0, FontLoadManager.getInstance().get(FontConfig.MENU_STANDARD),
                                        optionText, optionText.length(), SceneManager.getInstance().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    Log.d("WorkerMenu", "OnClick Option 1: " + this.getText());

                    SceneManager.getInstance().getBackgroundTile().clearAll();

                    List<TileLocatable> resourceOil = GameManager.getInstance().getResourcesBySubtype("oil");

                    OccupiedTiles occupiedTiles = new OccupiedTiles();
                    //TODO this sounds weird, to pass the second parameter of the configuration
                    occupiedTiles.setFree(resourceOil);
                    occupiedTiles.draw();


                    Mine newMine = new Mine(pSceneTouchEvent.getX() - 100, pSceneTouchEvent.getY(), ElementConfig.MINE_OIL);
                    newMine.setDragAndDropLocation();

                    SceneManager.getInstance().attachChild(newMine);
                }
                return true;
            }
        };
        this.addEntity(option1);
        SceneManager.getInstance().getScene().registerTouchArea(option1);

    }

    @Override
    public void display(StatusConfig statusConfig, TouchEvent touchEvent) {
        // no implementation yet
    }
}
