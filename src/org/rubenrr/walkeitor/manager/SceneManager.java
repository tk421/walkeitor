package org.rubenrr.walkeitor.manager;

import android.content.res.AssetManager;
import android.util.Log;
import org.andengine.engine.Engine;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXLayer;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.extension.tmx.TMXTiledMap;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.FontConfig;

/**
 * User: Ruben Rubio Rey
 * Date: 25/03/13
 * Time: 7:37 AM
 *
 * Game manager controls the game. This is the singleton that has access to the methods
 * of BaseGameActivity to modify the world.
 *
 */

public class SceneManager {

    // properties of game manager
    private TextureManager texturemanager;
    private Scene scene;
    private AssetManager assetmanager;
    private VertexBufferObjectManager vertexbufferobjectmanager;
    private FontManager fontmanger;
    private Engine.EngineLock enginelock;
    private TMXLayer tmxLayer;
    private TMXTiledMap tmxTiledMap;

    private Rectangle tileRectangle;

    private static SceneManager instance = null;

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();

            // Actions to be done when the SceneManager is created (like load bitmaps)

        }
        return instance;
    }

    // just for debug purposes, using lazy initialization
    public void setTileRectangle(float x, float y) {

        if (this.tileRectangle == null ) {
            this.tileRectangle = new Rectangle(0, 0, this.tmxTiledMap.getTileWidth(), this.tmxTiledMap.getTileHeight(), this.getVertexBufferObjectManager());
            this.tileRectangle.setAlpha(0.5f);
            Log.d("Movement", "Tilesize " + this.tmxTiledMap.getTileWidth() + "," + this.tmxTiledMap.getTileHeight());
            this.scene.attachChild(this.tileRectangle);
        }
        final TMXTile tmxTile = this.tmxLayer.getTMXTileAt(x, y);

        Log.d("Movement/SceneManager", "Tile Columns, rows:" + tmxTile.getTileColumn() + "," + tmxTile.getTileRow());

        if(tmxTile != null) {
            this.tileRectangle.setPosition(tmxTile.getTileX(), tmxTile.getTileY());
        }
    }

    //
    //

    /**
     * Only for debugging purposes!
     * WARNING! Rectangles created will never be deleted!!!
     * @param row
     * @param column
     */
    public void setDebugTileBackfground(int row, int column) {
        Rectangle tileRectangle = new Rectangle(0, 0, this.tmxTiledMap.getTileWidth(), this.tmxTiledMap.getTileHeight(), this.getVertexBufferObjectManager());
        tileRectangle.setColor(0,0.5f,0, 0.2f);
        this.scene.attachChild(tileRectangle);
        final TMXTile tmxTile =  this.tmxLayer.getTMXTile(column,row);
        if(tmxTile != null) {
            tileRectangle.setPosition(tmxTile.getTileX(), tmxTile.getTileY());
        }
    }



    /**
     * Given the X and Y show the proper Tile
     *
     * @param x
     * @param y
     * @return
     */
    public TMXTile getTile (float x, float y) {
        final TMXTile tmxTile = this.tmxLayer.getTMXTileAt(x, y);
        return tmxTile;
    }

    public TMXTiledMap getTmxTiledMap() {
        return tmxTiledMap;
    }

    public void setTmxTiledMap(TMXTiledMap tmxTiledMap) {
        this.tmxTiledMap = tmxTiledMap;
    }

    public TMXLayer getTmxLayer() {
        return tmxLayer;
    }

    public void setTmxLayer(TMXLayer tmxLayer) {
        this.tmxLayer = tmxLayer;
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
        // lets check that the position matches with a Tile
        scene.attachChild(sprite);
    }



}
