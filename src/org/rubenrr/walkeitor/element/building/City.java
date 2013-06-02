package org.rubenrr.walkeitor.element.building;

import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.status.StatusConfig;
import org.rubenrr.walkeitor.manager.SceneManager;

/**
 * User: Ruben Rubio Rey
 * Date: 23/03/13
 * Time: 3:57 PM
 *
 * The city is the main building of a civilization.
 *
 */
public class City extends Building {
    public City(float pX, float pY, ElementConfig elementConfig, StatusConfig statusConfig) {
        super(pX, pY, elementConfig, statusConfig);
        SceneManager.getInstance().getScene().registerTouchArea(this);
        this.setMenu(elementConfig.getMenuStrategy(), this);
    }

}
