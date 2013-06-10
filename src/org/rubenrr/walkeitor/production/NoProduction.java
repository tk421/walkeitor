package org.rubenrr.walkeitor.production;

import org.rubenrr.walkeitor.manager.util.Storage;

/**
 * User: Ruben Rubio Rey
 * Date: 26/05/13
 * Time: 6:16 PM
 */
public class NoProduction extends ProductionBase {

    public NoProduction() {
        super(null, new Storage(0), 0);
    }

    @Override
    public void generateProduction() {
        // Empty
    }

}
