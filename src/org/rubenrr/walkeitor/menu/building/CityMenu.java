package org.rubenrr.walkeitor.menu.building;

import android.util.Log;
import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.menu.MenuStrategy;
import org.rubenrr.walkeitor.element.unit.Person;

/**
 * User: Ruben Rubio Rey
 * Date: 6/04/13
 * Time: 6:57 PM
 */
public class CityMenu implements MenuStrategy {
    @Override
    public void actionOnTouch(Sprite sprite) {

        Log.d("CityMenu", "Menu OnClick. Action ");

        String optionText = "Create worker";

        // menu will be located to the right of the object
        final Text option1 = new Text(sprite.getWidth() + 10, 0, FontLoadManager.getInstance().get("menustandardfont"), optionText, optionText.length(), GameManager.getInstance().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    Log.d("CityMenu","OnClick Option 1: " + this.getText() );
                    Person person = new Person(this.getParent().convertLocalToSceneCoordinates(this.getX(), this.getY() + this.getHeight() + 10));
                    GameManager.getInstance().getScene().attachChild(person);

                    // after selection remove it
                    final Engine.EngineLock engineLock = GameManager.getInstance().getEngineLock();
                    engineLock.lock();
                    this.getParent().detachChild(this);
                    engineLock.unlock();
                }
                return true;
            }
        };
        sprite.attachChild(option1);
        GameManager.getInstance().getScene().registerTouchArea(option1);

    }
}
