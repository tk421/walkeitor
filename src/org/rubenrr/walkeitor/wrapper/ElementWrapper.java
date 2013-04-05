package org.rubenrr.walkeitor.wrapper;

import android.util.Log;

/**
 * Element wrapper represents to every object that can be used in the scene
 * It is a wrapper that contains information about it
 *
 * User: Ruben Rubio Rey
 * Date: 5/04/13
 * Time: 8:22 PM
 */
public class ElementWrapper {

    public ElementWrapper(String category, String type) {
        this(category, type, type);
    }

    public ElementWrapper(String category, String type, String subtype) {
        this.category = category;
        this.subtype = subtype;
        this.type = type;
    }

    private String category;
    private String subtype;
    private String type;

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePathNormal() {
        Log.d("ElementWrapper", "gfx/" + this.category + "/" + this.type + "/normal_" + this.subtype + ".png");
        return "gfx/" + this.category + "/" + this.type + "/normal_" + this.subtype + ".png";
    }



}
