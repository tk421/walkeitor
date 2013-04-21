package org.rubenrr.walkeitor.element.building;

import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.menu.building.OilMineMenu;

/**
 * User: Ruben Rubio Rey
 * Date: 22/03/13
 * Time: 7:27 AM
 *
 * Extracts resources
 *
 */
public class Mine extends Building {

    public Mine(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, elementConfig);
        SceneManager.getInstance().getScene().registerTouchArea(this);
        this.setMenu(elementConfig.getMenuStrategy(), this);
    }


}
