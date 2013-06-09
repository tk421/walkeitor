package org.rubenrr.walkeitor.element.building;

import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.status.StatusConfig;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.util.Buildable;
import org.rubenrr.walkeitor.manager.util.OccupiedTiles;

/**
 * User: Ruben Rubio Rey
 * Date: 22/03/13
 * Time: 7:29 AM
 *
 * Transforms resources to create products
 *
 */
public class Refinery  extends Building implements Buildable {
    public Refinery(float pX, float pY, ElementConfig elementConfig, StatusConfig statusConfig) {
        super(pX, pY, elementConfig, statusConfig);
        SceneManager.getInstance().getScene().registerTouchArea(this);
        this.setMenu(elementConfig.getMenuStrategy(), this);

        // if is setLocation we can get ready to locate the building
        // in the map
        if (this.getStatusConfig().isSetLocation()) {
            this.drawBuildableTiles();
            this.setDragAndDropLocation();
        }
    }


    public void drawBuildableTiles() {
        OccupiedTiles occupiedTiles = this.getBuildableTiles();
        occupiedTiles.draw();
    }

    public boolean isBuildableInResource() {
        OccupiedTiles occupiedTiles = this.getBuildableTiles();
        return occupiedTiles.isInFreeTiles(this);
    }

    private OccupiedTiles getBuildableTiles() {
        OccupiedTiles.clearAll();
        OccupiedTiles occupiedTiles = new OccupiedTiles();
        occupiedTiles.setOccupiedAllBuildings();
        return occupiedTiles;
    }


}
