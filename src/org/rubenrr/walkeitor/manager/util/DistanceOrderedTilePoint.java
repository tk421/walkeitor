package org.rubenrr.walkeitor.manager.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * User: Ruben Rubio Rey
 * Date: 9/06/13
 * Time: 5:28 PM
 */
public class DistanceOrderedTilePoint {
    private TilePoint referenceTilePoint;
    private SortedSet<TilePoint> points;

    public DistanceOrderedTilePoint(final TilePoint referenceTilePoint) {
        this.referenceTilePoint = referenceTilePoint;
        // No thread synchronized Collections.synchronizedSortedSet
        this.points =  new TreeSet<TilePoint>(new Comparator<TilePoint>() {
            @Override
            public int compare(TilePoint lhs, TilePoint rhs) {

                int ldistance = DistanceOrderedTilePoint.this.referenceTilePoint.getEuclideanDistance(lhs);
                int rdistance = DistanceOrderedTilePoint.this.referenceTilePoint.getEuclideanDistance(rhs);

                int comparation;

                if (ldistance > rdistance ) {
                    comparation = 1;
                } else if ( ldistance < rdistance  ) {
                    comparation = -1;
                } else {
                    comparation = 0;
                }

                return comparation;

            }
        });
    }

    public void add(TilePoint point) {
        this.points.add(point);
    }

    public Iterator<TilePoint> iterator() {
        return points.iterator();
    }
}
