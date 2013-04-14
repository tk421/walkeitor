package org.rubenrr.walkeitor.config;

/**
 * User: Ruben Rubio Rey
 * Date: 14/04/13
 * Time: 1:14 PM
 *
 * Tile Size, width and height in pixels
 */
public enum TileConfig {
    TILE_SIZE(32,32),;

    private final int tileWidth;
    private final int tileHeight;

    private TileConfig(int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int[] getTileDimensions() {
        int[] tileDimensions = {this.TILE_SIZE.getTileWidth(),this.TILE_SIZE.getTileHeight()};
        return tileDimensions;
    }
}