package org.rubenrr.walkeitor.config;

import org.rubenrr.walkeitor.menu.MenuStrategy;
import org.rubenrr.walkeitor.menu.NoMenu;
import org.rubenrr.walkeitor.menu.building.CityMenu;
import org.rubenrr.walkeitor.menu.building.OilMineMenu;
import org.rubenrr.walkeitor.menu.person.WorkerMenu;

/**
 * Configuration elements for the units displayed.
 *
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 12:21 PM
 */
public enum ElementConfig {

    BUILDING_CITY ("building_city", "building", "city", "", "gfx/building/city/normal_city.png", 3, 3, null),
    UNIT_WORKER ("unit_worker", "unit", "person", "worker", "gfx/unit/person/normal_worker.png", 1, 1, null),
    RESOURCE_OIL ("resource_oil", "resource", "ore", "oil", "gfx/resource/ore/normal_oil.png", 2, 2, null),
    MINE_OIL ("mine_oil", "building", "mine", "oil", "gfx/building/mine/normal_oil.png", 2, 2, RESOURCE_OIL); // MINE_OIL can only be built over RESOURCE_OIL

    private final String name;
    private final String category;
    private final String type;
    private final String subtype;
    private final String spriteNormalPath;
    private final int tileSizeColumn;
    private final int tileSizeRow;
    private final ElementConfig dependence;
    private final MenuStrategy menuStrategy;

    /**
     *
     *
     * @param name key that we use to reffer to the element
     * @param category can be building, resource or unit
     * @param type next category. We have a class specified of element per category
     * @param subtype Is like Oil for the Ore type under resource category
     * @param spriteNormalPath The path for the normal configuration of the element.
     * @param tileSizeColumn Number of columns that the sprite needs to be displayed
     * @param tileSizeRow Number of rows that the sprite need to be displayed
     * @param dependence If this object can just be built in a specific Element type, dependency is specified here
     *
     */
    private ElementConfig(String name, String category, String type, String subtype, String spriteNormalPath, int tileSizeColumn, int tileSizeRow, ElementConfig dependence) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.subtype = subtype;
        this.spriteNormalPath = spriteNormalPath;
        this.tileSizeColumn = tileSizeColumn;
        this.tileSizeRow = tileSizeRow;
        this.dependence = dependence;

        // need to implement a factory here
        if (this.name.equals("mine_oil")) {
            this.menuStrategy = new OilMineMenu();
        } else if (this.name.equals("unit_worker")) {
            this.menuStrategy = new WorkerMenu();
        } else if (this.name.equals("building_city")) {
            this.menuStrategy = new CityMenu();
        } else if (this.name.equals("resource_oil")) {
            this.menuStrategy = new NoMenu();
        } else {
            throw new IllegalArgumentException(this.name + " is unknown when setting the menuStrategy");
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getSpriteNormalPath() {
        return spriteNormalPath;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public int getTileSizeColumn() {
        return tileSizeColumn;
    }

    public int getTileSizeRow() {
        return tileSizeRow;
    }

    public ElementConfig getDependence() {
        return dependence;
    }

    public MenuStrategy getMenuStrategy() {
        return menuStrategy;
    }
}
