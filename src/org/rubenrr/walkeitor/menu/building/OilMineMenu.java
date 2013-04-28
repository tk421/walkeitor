package org.rubenrr.walkeitor.menu.building;

import org.rubenrr.walkeitor.element.building.Mine;
import org.rubenrr.walkeitor.menu.MenuAction;
import org.rubenrr.walkeitor.menu.MenuOption;

/**
 * User: Ruben Rubio Rey
 * Date: 14/04/13
 * Time: 6:50 PM
 */
public class OilMineMenu extends BuildableMenuBase {

    @Override
    public void display() {
        // no implementation yet
    }

    /**
     * Accept the location and build the mine
     */
    void displayAcceptStartConstruction() {
        MenuOption option1 = new MenuOption("Start construction");
        this.addMenuOption(option1, new StartConstructionOilMineAction());
    }

    class StartConstructionOilMineAction implements MenuAction {
        public void execute(float pX, float pY) {
            //construction started
            OilMineMenu.this.clear();
            Mine mine = (Mine)OilMineMenu.this.getSprite();

            // we mark the construction as finished
            mine.constructionFinish();
        }
    }

}
