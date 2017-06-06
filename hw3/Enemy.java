package hw3;

/**
 * Class for Enemy, which is a sprite whose position can change on each update. It also has the ability to
 * automatically reverse its x-direction upon reaching a left or right boundary
 * @author Sydney Ehlinger
 *
 */
public class Enemy extends AbstractHw3{
	
	/**
	 * Parent platform
	 */
	private Platform parent;

	/**
	 * Constructor for Enemy
	 * @param x
	 * 	X coordinate
	 * @param y
	 * 	Y coordinate
	 * @param width
	 * 	Width
	 * @param height
	 * 	Height
	 * @param r
	 * 	Renderer for object
	 */
	public Enemy(double x, double y, int width, int height, Renderer r){
		super(x, y, width, height, r);
		parent = null;
	}
	
	/**
	 * Sets the parent of this object to the given platform.
	 * @param parent
	 * 	Given parent platform
	 */
	public void setParent(Platform parent){
		this.parent = parent;
	}
	
	/**
	 * Overrides Sprite's update() method, checks if it has a parent platform and 
	 * sets bounds and positions accordingly if it does not, calls a helper method if it does
	 */
	@Override
	public void update(){
		super.update();
		if(parent != null){
			setBounds(parent.getX(), parent.getX() + parent.getWidth());
			setPosition(super.getXExact() + parent.getDx(), super.getYExact());
		}	
		helper();
	}
}
