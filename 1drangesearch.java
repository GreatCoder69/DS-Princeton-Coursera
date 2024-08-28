class 1drangesearch
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
    public int rank(int key)
    {
        return rank(root,key);
    }
    private int rank(Node x,int key)
    {
        if(x==null)
            return 0;
        if(key<x.key)
            return rank(x.left,key);
        else if(key>x.key)
            return 1+size(x.left)+rank(x.right,key);
        else
            return size(x.left);
    }
    private int size(Node x)
    {
        if(x==null)
            return 0;
        return 1+size(x.left)+size(x.right);
    }
}