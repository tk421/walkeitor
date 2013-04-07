package org.rubenrr.walkeitor.config;

/**
 * Configuration elements for the units displayed.
 *
 * User: Ruben Rubio Rey
 * Date: 7/04/13
 * Time: 12:21 PM
 */
public enum ElementConfig {
    BUILDING_CITY ("building_city", "building", "city", "", "gfx/building/city/normal_city.png"),
    UNIT_WORKER ("unit_worker", "unit", "person", "worker", "gfx/unit/person/normal_worker.png"),
    RESOURCE_OIL ("resource_oil", "resource", "ore", "oil", "gfx/resource/ore/normal_oil.png");

    private final String name;
    private final String category;
    private final String type;
    private final String subtype;
    private final String spriteNormalPath;

    /**
     * Get's the element configuration
     *
     * @param name key that we use to reffer to the element
     * @param category can be building, resource or unit
     * @param type next category. We have a class specified of element per category
     * @param subtype Is like Oil for the Ore type under resource category
     * @param spriteNormalPath The path for the normal configuration of the element.
     */
    private ElementConfig(final String name, final String category, final String type, final String subtype, final String spriteNormalPath) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.subtype = subtype;
        this.spriteNormalPath = spriteNormalPath;
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
}
