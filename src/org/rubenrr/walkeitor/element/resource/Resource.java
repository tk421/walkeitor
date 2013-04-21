package org.rubenrr.walkeitor.element.resource;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;
import org.rubenrr.walkeitor.menu.MenuExtendable;
import org.rubenrr.walkeitor.util.TileLocatable;

/**
 * User: Ruben Rubio Rey
 * Date: 5/04/13
 * Time: 8:44 PM
 */
public abstract class Resource extends Sprite  implements MenuExtendable, TileLocatable {

    ElementConfig elementConfig;
    TMXTile tmxTile;

    public Resource(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, TextureRegionManager.getInstance().get(elementConfig), SceneManager.getInstance().getVertexBufferObjectManager());
        this.elementConfig= elementConfig;
        GameManager.getInstance().addResource(this);
        this.setTiledPosition();
    }


    public int getColumnTileSize() {
        return this.elementConfig.getTileSizeColumn();
    }

    public int getRowTileSize() {
        return this.elementConfig.getTileSizeRow();
    }

    public int getTileColumn() {
        this.tmxTile = SceneManager.getInstance().getTile(this.getX(), this.getY());
        return this.tmxTile.getTileColumn();
    }

    public int getTileRow() {
        this.tmxTile = SceneManager.getInstance().getTile(this.getX(), this.getY());
        return this.tmxTile.getTileRow();
    }

    public String getSubtype() {
        return this.elementConfig.getSubtype();
    }


    /**
     * Set the position adjusted to the matched Tile
     */
    private void setTiledPosition() {
        this.setTiledPosition(this.getX(), this.getY());
    }

    private void setTiledPosition(float pX, float pY) {
        this.tmxTile = SceneManager.getInstance().getTile(pX, pY);
        this.setPosition(this.tmxTile.getTileX(), this.tmxTile.getTileY());
    }

    @Override
    public String toString() {
        final String string = this.elementConfig.toString() + " " + super.toString();
        return super.toString();
    }

}
