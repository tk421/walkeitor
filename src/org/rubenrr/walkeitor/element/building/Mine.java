package org.rubenrr.walkeitor.element.building;

import org.rubenrr.walkeitor.config.ConsumableConfig;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.action.Buildable;
import org.rubenrr.walkeitor.manager.action.OccupiedTiles;
import org.rubenrr.walkeitor.manager.action.building.ConsumableObserver;
import org.rubenrr.walkeitor.manager.action.building.ConsumableSubject;
import org.rubenrr.walkeitor.menu.building.OilMineMenu;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * User: Ruben Rubio Rey
 * Date: 22/03/13
 * Time: 7:27 AM
 *
 * Extracts resources
 *
 */


/*


            I AM PROGRAMMING HERE
            Creating Consumables, MINE will produce crudeOil




 */
public class Mine extends Building implements Buildable, ConsumableSubject {

    private HashSet<ConsumableObserver> consumableObserver = new HashSet<ConsumableObserver>();
    private List<ConsumableConfig> requiredConsumables;
    private ConsumableConfig producedConsumable;

    public Mine(float pX, float pY, ElementConfig elementConfig, StatusConfig statusConfig) {
        super(pX, pY, elementConfig, statusConfig);
        SceneManager.getInstance().getScene().registerTouchArea(this);
        this.setMenu(elementConfig.getMenuStrategy(), this);
        this.requiredConsumables = elementConfig.getConsumableRequired();
        this.producedConsumable = elementConfig.getConsumableProduce();

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

        List<TileLocatable> resourceOil = GameManager.getInstance().getResourcesBySubtype("oil");

        OccupiedTiles occupiedTiles = new OccupiedTiles();
        occupiedTiles.clearAll();
        occupiedTiles.setOccupiedAllBuildings();
        occupiedTiles.setFree(resourceOil);
        return occupiedTiles;

    }

    @Override
    public void registerObserver(ConsumableObserver consumableObserver) {
        this.consumableObserver.add(consumableObserver);
    }

    @Override
    public void removeObserver(ConsumableObserver consumableObserver) {
        this.consumableObserver.remove(consumableObserver);
    }

    @Override
    public void notifyObservers() {
        for (ConsumableObserver consumableObserver : this.consumableObserver) {
            // consumableObserver.update();
        }
    }
}
