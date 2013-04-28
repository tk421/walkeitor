package org.rubenrr.walkeitor.menu.person;

import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.building.Factory;
import org.rubenrr.walkeitor.element.building.Mine;
import org.rubenrr.walkeitor.element.building.Refinery;
import org.rubenrr.walkeitor.menu.MenuActions;
import org.rubenrr.walkeitor.menu.MenuBase;
import org.rubenrr.walkeitor.menu.MenuOption;

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

        // first menu option to build a Oil Mine
        MenuOption option1 = new MenuOption("New Oil Mine");
        this.addMenuOption(option1, new NewOilMineAction());

        // second option to build a Oil Refinery
        MenuOption option2 = new MenuOption("New Oil Refinery");
        this.addMenuOption(option2, new NewOilRefineryAction());

        // third option build a tank factory
        MenuOption option3 = new MenuOption("New Tank Factory");
        this.addMenuOption(option3, new NewTankFactoryAction());

    }

    @Override
    public void display(StatusConfig statusConfig, TouchEvent touchEvent) {
        // no implementation yet
    }

    class NewOilMineAction implements MenuActions{
        public void execute(float pX, float pY) {
            new Mine(pX - 100, pY, ElementConfig.MINE_OIL, StatusConfig.SET_LOCATION);
        }
    }

    class NewOilRefineryAction implements MenuActions{
        public void execute(float pX, float pY) {
            new Refinery(pX - 100, pY, ElementConfig.REFINERY_OIL, StatusConfig.SET_LOCATION);
        }
    }

    class NewTankFactoryAction implements MenuActions{
        public void execute(float pX, float pY) {
            new Factory(pX - 100, pY, ElementConfig.FACTORY_TANK, StatusConfig.SET_LOCATION);
        }
    }

}
