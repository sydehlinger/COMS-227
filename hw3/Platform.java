package hw3;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Class for Platform, which is a sprite whose position can change on each update. It also has 
 * the ability to automatically reverse its x-direction upon reaching a left or right boundary
 * @author Sydney Ehlinger
 *
 */
public class Platform extends AbstractHw3 {
		
	/**
	 * ArrayList that stores the enemy objects
	 */
	private ArrayList<Enemy> list;
	
	/**
	 * Constructor for Platform
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
	public Platform(double x, double y, int width, int height, Renderer r){
		super(x, y, width, height, r);
		list = new ArrayList<>();
	}
	
	/**
	 * Adds the given Enemy to this platform's list of children, and sets the child's parent to be this object
	 * @param e
	 * 	Given enemy
	 */
	public void addChild(Enemy e){ 
		list.add(e);
		e.setParent(this);
	}
	
	/**
	 * Returns this platform's list of children
	 * @return
	 * 	List of children
	 */
	public ArrayList<Enemy> getChildren(){ 
		return list;
	}
	
	/**
	 * Removes all children that have been marked for deletion
	 */
	public void deleteMarkedChildren(){
		for(int i = 0; i < list.size(); i += 1){
			if(list.get(i).shouldDelete()){
				list.remove(i);
			}
		}
	}

	/**
	 * Overrides Sprite's update() method, helper method is called to remove duplicated code. The update for each child object is called
	 */
	@Override
	public void update(){
		super.update();
		helper();
		for(int i = 0; i < list.size(); i += 1){
			Enemy e = list.get(i);
			e.update();
		}
	}
	
	/**
	 * Overrides Sprite's draw() method, calls draw() for each child
	 */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		for(int i = 0; i < list.size(); i += 1){
			list.get(i).draw(g);
		}
	}
}
