package org.rubenrr.walkeitor.manager;

import android.util.Log;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.algorithm.path.Path;
import org.andengine.util.math.MathUtils;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.config.TileConfig;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.element.resource.Resource;
import org.rubenrr.walkeitor.element.unit.Unit;
import org.rubenrr.walkeitor.manager.action.Movement;
import org.rubenrr.walkeitor.manager.action.OccupiedTiles;
import org.rubenrr.walkeitor.util.AStarPathModifier;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all in-game elements (units, resources and buildings)
 *
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 5:19 PM
 */
public class GameManager {

    private static GameManager instance = null;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // Current scene status in the game
    private StatusConfig status = StatusConfig.NONE;

    // get the information about the elements that exists in the scene
    // exclude elements that are not constructed / built
    private List<Building> buildings = new ArrayList<Building>();
    private List<Resource> resources = new ArrayList<Resource>();
    private List<Unit> units = new ArrayList<Unit>();

    public void addBuilding(final Building building) {
        this.buildings.add(building);
    }

    public void addResource(final Resource resource) {
        this.resources.add(resource);
    }

    public void addUnit(final Unit unit) {
        this.units.add(unit);
    }

    public List<Building> getBuildings() {
        return this.buildings;
    }

    /**
     * Get resources filtering by subtype
     *
     * TODO filter by subtype
     *
     * @param subtype
     * @return
     */
    public List<TileLocatable> getResourcesBySubtype(String subtype) {

        List<TileLocatable> resourceSubtype = new ArrayList<TileLocatable>();

        for (Resource resource: this.resources ) {
            if (resource.getSubtype().equals(subtype)) {
                resourceSubtype.add(resource);
            }
        }

        return resourceSubtype;
    }

    // get the information about the selected elements in the scene
    private ArrayList<Unit> unitsSelected = new ArrayList<Unit>();

    /**
     * Unselect all selected units
     */
    public void unselectUnits() {
        for (Sprite unit : unitsSelected ) {

            // remove fade
            unit.setColor(0, 0, 0, 1f);
        }
        unitsSelected.clear();
    }

    public void selectUnit(final Unit unit) {

        // so far we can just select units, therefore we could unselect everything and keep going!
        this.unselectUnits();
        this.status = StatusConfig.UNIT_SELECTED;
        //unit.this
        Log.d("GameManager", "Unit selected: " + unit.toString());

        // set element look and feel "selected" fading it out
        unit.setColor(0, 0, 0, 0.30f);

        // set the element "selected"
        this.unitsSelected.add(unit);

    }

    /**
     * Get the game status
     * @return
     */
    public StatusConfig getStatus() {
        return status;
    }

    public void moveTo(float posX, float posY) {

        final int[] tileDimensions = TileConfig.TILE_SIZE.getTileDimensions();

        // we will only move if there are units selected
        if (this.status.equals(StatusConfig.UNIT_SELECTED)) {
            // Temporarily simple algorithm to move units.
            // TODO get speed based on distance and unit capabilities
            // TODO Several unit must support formation

            //hack: not sure why buildings are not accepted as TileLocatable
            List<TileLocatable> buildingLocatables = new ArrayList<TileLocatable>();
            for (Building building : this.buildings) {
                buildingLocatables.add(building);
            }

            for (Unit unit : unitsSelected ) {
                unit.clearMenu();
                Path path = Movement.generatePath(unit, buildingLocatables, posX,  posY);
                if (path != null ) { // TODO Better of to be handled with exceptions ?
                    unit.registerEntityModifier(new AStarPathModifier(2, path, tileDimensions));
                } else {
                    Log.w("Movement", "Unable to generate path to destination");
                }
            }

            this.unselectUnits();

        }
    }

    /**
     * Starts the construction process of a building
     * @param elementConfig
     */
    public void constructBuilding(ElementConfig elementConfig) {
        if ( ! elementConfig.getCategory().equals("building")) {
            throw new IllegalArgumentException("ElemnentConfig should be a building");
        }

        ElementConfig dependence = elementConfig.getDependence();
        if (  dependence != null ) {

        }

    }

}
