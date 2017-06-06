package hw3;

/**
 * Class for Projectile which is a sprite whose position can change on each update
 * @author Sydney Ehlinger
 *
 */
public class Projectile extends AbstractHw3{
	
	/**
	 * Value for gravity
	 */
	private double gravity;
	
	/**
	 * Value for ballistic
	 */
	private boolean ballistic;
	
	/**
	 * Constructor for Projectile
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
	 */
	public Projectile(double x, double y, int width, int height, Renderer r){
		super(x, y, width, height, r);
	}
	
	/**
	 * Sets the current direction to the given dx and dy values. However, when the object is ballistic, the dx value is not modified.
	 */
	public void setDirection(double givenDx, double givenDy){
		if(isBallistic()){
			super.setDirection(this.getDx(), givenDy);;
		}else{
			super.setDirection(givenDx, givenDy);
		}
	}
	
	/**
	 * Sets the gravity
	 * @param givenGravity
	 * 	Value of gravity to be added
	 */
	public void setGravity(double givenGravity){
		gravity = givenGravity;
	}
	
	/**
	 * Sets the "ballistic" status to the given value.
	 * @param givenIsBallistic
	 * 	If the projectile is ballistic or not
	 */
	public void setBallistic(boolean givenIsBallistic){
		ballistic = givenIsBallistic;
	}
	
	/**
	 * Checks if the object is ballistic
	 * @return
	 * 	Value for ballistic
	 */
	public boolean isBallistic(){
		return ballistic;
	}
	
	/**
	 * Overrides Sprite's update() method, the current dx value is added to the x coordinate, the current dy value is added to the y coordinate, and the gravity is added to the dy value
	 */
	@Override
	public void update(){
		super.update();
		super.setPosition(super.getXExact() + super.getDx() , super.getYExact() + super.getDy());
		setDirection(getDx(), getDy() + gravity);
	}
}
