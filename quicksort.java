class quicksort
{
    static int a[];
    public static void swap(int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static int partition(int lo,int hi)
    {
        int i=lo,j=hi+1;
        while(true)
        {
            while(a[++i]<a[lo])
            {
                if(i==hi)
                    break;
            }
            while(a[lo]<a[--j])
            {
                if(j==lo)
                    break;
            }
            if(i>=j)
                break;
            swap(i,j);
        }
        swap(lo,j);
        return j;
    }
    public static void sort(int lo,int hi)
    {
        if(hi<=lo)
            return;
        int j=partition(lo,hi);
        sort(lo,j-1);
        sort(j+1,hi);
    }
    public static void main(String args[])
    {
        a=new int[]{5,2,3,4,1};
        sort(0,a.length-1);
        for(int i=0;i<a.length;i++)
        {
            System.out.println(a[i]);
        }
    }
}