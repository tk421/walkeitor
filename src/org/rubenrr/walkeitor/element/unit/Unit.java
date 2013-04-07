package org.rubenrr.walkeitor.element.unit;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;
import org.rubenrr.walkeitor.menu.MenuStrategy;

/**
 * User: Ruben Rubio Rey
 * Date: 30/03/13
 * Time: 1:07 PM
 *
 * TODO This is very similar to Building. Should be the same ?
 *
 */
abstract class Unit extends Sprite {

    private MenuStrategy menu;

    public Unit(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, TextureRegionManager.getInstance().get(elementConfig), GameManager.getInstance().getVertexBufferObjectManager());
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

    protected void setMenu(MenuStrategy menu) {
        this.menu = menu;
    }
}
