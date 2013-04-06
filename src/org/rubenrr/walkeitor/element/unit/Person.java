package org.rubenrr.walkeitor.element.unit;

import android.graphics.Typeface;
import android.util.Log;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.rubenrr.walkeitor.manager.GameManager;

/**
 * Created with IntelliJ IDEA.
 * User: tk421
 * Date: 22/03/13
 * Time: 7:14 AM
 *
 */
public class Person extends Unit {

    private Font menufont;

    public Person( float[] position) {
        this(position[0], position[1]);
    }

    public Person(float pX, float pY) {
        super(pX, pY, "gfx/unit/person/normal_person.png");
        this.menufont = FontFactory.create(GameManager.getInstance().getFontManger(), GameManager.getInstance().getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 20);
        this.menufont.load();
        GameManager.getInstance().getScene().registerTouchArea(this);
    }

    @Override
    void menuOnClick() {
        // If someone clicks in the person, a construction menu will come up
        final Text option1 = new Text(this.getWidth() + 10, 0, this.menufont , "Construct", "Construct".length(), GameManager.getInstance().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    Log.d("PersonmenuOnClick", "OnClick Option 1: " + this.getText());
                }
                return true;
            }
        };
        this.attachChild(option1);
        GameManager.getInstance().getScene().registerTouchArea(option1);
    }
}
