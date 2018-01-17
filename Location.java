/*
 * the location(x,y) of pixel
 */
public class Location {
	private int x;
	private int y;
	public Location(int x, int y){
		this.x=x;
		this.y=y;
	}
	public int xCoord(){
		return x;
	}
	public int yCoord(){
		return y;
	}
	/*
	 * compare the location with the target location
	 */
	public int compareTo(Location p){//compare the location with target location
		if( this.xCoord() > p.xCoord())
			return 1;
		else if( this.xCoord() == p.xCoord() && this.yCoord() > p.yCoord())
			return 1;
		else if( this.xCoord() == p.xCoord() && this.yCoord() == p.yCoord())
			return 0;
		else if( this.xCoord() < p.xCoord())
			return -1;
		else if(this.xCoord()==p.xCoord() && this.xCoord()<p.xCoord())
			return -1;
		else
			return -1;
	}
}
