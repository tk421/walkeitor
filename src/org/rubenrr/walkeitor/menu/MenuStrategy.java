package org.rubenrr.walkeitor.menu;

import org.andengine.entity.sprite.Sprite;

/**
 * User: Ruben Rubio Rey
 * Date: 6/04/13
 * Time: 6:35 PM
 */
public interface MenuStrategy {
    // display menu
    void display();

    // get the parent sprite which the menu will pop up
    void setSprite(MenuExtendable sprite);

    //clear menu from screen
    void clear();
}
