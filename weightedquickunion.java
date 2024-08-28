import java.util.*;
class weightedquickunion
{
    int id[],sz[];
    weightedquickunion(int n)
    {
        id=new int[n];
        for(int i=0;i<n;i++)
            id[i]=i;
        sz=new int[n];
        for(int i=0;i<n;i++)
            sz[i]=1;
    }
    int root(int i)
    {
        while(i!=id[i])
        {
            id[i]=id[id[i]];//implementing path compression
            i=id[i];
        }
        return i;
    }
    boolean connected(int p,int q)
    {
        return root(p)==root(q);
    }
    void union(int p,int q)//time complexity is reduced to log (base 2) n
    {
        int i=root(p);
        int j=root(q);
        if(i==j)
            return;
        if(sz[i]<sz[j])
        {
            id[i]=j;
            sz[j]+=sz[i];
        }
        else
        {
            id[j]=i;
            sz[i]+=sz[j];
        }
    }
    public void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of elements");
        int n=sc.nextInt();
        weightedquickunion obj=new weightedquickunion(n);
        do
        {
            System.out.println("1.Union\n2.Find\n3.Exit");
            int ch=sc.nextInt();
            if(ch==1)
            {
                System.out.println("Enter the elements to be connected");
                int a=sc.nextInt();
                int b=sc.nextInt();
                union(a,b);
            }
            else if(ch==2)
            {
                System.out.println("Enter the elements to be checked");
                int a=sc.nextInt();
                int b=sc.nextInt();
                if(connected(a,b))
                    System.out.println("Connected");
                else
                    System.out.println("Not Connected");
            }
            else
                break;
        }while(true);
    }
}