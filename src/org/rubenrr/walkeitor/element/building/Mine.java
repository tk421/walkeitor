package org.rubenrr.walkeitor.element.building;

import android.util.Log;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.consumable.CrudeOil;
import org.rubenrr.walkeitor.element.resource.Resource;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.action.Buildable;
import org.rubenrr.walkeitor.manager.action.OccupiedTiles;
import org.rubenrr.walkeitor.production.ProductionStrategy;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.List;

/**
 * User: Ruben Rubio Rey
 * Date: 22/03/13
 * Time: 7:27 AM
 *
 * Extracts resources
 *
 */

public class Mine extends Building implements Buildable {



    private TimerHandler productionTimeHandler;

    // TODO Mines need to have limited capacity

    // resource where the mine is extracting from
    private Resource resource;

    public Mine(float pX, float pY, ElementConfig elementConfig, StatusConfig statusConfig) {
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

        //TODO remove hardcoded text
        List<TileLocatable> resourceOil = GameManager.getInstance().getResourcesBySubtype("oil");

        OccupiedTiles occupiedTiles = new OccupiedTiles();
        occupiedTiles.clearAll();
        occupiedTiles.setOccupiedAllBuildings();
        occupiedTiles.setFree(resourceOil);
        return occupiedTiles;

    }

    @Override
    public void constructionFinish() {
        // first of all, we have to detect over we are over
        this.resource = GameManager.getInstance().getResourceColidesWithMine(this, this.getElementConfig().getDependence());
        Log.d("Mine", "Mine depends on resource: " + this.resource);

        //this.startProduction();
        super.constructionFinish();
    }

}
