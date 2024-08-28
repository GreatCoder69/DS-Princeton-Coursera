import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points array is null");
        }

        int n = points.length;
        Point[] copy = new Point[n];
        for (int i = 0; i < n; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("point is null");
            }
            copy[i] = points[i];
        }

        Arrays.sort(copy);

        for (int i = 0; i < n; i++) {
            if (i > 0 && copy[i].compareTo(copy[i - 1]) == 0) {
                throw new IllegalArgumentException("duplicate points");
            }
        }

        segments = new ArrayList<>();

        for (int p = 0; p < n; p++) {
            List<Integer> indices = new ArrayList<>();
            for (int q = p + 1; q < n; q++) {
                double slope = copy[p].slopeTo(copy[q]);
                if (indices.isEmpty() || Double.compare(slope, copy[p].slopeTo(copy[indices.get(indices.size() - 1)])) == 0) {
                    indices.add(q);
                } else {
                    if (indices.size() >= 4) {
                        processCollinearPoints(copy, p, indices);
                    }
                    indices = new ArrayList<>();
                    indices.add(q);
                }
            }
            if (indices.size() >= 4) {
                processCollinearPoints(copy, p, indices);
            }
        }
    }

    private void processCollinearPoints(Point[] points, int p, List<Integer> indices) {
        indices.sort(Integer::compareTo);
        Point start = points[indices.get(0)];
        Point end = points[indices.get(indices.size() - 1)];
        segments.add(new LineSegment(points[p], end));
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
}