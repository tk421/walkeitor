package org.rubenrr.walkeitor.element.unit;

import android.graphics.Typeface;
import android.util.Log;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.menu.person.PersonMenu;

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
        GameManager.getInstance().getScene().registerTouchArea(this);
        this.setMenu(new PersonMenu());
    }
}
