class PriorityQueue 
{
    static int pq[];
    static int N = 0;
    public void insert(int x) 
    {
        pq[N++] = x;
    }
    void swap(int i,int j) 
    {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    void delmin() 
    {
        int min=0;
        for (int i=1;i<N;i++) 
            if (pq[i] < pq[min]) 
                min = i;
        swap(min, N - 1);
        N--;  // Reduce the size of the queue after removal
    }

    public static void main(String args[]) {
        int M = 2;  // Number of largest elements to print
        PriorityQueue obj = new PriorityQueue();
        obj.pq = new int[5];
        obj.insert(5);
        obj.insert(2);
        obj.insert(3);
        obj.insert(4);
        obj.insert(1);

        // If N > M, delete the smallest elements until we have only M elements left
        while (N > M) {
            obj.delmin();
        }

        // Print the top M largest elements
        for (int i = 0; i < M; i++) 
            System.out.println(obj.pq[i]);
    }
}
