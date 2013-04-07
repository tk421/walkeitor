package org.rubenrr.walkeitor.element.resource;

import org.andengine.entity.sprite.Sprite;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.ElementManager;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

/**
 * User: Ruben Rubio Rey
 * Date: 5/04/13
 * Time: 8:44 PM
 */
abstract class Resource extends Sprite{

    ElementConfig elementConfig;

    public Resource(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, TextureRegionManager.getInstance().get(elementConfig), GameManager.getInstance().getVertexBufferObjectManager());
        this.elementConfig= elementConfig;
        ElementManager.getInstance().addResource(this);
    }

    @Override
    public String toString() {
        final String string = this.elementConfig.toString() + " " + super.toString();
        return super.toString();
    }

}
