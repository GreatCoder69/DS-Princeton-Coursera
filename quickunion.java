import java.util.*;
class quickunion
{
    int id[];
    quickunion(int n)
    {
        id=new int[n];
        for(int i=0;i<n;i++)
        {
            id[i]=i;
        }
    }
    int root(int i)
    {
        while(i!=id[i])
        {
            i=id[i];
        }
        return i;
    }
    boolean connected(int p,int q)
    {
        return root(p)==root(q);
    }
    void union(int p,int q)
    {
        int i=root(p);
        int j=root(q);
        id[i]=j;
    }
    public void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of elements");
        int n=sc.nextInt();
        quickunion obj=new quickunion(n);
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