public class BinaryNode {
	private Pixel data;
	private BinaryNode left;
	private BinaryNode right;
	private BinaryNode parent;
	//initialize the Node to specified node
	public BinaryNode(Pixel value,BinaryNode left, BinaryNode right,BinaryNode parent){
		this.data=value;
		this.left=left;
		this.right=right;
		this.parent=parent;
	}
	//initialize the node to null
	public BinaryNode(){
		this.data=null;
		this.left=null;
		this.right=null;
		this.parent=null;
	}
	//get the current node'parent
	public BinaryNode getParent(){
		return parent;
	}
	//set current parent to the specified parent
	public void setParent(BinaryNode parent){
		this.parent=parent;
	}
	//set the left node
	public void setLeft(BinaryNode left){
		this.left=left;
	}
	//set the right child
	public void setRight(BinaryNode right){
		this.right=right;
	}
	//set the pixel to specified pixel
	public void setData(Pixel value){
		this.data=value;
	}
	// if this node is leaf return true ,false otherwise
	public boolean isLeaf(){
		if(this.data==null)
			return true;
		else
			return false;
	}
	//get the Pixel data
	public Pixel getData(){
		return this.data;
	}
	//get the left node
	public BinaryNode getLeft(){
		return this.left;
	}
	//get the nodes'right child
	public BinaryNode getRight(){
		return this.right;
	}
}
