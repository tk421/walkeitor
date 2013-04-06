package org.rubenrr.walkeitor.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Ruben Rubio Rey
 * Date: 6/04/13
 * Time: 12:42 PM
 */
public class MemoryStorage {

    private Map<String, Object> map;

    MemoryStorage () {
        this.map= new HashMap<String, Object>();
    }

    protected void put (String key, Object value ) {
        map.put(key, value);
    }

    protected Object get (String key) {
        return map.get(key);
    }

}
