package org.rubenrr.walkeitor.config;

import android.util.Log;
import org.rubenrr.walkeitor.config.element.ConsumableConfig;
import org.rubenrr.walkeitor.config.element.ProductionConfig;
import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.element.building.Refinery;
import org.rubenrr.walkeitor.element.consumable.Consumable;
import org.rubenrr.walkeitor.manager.util.Storage;
import org.rubenrr.walkeitor.menu.MenuStrategy;
import org.rubenrr.walkeitor.menu.NoMenu;
import org.rubenrr.walkeitor.menu.building.CityMenu;
import org.rubenrr.walkeitor.menu.building.OilMineMenu;
import org.rubenrr.walkeitor.menu.building.OilRefineryMenu;
import org.rubenrr.walkeitor.menu.building.TankFactoryMenu;
import org.rubenrr.walkeitor.menu.person.WorkerMenu;
import org.rubenrr.walkeitor.production.NoProduction;
import org.rubenrr.walkeitor.production.ProductionStrategy;
import org.rubenrr.walkeitor.production.building.OilMineProduction;
import org.rubenrr.walkeitor.production.building.OilRefineryProduction;

/**
 * Configuration elements for the units displayed.
 *
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 12:21 PM
 */
public enum ElementConfig {

    BUILDING_CITY ("building_city", "building", "city", "", "gfx/building/city/normal_city.png", 3, 3, 5000),
    UNIT_WORKER ("unit_worker", "unit", "person", "worker", "gfx/unit/person/normal_worker.png", 1, 1, 100),
    RESOURCE_OIL ("resource_oil", "resource", "ore", "oil", "gfx/resource/ore/normal_oil.png", 2, 2, 100000),
    MINE_OIL ("mine_oil", "building", "mine", "oil", "gfx/building/mine/normal_oil.png", 2, 2, 500, RESOURCE_OIL, ProductionConfig.MINE_OIL),
    REFINERY_OIL ("refinery_oil", "building", "refinery", "oil", "gfx/building/refinery/normal_oil.png", 2, 2, 1000, ProductionConfig.REFINERY_OIL),
    FACTORY_TANK ("factory_tank", "building", "factory", "tank", "gfx/building/factory/normal_tank.png", 2, 2, 50000),
    UNIT_TANK ("unit_tank", "unit", "vehicle", "tank", "gfx/unit/vehicle/normalTank.png", 1, 1, 60);


    private final String name;
    private final String category;
    private final String type;
    private final String subtype;
    private final String spriteNormalPath;
    private final int tileSizeColumn;
    private final int tileSizeRow;
    private final int storageSize;
    private final ElementConfig dependence;
    private final ProductionConfig productionConfig;



    private final MenuStrategy menuStrategy;

    /**
     *
     *
     * @param name key that we use to refer to the element
     * @param category can be building, resource or unit
     * @param type next category. We have a class specified of element per category
     * @param subtype Is like Oil for the Ore type under resource category
     * @param spriteNormalPath The path for the normal configuration of the element.
     * @param tileSizeColumn Number of columns that the sprite needs to be displayed
     * @param tileSizeRow Number of rows that the sprite need to be displayed
     * @param dependence If this object can just be built in a specific Element type, dependency is specified here
     *
     */
    private ElementConfig(String name, String category, String type, String subtype, String spriteNormalPath,
                            int tileSizeColumn, int tileSizeRow, int storageSize, ElementConfig dependence,
                            ProductionConfig productionConfig) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.subtype = subtype;
        this.spriteNormalPath = spriteNormalPath;
        this.tileSizeColumn = tileSizeColumn;
        this.tileSizeRow = tileSizeRow;
        this.storageSize = storageSize;
        this.dependence = dependence;
        this.menuStrategy = this.menuFactory();
        this.productionConfig = productionConfig;


    }

    private ElementConfig(String name, String category, String type, String subtype, String spriteNormalPath, int tileSizeColumn, int tileSizeRow,
                          int storageSize) {
        this(name, category, type, subtype, spriteNormalPath, tileSizeColumn, tileSizeRow, storageSize, null, null);
    }

    private ElementConfig(String name, String category, String type, String subtype, String spriteNormalPath, int tileSizeColumn, int tileSizeRow,
                          int storageSize, ProductionConfig productionConfig) {
        this(name, category, type, subtype, spriteNormalPath, tileSizeColumn, tileSizeRow, storageSize, null, productionConfig);
    }

    private MenuStrategy menuFactory() {

        MenuStrategy menuStrategy;

        // need to implement a factory here
        if (this.name.equals("mine_oil")) {
            menuStrategy = new OilMineMenu();
        } else if (this.name.equals("unit_worker")) {
            menuStrategy = new WorkerMenu();
        } else if (this.name.equals("building_city")) {
            menuStrategy  = new CityMenu();
        } else if (this.name.equals("resource_oil")) {
            menuStrategy  = new NoMenu();
        } else if (this.name.equals("refinery_oil")) {
            menuStrategy  = new OilRefineryMenu();
        } else if (this.name.equals("factory_tank")) {
            menuStrategy  = new TankFactoryMenu();
        } else if (this.name.equals("unit_tank")) {
            menuStrategy  = new NoMenu();
        } else {
            throw new IllegalArgumentException(this.name + " is unknown when setting the menuStrategy");
        }

        return menuStrategy;
    }

    /**
     * @param storage the storage directly related with this production
     * @return
     */
    private ProductionStrategy productionFactory(Building building, Storage storage) {

        ProductionStrategy productionStrategy;

        if (this.name.equals("mine_oil")) {
            Log.d("ElementConfig/Production", "Generating product strategy for " + this.name + " which production is "
                    + this.productionConfig.toString() + "  and timeElapsed is " + this.getTimeElapsed());
            productionStrategy = new OilMineProduction(building, storage, this.getTimeElapsed());
        } else if (this.name.equals("refinery_oil")) {
            if (building instanceof  Refinery ) {
                productionStrategy = new OilRefineryProduction((Refinery)building, storage, this.getTimeElapsed());
            } else {
                Log.e("productionFactory", "Trying to assign a NOT refinery to a refinery ?!!");
                productionStrategy = new NoProduction();
            }
        } else {
            productionStrategy = new NoProduction();
        }
        return productionStrategy;
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

    /**
     * Generates the consumable that is required
     * @return
     */
    public Consumable getConsumableRequired() {
        Consumable consumableRequired = this.productionConfig.getConsumableRequire().factory();
        consumableRequired.addAmount(this.productionConfig.getConsumableRequiredUnit());
        return consumableRequired;
    }

    /**
     * Generates the consumable that is produced
     *
     * @return
     */
    public Consumable getConsumableProduced() {
        Consumable consumableProduced = this.productionConfig.getConsumableProduce().factory();
        consumableProduced.addAmount(this.productionConfig.getConsumableProducedUnit());
        return consumableProduced;
    }

    public ConsumableConfig getConsumableProduce() {
        return productionConfig.getConsumableProduce();
    }

    public float getTimeElapsed() {
        return productionConfig.getTimeElapsed();
    }

    public float getConsumableProducedUnit() {
        return productionConfig.getConsumableProducedUnit();
    }

    public int getEnergyNeededKwh() {
        return productionConfig.getEnergyNeededKwh();
    }

    /**
     * Generate storage capabilities.
     * We have to create a new Storage every time
     * this mdule is called.
     *
     * @return
     */
    public Storage getStorage() {
        return new Storage(this.storageSize);
    }

    /**
     * Get the production strategy related with the selected storage
     * We have to create a new Production Strategy every time this is called
     *
     * TODO not very convinced about Building here
     *
     * @param storage
     * @return
     */
    public ProductionStrategy getProductionStrategy(Building building, Storage storage) {
        return this.productionFactory(building, storage);
    }
}
