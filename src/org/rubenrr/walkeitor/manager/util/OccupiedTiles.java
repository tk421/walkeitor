package org.rubenrr.walkeitor.manager.util;

import android.util.Log;
import org.andengine.util.algorithm.path.IPathFinderMap;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.*;

/**
 * Manges the occupied or the free tiles.
 */
 public class OccupiedTiles implements IPathFinderMap {

    //Two ways to set the information:
    // 1) All free by default, but we specify with tiles are occupied
    private Set<TilePoint> occupiedTiles = new HashSet<TilePoint>();

    // 1) All occupied by default, we specify with tiles are free
    private Set<TilePoint> freeTiles = new HashSet<TilePoint>();

    private List<TileLocatable> sprites;

    // TODO don't like this constructor too much, as sets null by default
    // all cases that calls this constructor it also uses setOccupiedAllBuildings
    // this is very likely to be the default way to pre-fill this.sprites
    public OccupiedTiles() {
        this(null);
    }

    public OccupiedTiles(List<TileLocatable> sprites) {
        this.sprites = sprites;
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

                // If we have values for the free algorithm we use it
                if (!this.freeTiles.isEmpty()) {
                    if ( this.isTileFree(column, row)) {
                        this.setTileGreen(column, row);
                    } else {
                        this.setTileRed(column, row);
                        isFree = false;
                    }
                }

                // we always use the occupied algorithm
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
    public boolean isBlocked(int column, int row, Object pEntity) {
        return this.isTileOccupied(column, row);
    }

    public void setOccupiedAllBuildings() {
        List<TileLocatable> buildingLocatables = new ArrayList<TileLocatable>();
        List<Building> buildings = GameManager.getInstance().getBuildings();
        for (Building building : buildings) {
            buildingLocatables.add(building);
        }
        this.setOccupied(buildingLocatables);
    }

    public void setOccupied(final List<TileLocatable> sprites) {
        this.sprites = sprites;
        this.setAllocation(false);
    }

    public void setFree( final List<TileLocatable> sprites) {
        this.sprites = sprites;
        this.setAllocation(true);
    }

    static public void clearAll() {
        SceneManager.getInstance().getBackgroundTile().clearAll();
    }

    private void setAllocation (Boolean isFree) {

        for (TileLocatable sprite: this.sprites) {
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

    /**
     * Given an Origin and a Destination, and a list of Blocking Elements
     * return the closest free tile
     *
     * @param origin
     * @param destination
     * @return
     */
    public TilePoint getClosestFreeTile(TilePoint origin, TilePoint destination) {

        TilePoint finalDestination = null;

        Log.d("OccupiedTiles/FindDestination", "Checking destination point: " + destination);
        if ( this.isTileFree(destination.getColumn(), destination.getRow()) ) {
            Log.d("OccupiedTiles/FindDestination", "All good, destination is free");
            finalDestination = destination;
        } else {
            Log.d("OccupiedTiles/FindDestination", "Not good, destination is not free");
            TileLocatable collidedTilePoint = this.getCollideTileLocatable(destination);
            List<TilePoint> boundaries = this.getBoundaries(collidedTilePoint);
            DistanceOrderedTilePoint orderedPoints = new DistanceOrderedTilePoint(origin);
            for (TilePoint point: boundaries) {
                orderedPoints.add(point);
            }
            Iterator iterator;
            iterator = orderedPoints.iterator();

            while (iterator.hasNext()) {
                TilePoint evaluateTilePoint = (TilePoint)iterator.next();
                if (this.isTileFree(evaluateTilePoint.getColumn(), evaluateTilePoint.getRow())) {
                    finalDestination = evaluateTilePoint;
                    break;
                }

            }

        }

        if (finalDestination == null) {
            Log.w("OccupiedTiles/FindDestination", "Unable to find destination for point " + destination.toString());
        }

        return finalDestination;
    }


    /**
     * Find the tileLocatable that collides with the given point
     *
     * @param point
     * @return
     */
    private TileLocatable getCollideTileLocatable(TilePoint point) {

        TileLocatable collideTileLocatable = null;

        for (TileLocatable sprite: this.sprites) {
            final int tileFromRow = sprite.getTileRow();
            final int tileFromColumn = sprite.getTileColumn();

            final int tileToRow = tileFromRow  + sprite.getRowTileSize();
            final int tileToColumn = tileFromColumn  + sprite.getColumnTileSize();

            // All sprites are square
            for(int column = tileFromColumn; column < tileToColumn; column = column+1) {
                for(int row = tileFromRow; row < tileToRow; row = row+1) {
                    TilePoint tilePoint = new TilePoint(column, row);
                    if (tilePoint.equals(point)) {
                        collideTileLocatable = sprite;
                        break;
                    }
                }
            }
        }

        return collideTileLocatable;
    }


    /**
     * Calculate a set of TilePoints that will compose the
     * boundaries of the tileLocatable
     *
     * @param tileLocatable
     * @return
     */
    private List<TilePoint> getBoundaries(TileLocatable tileLocatable) {

        List<TilePoint> boundaries = new ArrayList<TilePoint>();

        final int tileFromRow = tileLocatable.getTileRow();
        final int tileFromColumn = tileLocatable.getTileColumn();

        final int tileToRow = tileFromRow  + tileLocatable.getRowTileSize();
        final int tileToColumn = tileFromColumn  + tileLocatable.getColumnTileSize();

        for(int column = tileFromColumn; column < tileToColumn; column = column+1) {
            // top line
            boundaries.add(new TilePoint(column, tileFromRow - 1));

            //bottom line
            boundaries.add(new TilePoint(column, tileToRow));
        }

        for(int row = tileFromRow; row < tileToRow; row = row+1) {
            // left
            boundaries.add(new TilePoint(tileFromColumn - 1, row));

            // right
            boundaries.add(new TilePoint(tileToColumn, row));
        }

        // corner top-left
        boundaries.add(new TilePoint(tileFromColumn -1, tileFromRow - 1));

        // corner top-right
        boundaries.add(new TilePoint(tileToColumn, tileFromRow - 1));

        // corner bottom-left
        boundaries.add(new TilePoint(tileFromColumn - 1, tileToRow ));

        // corner bottom-right
        boundaries.add(new TilePoint(tileToColumn, tileToRow ));


        for (TilePoint boundary : boundaries ) {
            this.setTileOrange(boundary);
        }


        return boundaries;

    }


    /**
     * Using the free algorithm: By default everything is occupied but the tiles
     * that are marked
     */
    public boolean isTileFree(TilePoint tilePoint) {
        Boolean isFree;
        if (this.freeTiles.isEmpty()) { // Select the Free algorithm or the occupied algorithm
            isFree = !this.occupiedTiles.contains(tilePoint);
        } else {
            isFree = this.freeTiles.contains(tilePoint);
        }
        Log.d("OccupiedTiles/FindDestination", "isTileFree " + tilePoint + "? " + isFree);
        return isFree;
    }


    public boolean isTileFree (final int column, final int row) {
        final TilePoint tilePoint = new TilePoint(column, row);
        return this.isTileFree(tilePoint);
    }


    /**
     * Using the occupied algorithm: By default everything is free but the tiles
     * that are marked
     */
    public boolean isTileOccupied(int column, int row) {
        final TilePoint tilePoint = new TilePoint(column, row);
        return  this.occupiedTiles.contains(tilePoint);
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

    private void setTileOrange(TilePoint point) {
        this.setTileOrange(point.getColumn(), point.getRow());
    }

    private void setTileOrange(int column, int row) {
        SceneManager.getInstance().getBackgroundTile().setTileBackground(column, row, 1, 0.5f, 0, 0.2f);
    }



}
