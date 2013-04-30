package org.rubenrr.walkeitor.manager.scene;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.text.Text;
import org.rubenrr.walkeitor.config.FontConfig;
import org.rubenrr.walkeitor.manager.FontLoadManager;
import org.rubenrr.walkeitor.manager.SceneManager;

/**
 * User: Ruben Rubio Rey
 * Date: 30/04/13
 * Time: 7:23 PM
 */
public class HUDManager {

    private HUD hud;

    // labels values
    // crude oil
    private Text co_value;

    // fuel (refinered)
    private Text fuel_value;

    public HUDManager(HUD hud) {
        this.hud = hud;
    }

    public void createLabels() {
        String labelText = "Crude oil: ";
        final Text co = new Text(1, 1, FontLoadManager.getInstance().get(FontConfig.HUD),
                labelText, labelText.length(), SceneManager.getInstance().getVertexBufferObjectManager());
        hud.attachChild(co);

        String labelValue = "0";
        this.co_value = new Text(70, 1, FontLoadManager.getInstance().get(FontConfig.HUD),
                labelValue, labelValue.length(), SceneManager.getInstance().getVertexBufferObjectManager());
        hud.attachChild(co_value);

        labelText = "Fuel: ";
        final Text fuel = new Text(co_value.getX() + 50, 1, FontLoadManager.getInstance().get(FontConfig.HUD),
                labelText, labelText.length(), SceneManager.getInstance().getVertexBufferObjectManager());
        hud.attachChild(fuel);

        labelValue = "0";
        this.fuel_value = new Text(fuel.getX() + 35, 1, FontLoadManager.getInstance().get(FontConfig.HUD),
                labelValue, labelValue.length(), SceneManager.getInstance().getVertexBufferObjectManager());
        hud.attachChild(this.fuel_value );
    }

    public void setCrudeOil(int co_value) {
        this.co_value.setText(String.valueOf(co_value));
    }

    public void setFuel(int fuel_value) {
        this.fuel_value.setText(String.valueOf(fuel_value));
    }
}
