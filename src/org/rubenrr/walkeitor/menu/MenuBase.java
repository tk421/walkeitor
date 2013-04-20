package org.rubenrr.walkeitor.menu;

import org.andengine.engine.Engine;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;
import org.rubenrr.walkeitor.manager.SceneManager;

import java.util.ArrayList;

/**
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 6:30 PM
 */
public abstract class MenuBase implements MenuStrategy {

    // the sprite that we are generating the menu from
    private MenuExtendable sprite;

    // list of all elements are generated as menu
    private ArrayList<Entity> menuEntities = new ArrayList<Entity>();

    protected MenuBase(MenuExtendable sprite) {
        this.sprite = sprite;
    }

    protected void addEntity(final Entity entity) {
        this.sprite.attachChild(entity);
        menuEntities.add(entity);
    }

    @Override
    public void clear() {
        final Engine.EngineLock engineLock = SceneManager.getInstance().getEngineLock();
        engineLock.lock();
        for (final Entity menuEntity: menuEntities) {
            this.sprite.detachChild(menuEntity);
        }
        menuEntities.clear();
        engineLock.unlock();
    }
}
