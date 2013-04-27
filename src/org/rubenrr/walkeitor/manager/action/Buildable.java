package org.rubenrr.walkeitor.manager.action;

/**
 * User: Ruben Rubio Rey
 * Date: 27/04/13
 * Time: 6:29 PM
 */
public interface Buildable {

    /**
     * Visually draw the tiles that are able to build and the ones that
     * are not able to build
     */
    public void drawBuildableTiles();

    /**
     * Knowing the current position of the object, check if we are able to
     * build this object
     * @return
     */
    public boolean isBuildableInResource();
}
