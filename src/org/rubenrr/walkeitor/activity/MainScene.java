package org.rubenrr.walkeitor.activity;

import org.andengine.entity.scene.Scene;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

/**
 * User: Ruben Rubio Rey
 * Date: 23/03/13
 * Time: 4:20 PM
 */


/*
Backlog:
  - Create a resource type resource type loading from the MainScene DONE 06/04/2013
  - Refactor to generalize  DONE 06/04/2013
  - Use enum instead strings
  - Make the Person walk to move to the Oil Resource
  - Make the person Walk to the Oil Resource and start to construct an oil mine
  - Make the person to construct an Oil Refinery
  - Create resources in the game manager needed to construct buildings
*/
public class MainScene extends TiledPinchZoomBaseActivity {

    @Override
    public void onCreateResources() {
        GameManager.getInstance().setTextureManager(this.getTextureManager());
        GameManager.getInstance().setFontManger(this.getFontManager());
        GameManager.getInstance().setAssetManager(this.getAssets());
        GameManager.getInstance().setEngineLock(this.mEngine.getEngineLock());
        GameManager.getInstance().loadBitmap();
        GameManager.getInstance().loadFont();
    }

    @Override
    public Scene onCreateScene() {

        Scene scene = super.onCreateScene();
        GameManager.getInstance().setScene(scene);
        GameManager.getInstance().setVertexBufferObjectManager(this.getVertexBufferObjectManager());


        GameManager.getInstance().createSprite(new ElementWrapper("building", "city"), this.getCameraWidth() / 2, this.getCameraHeight() / 2);
        GameManager.getInstance().createSprite(new ElementWrapper("resource", "ore", "oil"), 100, 100);

        return scene;
    }

}
