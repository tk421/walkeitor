package org.rubenrr.walkeitor.menu.building;

import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.element.building.Factory;
import org.rubenrr.walkeitor.element.unit.Vehicle;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.menu.MenuAction;
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

    class NewTankAction implements MenuAction {
        public void execute(float pX, float pY) {
            Vehicle tank = new Vehicle(pX, pY, ElementConfig.UNIT_TANK);

            // TODO algorithm to allocate the unit
            SceneManager.getInstance().attachChild(tank);
        }
    }


    class StartConstructionTankFactoryAction implements MenuAction {
        public void execute(float pX, float pY) {
            //construction started
            TankFactoryMenu.this.clear();
            Factory factory = (Factory)TankFactoryMenu.this.getSprite();

            // we mark the construction as finished
            // TODO construction process
            factory.constructionFinish();
        }
    }


}
