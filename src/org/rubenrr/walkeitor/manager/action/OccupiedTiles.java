package org.rubenrr.walkeitor.manager.action;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.algorithm.path.IPathFinderMap;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.manager.SceneManager;

import java.util.ArrayList;
import java.util.List;

// Object that contains information about the Occupied Tiles
 class OccupiedTiles implements IPathFinderMap {

    private List<Integer> occuppiedRows    = new ArrayList<Integer>();
    private List<Integer> occuppiedColumns = new ArrayList<Integer>();

    // TODO parameter should be sprites
    OccupiedTiles(final List<Building> sprites ) {
        this.setOccupied(sprites);
    }

    @Override
    public boolean isBlocked(int pX, int pY, Object pEntity) {
        return this.isTileOccupied(pX, pY);
    }

    public void setOccupied(final List<Building> sprites) {
        for (Sprite sprite: sprites) {
            final float fromX = sprite.getX();
            final float sizeX = sprite.getWidth();
            final float fromY = sprite.getY();
            final float sizeY = sprite.getHeight();

            final TMXTile tileFrom = SceneManager.getInstance().getTile(fromX, fromY);
            final TMXTile tileTo   = SceneManager.getInstance().getTile(fromX + sizeX, fromY + sizeY);

            // All sprites are square
            for(int x = tileFrom.getTileColumn(); x < tileTo.getTileColumn(); x = x+1) {
                for(int y = tileFrom.getTileRow(); y < tileTo.getTileRow(); y = y+1) {
                    this.occuppiedColumns.add(x);
                    this.occuppiedRows.add(y);

                    //Just for debug purposes to colour the tiles that are 'Occupied'
                    //SceneManager.getInstance().setDebugTileBackfground(y, x);
                }
            }
        }
    }

    public boolean isTileOccupied(int column, int row) {

        Boolean columnOccupied = this.occuppiedColumns.contains(column);
        Boolean rowOccupied = this.occuppiedRows.contains(row);

        Log.d("isTileOccupied", "(" + column + "," + row + ") -> (" + columnOccupied + "," + rowOccupied + ") -> " + (columnOccupied && rowOccupied) );

        return  columnOccupied && rowOccupied;
    }


}
