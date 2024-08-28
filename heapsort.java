class heapsort
{
    int arr[];
    int N;
    void swap(int i,int j)
    {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    void constructheap()
    {
        for(int i=N/2;i>=1;i--)
            sink(i);
        while(N>1)
        {
            swap(1,N--);
            sink(1);
        }
    }
    void sink(int k)
    {
        while(2*k<=N)
        {
            int j=2*k;
            if(j<N && arr[j]<arr[j+1])
                j++;
            if(arr[k]>=arr[j])
                break;
            swap(k,j);
            k=j;
        }
    }
    public static void main(String args[])
    {
        heapsort h=new heapsort();
        h.arr=new int[]{0,4,3,7,1,8,5};
        h.N=h.arr.length-1;
        h.constructheap();
        for(int i=1;i<h.arr.length;i++)
            System.out.print(h.arr[i]+" ");
    }
}