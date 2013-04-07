package org.rubenrr.walkeitor.manager;

import android.util.Log;
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
public class ElementManager {

    private static ElementManager instance = null;

    public static ElementManager getInstance() {
        if (instance == null) {
            instance = new ElementManager();
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

        Log.d("ElementManager", "Unit selected: " + unit.toString());

        // set element look and feel "selected" fading it out
        unit.setColor(0, 0, 0, 0.30f);

        // set the element "selected"
        this.unitsSelected.add(unit);

    }

}
