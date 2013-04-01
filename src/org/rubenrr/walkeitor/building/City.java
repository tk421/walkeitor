package org.rubenrr.walkeitor.building;

import android.graphics.Typeface;
import android.util.Log;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.rubenrr.walkeitor.GameManager;

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

    public City(float pX, float pY) {

        super(pX, pY, "gfx/building/city/normal_city.png");
        this.menufont = FontFactory.create(GameManager.getInstance().getFontManger(), GameManager.getInstance().getTextureManager(), 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 20);
        this.menufont.load();

    }

    /**
     * Actions to be accomplished when the menu
     */
    protected void menuOnClick() {
        // if anyone clicks on the City sprite, and menu will come up that will ask to
        // create a new worker
        Log.d("CitymenuOnClick","Menu OnClick under city object");

        // menu will be located to the right of the object
        final Text option1 = new Text(this.getWidth() + 10, 0, this.menufont , "Create worker", "create worker".length(), GameManager.getInstance().getVertexBufferObjectManager());
        this.attachChild(option1);
        final Text option2 = new Text(this.getWidth() + 10, option1.getHeight() + 5, this.menufont , "option 2", "option 2".length(), GameManager.getInstance().getVertexBufferObjectManager());
        this.attachChild(option2);
        final Text option3 = new Text(this.getWidth() + 10, option1.getHeight() + option2.getHeight() + 5 + 5, this.menufont , "Another option", "Another option".length(), GameManager.getInstance().getVertexBufferObjectManager());
        this.attachChild(option3);

        //GameManager.getInstance().getScene().attachChild(menuTest);
    }

}
