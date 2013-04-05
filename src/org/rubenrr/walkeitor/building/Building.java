package org.rubenrr.walkeitor.building;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.GameManager;
import org.rubenrr.walkeitor.utils.TextureRegionManager;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

/**
 * User: Ruben Rubio Rey
 * Date: 30/03/13
 * Time: 1:07 PM
 */
abstract class Building extends Sprite {

    public Building(float pX, float pY, ElementWrapper buildingwrapper) {
        super(pX, pY, TextureRegionManager.getInstance().getTextureRegion(buildingwrapper.getImagePathNormal()), GameManager.getInstance().getVertexBufferObjectManager());
   }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

        switch (pSceneTouchEvent.getAction()) {
            case TouchEvent.ACTION_DOWN:
                this.menuOnClick();
                break;
            //case TouchEvent.ACTION_MOVE: {
            //    break;}
            //case TouchEvent.ACTION_UP:
            //    break;
        }

        return true;
    }

    /**
     * Displays menu when someone clicks on the entity
     */
    abstract void menuOnClick();

}
