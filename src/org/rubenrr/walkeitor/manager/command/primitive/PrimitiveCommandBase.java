package org.rubenrr.walkeitor.manager.command.primitive;

import org.andengine.entity.IEntity;
import org.rubenrr.walkeitor.element.unit.Unit;
import org.rubenrr.walkeitor.util.AStarPathModifier;

/**
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 8:29 PM
 */
public abstract class PrimitiveCommandBase implements PrimitiveCommand {

    private Unit unit;

    protected void setUnit(Unit unit) {
        this.unit = unit;
    }

    protected Unit getUnit() {
        return unit;
    }

    protected AStarPathModifier.IAStarPathModifierListener getPathListeners() {
        return new CommandPathModifierListener() {
            @Override
            public void onPathFinished(AStarPathModifier pPathModifier, IEntity pEntity) {
                PrimitiveCommandBase.this.unit.nextCommand();
            }
        };
    }
}
