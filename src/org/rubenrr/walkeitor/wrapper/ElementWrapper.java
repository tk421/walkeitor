package org.rubenrr.walkeitor.wrapper;

import android.util.Log;
import org.rubenrr.walkeitor.config.ElementConfig;

/**
 * Element wrapper represents to every object that can be used in the scene
 * It is a wrapper that contains information about it
 *
 * User: Ruben Rubio Rey
 * Date: 5/04/13
 * Time: 8:22 PM
 */
public class ElementWrapper {

    public ElementWrapper(final ElementConfig element) {
        this.element = element;
    }

    private final ElementConfig element;

    public ElementConfig getElement() {
        return this.element;
    }

}
