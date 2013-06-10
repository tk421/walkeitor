package org.rubenrr.walkeitor.manager.command.primitive;

import org.rubenrr.walkeitor.element.building.Building;
import org.rubenrr.walkeitor.production.ProductionStrategy;

/**
 * User: Ruben Rubio Rey
 * Date: 10/06/13
 * Time: 4:12 PM
 */
public class StartProductionPrimitiveCommand implements PrimitiveCommand {

    // element will start the production
    ProductionStrategy productor;

    public StartProductionPrimitiveCommand(ProductionStrategy productor) {
        this.productor = productor;
    }

    @Override
    public void execute() {
        this.productor.startProduction();
    }
}
