package org.rubenrr.walkeitor.element.building;

import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.menu.building.CityMenu;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

/**
 * User: Ruben Rubio Rey
 * Date: 23/03/13
 * Time: 3:57 PM
 *
 * The city is the main building of a civilization.
 *
 */
public class City extends Building {
    public City(float pX, float pY, ElementWrapper buildingwrapper) {
        super(pX, pY, buildingwrapper);
        GameManager.getInstance().getScene().registerTouchArea(this);
        this.setMenu(new CityMenu());
    }

}
