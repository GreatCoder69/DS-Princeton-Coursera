import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] segments;
    private int numberOfSegments;

    // Finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Points array cannot be null");

        int n = points.length;
        Point[] copy = new Point[n];

        // Check for any null points
        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Point cannot be null");
            copy[i] = points[i];
        }

        // Sort points in natural order
        Arrays.sort(copy);

        // Check for duplicate points
        for (int i = 1; i < n; i++) {
            if (copy[i].equals(copy[i - 1])) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }

        segments = new LineSegment[1]; // initial capacity, will resize dynamically
        numberOfSegments = 0;

        // Find all 4-point line segments
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = copy[i];
                        Point q = copy[j];
                        Point r = copy[k];
                        Point s = copy[l];

                        double slopePQ = p.slopeTo(q);
                        double slopePR = p.slopeTo(r);
                        double slopePS = p.slopeTo(s);

                        // Check if the points are collinear
                        if (slopePQ == slopePR && slopePQ == slopePS) {
                            // Create a line segment from the smallest to the largest point
                            LineSegment segment = new LineSegment(p, s);
                            if (!contains(segment)) {
                                addSegment(segment);
                            }
                        }
                    }
                }
            }
        }
    }

    // Add a new line segment to the segments array
    private void addSegment(LineSegment segment) {
        if (numberOfSegments == segments.length) {
            resize(2 * segments.length);
        }
        segments[numberOfSegments++] = segment;
    }

    // Check if the segment is already present
    private boolean contains(LineSegment segment) {
        for (int i = 0; i < numberOfSegments; i++) {
            if (segments[i].equals(segment)) {
                return true;
            }
        }
        return false;
    }

    // Resize the segments array
    private void resize(int capacity) {
        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(segments, 0, temp, 0, numberOfSegments);
        segments = temp;
    }

    // Return the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // Return the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments);
    }
}
