package org.rubenrr.walkeitor.element.building;

import android.util.Log;
import org.andengine.engine.handler.timer.TimerHandler;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.resource.Resource;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.action.Buildable;
import org.rubenrr.walkeitor.manager.action.OccupiedTiles;
import org.rubenrr.walkeitor.manager.action.building.CreateUtilizeConsumableObserver;
import org.rubenrr.walkeitor.manager.action.building.CreateUtilizeConsumableSubject;
import org.rubenrr.walkeitor.util.TileLocatable;

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

public class Mine extends Building implements Buildable, CreateUtilizeConsumableSubject {

    private HashSet<CreateUtilizeConsumableObserver> createUtilizeConsumableObserver = new HashSet<CreateUtilizeConsumableObserver>();
    private ConsumableConfig requiredConsumable;
    private ConsumableConfig producedConsumable;

    // resource related with the mine
    private Resource resource;

    public Mine(float pX, float pY, ElementConfig elementConfig, StatusConfig statusConfig) {
        super(pX, pY, elementConfig, statusConfig);
        SceneManager.getInstance().getScene().registerTouchArea(this);
        this.setMenu(elementConfig.getMenuStrategy(), this);
        this.requiredConsumable = elementConfig.getConsumableRequire();
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

        /*
        VOY POR AQUI !!!!
        PUTTING THE TIMERS RELATED WITH THE PRODUCTION
        AT SCENE MANAGER registerUpdateHandler

        scene.registerUpdateHandler(new TimerHandler(1 / 20.0f, true, new ITimerCallback() {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler) {
                elapsedText.setText("Seconds elapsed: " + ChangeableTextExample.this.mEngine.getSecondsElapsedTotal());
                fpsText.setText("FPS: " + fpsCounter.getFPS());
            }
        }));
        */

        super.constructionFinish();
    }

    @Override
    public void registerObserver(CreateUtilizeConsumableObserver createUtilizeConsumableObserver) {
        this.createUtilizeConsumableObserver.add(createUtilizeConsumableObserver);
    }

    @Override
    public void removeObserver(CreateUtilizeConsumableObserver createUtilizeConsumableObserver) {
        this.createUtilizeConsumableObserver.remove(createUtilizeConsumableObserver);
    }

    @Override
    public void notifyCreateUtilizeObservers() {
        for (CreateUtilizeConsumableObserver createUtilizeConsumableObserver : this.createUtilizeConsumableObserver) {
            // createUtilizeConsumableObserver.update();
        }
    }
}
