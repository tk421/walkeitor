package org.rubenrr.walkeitor.element.unit;

import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.status.UnitStatusConfig;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;

/**
 * User: Ruben Rubio Rey
 * Date: 22/03/13
 * Time: 7:19 AM
 *
 * Vehicle: Transport (i.e Truck, Military) unit (i.e tank)
 *
 */
public class Vehicle extends Unit {

    public Vehicle(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, elementConfig, UnitStatusConfig.IDLE);
        SceneManager.getInstance().getScene().registerTouchArea(this);
        GameManager.getInstance().addUnit(this);
        this.setMenu(elementConfig.getMenuStrategy(), this);
    }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

        switch (pSceneTouchEvent.getAction()) {
            case TouchEvent.ACTION_DOWN:

                // Notify GameManager that a unit has been selected
                GameManager.getInstance().selectUnit(this);

                break;
        }
        super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
        return true;
    }


}
