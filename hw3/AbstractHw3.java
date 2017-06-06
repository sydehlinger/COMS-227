package hw3;

/**
 * Abstract class containing the duplicated code for the subclasses
 * @author Sydney Ehlinger
 *
 */
public abstract class AbstractHw3 extends Sprite{
	
	/**
	 * Value of dx
	 */
	private double dxValue;
	
	/**
	 * Value of dy
	 */
	private double dyValue;
	
	/**
	 * Value of the left boundary
	 */
	private double boundLeft;
	
	/**
	 * Value of the right boundary
	 */
	private double boundRight;

	/**
	 * Constructor for the abstract class
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
	protected AbstractHw3(double x, double y, int width, int height, Renderer r){
		super(x, y, width, height, r);
		boundLeft = Double.NEGATIVE_INFINITY;
		boundRight = Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Sets the current direction to the given dx and dy values
	 * @param givenDx
	 * 	Change in x 
	 * @param givenDy
	 * 	Change in y
	 */
	public void setDirection(double givenDx, double givenDy){
		dxValue = givenDx;
		dyValue = givenDy;
	}
	
	/**
	 * Returns the current dx
	 * @return
	 * 	Change in x value (dx)
	 */
	public double getDx(){
		return dxValue;
	}
	
	/**
	 * Returns the current dy
	 * @return
	 * 	Change in y value (dy)
	 */
	public double getDy(){
		return dyValue;
	}
	
	/**
	 * Sets the left and right boundaries
	 * @param left
	 * 	Given left bound
	 * @param right
	 * 	Given right bound
	 */
	public void setBounds(double left, double right){
		boundLeft = left;
		boundRight = right;
	}
	
	/**
	 * Helper method used in both the override update() methods in Platform and Enemy to remove duplicated code.
	 * The current dx value is added to the x coordinate,the current dy value is added to the y coordinate. 
	 * However, the left side of the object is not allowed to go below the left boundary, and right side
	 * of the object is not allowed to go above the right boundary; if the boundary is reached in either 
	 * case, the dx value is reversed.
	 */
	protected void helper(){
		if(getXExact() + dxValue < boundLeft){
			setDirection(dxValue * -1, dyValue);
			setPosition(boundLeft, getYExact() + dyValue);
		}else if(getXExact() + getWidth() + dxValue > boundRight){
			setDirection(dxValue * -1, dyValue);
			setPosition(boundRight - getWidth(), getYExact() + dyValue);
		}else{
			setPosition(getXExact() + dxValue, getYExact() + dyValue);
		}
	}
}
