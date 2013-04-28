package org.rubenrr.walkeitor.element.resource;

import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.SceneManager;

/**
 * User: Ruben Rubio Rey
 * Date: 5/04/13
 * Time: 8:38 PM
 */
public class Ore extends Resource {

    public Ore(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, elementConfig);
        SceneManager.getInstance().attachChild(this);
    }
}
