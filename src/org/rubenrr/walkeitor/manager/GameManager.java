package org.rubenrr.walkeitor.manager;

import android.util.Log;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.unit.Unit;

import java.util.ArrayList;

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
    private ArrayList<Sprite> buildings = new ArrayList<Sprite>();
    private ArrayList<Sprite> resources = new ArrayList<Sprite>();
    private ArrayList<Sprite> units = new ArrayList<Sprite>();

    // TODO not convinced with the argument here
    public void addBuilding(final Sprite building) {
        this.buildings.add(building);
    }

    public void addResource(final Sprite resource) {
        this.buildings.add(resource);
    }

    public void addUnit(final Sprite unit) {
        this.buildings.add(unit);
    }

    // get the information about the selected elements in the scene
    private ArrayList<Sprite> unitsSelected = new ArrayList<Sprite>();

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

        /**
         * I AM PROGRAMMING HERE, WE HAVE UNITS SELECTED AND NOW WE ARE GOING TO MOVE THEM
         */

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
        // we will only move if there are units selected
        if (this.status.equals(StatusConfig.UNIT_SELECTED)) {
            // Temporarily simple algorithm to move units.
            // TODO Improve it, dodge buildings and units and create paths.
            // TODO get speed based on distance and unit capabilities
            // TODO Unit should move to selected Tile
            // TODO Several unit must support formation
            for (Sprite unit : unitsSelected ) {
                unit.registerEntityModifier(new MoveModifier(1, unit.getX(), posX, unit.getY(), posY));
            }

        }
    }

}
