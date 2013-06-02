package org.rubenrr.walkeitor.element.unit;

import android.util.Log;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.tmx.TMXTile;
import org.andengine.input.touch.TouchEvent;
import org.rubenrr.walkeitor.config.ElementConfig;
import org.rubenrr.walkeitor.config.status.UnitStatusConfig;
import org.rubenrr.walkeitor.manager.GameManager;
import org.rubenrr.walkeitor.manager.SceneManager;
import org.rubenrr.walkeitor.manager.TextureRegionManager;
import org.rubenrr.walkeitor.manager.command.Command;
import org.rubenrr.walkeitor.manager.command.Commandable;
import org.rubenrr.walkeitor.manager.command.primitive.PrimitiveCommand;
import org.rubenrr.walkeitor.menu.MenuExtendable;
import org.rubenrr.walkeitor.menu.MenuStrategy;
import org.rubenrr.walkeitor.util.TileLocatable;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * User: Ruben Rubio Rey
 * Date: 30/03/13
 * Time: 1:07 PM
 *
 * Note: this cannot be abstract as I need to recognize the object at GameManager
 *
 */
public abstract class Unit extends Sprite implements MenuExtendable, TileLocatable, Commandable {

    BlockingQueue<PrimitiveCommand> commandQueue;

    private ElementConfig elementConfig;
    private UnitStatusConfig unitStatusConfig;
    private MenuStrategy menu;
    TMXTile tmxTile;

    public Unit(float pX, float pY, ElementConfig elementConfig, UnitStatusConfig unitStatusConfig) {
        super(pX, pY, TextureRegionManager.getInstance().get(elementConfig), SceneManager.getInstance().getVertexBufferObjectManager());
        this.unitStatusConfig = unitStatusConfig;
        this.commandQueue = new ArrayBlockingQueue<PrimitiveCommand>(100);
        this.elementConfig = elementConfig;
        GameManager.getInstance().addUnit(this);
        this.setTiledPosition();
    }

    /**
     * Set the position adjusted to the matched Tile
     * TODO code duplicated with building
     */
    private void setTiledPosition() {
        this.setTiledPosition(this.getX(), this.getY());
    }
    private void setTiledPosition(float pX, float pY) {
        this.tmxTile = SceneManager.getInstance().getTile(pX, pY);
        this.setPosition(this.tmxTile.getTileX(), this.tmxTile.getTileY());
    }

    public int getTileColumn() {
        this.tmxTile = SceneManager.getInstance().getTile(this.getX(), this.getY());
        return this.tmxTile.getTileColumn();
    }

    public int getTileRow() {
        this.tmxTile = SceneManager.getInstance().getTile(this.getX(), this.getY());
        return this.tmxTile.getTileRow();
    }


    public int getColumnTileSize() {
        return this.elementConfig.getTileSizeColumn();
    }

    public int getRowTileSize() {
        return this.elementConfig.getTileSizeRow();
    }


    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {

        switch (pSceneTouchEvent.getAction()) {
            case TouchEvent.ACTION_DOWN:
                this.menu.display();
                break;
            //case TouchEvent.ACTION_MOVE: {
            //    break;}
            //case TouchEvent.ACTION_UP:
            //    break;
        }

        return true;
    }

    @Override
    public String toString() {
        final String string = this.elementConfig.toString() + " " + super.toString();
        return super.toString();
    }

    protected void setMenu(MenuStrategy menu, MenuExtendable sprite) {
        menu.setSprite(sprite);
        this.menu = menu;
    }

    public void clearMenu() {
        this.menu.clear();
    }

    public ElementConfig getElementConfig() {
        return elementConfig;
    }

    public boolean setBusy() {
        Boolean success = false;
        if (this.unitStatusConfig.equals(UnitStatusConfig.IDLE)) {
            this.unitStatusConfig = UnitStatusConfig.COMMAND_ON_PROGRESS;
            success = true;
        } else {
            Log.e("Unit", "Trying to set Busy a unit that was Busy!");
        }
        return success;
    }

    public void addCommand(PrimitiveCommand command) {
        this.commandQueue.add(command);
    }

    @Override
    public void startExecuteCommands() {
        this.setBusy();
        this.executeCommand();
    }

    @Override
    public void executeCommand() {
        try {
            if (this.commandQueue.size() > 0) {
                PrimitiveCommand command = this.commandQueue.take();
                command.execute();
            } else {
                Log.d("Unit/Command", "Command finished");
            }

        } catch (InterruptedException e) {
            Log.e("Unit/Command", "Error while consuming the queue, cannot take next element" , e);
        }
    }

    @Override
    public void nextCommand() {
        if (this.unitStatusConfig.equals(UnitStatusConfig.COMMAND_ON_PROGRESS)) {
            this.executeCommand();
        } else {
            Log.e("Unit/Command", "Trying to execute a command in a unit that is not COMMAND_ON_PROGRESS!");
        }

    }
}
