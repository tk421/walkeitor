package org.rubenrr.walkeitor.element.resource;

import org.andengine.entity.sprite.Sprite;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;

/**
 * User: Ruben Rubio Rey
 * Date: 5/04/13
 * Time: 8:44 PM
 */
abstract class Resource extends Sprite{

    ElementConfig elementConfig;

    public Resource(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, TextureRegionManager.getInstance().get(elementConfig), SceneManager.getInstance().getVertexBufferObjectManager());
        this.elementConfig= elementConfig;
        GameManager.getInstance().addResource(this);
    }

    @Override
    public String toString() {
        final String string = this.elementConfig.toString() + " " + super.toString();
        return super.toString();
    }

}
