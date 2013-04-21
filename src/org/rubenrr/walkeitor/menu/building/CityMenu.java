package org.rubenrr.walkeitor.menu.building;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.element.building.City;
import org.rubenrr.walkeitor.element.unit.Person;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.menu.MenuBase;
import org.rubenrr.walkeitor.menu.MenuExtendable;

/**
 *  Menu for the City:
 *   - Create worker
 *
 * User: Ruben Rubio Rey
 * Date: 6/04/13
 * Time: 6:57 PM
 */
public class CityMenu extends MenuBase  {

    /**
     * If the city received one touch, a menu will be
     * - Construct worker: Will create a class person
     */
    public void display() {

        Log.d("CityMenu", "Menu OnClick. Action ");

        String optionText = "Create worker";

        // menu will be located to the right of the object
        final Text option1 = new Text(this.getSprite().getWidth() + 10, 0, FontLoadManager.getInstance().get(FontConfig.MENU_STANDARD), optionText, optionText.length(), SceneManager.getInstance().getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    Log.d("CityMenu","OnClick Option 1: " + this.getText() );

                    this.onCreateWorker();

                    CityMenu.this.clear();

                }
                return true;
            }


            /**
             * When someone touch Create Worker a new worker user is created
             */
            private void onCreateWorker() {
                // TODO Algorithm to allocate the unit
                // TODO Resources usage and time needed to build the unit
                Person person = new Person(this.getParent().convertLocalToSceneCoordinates(this.getX(), this.getY() + this.getHeight() + 10), ElementConfig.UNIT_WORKER);
                SceneManager.getInstance().attachChild(person);
            }
        };

        this.addEntity(option1);
        SceneManager.getInstance().getScene().registerTouchArea(option1);

    }

}
