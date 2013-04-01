package org.rubenrr.walkeitor.activity;

import org.andengine.entity.scene.Scene;
import org.rubenrr.walkeitor.GameManager;

/**
 * User: Ruben Rubio Rey
 * Date: 23/03/13
 * Time: 4:20 PM
 */
public class MainScene extends TiledPinchZoomBaseActivity {

    @Override
    public void onCreateResources() {

        // testing how to organize load resources
        GameManager.getInstance().setTextureManager(this.getTextureManager());
        GameManager.getInstance().setFontManger(this.getFontManager());
        GameManager.getInstance().setAssetManager(this.getAssets());
        GameManager.getInstance().loadBitmap();
    }

    @Override
    public Scene onCreateScene() {

        // testing how to organize loading resources
        Scene scene = super.onCreateScene();
        GameManager.getInstance().setScene(scene);
        GameManager.getInstance().setVertexBufferObjectManager(this.getVertexBufferObjectManager());


        GameManager.getInstance().createSprite("city", this.getCameraWidth() / 2, this.getCameraHeight() / 2);

        return scene;
    }

}
