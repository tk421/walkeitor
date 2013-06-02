package org.rubenrr.walkeitor.manager.command;

import org.rubenrr.walkeitor.manager.command.primitive.PrimitiveCommand;

/**
 *
 * IN PROGRESSS
 *
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 5:39 PM
 */
public interface Commandable {

    void addCommand(PrimitiveCommand command);

    void startExecuteCommands();

    public void executeCommand();

    public void nextCommand();

}
