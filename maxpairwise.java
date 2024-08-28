import java.util.*;
class maxpairwise
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long a[]=new long[n];
        for(int i=0;i<n;i++)
            a[i]=sc.nextLong();
        Arrays.sort(a);
        long result=a[n-1]*a[n-2];
        System.out.println(result);
    }
}