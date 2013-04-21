package org.rubenrr.walkeitor.menu;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.StatusConfig;

/**
 * User: Ruben Rubio Rey
 * Date: 6/04/13
 * Time: 6:35 PM
 */
public interface MenuStrategy {
    // display menu on normal cirmcunstances
    void display();

    // display the menu that depends on the context
    void display(StatusConfig statusConfig, TouchEvent touchEvent);

    // get the parent sprite which the menu will pop up
    void setSprite(MenuExtendable sprite);

    //clear menu from screen
    void clear();
}
