package org.rubenrr.walkeitor.manager.action;

/**
 * User: Ruben Rubio Rey
 * Date: 20/04/13
 * Time: 7:23 PM
 */
public class TilePoint {
    final private int column;
    final private int row;

    public TilePoint(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TilePoint)) return false;

        TilePoint tilePoint = (TilePoint) o;

        if (column != tilePoint.column) return false;
        if (row != tilePoint.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }
}
