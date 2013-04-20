package org.rubenrr.walkeitor.menu;

import org.andengine.entity.IEntity;

/**
 * The classes that implements the menus are compatible with the menus
 *
 * User: Ruben Rubio Rey
 * Date: 20/04/13
 * Time: 5:14 PM
 */
public interface MenuExtendable {

    public void attachChild(IEntity pEntity);

    public boolean detachChild(IEntity pEntity);

    public float getWidth();

}
