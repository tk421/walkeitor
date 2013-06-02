package org.rubenrr.walkeitor.manager.command.primitive;

import org.andengine.entity.IEntity;
import org.rubenrr.walkeitor.util.AStarPathModifier;

/**
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 8:21 PM
 */
abstract class CommandPathModifierListener implements AStarPathModifier.IAStarPathModifierListener {
    @Override
    public void onPathStarted(AStarPathModifier pPathModifier, IEntity pEntity) {
    }

    @Override
    public void onNextMoveLeft(AStarPathModifier aStarPathModifier, IEntity pEntity, int pIndex) {
    }

    @Override
    public void onNextMoveUp(AStarPathModifier aStarPathModifier, IEntity pEntity, int pIndex) {
    }

    @Override
    public void onNextMoveRight(AStarPathModifier aStarPathModifier, IEntity pEntity, int pIndex) {
    }

    @Override
    public void onNextMoveDown(AStarPathModifier aStarPathModifier, IEntity pEntity, int pIndex) {
    }

    @Override
    public void onPathWaypointStarted(AStarPathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
    }

    @Override
    public void onPathWaypointFinished(AStarPathModifier pPathModifier, IEntity pEntity, int pWaypointIndex) {
    }

    @Override
    public abstract void onPathFinished(AStarPathModifier pPathModifier, IEntity pEntity);
}
