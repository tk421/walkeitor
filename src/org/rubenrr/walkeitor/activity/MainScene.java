package org.rubenrr.walkeitor.activity;

import org.andengine.entity.scene.Scene;
import org.rubenrr.walkeitor.GameManager;

/**
 * User: Ruben Rubio Rey
 * Date: 23/03/13
 * Time: 4:20 PM
 */


/*
Backlog:
  - Create a resource type mine type loading from the MainScene
  - Refactor to generalize
  - Make the Person walk to move to the Oil Resource
  - Make the person Walk to the Oil Resource and start to construct an oil mine
  - Make the person to construct an Oil Refinery
  - Create resources in the game manager needed to construct buildings
*/
public class MainScene extends TiledPinchZoomBaseActivity {

    @Override
    public void onCreateResources() {

        // testing how to organize load resources
        GameManager.getInstance().setTextureManager(this.getTextureManager());
        GameManager.getInstance().setFontManger(this.getFontManager());
        GameManager.getInstance().setAssetManager(this.getAssets());
        GameManager.getInstance().setEngineLock(this.mEngine.getEngineLock());
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
