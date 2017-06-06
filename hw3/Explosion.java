package hw3;

/**
 * Class constructs an explosion for when the enemy is "killed"
 * @author Sydney Ehlinger
 *
 */
public class Explosion extends AbstractHw3{
	
	/**
	 * Remaining life
	 */
	private int count;
	
	/**
	 * Constructor for explosion
	 * @param x
	 * 	X coordinate
	 * @param y
	 * 	Y coordinate
	 * @param width
	 * 	Width
	 * @param height
	 * 	Height
	 * @param r
	 * 	Renderer for the object
	 * @param initialCount
	 * 	Initial life
	 */
	public Explosion(double x, double y, int width, int height, Renderer r, int initialCount){
		super(x, y, width, height, r);
		count = initialCount;
	}
	
	/**
	 * Gets object's remaining life
	 * @return
	 * 	remaining life
	 */
	public int getCount(){
		return count;
	}
	
	/**
	 * Overrides Sprite's update() method, reduces remaining life by one each time called, after called the life amount the object marks itself for deletion
	 */
	@Override
	public void update(){
		super.update();
		count -= 1;
		if(count == 0){
			super.markForDeletion();
		}
	}
}
