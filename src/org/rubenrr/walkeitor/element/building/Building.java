package org.rubenrr.walkeitor.element.building;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;
import org.rubenrr.walkeitor.menu.MenuStrategy;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

/**
 * User: Ruben Rubio Rey
 * Date: 30/03/13
 * Time: 1:07 PM
 */
abstract class Building extends Sprite {

    private MenuStrategy menu;

    public Building(float pX, float pY, ElementWrapper buildingwrapper) {
        super(pX, pY, TextureRegionManager.getInstance().get(buildingwrapper.getImagePathNormal()), GameManager.getInstance().getVertexBufferObjectManager());
   }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

        switch (pSceneTouchEvent.getAction()) {
            case TouchEvent.ACTION_DOWN:
                this.menu.actionOnTouch(this);
                break;
            //case TouchEvent.ACTION_MOVE: {
            //    break;}
            //case TouchEvent.ACTION_UP:
            //    break;
        }

        return true;
    }

    public void setMenu(MenuStrategy menu) {
        this.menu = menu;
    }
}
