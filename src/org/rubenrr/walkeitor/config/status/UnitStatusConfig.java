package org.rubenrr.walkeitor.config.status;

/**
 * User: Ruben Rubio Rey
 * Date: 2/06/13
 * Time: 5:19 PM
 */
public enum UnitStatusConfig {
    IDLE("Idle"),
    COMMAND_ON_PROGRESS("Executing command");

    private final String name;

    private UnitStatusConfig (final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean isIdle() {
        return (this.equals(UnitStatusConfig.IDLE));
    }

    public boolean isBusy() {
        return (this.equals(UnitStatusConfig.COMMAND_ON_PROGRESS));
    }

}
