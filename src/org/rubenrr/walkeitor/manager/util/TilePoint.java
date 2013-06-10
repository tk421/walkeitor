package org.rubenrr.walkeitor.manager.util;

import org.rubenrr.walkeitor.config.TileConfig;

import java.util.Comparator;

/**
 * User: Ruben Rubio Rey
 * Date: 20/04/13
 * Time: 7:23 PM
 */
public class TilePoint  {
    final private int column;
    final private int row;

    public TilePoint(int column, int row) {
        // TODO move this to a config file
        if (column < 0 && column > 40 ) {
            throw new IllegalArgumentException("TilePoint column out of range: " + column);
        }

        if (row < 0 && row > 40 ) {
            throw new IllegalArgumentException("TilePoint row out of range: " + row);
        }

        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getEuclideanDistance( TilePoint point ) {
        return Distance.getIntEuclidean(this, point);
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

    @Override
    public String toString() {
        return "TilePoint{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }
}
