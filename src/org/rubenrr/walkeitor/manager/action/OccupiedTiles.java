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

    //Two ways to set the information:
    // 1) All free by default, but we specify with tiles are occupied
    private Set<TilePoint> occupiedTiles = new HashSet<TilePoint>();

    // 1) All occupied by default, we specify with tiles are free
    private Set<TilePoint> freeTiles = new HashSet<TilePoint>();

    public OccupiedTiles() {

    }

    public boolean isInFreeTiles(TileLocatable sprite) {

        // clean and start over again
        this.draw();

        final int tileFromRow = sprite.getTileRow();
        final int tileFromColumn = sprite.getTileColumn();

        final int tileToRow = tileFromRow  + sprite.getRowTileSize();
        final int tileToColumn = tileFromColumn  + sprite.getColumnTileSize();

        Boolean isFree = true;
        for(int column = tileFromColumn; column < tileToColumn; column = column+1) {
            for(int row = tileFromRow; row < tileToRow; row = row+1) {
                if ( this.isTileOccupied(column, row) ) {
                    this.setTileRed(column, row);
                    isFree = false;
                } else {
                    this.setTileGreen(column, row);
                }
            }
        }

        Log.d("OilMineMenu", "Return: " + isFree);
        return isFree;
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
                    TilePoint tilePoint = new TilePoint(column, row);
                    if (isFree ) {
                        this.freeTiles.add(tilePoint);
                    } else {
                        this.occupiedTiles.add(tilePoint);
                    }
                }
            }
        }
    }


    //private void addToFreeOrOccupied( int)

    public boolean isTileOccupied(int column, int row) {

        Boolean isOccupied;
        final TilePoint tilePoint = new TilePoint(column, row);
        if ( ! this.occupiedTiles.isEmpty() ) { // Everything free by default
            isOccupied = this.occupiedTiles.contains(tilePoint);
        } else { // Everything occupied by default unless specified that is free
            isOccupied = this.freeTiles.contains(tilePoint);
        }
        //Log.d("Movement/OccupiedTiles", "(" + column + "," + row + ") -> (" + columnOccupied + "," + rowOccupied + ") -> " + (columnOccupied && rowOccupied) );
        return  isOccupied;
    }

    public void draw() {
        this.clearAll();

        for (TilePoint tilePoint : this.occupiedTiles ) {
            this.setTileRed(tilePoint.getColumn(), tilePoint.getRow());
            Log.d("Movement/OccupiedTiles", "Drawing occupied (" + tilePoint.getColumn() + "," + tilePoint.getRow() + ")");
        }

        for (TilePoint tilePoint: this.freeTiles) {
            this.setTileGreen(tilePoint.getColumn(), tilePoint.getRow());
        }

    }

    private void setTileRed(int column, int row) {
        SceneManager.getInstance().getBackgroundTile().setTileBackground(column, row, 1, 0, 0, 0.2f);
    }

    private void setTileGreen(int column, int row) {
        SceneManager.getInstance().getBackgroundTile().setTileBackground(column, row, 0, 1, 0, 0.2f);
    }


}
