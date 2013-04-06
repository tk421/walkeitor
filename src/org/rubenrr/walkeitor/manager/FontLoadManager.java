package org.rubenrr.walkeitor.manager;

import android.graphics.Typeface;
import android.util.Log;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;

/**
 *
 * This class pre-load all information related with fonts following andengine conventions
 *
 * User: Ruben Rubio Rey
 * Date: 6/04/13
 * Time: 12:32 PM
 */
public class FontLoadManager extends MemoryStorage {

    private static FontLoadManager instance = null;

    public static FontLoadManager getInstance() {
        if (instance == null) {
            instance = new FontLoadManager();
        }
        return instance;
    }

    /**
     * @param key name that we will use for the text
     * @param textureHeight not sure what is this parameter. Very likely the "space" that has available for the text
     * @param textureWidth Very likely the "space" that has available for the text
     * @param typeFace standard Android typeFace
     * @param size font size
     */
    public void put ( String key, int textureHeight, int textureWidth, Typeface typeFace, float size ) {
        Log.d("FontLoadManager", "PUT: " + key);
        if (this.get(key) == null) {
            Font value = this.getFont(textureHeight, textureWidth, typeFace, size);
            value.load();
            Log.d("FontLoadManager", "Put " + key + " is empty, storing value " + value);
            super.put(key, value);
        }
    }

    public Font get(String key) {
        Font value = (Font)super.get(key);
        Log.d("FontLoadManager", "GET: key " + key + " value " + value);
        if ( value == null ) {
            Log.e("FontLoadManager", "Font " + key + " not loaded");
        }
        return value;
    }

    private Font getFont(int textureHeigh, int textureWidth, Typeface typeFace, float size) {
        return FontFactory.create(GameManager.getInstance().getFontManger(), GameManager.getInstance().getTextureManager(), textureHeigh, textureWidth, typeFace, size);
    }

}
