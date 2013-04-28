package org.rubenrr.walkeitor.menu;

/**
 * User: Ruben Rubio Rey
 * Date: 28/04/13
 * Time: 5:26 PM
 */
public class MenuOption {
    private String label;

    public MenuOption(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "MenuOption{" +
                "label='" + label + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuOption)) return false;

        MenuOption that = (MenuOption) o;

        if (label != null ? !label.equals(that.label) : that.label != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return label != null ? label.hashCode() : 0;
    }
}
