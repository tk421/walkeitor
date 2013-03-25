package org.rubenrr.walkeitor;

import android.content.ContextWrapper;
import android.content.res.AssetManager;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: Ruben Rubio Rey
 * Date: 25/03/13
 * Time: 7:37 AM
 *
 * Game manager controls the game.
 *
 */

public class GameManager {

    // properties of game manager
    private TextureManager texturemanager;
    private Scene scene;
    private AssetManager assetmanager;
    private VertexBufferObjectManager vertexbufferobjectmanager;

    public VertexBufferObjectManager getVertexbufferobjectManager() {
        return vertexbufferobjectmanager;
    }

    public void setVertexbufferobjectManager(VertexBufferObjectManager vertexbufferobjectmanager) {
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


    // testing how to load resources
    private ITextureRegion city;

    public void setBitmap(final String path) {

        ITexture mTexture;

        // get the city
        try {
            mTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return getAssetManager().open(path);
                }
            });

            mTexture.load();

            this.city = TextureRegionFactory.extractFromTexture(mTexture);

        } catch (IOException e) {
            Debug.e(e);
        }

    }

    public void setSprite(final String name, final float posX, final float posY ) {

        final Sprite city = new Sprite(posX, posY, this.city, this.getVertexbufferobjectManager());
        scene.attachChild(city);

    }

    public ITextureRegion getCity() {
        return this.city;
    }

}
