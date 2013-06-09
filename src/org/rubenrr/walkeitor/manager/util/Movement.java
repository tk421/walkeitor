package org.rubenrr.walkeitor.manager.util;

import android.util.Log;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.util.algorithm.path.ICostFunction;
import org.andengine.util.algorithm.path.IPathFinderMap;
import org.andengine.util.algorithm.path.Path;
import org.andengine.util.algorithm.path.astar.AStarPathFinder;
import org.andengine.util.algorithm.path.astar.EuclideanHeuristic;
import org.rubenrr.walkeitor.element.unit.Unit;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.List;

/**
 * Static class that manages the movement of Objects
 *
 * User: Ruben Rubio Rey
 * Date: 10/04/13
 * Time: 8:41 PM
 */
public final class Movement {

    /**
     * Generate the path between two points, having buildings that might block the path
     *
     *
     *
     * @param unit
     * @param sprites elements that are present
     * @param posX
     * @param posY
     * @return
     */
    static public Path generatePath( final Unit unit, final List<TileLocatable> sprites, final float posX, final float posY) {

        // translate posX and poxY to Tiles
        TMXTile intendedDestination = SceneManager.getInstance().getTile(posX, posY);

        //create objects needed for the AStar
        AStarPathFinder<TMXLayer> aStar = new AStarPathFinder<TMXLayer>();
        EuclideanHeuristic<TMXLayer> heuristic = new EuclideanHeuristic<TMXLayer>();

        final OccupiedTiles occupiedTiles = new OccupiedTiles(sprites);
        // TODO This is definitely very weird
        // One solution might be to get it from Game Manager
        occupiedTiles.setOccupied(sprites);
        occupiedTiles.draw();
        IPathFinderMap<TMXLayer> pathMap = occupiedTiles;

        // check if the destination tile is occupied.
        TilePoint destination = Movement.findClosestFreeTile(unit, occupiedTiles, intendedDestination);


        ICostFunction<TMXLayer>  costCallback = new ICostFunction<TMXLayer>() {
            @Override
            public float getCost(IPathFinderMap<TMXLayer> pPathFinderMap,
                                 int pFromX, int pFromY, int pToX, int pToY, TMXLayer pEntity) {

                // Read the cost attribute from the tile at the given position
                // TMXProperty cost = pEntity.getTMXTile(pFromX, pFromY).getTMXTileProperties(mMap.getMap()).get(0);
                return 1;
            }
        };

        // Find the path
        // Param 1: IPathFinderMap defined above
        // Param 2,3: min X and min Y values
        // Param 4,5: max X and max Y values
        // Param 6: The TMXLayer the road is defined on
        // Param 7,8: Starting column and row positions
        // Param 9,10: Ending column and row positions
        // Param 11: do not allow diagonal movement
        // Param 12: Heuristics defined above
        // Param 13: CostFunction defined above
        // Param 14: (Optional) max cost of a path
        //Log.d("Movement/generatePath", "pathMap (" + pathMap + ")");
        //Log.d("Movement/generatePath", "maxX(" + SceneManager.getInstance().getTmxTiledMap().getTileColumns() + ")");
        //Log.d("Movement/generatePath", "maxY(" + SceneManager.getInstance().getTmxTiledMap().getTileRows() + ")");
        //Log.d("Movement/generatePath", "TMXLayer(" + SceneManager.getInstance().getTmxTiledMap().getTMXLayers().get(0) + ")");
        //Log.d("Movement/generatePath", "start Column(" + unit.getTileSizeColumn() + ")");
        //Log.d("Movement/generatePath", "start row(" + unit.getTileSizeRow() + ")");
        //Log.d("Movement/generatePath", "destination column (" + destination.getTileSizeColumn() + ")");
        //Log.d("Movement/generatePath", "destination row (" + destination.getTileSizeRow() + ")");
        //Log.d("Movement/generatePath", "heuristic (" + heuristic + ")");
        //Log.d("Movement/generatePath", "costCallBack(" + costCallback + ")");
        Path path = aStar.findPath(pathMap, 0, 0,
                SceneManager.getInstance().getTmxTiledMap().getTileColumns(), SceneManager.getInstance().getTmxTiledMap().getTileRows(),
                SceneManager.getInstance().getTmxTiledMap().getTMXLayers().get(0),
                unit.getTileColumn(), unit.getTileRow(), destination.getColumn(), destination.getRow(), true, heuristic, costCallback);

        Log.d("Movement/generatePath", "Size (" + SceneManager.getInstance().getTmxTiledMap().getTileColumns() + "," + SceneManager.getInstance().getTmxTiledMap().getTileRows() + ")");
        Log.d("Movement/generatePath", "From (" + unit.getTileColumn() + "," + unit.getTileRow() + ")");
        Log.d("Movement/generatePath", "To (" + destination.getColumn() + "," +  destination.getRow() + ")");

        //Log.d("Movement", "Path " + path.toString());

        return path;


    }

    /**
     * Finds the closest free tile to a destination
     *
     * @param unit
     * @param occupiedTiles
     * @param originalDestination
     * @return
     */
    static TilePoint findClosestFreeTile(final Unit unit, final OccupiedTiles occupiedTiles, TMXTile originalDestination) {
        TilePoint finalDestination;
        TilePoint origin = new TilePoint(unit.getTileColumn(), unit.getTileRow());
        TilePoint destination = new TilePoint(originalDestination.getTileColumn(), originalDestination.getTileRow());


        if ( occupiedTiles.isTileFree(destination) ) {
            finalDestination = destination;
        } else {
            // get object that collides
            finalDestination = occupiedTiles.getClosestFreeTile(origin, destination);
        }
        return finalDestination;
    }



}
