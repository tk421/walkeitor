package org.rubenrr.walkeitor.config;

/**
 * Define the status of the scene
 * NONE - There is no action on progress
 * UNIT_SELECTED - Unit has been selected and should be moved
 *
 *
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 6:02 PM
 */
public enum StatusConfig {
    NONE("None"),
    UNIT_SELECTED("Unit Selected");

    private final String name;

    private StatusConfig (final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
