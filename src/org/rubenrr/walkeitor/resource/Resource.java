package org.rubenrr.walkeitor.resource;

import org.andengine.entity.sprite.Sprite;
import org.rubenrr.walkeitor.GameManager;
import org.rubenrr.walkeitor.utils.TextureRegionManager;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

/**
 * User: Ruben Rubio Rey
 * Date: 5/04/13
 * Time: 8:44 PM
 */
abstract class Resource extends Sprite{

    ElementWrapper elementwrapper;

    public Resource(float pX, float pY, ElementWrapper elementwrapper) {
        super(pX, pY, TextureRegionManager.getInstance().getTextureRegion(elementwrapper.getImagePathNormal()), GameManager.getInstance().getVertexBufferObjectManager());
        this.elementwrapper = elementwrapper;

    }


}
