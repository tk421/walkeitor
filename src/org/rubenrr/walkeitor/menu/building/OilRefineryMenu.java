package org.rubenrr.walkeitor.menu.building;

import org.rubenrr.walkeitor.element.building.Refinery;
import org.rubenrr.walkeitor.menu.MenuAction;
import org.rubenrr.walkeitor.menu.MenuOption;

/**
 * User: Ruben Rubio Rey
 * Date: 27/04/13
 * Time: 6:51 PM
 */
public class OilRefineryMenu extends BuildableMenuBase {
    @Override
    public void display() {
        // no implementation yet
    }

    /**
     * Accept the location and build the mine
     */
    void displayAcceptStartConstruction() {
        MenuOption option1 = new MenuOption("Start construction");
        this.addMenuOption(option1, new StartConstructionOilRefineryAction());
    }

    class StartConstructionOilRefineryAction implements MenuAction {
        public void execute(float pX, float pY) {
            //construction started
            OilRefineryMenu.this.clear();
            Refinery refinery = (Refinery)OilRefineryMenu.this.getSprite();

            // we mark the construction as finished
            // TODO construction process
            refinery.constructionFinish();
        }
    }

}
