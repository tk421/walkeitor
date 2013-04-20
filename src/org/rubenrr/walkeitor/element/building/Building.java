package org.rubenrr.walkeitor.element.building;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.math.MathUtils;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.StatusConfig;
import org.rubenrr.walkeitor.element.unit.Unit;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;
import org.rubenrr.walkeitor.menu.MenuExtendable;
import org.rubenrr.walkeitor.menu.MenuStrategy;

/**
 * User: Ruben Rubio Rey
 * Date: 30/03/13
 * Time: 1:07 PM
 */
public abstract class Building extends Sprite implements MenuExtendable {

    private MenuStrategy menu;
    private ElementConfig elementConfig;
    private StatusConfig statusConfig = StatusConfig.NONE;

    public Building(float pX, float pY, ElementConfig elementConfig) {
        super(pX, pY, TextureRegionManager.getInstance().get(elementConfig), SceneManager.getInstance().getVertexBufferObjectManager());
        this.elementConfig = elementConfig;
        GameManager.getInstance().addBuilding(this);
        this.setTiledPosition();
    }

    /**
     * Set the position adjusted to the matched Tile
     */
    private void setTiledPosition() {
        this.setTiledPosition(this.getX(), this.getY());
    }
    private void setTiledPosition(float pX, float pY) {
        final TMXTile tmxtile = SceneManager.getInstance().getTile(pX, pY);
        this.setPosition(tmxtile.getTileX(), tmxtile.getTileY());
    }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

        if (this.getStatusConfig().equals(StatusConfig.NONE)) {
            switch (pSceneTouchEvent.getAction()) {
                case TouchEvent.ACTION_DOWN:
                    this.menu.display(this);
                    break;
                //case TouchEvent.ACTION_MOVE: {
                //    break;}
                //case TouchEvent.ACTION_UP:
                //    break;
            }
        } else if (this.getStatusConfig().equals(StatusConfig.SET_LOCATION)) {
            switch (pSceneTouchEvent.getAction()) {
                case TouchEvent.ACTION_DOWN: {
                    //this.menu.actionOnTouch(this);
                    break;
                }
                case TouchEvent.ACTION_MOVE: {
                    this.setTiledPosition(pSceneTouchEvent.getX(),pSceneTouchEvent.getY());
                    break;
                }
            }


        }

        return true;
    }


    @Override
    public String toString() {
        final String string = this.elementConfig.toString() + " " + super.toString();
        return super.toString();
    }

    protected void setMenu(MenuStrategy menu) {
        this.menu = menu;
    }

    protected StatusConfig getStatusConfig() {
        return statusConfig;
    }

    protected void setStatusConfig(StatusConfig statusConfig) {
        this.statusConfig = statusConfig;
    }

    /**
     * The mine is new, therefore the user should set the location of this object
     */
    public void setDragAndDropLocation() {
        this.setStatusConfig(StatusConfig.SET_LOCATION);
        this.setAlpha(0.2f);
        this.setZIndex(99); // first line to respond to the everything
    }

    /**
     * If the item has the status SET_LOCATION this will tell if the location is good.
     * Which means that the building has enough space to be built.
     *
     * @return
     */
    public boolean isLocationGood() {
        /**
         * I AM PROGRAMMING HERE !!!! BY USING OCCUPIED TILES WE SHOULD BE ABLE TO KNOW
         * IF THE CURRENT OBJECT IS WITHIN THE FREE TILES SPACE
         *
         *
         */
        return false;
    }



}
