package org.rubenrr.walkeitor.manager.action;

import org.andengine.engine.Engine;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.extension.tmx.TMXTile;
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
    public void setTileBackground(final int row, final int column, float red, float green, float blue, float alpha) {

        // create new ones
        Rectangle tileRectangle = new Rectangle(0, 0, SceneManager.getInstance().getTmxTiledMap().getTileWidth(), SceneManager.getInstance().getTmxTiledMap().getTileHeight(), SceneManager.getInstance().getVertexBufferObjectManager());
        //tileRectangle.setColor(0,0.5f,0, 0.2f);
        tileRectangle.setColor( red, green, blue, alpha );
        SceneManager.getInstance().getScene().attachChild(tileRectangle);
        final TMXTile tmxTile =  SceneManager.getInstance().getTmxLayer().getTMXTile(column, row);
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
