package org.rubenrr.walkeitor;

import android.content.res.AssetManager;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.rubenrr.walkeitor.building.City;
import org.rubenrr.walkeitor.utils.TextureRegionManager;

/**
 * User: Ruben Rubio Rey
 * Date: 25/03/13
 * Time: 7:37 AM
 *
 * Game manager controls the game. This is the singleton that has access to the methods
 * of BaseGameActivity to modify the world.
 *
 */

public class GameManager {

    // properties of game manager
    private TextureManager texturemanager;
    private Scene scene;
    private AssetManager assetmanager;
    private VertexBufferObjectManager vertexbufferobjectmanager;
    private FontManager fontmanger;

    private static GameManager instance = null;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();

            // Actions to be done when the GameManager is created (like load bitmaps)

        }
        return instance;
    }

    public FontManager getFontManger() {
        return fontmanger;
    }

    public void setFontManger(FontManager fontmanger) {
        this.fontmanger = fontmanger;
    }

    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return vertexbufferobjectmanager;
    }

    public void setVertexBufferObjectManager(VertexBufferObjectManager vertexbufferobjectmanager) {
        this.vertexbufferobjectmanager = vertexbufferobjectmanager;
    }

    public AssetManager getAssetManager() {
        return assetmanager;
    }

    public void setAssetManager(AssetManager assetmanager) {
        this.assetmanager = assetmanager;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public TextureManager getTextureManager() {
        return texturemanager;
    }

    public void setTextureManager(TextureManager texturemanager) {
        this.texturemanager = texturemanager;
    }

    /**
     * Take all available objects and load all bitmaps in related with the Scene in a Singleton
     *  This is useful so we will load all images at the phase OnCreateResources
     *
     * TODO I would like to centralize all configuration parameters as they are duplicated
     * TODO within the Buildings and Unit objects.
     */
    public void loadBitmap () {
        TextureRegionManager.getInstance().loadBitmap("gfx/building/city/normal_city.png");
    }


    /**
     * Create script behaves as a factory pattern
     * it receives the type of object that should be created and the position.
     * Game manager will initialize the object
     *
     *
     * @param name name of the object to be created. TODO consider using type of object ?
     * @param posX relative position to the scene
     * @param posY relative position to the scene
     */
    public void createSprite(final String name, final float posX, final float posY) {

        final City city = new City(posX, posY);
        scene.attachChild(city);
        //this.getScene().setTouchAreaBindingOnActionDownEnabled(true);

    }




}
