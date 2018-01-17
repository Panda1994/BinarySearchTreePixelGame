/*
 * constructor the binary search tree 
 */
public class BinarySearchTree implements BinarySearchTreeADT {
	private BinaryNode root;
	//constructor create a bst the root is a leaf the parameter is r
	public BinarySearchTree(){
		this.root= new BinaryNode();
	}
	//return the root of this bst
	public BinaryNode getRoot() {
		return root;
	}

	// traverse the bst get the pixel, if the key exist
	/*
	 * get the target pixel object with the target key,if it is not exist return null
	 * @see asmt4.BinarySearchTreeADT#get(asmt4.BinaryNode, asmt4.Location)
	 */
	public Pixel get(BinaryNode r, Location key) {
		BinaryNode tmp=r;
		if(tmp.isLeaf() == true){
			return tmp.getData();
		}
		else if(key==null)
			return null;
		else{//because it is a binary search tree, therefore it follow the rule
			//left child lesser than right child
			while(tmp.isLeaf() != true){
				if(key.compareTo(tmp.getData().getLocation()) == -1)  
                    tmp=tmp.getLeft(); //if the current location lesser than root,go left to search
				//the root node will be the left child of previous root node
                else if(key.compareTo(tmp.getData().getLocation())==1)// if the location larger than root
                	//we go right to search and the root change to the right child of previous root
                    tmp=tmp.getRight();  
                else if(key.compareTo(tmp.getData().getLocation())==0) //if it is equal,we find the node we want
                	//return the pixel store in node
                    return tmp.getData(); 
			}
			//the target is not in the tree
			System.out.println("the target location was not be found in this tree");
			return null;
		}
		
	}

	/*
	 * this method  put the target in the tree, if it already exist throw exception then
	 * @see asmt4.BinarySearchTreeADT#put(asmt4.BinaryNode, asmt4.Pixel)
	 */
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		if(r.isLeaf()){
			r.setData(data);
			BinaryNode left=new BinaryNode();
			left.setParent(r);
			BinaryNode right=new BinaryNode();
			right.setParent(r);
			r.setLeft(left);
			r.setRight(right);
		}
		else{
			while(r.isLeaf() != true){
				if(data.getLocation().compareTo(r.getData().getLocation())==1){
					r=r.getRight();
				}
				else if(data.getLocation().compareTo(r.getData().getLocation())==-1){
					r=r.getLeft();
				}
				else
					throw new DuplicatedKeyException("the target location already in the tree \n");
			}
			r.setData(data);
			BinaryNode left=new BinaryNode();
			BinaryNode right=new BinaryNode();
			r.setLeft(left);
			left.setParent(r);
			r.setRight(right);
			right.setParent(r);
			/*if(data.getLocation().compareTo(p.getData().getLocation())==1)
				p.setRight(current);
			else if(data.getLocation().compareTo(p.getData().getLocation())==-1)
				p.setLeft(current);
			BinaryNode left= new BinaryNode();
			left.setParent(current);
			BinaryNode right=new BinaryNode();
			right.setParent(current);
			current.setLeft(left);
			current.setRight(right);*/
		}
	}

	/*
	 * this method to remove a target node in tree,if it is not in tree throw the exception
	 * @see asmt4.BinarySearchTreeADT#remove(asmt4.BinaryNode, asmt4.Location)
	 */
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		if (r.isLeaf())
		{
			// Go back up the recursive loop, but let our object know that the
			// element we wanted to delete wasn't found
			throw new InexistentKeyException ("the target pixel wasnt fount");
		}
		int compar=key.compareTo(r.getData().getLocation());
		// key<r get -1
		//key>r get 1
		//key=r get 0
		if(compar==-1){//the target in the left tree
			remove(r.getLeft(),key);
		}
		else if(compar==1){//the target in the right tree
			remove(r.getRight(),key);
		}
		else{//WOW we find the target !
			//the target has two children and both of them are not leaves
			if(r.getLeft().isLeaf()!=true && r.getRight().isLeaf()!=true){
				//we back up the parent left and right nodes
				Pixel rightmin=successor(r, key);//we get the successor of target 
				BinaryNode targetNode=getBinaryNode(r,rightmin.getLocation());//we find the successor's node
				BinaryNode targetNodeP=targetNode.getParent();//we find the successor's parent
				targetNodeP.setRight(targetNode.getRight());//we point the target's right to the target's parent
				r.setData(rightmin);//we change the data to the target's data
				int compare2=rightmin.getLocation().compareTo(targetNodeP.getData().getLocation());
				if(compare2==1){
					targetNodeP.setRight(targetNode.getRight());//if the target's parent lesser than target 
				}
				else if(compare2==-1){
					targetNodeP.setLeft(targetNode.getRight());//if target's parent larger than target
				}
			}
			else if(r.getLeft().isLeaf() && r.getRight().isLeaf()){//if the node we want delete has two children and both of them are leaves
				if(r.getParent()==null){
					r.setData(null);
				}
				else{
					int compare3=r.getData().getLocation().compareTo(r.getParent().getData().getLocation());
					if(compare3==1){
						BinaryNode n=new BinaryNode();
						r.getParent().setRight(n);
						n.setParent(r.getParent());
						r=null;
					}
					else{
						BinaryNode n=new BinaryNode();
						r.getParent().setLeft(n);
						n.setParent(r.getParent());
						r=null;
					}
				}
			}
			//if the node's left is not a leaf and right is a leaf
			else if(r.getLeft().isLeaf()!=true && r.getRight().isLeaf()){
				if(r.getParent()==null){//the node is a root
					BinaryNode left=r.getLeft();
					left.setParent(null);
					r=left;
				}
				else{
					int compare4=r.getParent().getData().getLocation().compareTo(r.getData().getLocation());
					if(compare4==1){
						r.getParent().setLeft(r.getLeft());
						r.getLeft().setParent(r.getParent());
					}
					else{
						r.getParent().setRight(r.getLeft());
						r.getLeft().setParent(r.getParent());
					}
				}
			}
			//the node's right is leaf and left not leaf
			else if(r.getLeft().isLeaf()!=true && r.getRight().isLeaf()){
				if(r.getParent()==null){
					BinaryNode right=r.getRight();
					right.setParent(null);
					r=right;
				}
				else{
					int compare4=r.getParent().getData().getLocation().compareTo(r.getData().getLocation());
					if(compare4==1){
						r.getParent().setLeft(r.getRight());
						r.getRight().setParent(r.getParent());
					}
					else{
						r.getParent().setRight(r.getRight());
						r.getRight().setParent(r.getParent());
					}
					
				}
			}
		}
	}
	/*
	 * this is a helper method to find the target node
	 */
	private BinaryNode getBinaryNode(BinaryNode r, Location key){

		BinaryNode tmp=r;
		if(r.isLeaf() == true){
			return r;
		}
		else{//because it is a binary search tree, therefore it follow the rule
			//left child lesser than right child
			while(tmp.isLeaf() != true){
				if(key.compareTo(tmp.getData().getLocation()) == -1)  
                    tmp=tmp.getLeft(); //if the current location lesser than root,go left to search
				//the root node will be the left child of previous root node
                else if(key.compareTo(tmp.getData().getLocation())==1)// if the location larger than root
                	//we go right to search and the root change to the right child of previous root
                    tmp=tmp.getRight();  
                else if(key.compareTo(tmp.getData().getLocation())==0) //if it is equal,we find the node we want
                	//return the pixel store in node
                    return tmp; 
			}
			//the target is not in the tree
			System.out.println("the target location was not be found in this tree");
			return null;
		}
		
	
	}
	/*
	 * find the smallest location larger than key location
	 * @see asmt4.BinarySearchTreeADT#successor(asmt4.BinaryNode, asmt4.Location)
	 */
	public Pixel successor(BinaryNode r, Location key) {
		Location rL=r.getData().getLocation();
		BinaryNode tmp=null;
		if(r.isLeaf()==true){
			return null;
		}
		else{//first we compare the key and root if key > or = the root we turn right
			if(key.compareTo(rL)==1 || key.compareTo(rL)==0){
				tmp=r.getRight();
				while(tmp.isLeaf()!=true){
					Location tmpl=tmp.getData().getLocation();
					if(tmpl.compareTo(key)==1){
						BinaryNode current=tmp;
						while(current.getLeft().isLeaf()!=true){
							current=current.getLeft();
						}
						return current.getData();
					}
					else{//tmp < key or equal to key tmp become the tmp's right
						tmp=tmp.getRight();
					}
				}
				return null;
			}
			else {//if key < root's location
				tmp=r.getLeft();//tmp equal to r's left child
				BinaryNode small=r;
				while(tmp.isLeaf()!=true){
					Location tmpl=tmp.getData().getLocation();
					if(tmpl.compareTo(key)==0)
						tmp=tmp.getRight();
					else if(tmpl.compareTo(key)==-1){
						tmp=tmp.getRight();
					}
					else{//tmpl>key
						small=tmp;//change the smallest to tmpl
						BinaryNode current=tmp.getLeft();
						while(current.isLeaf()!=true){
							if(current.getData().getLocation().compareTo(key)==1){
								small=current;
								current=current.getLeft();
							}
							else if(current.getData().getLocation().compareTo(key)==0){
								current=current.getRight();
							}
							else{
								current=current.getRight();
							}
						}
						return small.getData();
					}
				}
				return small.getData();//return the smallest's pixel
			}
		}		
	}

	/*find the largest node lesser than key*/
	public Pixel predecessor(BinaryNode r, Location key) {
		Pixel smallest=null;//small is null
		BinaryNode current=r;//we let current is root
		if(current.isLeaf())//if tree is empty return null
			return null;
		else{// if not
			while(current.isLeaf()!=true){
				if(current.getData().getLocation().compareTo(key)==0){
					if(current.getLeft().isLeaf()){
						return smallest;
					}
					else{
						try{
							smallest=largest(current.getLeft());
								return smallest;
						}
						catch(EmptyTreeException e){
						}
					}
				}
				else if(current.getData().getLocation().compareTo(key)==-1){
					//if current > large but smaller than key
					if(smallest==null){
						smallest = current.getData();
						current=current.getRight();
					}
					else if(smallest.getLocation().compareTo(current.getData().getLocation())==-1){
						smallest=current.getData();
						current=current.getRight();
					}
				}
				//if current>key
				else if(current.getData().getLocation().compareTo(key)==1){
					if(current.getLeft().isLeaf()){//if current is leaf
						return smallest;//return smallest
					}
					current=current.getLeft();//else go left
				}
			}
		}
		return smallest;//we return the smallest otherwise
	}

	//find the smallest node in this tree
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		if(r.isLeaf())
			throw new EmptyTreeException ("this tree is empty");//if root is leaf throw exception
		else{
			BinaryNode current=r;
			while(current.getLeft().isLeaf()!=true){
				current=current.getLeft();
			}
			return current.getData();
		}
	}

	//find the largest node in this tree
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		if(r.isLeaf()){
			throw new EmptyTreeException("this tree is empty");//if root is leaf throw exception
		}
		else{
			BinaryNode current=r;
			while(current.getRight().isLeaf()!=true){
				current=current.getRight();
			}
			return current.getData();
		}
	}

}
