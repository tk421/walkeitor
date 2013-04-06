package org.rubenrr.walkeitor.utils;

import android.util.Log;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;
import org.rubenrr.walkeitor.GameManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * Tools of methods to manage BitMaps.
 *
 *
 * User: Ruben Rubio Rey
 * Date: 1/04/13
 * Time: 6:58 PM
 */
public class TextureRegionManager {

    private static TextureRegionManager instance = null;

    Map<String, TextureRegion> textureMap;

    TextureRegionManager() {
        this.textureMap = new HashMap<String, TextureRegion>();
    }

    public static TextureRegionManager getInstance() {
        if (instance == null) {
            instance = new TextureRegionManager();
        }
        return instance;
    }

    /**
     * Load bitmaps and keeps it in a NavigableMap.
     * The bitmap will be stored in memory as a TextureRegionManager
     *
     * @param path
     */
    public void loadBitmap (String path) {
        if ( ! textureMap.containsKey(path)) {
            TextureRegion valueTexture = this.getTexttureRegionFromBitmap(path);
            Log.d("TextureRegionManager","PUT key: " + path + " value: " + valueTexture);
            textureMap.put(path, valueTexture);
        } else {
            Log.d("TextureRegionManager","PUT key: " + path + " already exists");
        }
    }

    public TextureRegion getTextureRegion(String path) {
        //make sure that the key is loaded
        TextureRegion valueTexture = textureMap.get(path);

        Log.d("TextureRegionManager","GET: key: " + path + " value " + valueTexture);

        if ( valueTexture == null ) {
            Log.w("TextureRegionManager", "GET: key: " + path + " value was not preloaded");
            this.loadBitmap(path);
            valueTexture = textureMap.get(path);
        }

        return valueTexture;
    }

    private TextureRegion getTexttureRegionFromBitmap( final String path ) {
        ITexture mTexture;

        TextureRegion texttureregion = null;

        // get the city
        try {
            mTexture = new BitmapTexture(GameManager.getInstance().getTextureManager(), new IInputStreamOpener() {
                @Override
                public InputStream open() throws IOException {
                    return GameManager.getInstance().getAssetManager().open(path);
                }
            });

            mTexture.load();

            texttureregion = TextureRegionFactory.extractFromTexture(mTexture);


        } catch (IOException e) {
            Log.e("TextureRegionManager", "Error loading texture " + path, e);
        }

        return texttureregion;

    }
}
