package org.rubenrr.walkeitor.element.unit;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.ElementManager;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;
import org.rubenrr.walkeitor.menu.MenuStrategy;

/**
 * User: Ruben Rubio Rey
 * Date: 30/03/13
 * Time: 1:07 PM
 *
 * Note: this cannot be abstract as I need to recognize the object at ElementManager
 *
 */
public class Unit extends Sprite {

    private ElementConfig elementConfig;
    private MenuStrategy menu;

    public Unit(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, TextureRegionManager.getInstance().get(elementConfig), GameManager.getInstance().getVertexBufferObjectManager());
        this.elementConfig = elementConfig;
        ElementManager.getInstance().addUnit(this);
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

    @Override
    public String toString() {
        final String string = this.elementConfig.toString() + " " + super.toString();
        return super.toString();
    }

    protected void setMenu(MenuStrategy menu) {
        this.menu = menu;
    }

    public void clearMenu() {
        this.menu.clear();
    }
}
