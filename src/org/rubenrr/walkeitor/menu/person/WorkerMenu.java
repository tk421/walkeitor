package org.rubenrr.walkeitor.menu.person;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.menu.MenuBase;

/**
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 4:54 PM
 */
public class WorkerMenu extends MenuBase {

    public WorkerMenu(Sprite sprite) {
        super(sprite);
    }

    /**
     * If the city received one touch, a menu will be
     * - Construct worker: Will create a class person
     */
    public void actionOnTouch(Sprite sprite) {

        String optionText = "Construct";

        final Text option1 = new Text(sprite.getWidth() + 10, 0, FontLoadManager.getInstance().get(FontConfig.MENU_STANDARD) , optionText, optionText.length(), SceneManager.getInstance().getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    Log.d("PersonmenuOnClick", "OnClick Option 1: " + this.getText());
                }
                return true;
            }
        };
        this.addEntity(option1);
        SceneManager.getInstance().getScene().registerTouchArea(option1);

    }

}
