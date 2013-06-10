package org.rubenrr.walkeitor.manager.command;

import org.rubenrr.walkeitor.element.unit.Unit;

/**
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 4:23 PM
 */
public interface Command {
    public boolean execute();
    public void setUnit(Unit unit);
}
