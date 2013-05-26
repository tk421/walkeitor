package org.rubenrr.walkeitor.activity;

import android.util.Log;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.Constants;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.building.City;
import org.rubenrr.walkeitor.element.resource.Ore;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;

/**
 * User: Ruben Rubio Rey
 * Date: 23/03/13
 * Time: 4:20 PM
 */

public class MainScene extends TiledPinchZoomBaseActivity {

    @Override
    public void onCreateResources() {
        SceneManager.getInstance().setTextureManager(this.getTextureManager());
        SceneManager.getInstance().setFontManger(this.getFontManager());
        SceneManager.getInstance().setAssetManager(this.getAssets());
        SceneManager.getInstance().setEngineLock(this.mEngine.getEngineLock());
        SceneManager.getInstance().loadBitmap();
        SceneManager.getInstance().loadFont();

        HUD hud = new HUD();
        SceneManager.getInstance().setHud(hud);
        this.setHUD(hud);


    }

    @Override
    public Scene onCreateScene() {

        Scene scene = super.onCreateScene();
        SceneManager.getInstance().setScene(scene);
        SceneManager.getInstance().setVertexBufferObjectManager(this.getVertexBufferObjectManager());
        SceneManager.getInstance().setTmxLayer(this.getTmxLayer());
        SceneManager.getInstance().setTmxTiledMap(this.getTmxTiledMap());
        new City(this.getCameraWidth() / 2, this.getCameraHeight() / 2, ElementConfig.BUILDING_CITY, StatusConfig.READY);
        new Ore(100, 100, ElementConfig.RESOURCE_OIL);
        return scene;
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

        switch (pSceneTouchEvent.getAction()) {
            case TouchEvent.ACTION_DOWN:
                SceneManager.getInstance().setTileRectangle(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                // If unit is selected then let's move it
                Log.d("Walkeitor/TouchEvent", "ACTION_DOWN event in onSceneTouchEvent");
                if ( GameManager.getInstance().getStatus().equals(StatusConfig.UNIT_SELECTED)  ) {
                    Log.d("Walkeitor/TouchEvent", "Unit selected, moving unit");
                    GameManager.getInstance().moveTo(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                }
                break;
            //case TouchEvent.ACTION_MOVE:
            //    GameManager.getInstance().moveTo(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
            //    break;
            //case TouchEvent.ACTION_UP:
            //    break;
        }


        return super.onSceneTouchEvent(pScene, pSceneTouchEvent);
    }



}
