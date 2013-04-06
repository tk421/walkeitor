package org.rubenrr.walkeitor.element.resource;

import org.andengine.entity.sprite.Sprite;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

/**
 * User: Ruben Rubio Rey
 * Date: 5/04/13
 * Time: 8:44 PM
 */
abstract class Resource extends Sprite{

    ElementWrapper elementwrapper;

    public Resource(float pX, float pY, ElementWrapper elementwrapper) {
        super(pX, pY, TextureRegionManager.getInstance().get(elementwrapper.getImagePathNormal()), GameManager.getInstance().getVertexBufferObjectManager());
        this.elementwrapper = elementwrapper;

    }


}
