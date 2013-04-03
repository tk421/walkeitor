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

/**
 * User: Ruben Rubio Rey
 * Date: 22/03/13
 * Time: 7:27 AM
 *
 * Extracts resources
 *
 */
public class Mine {
    /**
     * I AM HERE, WE NEED TO DEFINE THE MINE, THE PATH, ETC
     * ACTUALLY THE SAMPLE IS OIL. OIL SHOULD BE A RESOURCE.


    private Font menufont;
    private String gfx_base = "gfx/building/mine/";
    public Mine(float pX, float pY, String type) {
        super(pX, pY, type);
        //String image = "gfx/building/mine/normal_" + type + ".png";
        this.menufont = FontFactory.create(GameManager.getInstance().getFontManger(), GameManager.getInstance().getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 20);
        this.menufont.load();
        GameManager.getInstance().getScene().registerTouchArea(this);

    }

    **
     * Actions to be accomplished when the menu
     *
    protected void menuOnClick() {
        // if anyone clicks on the City sprite, and menu will come up that will ask to
        // create a new worker
        Log.d("CitymenuOnClick", "Menu OnClick. Action ");

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
     */
}
