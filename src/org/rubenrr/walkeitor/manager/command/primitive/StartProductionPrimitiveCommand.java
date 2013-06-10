package org.rubenrr.walkeitor.manager.command.primitive;

import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.manager.command.Commandable;
import org.rubenrr.walkeitor.production.ProductionStrategy;

/**
 * User: Ruben Rubio Rey
 * Date: 10/06/13
 * Time: 4:12 PM
 */
public class StartProductionPrimitiveCommand implements PrimitiveCommand {

    // element will start the production
    private ProductionStrategy productor;

    private Commandable unit;

    public StartProductionPrimitiveCommand(ProductionStrategy productor, Commandable unit) {
        this.productor = productor;
        this.unit = unit;
    }

    @Override
    public void execute() {
        this.productor.startProduction();

        this.unit.nextCommand();
    }
}
