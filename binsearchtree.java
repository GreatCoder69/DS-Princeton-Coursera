class binsearchtree
{
    private class Node
    {
        int key,val;
        Node left;
        Node right;
        Node(int key,int val)
        {
            this.key=key;
            this.val=val;
        }
    }
    private Node root;
    public void put(int key,int val)
    {
        root=put(root,key,val);
    }
    private Node put(Node x,int key,int val)
    {
        if(x==null)
            return new Node(key,val);
        if(key<x.key)
            x.left=put(x.left,key,val);
        else if(key>x.key)
            x.right=put(x.right,key,val);
        else
            x.val=val;
        return x;
    }
    public int get(int key)
    {
        Node x=root;
        while(x!=null)
        {
            if(key<x.key)
                x=x.left;
            else if(key>x.key)
                x=x.right;
            else
                return x.val;
        }
        return -1;
    }
    public static void main(String[] args)
    {
        binsearchtree bst=new binsearchtree();
        bst.put(1,10);
        bst.put(2,30);
        bst.put(3,20);
        bst.put(4,50);
        bst.put(5,40);
        System.out.println(bst.get(3));
    }
}