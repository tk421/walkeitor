package org.rubenrr.walkeitor.manager.action;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.algorithm.path.IPathFinderMap;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Manges the occupied or the free tiles.
 */
 public class OccupiedTiles implements IPathFinderMap {

    /**
     * TODO What was I thinking ? This algorithm can't work with multiple buildings
     * TODO I have to create the concept of TilePoint.
     */



    //Two ways to set the information:
    // 1) All free by default, but we specify with tiles are occupied
    private Set<Integer> occuppiedRows    = new HashSet<Integer>();
    private Set<Integer> occuppiedColumns = new HashSet<Integer>();

    // 1) All occupied by default, we specify with tiles are free
    private Set<Integer> freeRows    = new HashSet<Integer>();
    private Set<Integer> freeColumns = new HashSet<Integer>();

    public OccupiedTiles() {

    }

    public Set<Integer> getRows(Boolean isFree) {
        if ( isFree ) {
            return this.freeRows;
        } else {
            return this.occuppiedRows;
        }
    }

    public Set<Integer> getColumns(Boolean isFree) {
        if ( isFree ) {
            return this.freeColumns;
        } else {
            return this.occuppiedColumns;
        }
    }

    public boolean isInFreeTiles(Sprite sprite) {


        return false;
    }

    @Override
    public boolean isBlocked(int pX, int pY, Object pEntity) {
        return this.isTileOccupied(pX, pY);
    }

    public void setOccupied(final List<TileLocatable> sprites) {
        this.setAllocation(sprites, false);
    }

    public void setFree( final List<TileLocatable> sprites) {
        this.setAllocation(sprites, true);
    }

    public void clearAll() {
        SceneManager.getInstance().getBackgroundTile().clearAll();
    }

    private void setAllocation (final List<TileLocatable> sprites, Boolean isFree) {

        for (TileLocatable sprite: sprites) {
            final int tileFromRow = sprite.getTileRow();
            final int tileFromColumn = sprite.getTileColumn();

            final int tileToRow = tileFromRow  + sprite.getRowTileSize();
            final int tileToColumn = tileFromColumn  + sprite.getColumnTileSize();

            Log.d("Movement/OccupiedTiles", "Sprite " + sprite.toString() + "FROM, (" + tileFromColumn + "," + tileFromRow + ") TO (" + tileToColumn + "," + tileToRow + ")");

            // All sprites are square
            for(int column = tileFromColumn; column < tileToColumn; column = column+1) {
                for(int row = tileFromRow; row < tileToRow; row = row+1) {
                    Log.d("Movement/OccupiedTiles", "Adding (" + column + "," + row + "). Is Free ? " + isFree);
                    if (isFree ) {
                        this.freeColumns.add(column);
                        this.freeRows.add(row);
                    } else {
                        this.occuppiedColumns.add(column);
                        this.occuppiedRows.add(row);
                    }
                }
            }
        }
    }


    //private void addToFreeOrOccupied( int)

    public boolean isTileOccupied(int column, int row) {

        Boolean columnOccupied;
        Boolean rowOccupied;

        if ( ! this.occuppiedColumns.isEmpty() ||  this.occuppiedRows.isEmpty()) { // Everything free by default
                                                                                  // unless specified that is occupied
            //Log.d("Movement/OccupiedTiles", "Occupied algorithm" );
            columnOccupied = this.occuppiedColumns.contains(column);
            rowOccupied = this.occuppiedRows.contains(row);
        } else { // Everything occupied by default unless specified that is free
            //Log.d("Movement/OccupiedTiles", "Free algorithm" );
            columnOccupied = !this.freeColumns.contains(column);
            rowOccupied = !this.freeRows.contains(row);
        }

        //Log.d("Movement/OccupiedTiles", "(" + column + "," + row + ") -> (" + columnOccupied + "," + rowOccupied + ") -> " + (columnOccupied && rowOccupied) );

        return  columnOccupied && rowOccupied;
    }

    public void draw() {
        this.clearAll();

        for (int row : this.occuppiedRows ) {
            for (int column: this.occuppiedColumns) {
                SceneManager.getInstance().getBackgroundTile().setTileBackground(row, column, 1, 0, 0, 0.2f);
                Log.d("Movement/OccupiedTiles", "Drawing occupied (" + column + "," + row + ")");
            }
        }

        for (int row : this.freeRows   ) {
            for (int column: this.freeColumns) {
                SceneManager.getInstance().getBackgroundTile().setTileBackground(row, column, 0, 1, 0, 0.2f);
            }
        }

    }

}
