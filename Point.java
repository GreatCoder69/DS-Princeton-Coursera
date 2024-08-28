import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x; // x-coordinate of the point
    private final int y; // y-coordinate of the point

    // Constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // Draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // String representation of the point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else {
            if (this.x < that.x) {
                return -1;
            } else if (this.x > that.x) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // The slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY; // Degenerate line segment (point)
        } else if (this.y == that.y) {
            return +0.0; // Horizontal line
        } else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY; // Vertical line
        } else {
            return (double) (that.y - this.y) / (that.x - this.x); // Slope formula
        }
    }

    // Compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                double slope1 = slopeTo(p1);
                double slope2 = slopeTo(p2);
                return Double.compare(slope1, slope2);
            }
        };
    }
}
