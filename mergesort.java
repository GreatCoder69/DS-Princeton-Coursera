public class mergesort
{
    static void merge(int a[],int aux[],int lo,int mid,int hi)
    {
        for(int k=lo;k<=hi;k++)
        {
            aux[k]=a[k];
        }
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++)
        {
            if(i>mid)
                a[k]=aux[j++];
            else if(j>hi)
                a[k]=aux[i++];
            else if(aux[i]<aux[j])
                a[k]=aux[i++];
            else
                a[k]=aux[j++];
        }
    }
    static void sort(int a[],int aux[],int lo,int hi)
    {
        if(lo>=hi)
            return;
        int mid=lo+(hi-lo)/2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        merge(a,aux,lo,mid,hi);
    }
    public static void main()
    {
        int a[]={1,3,2,7,5};
        int aux[]=new int[a.length];
        sort(a,aux,0,a.length-1);
        for(int i=0;i<a.length;i++)
        {
            System.out.println(a[i]);
        }
    }
}