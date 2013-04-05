package org.rubenrr.walkeitor.building;

import android.graphics.Typeface;
import android.util.Log;
import org.andengine.engine.Engine;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.rubenrr.walkeitor.GameManager;
import org.rubenrr.walkeitor.unit.Person;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

/**
 * User: Ruben Rubio Rey
 * Date: 23/03/13
 * Time: 3:57 PM
 *
 * The city is the main building of a civilization.
 *
 */
public class City extends Building {

    private Font menufont;

    public City(float pX, float pY, ElementWrapper buildingwrapper) {
        super(pX, pY, buildingwrapper);
        this.menufont = FontFactory.create(GameManager.getInstance().getFontManger(), GameManager.getInstance().getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 20);
        this.menufont.load();
        GameManager.getInstance().getScene().registerTouchArea(this);

    }

    /**
     * Actions to be accomplished when the menu
     */
    protected void menuOnClick() {
        // if anyone clicks on the City sprite, and menu will come up that will ask to
        // create a new worker
        Log.d("CitymenuOnClick","Menu OnClick. Action " );

        // menu will be located to the right of the object
        final Text option1 = new Text(this.getWidth() + 10, 0, this.menufont , "Create worker", "create worker".length(), GameManager.getInstance().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {

                    Log.d("CitymenuOnClick","OnClick Option 1: " + this.getText() );

                    Person person = new Person(this.getParent().convertLocalToSceneCoordinates(this.getX(), this.getY() + this.getHeight() + 10));
                    GameManager.getInstance().getScene().attachChild(person);

                    final Engine.EngineLock engineLock = GameManager.getInstance().getEngineLock();
                    engineLock.lock();
                    this.getParent().detachChild(this);
                    engineLock.unlock();



                }
                return true;
            }
        };
        this.attachChild(option1);
        GameManager.getInstance().getScene().registerTouchArea(option1);

    }

}
