package org.rubenrr.walkeitor.util;

/**
 * Defines an object that can be located
 * within the scene
 *
 * User: Ruben Rubio Rey
 * Date: 20/04/13
 * Time: 6:13 PM
 */
public interface TileLocatable {
    // get coordinates of the current tile position
    public int getTileColumn();
    public int getTileRow();

    //get Tile Size
    public int getColumnTileSize();
    public int getRowTileSize();
}
