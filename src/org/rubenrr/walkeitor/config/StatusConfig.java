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
    UNIT_SELECTED("Unit Selected"),
    SET_LOCATION("Location is being set for this object");

    private final String name;

    private StatusConfig (final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Boolean isSetLocation() {
        return (this.equals(StatusConfig.SET_LOCATION));
    }

    public Boolean isNone() {
        return (this.equals(StatusConfig.NONE));
    }

    public Boolean isUnitSelected() {
        return (this.equals(StatusConfig.UNIT_SELECTED));
    }


}
