package org.rubenrr.walkeitor.manager.action;

import android.util.Log;
import org.andengine.engine.Engine;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.extension.tmx.TMXTile;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.SceneManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the Background tiles displayed in-game when creating actions
 *
 * User: Ruben Rubio Rey
 * Date: 15/04/13
 * Time: 7:20 AM
 */
public class BackgroundTile {

    private List<Rectangle> tileRectangles = new ArrayList<Rectangle>();

    /**
     * Only for debugging purposes!
     *
     * @param row
     * @param column
     */
    public void setTileBackground(final int column, final int row, float red, float green, float blue, float alpha) {

        // create new ones
        Rectangle tileRectangle = new Rectangle(0, 0, SceneManager.getInstance().getTmxTiledMap().getTileWidth(), SceneManager.getInstance().getTmxTiledMap().getTileHeight(), SceneManager.getInstance().getVertexBufferObjectManager());
        //tileRectangle.setColor(0,0.5f,0, 0.2f);
        tileRectangle.setColor( red, green, blue, alpha );
        SceneManager.getInstance().getScene().attachChild(tileRectangle);
        TMXTile tmxTile = null;
        //try {
            tmxTile =  SceneManager.getInstance().getTmxLayer().getTMXTile(column, row);
        //} catch ( ArrayIndexOutOfBoundsException e ) {
        //    Log.w("setTileBackground", "Out of bound exeception", e);
        //}
        if(tmxTile != null) {
            tileRectangle.setPosition(tmxTile.getTileX(), tmxTile.getTileY());
            this.tileRectangles.add(tileRectangle);
        }
    }


    /**
     * Remove all created Tiles
     */
    public void clearAll() {
        //unattach the previously created rectangles
        if ( ! this.tileRectangles.isEmpty() ) {
            final Engine.EngineLock engineLock = SceneManager.getInstance().getEngineLock();
            engineLock.lock();
            for (Rectangle tileRectangle : this.tileRectangles)     {
                SceneManager.getInstance().getScene().detachChild(tileRectangle);
            }
            engineLock.unlock();
        }
    }



}
