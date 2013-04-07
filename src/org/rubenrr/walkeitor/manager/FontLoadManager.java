package org.rubenrr.walkeitor.manager;

import android.util.Log;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.rubenrr.walkeitor.config.FontConfig;

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
     *
     * @param font fontType
     */
    public void put ( final FontConfig font ) {
        Log.d("FontLoadManager", "PUT: " + font.toString());
        final String key = font.toString();
        if (this.get(key) == null) {
            Font value = this.getFont(font);
            value.load();
            Log.d("FontLoadManager", "Put " + key + " is empty, storing value " + value);
            super.put(key, value);
        }
    }

    public Font get(final FontConfig font) {
        final String key = font.toString();
        Font value = (Font)super.get(key);
        Log.d("FontLoadManager", "GET: key " + key + " value " + value);
        if ( value == null ) {
            Log.e("FontLoadManager", "Font " + key + " not loaded");
        }
        return value;
    }

    private Font getFont(final FontConfig font) {
        return FontFactory.create(SceneManager.getInstance().getFontManger(), SceneManager.getInstance().getTextureManager(), font.getTextureHeight(), font.getTextureWidth(), font.getTypeFace(), font.getSize());
    }

}
