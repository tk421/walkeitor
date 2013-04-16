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
import org.rubenrr.walkeitor.util.AStarPathModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all in-game elements (units, resources and buildings)
 *
 * TODO The connotation <? extends Building> does not work in the ArrayList
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
    private List<Building> buildings = new ArrayList<Building>();
    private List<Resource> resources = new ArrayList<Resource>();
    private List<Unit> units = new ArrayList<Unit>();

    // TODO not convinced with the argument here
    public void addBuilding(final Building building) {
        this.buildings.add(building);
    }

    public void addResource(final Resource resource) {
        this.resources.add(resource);
    }

    public void addUnit(final Unit unit) {
        this.units.add(unit);
    }


    /**
     * Get resources filtering by subtype
     *
     * @param subtype
     * @return
     */
    public List<Sprite> getSpriteSubtype(String subtype) {

        List<Sprite> resourceSubtype = new ArrayList<Sprite>();

        for (Resource resource: this.resources ) {
            resourceSubtype.add(resource);
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

            //hack: not sure why buildings are not accepted as sprites
            List<Sprite> buildingSprites = new ArrayList<Sprite>();
            for (Building building : this.buildings) {
                buildingSprites.add(building);
            }

            for (Unit unit : unitsSelected ) {
                unit.clearMenu();
                Path path = Movement.generatePath(unit, buildingSprites, posX,  posY);
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
