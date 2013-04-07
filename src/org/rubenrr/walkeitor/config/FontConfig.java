package org.rubenrr.walkeitor.config;

import android.graphics.Typeface;

/**
 * The configuration for all in game fonts should be defined here and preloaded at SceneManager
 *
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 11:59 AM
 */
public enum FontConfig {

    MENU_STANDARD("menustandardfont", 256, 256, Typeface.create(Typeface.DEFAULT, Typeface.NORMAL), 20);

    private final String key;
    private final int textureHeight;
    private final int textureWidth;
    private final Typeface typeFace;
    private final float size;

    private FontConfig(final String key, final int textureHeight, final int textureWidth, final Typeface typeFace, final float size) {
        this.key = key;
        this.textureHeight = textureHeight;
        this.textureWidth = textureWidth;
        this.typeFace = typeFace;
        this.size = size;
    }

    @Override
    public String toString() {
        return this.key;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public Typeface getTypeFace() {
        return typeFace;
    }

    public float getSize() {
        return size;
    }

}
