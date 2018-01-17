
import java.util.ArrayList;

public class GraphicalObject implements GraphicalObjectADT {
	private int id;
	private int width;
	private int height;
	private String type;
	private Location pos;
	private BinarySearchTree tree;
	public GraphicalObject (int id, int width, int height, String type, Location pos){
		this.tree=new BinarySearchTree();//create a new tree which root is a leaf
		this.id=id;
		this.width=width;
		this.height=height;
		this.type=type;
		this.pos=pos;
	}
	@Override
	//return width
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}
	@Override
	//return height
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}
	@Override
	//return the object's type
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	@Override
	//return the id
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	//return the offset
	public Location getOffset() {
		// TODO Auto-generated method stub
		return this.pos;
	}
	@Override
	// reset the offset
	public void setOffset(Location value) {
		this.pos=value;
		// TODO Auto-generated method stub
		
	}
	@Override
	//set the type
	public void setType(String t) {
		this.type=t;
		// TODO Auto-generated method stub
		
	}
	@Override
	//add a new pixel in the object's tree if it exist already throw the exception
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		this.tree.put(tree.getRoot(), pix);// TODO Auto-generated method stub
		
	}
	@Override
	// this method check the intersects between target tree and this tree
	public boolean intersects(GraphicalObject fig) {
		if(tree.getRoot().isLeaf())//if this object is empty so it will not get intersects so return false
			return false;
		else if(fig.tree.getRoot().isLeaf())//if fig is empty object we still go false
			return false;
		else if(tree.getRoot().isLeaf() && fig.tree.getRoot().isLeaf()!=true)
			return false;
		else{//both of them have pixels in object we check if them get insects
		int offsetx=fig.getOffset().xCoord();
		int offsety=fig.getOffset().yCoord();
		int offset_x=this.getOffset().xCoord();
		int offset_y=this.getOffset().yCoord();
		ArrayList<Location> orgional=new ArrayList<Location>();//this list we store all the pixels after add offset location
		this.InorderTraverse(tree.getRoot(), orgional, offset_x, offset_y);
		ArrayList<Location> figlist=new ArrayList<Location>();//this to store the pixel in the fig add fig's offset location
		fig.InorderTraverse(fig.tree.getRoot(), figlist,offsetx,offsety);
		//we check every Location in both list, if there is a same item! we return true 
		//and the loop stop
		//if there is no insects we return false at the end
		for(int i=0;i<orgional.size();i++){
			for(int j=0;j<figlist.size();j++){
				if(orgional.get(i).compareTo(figlist.get(j))==0)
					return true;
				else
					continue;
			}
		}
		return false;
		}
	}
	//this is a helper method to traverse target binary tree and put all the node's location which is not a leaf plus the offset in to the arraylist
	private void InorderTraverse(BinaryNode r,ArrayList<Location> list,int offsetx,int offsety){
		if(r.isLeaf()!=true){
			InorderTraverse(r.getLeft(),list,offsetx,offsety);
				//add the new location to arraylist which is orgional plus offset x,y
				list.add(new Location(r.getData().getLocation().xCoord()+offsetx,r.getData().getLocation().yCoord()+offsety));//we add all the location in fig in a list
			InorderTraverse(r.getRight(),list,offsetx,offsety);
		}
	}
}
