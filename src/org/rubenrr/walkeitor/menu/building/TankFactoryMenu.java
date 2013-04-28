package org.rubenrr.walkeitor.menu.building;

import android.util.Log;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.building.Factory;
import org.rubenrr.walkeitor.element.unit.Person;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.menu.MenuActions;
import org.rubenrr.walkeitor.menu.MenuBase;
import org.rubenrr.walkeitor.menu.MenuOption;

/**
 * User: Ruben Rubio Rey
 * Date: 27/04/13
 * Time: 7:37 PM
 */
public class TankFactoryMenu extends BuildableMenuBase {

    /**
     * If the city received one touch, a menu will be
     * - Construct worker: Will create a class person
     */
    public void display() {

        MenuOption option1 = new MenuOption("New Tank");
        this.addMenuOption(option1, new NewTankAction());

    }


    void displayAcceptStartConstruction() {
        MenuOption option1 = new MenuOption("Start construction");
        this.addMenuOption(option1, new StartConstructionTankFactoryAction());
    }

    class NewTankAction implements MenuActions {
        public void execute(float pX, float pY) {
            // So far nothing
            // By default buildings are attached to the scene, however units but be specified
            //SceneManager.getInstance().attachChild(person);
        }
    }


    class StartConstructionTankFactoryAction implements MenuActions {
        public void execute(float pX, float pY) {
            //construction started
            TankFactoryMenu.this.clear();
            Factory factory = (Factory)TankFactoryMenu.this.getSprite();

            // we mark the construction as finished
            factory.constructionFinish();
        }
    }


}
