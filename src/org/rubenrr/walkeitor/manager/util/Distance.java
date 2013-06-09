package org.rubenrr.walkeitor.manager.util;

import java.util.Comparator;

/**
 * User: Ruben Rubio Rey
 * Date: 9/06/13
 * Time: 5:07 PM
 */
public  class Distance {
    static  private double getEuclidean(TilePoint lhs, TilePoint rhs) {
        final double x1 = lhs.getColumn();
        final double y1 = lhs.getRow();
        final double x2 = rhs.getColumn();
        final double y2 = rhs.getRow();

        final double  xDiff = x1-x2;
        final double  xSqr  = Math.pow(xDiff, 2);

        final double yDiff = y1-y2;
        final double ySqr = Math.pow(yDiff, 2);

        final double distance = Math.sqrt(xSqr + ySqr);

        return distance ;
    }

    static public int getIntEuclidean(TilePoint lhs, TilePoint rhs) {
        return (int)Math.round(Distance.getEuclidean(lhs, rhs));
    }

}
