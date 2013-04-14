package org.rubenrr.walkeitor.manager.action;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.algorithm.path.IPathFinderMap;
import org.andengine.util.modifier.ease.EaseStrongIn;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.manager.SceneManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manges the occupied or the free tiles.
 */
 class OccupiedTiles implements IPathFinderMap {

    //TODO ArrayList here duplicates elements

    //Two ways to set the information:
    // 1) All free by default, but we specify with tiles are occupied
    private List<Integer> occuppiedRows    = new ArrayList<Integer>();
    private List<Integer> occuppiedColumns = new ArrayList<Integer>();

    // 1) All occupied by default, we specify with tiles are free
    private List<Integer> freeRows    = new ArrayList<Integer>();
    private List<Integer> freeColumns = new ArrayList<Integer>();

    // TODO parameter should be sprites
    OccupiedTiles(final List<Sprite> sprites ) {
        this.setOccupied(sprites);
    }

    @Override
    public boolean isBlocked(int pX, int pY, Object pEntity) {
        return this.isTileOccupied(pX, pY);
    }

    public void setOccupied(final List<Sprite> sprites) {
        this.setAllocation(sprites, false);
    }

    public void setFree( final List<Sprite> sprites ) {
        this.setAllocation(sprites, true);
    }

    private void setAllocation (final List<Sprite> sprites, Boolean isFree) {
        for (Sprite sprite: sprites) {
            final float fromX = sprite.getX();
            final float sizeX = sprite.getWidth();
            final float fromY = sprite.getY();
            final float sizeY = sprite.getHeight();

            final TMXTile tileFrom = SceneManager.getInstance().getTile(fromX, fromY);
            final TMXTile tileTo   = SceneManager.getInstance().getTile(fromX + sizeX, fromY + sizeY);

            // All sprites are square
            for(int column = tileFrom.getTileColumn(); column < tileTo.getTileColumn(); column = column+1) {
                for(int row = tileFrom.getTileRow(); row < tileTo.getTileRow(); row = row+1) {
                    Log.d("Movement/OccupiedTiles", "Adding (" + column + "," + row + "). Is Free ? " + isFree);
                    if (! isFree ) {
                        this.occuppiedColumns.add(column);
                        this.occuppiedRows.add(row);
                    } else {
                        this.freeColumns.add(column);
                        this.freeRows.add(row);
                    }

                    //Just for debug purposes to colour the tiles that are 'Occupied' or Free
                    SceneManager.getInstance().setDebugTileBackfground(row, column);
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


}
