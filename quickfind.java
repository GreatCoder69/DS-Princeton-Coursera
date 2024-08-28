import java.util.*;
class quickfind
{
    static int arr[];
    static void union(int a,int b)
    {
        for(int i=0;i<10;i++)
        {
            if(arr[i]==arr[a])
                arr[i]=arr[b];
        }
    }
    static void find(int a,int b)
    {
        if(arr[a]==arr[b])
            System.out.println("Connected");
        else
            System.out.println("Not Connected");
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        arr=new int[10];
        for(int i=0;i<10;i++)
            arr[i]=i;
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
                find(a,b);
            }
            else
                break;
        }while(true);
    }
}