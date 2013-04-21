package org.rubenrr.walkeitor.element.unit;

import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.menu.person.WorkerMenu;

/**
 * Created with IntelliJ IDEA.
 * User: tk421
 * Date: 22/03/13
 * Time: 7:14 AM
 *
 */
public class Person extends Unit {

    public Person( float[] position, ElementConfig elementConfig) {
        this(position[0], position[1], elementConfig);
    }

    public Person(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, elementConfig);
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
