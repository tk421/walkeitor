package org.rubenrr.walkeitor.menu;

import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.StatusConfig;

/**
 * NUll implementation for the sprite menu
 *
 * User: Ruben Rubio Rey
 * Date: 21/04/13
 * Time: 6:28 PM
 */
public class NoMenu extends MenuBase {
    @Override
    public void display() {
        // Nothing
    }

    @Override
    public void display(StatusConfig statusConfig, TouchEvent touchEvent) {
        // Nothing
    }

}
