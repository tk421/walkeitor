package org.rubenrr.walkeitor.manager;

import android.content.res.AssetManager;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.element.building.City;
import org.rubenrr.walkeitor.element.resource.Ore;
import org.rubenrr.walkeitor.element.unit.Person;
import org.rubenrr.walkeitor.wrapper.ElementWrapper;

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
    private Engine.EngineLock enginelock;

    private static GameManager instance = null;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();

            // Actions to be done when the GameManager is created (like load bitmaps)

        }
        return instance;
    }

    public Engine.EngineLock getEngineLock() {
        return enginelock;
    }

    public void setEngineLock(Engine.EngineLock enginelock) {
        this.enginelock = enginelock;
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
     */
    public void loadBitmap () {
        for (ElementConfig ec : ElementConfig.values()) {
            TextureRegionManager.getInstance().put(ec);
        }
    }

    /**
     *  Preload all possible fonts available in the system
     *  This is useful so we will load all the fonts OnCreateResources
     */
    public void loadFont() {
        for (FontConfig fc : FontConfig.values()) {
            FontLoadManager.getInstance().put(fc);
        }
    }

    /**
     * Attach sprite to scene
     * @param sprite
     */
    public void attachChild(final Sprite sprite) {
        scene.attachChild(sprite);
    }



}
