import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private final int[][] arr;
    private final WeightedQuickUnionUF uf;
    private final int virtualTop;
    private final int virtualBottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size must be greater than 0");
        }

        N = n;
        arr = new int[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2); // +2 for virtual top and bottom
        virtualTop = n * n; // index for virtual top site
        virtualBottom = n * n + 1; // index for virtual bottom site

        // Initialize all sites to blocked (0)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = 0;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        int i = row - 1;
        int j = col - 1;

        if (arr[i][j] == 0) {
            arr[i][j] = 1;
            int currentIndex = i * N + j;

            // Connect to virtual top if in top row
            if (i == 0) {
                uf.union(currentIndex, virtualTop);
            }

            // Connect to virtual bottom if in bottom row
            if (i == N - 1) {
                uf.union(currentIndex, virtualBottom);
            }

            // Connect to adjacent open sites
            if (i > 0 && isOpen(row - 1, col)) { // above
                uf.union(currentIndex, (i - 1) * N + j);
            }
            if (i < N - 1 && isOpen(row + 1, col)) { // below
                uf.union(currentIndex, (i + 1) * N + j);
            }
            if (j > 0 && isOpen(row, col - 1)) { // left
                uf.union(currentIndex, i * N + (j - 1));
            }
            if (j < N - 1 && isOpen(row, col + 1)) { // right
                uf.union(currentIndex, i * N + (j + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return arr[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > N || col < 1 || col > N) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        int currentIndex = (row - 1) * N + (col - 1);
        return isOpen(row, col) && uf.find(currentIndex) == uf.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isOpen(i + 1, j + 1)) {
                    count++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }
}
