package org.rubenrr.walkeitor.scene;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.rubenrr.walkeitor.GameManager;

/**
 * User: Ruben Rubio Rey
 * Date: 23/03/13
 * Time: 4:20 PM
 */
public class MainScene extends TiledPinchZoomBaseActivity {

    private GameManager gamemanager = new GameManager();

    @Override
    public void onCreateResources() {

        // testing how to organize load resources
        this.gamemanager.setTextureManager(this.getTextureManager());
        this.gamemanager.setAssetManager(this.getAssets());
        this.gamemanager.setBitmap("gfx/building/city/normal_city.png");
    }

    @Override
    public Scene onCreateScene() {

        // testing how to organize loading resources
        Scene scene = super.onCreateScene();
        this.gamemanager.setScene(scene);
        this.gamemanager.setVertexbufferobjectManager(this.getVertexBufferObjectManager());
        this.gamemanager.setSprite("city", this.getCameraWidth()/2, this.getCameraHeight()/2);

        return scene;
    }

}
