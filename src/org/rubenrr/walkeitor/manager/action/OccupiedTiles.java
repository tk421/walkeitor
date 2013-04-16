package org.rubenrr.walkeitor.manager.action;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.algorithm.path.IPathFinderMap;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.SceneManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manges the occupied or the free tiles.
 */
 public class OccupiedTiles implements IPathFinderMap {

    //TODO ArrayList here duplicates elements (Use sets)

    //Two ways to set the information:
    // 1) All free by default, but we specify with tiles are occupied
    private List<Integer> occuppiedRows    = new ArrayList<Integer>();
    private List<Integer> occuppiedColumns = new ArrayList<Integer>();

    // 1) All occupied by default, we specify with tiles are free
    private List<Integer> freeRows    = new ArrayList<Integer>();
    private List<Integer> freeColumns = new ArrayList<Integer>();

    public OccupiedTiles() {

    }

    @Override
    public boolean isBlocked(int pX, int pY, Object pEntity) {
        return this.isTileOccupied(pX, pY);
    }

    public void setOccupied(final List<Sprite> sprites, ElementConfig elementConfig) {
        this.setAllocation(sprites, false, elementConfig);
    }

    public void setFree( final List<Sprite> sprites, ElementConfig elementConfig) {
        this.setAllocation(sprites, true, elementConfig);
    }

    private void setAllocation (final List<Sprite> sprites, Boolean isFree, ElementConfig elementConfig) {

        SceneManager.getInstance().getBackgroundTile().clearAll();

        for (Sprite sprite: sprites) {
            final float fromX = sprite.getX();
            final float sizeX = sprite.getWidth();
            final float fromY = sprite.getY();
            final float sizeY = sprite.getHeight();

            final TMXTile tileFrom = SceneManager.getInstance().getTile(fromX, fromY);
            final int tileToRow = tileFrom.getTileRow() + elementConfig.getTileRow();
            final int tileToColumn = tileFrom.getTileColumn() + elementConfig.getTileColumn();

            // All sprites are square
            for(int column = tileFrom.getTileColumn(); column < tileToColumn; column = column+1) {
                for(int row = tileFrom.getTileRow(); row < tileToRow; row = row+1) {
                    Log.d("Movement/OccupiedTiles", "Adding (" + column + "," + row + "). Is Free ? " + isFree);
                    if (! isFree ) {
                        this.occuppiedColumns.add(column);
                        this.occuppiedRows.add(row);
                    } else {
                        this.freeColumns.add(column);
                        this.freeRows.add(row);
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
            Log.d("Movement/OccupiedTiles", "Occupied algorithm" );
            columnOccupied = this.occuppiedColumns.contains(column);
            rowOccupied = this.occuppiedRows.contains(row);
        } else { // Everything occupied by default unless specified that is free
            Log.d("Movement/OccupiedTiles", "Free algorithm" );
            columnOccupied = !this.freeColumns.contains(column);
            rowOccupied = !this.freeRows.contains(row);
        }

        Log.d("Movement/OccupiedTiles", "(" + column + "," + row + ") -> (" + columnOccupied + "," + rowOccupied + ") -> " + (columnOccupied && rowOccupied) );

        return  columnOccupied && rowOccupied;
    }

    public void draw() {
        for (int row : this.occuppiedRows ) {
            for (int column: this.occuppiedColumns) {
            SceneManager.getInstance().getBackgroundTile().setTileBackground(row, column, 1, 0, 0, 0.2f);
            }
        }

        for (int row : this.freeRows   ) {
            for (int column: this.freeColumns) {
                SceneManager.getInstance().getBackgroundTile().setTileBackground(row, column, 0, 1, 0, 0.2f);
            }
        }

    }

}
