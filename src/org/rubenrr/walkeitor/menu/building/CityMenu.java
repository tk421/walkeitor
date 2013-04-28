package org.rubenrr.walkeitor.menu.building;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.building.City;
import org.rubenrr.walkeitor.element.unit.Person;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.menu.MenuActions;
import org.rubenrr.walkeitor.menu.MenuBase;
import org.rubenrr.walkeitor.menu.MenuExtendable;
import org.rubenrr.walkeitor.menu.MenuOption;

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

        // first menu option to build a Oil Mine
        MenuOption option1 = new MenuOption("New Worker");
        this.addMenuOption(option1, new NewWorkerAction());

    }

    @Override
    public void display(StatusConfig statusConfig, TouchEvent touchEvent) {
        // No implementation needed ...
    }

    class NewWorkerAction implements MenuActions {
        public void execute(float pX, float pY) {
            // TODO Algorithm to allocate the unit
            // TODO Resources usage and time needed to build the unit
            Person person = new Person(pX, pY, ElementConfig.UNIT_WORKER);

            // By default buildings are attached to the scene, however units but be specified
            SceneManager.getInstance().attachChild(person);
        }
    }

}
