package org.rubenrr.walkeitor.manager.util;

import org.andengine.entity.IEntity;

/**
 * The classes that implements the menus are compatible with the menus
 *
 * User: Ruben Rubio Rey
 * Date: 20/04/13
 * Time: 5:14 PM
 */
public interface SpriteAttachable {

    public void attachChild(IEntity pEntity);

    public boolean detachChild(IEntity pEntity);

}
