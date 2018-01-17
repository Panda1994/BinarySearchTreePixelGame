/*
 * this class implement pixel object
 */
public class Pixel {
	private Location p;//Location
	private int color;//Color
	/*
	 * the pixel include color and location 
	 */
	public Pixel(Location p,int color){//constructor
		this.p=p;
		this.color=color;
		
	}
	public Location getLocation(){//return location
		return p;
	}
	public int getColor(){//return color
		return color;
	}
}
