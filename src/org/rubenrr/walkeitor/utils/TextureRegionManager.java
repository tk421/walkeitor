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

    NavigableMap<String, TextureRegion> textureMap;

    TextureRegionManager() {
        this.textureMap = new ConcurrentSkipListMap<String, TextureRegion>();
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
        Log.d("TextureRegionManager","Loading path: " + path);
        if (textureMap.ceilingEntry(path) == null ) {
            textureMap.put(path, this.getTexttureRegionFromBitmap(path));
        }
    }

    public TextureRegion getTextureRegion(String path) {
        Log.d("TextureRegionManager","Getting path: " + path + " that is " + textureMap.ceilingEntry(path));
        if ( textureMap.ceilingEntry(path) == null ) {
            Log.w("TextureRegionManager", "Getting path: " + path + " is NULL, loading AGAIN");
            textureMap.put(path, this.getTexttureRegionFromBitmap(path));
        }
        return textureMap.ceilingEntry(path).getValue();
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
            Debug.e(e);
        }

        return texttureregion;

    }
}
