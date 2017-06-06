package mini1;

import java.awt.Rectangle;

public class Projectile {
	
	private double positionX;
	
	private double positionY;
	
	private double velocityX;
	
	private double velocityY;
	
	private int size;
	
	private boolean isAlive;
		
	private int age;
	
	public Projectile(double initialPositionX, double initialPositionY, double initialVelocityX, double initialVelocityY, int givenSize){
		positionX = initialPositionX;
		positionY = initialPositionY;
		velocityX = initialVelocityX;	
		velocityY = initialVelocityY;
		size = givenSize;
		isAlive = true;
	}

	
	public boolean collides(Projectile other){
		Projectile collide = new Projectile(positionX, positionY, velocityX, velocityY, size);
		Rectangle projectile1 = collide.getBoundingBox();
		Rectangle projectile2 = other.getBoundingBox();
		return projectile1.intersects(projectile2);
	}

	public int getAge(){
		return age;
	}
	
	public java.awt.Rectangle getBoundingBox(){
		int x = (int) Math.round(positionX);
		int y = (int) Math.round(positionY);
		int width = size;
		int height = size; 
		Rectangle rect = new Rectangle(x,y,width,height);
		return rect;
	}
	
	public double getVelocityX(){
		return velocityX;
	}
	
	public double getVelocityY(){
		return velocityY;
	}
	
	public double getX(){
		return positionX;
	}

	public double getY(){
		return positionY;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public void kill(){
		isAlive = false;
	}
	
	public void timeStep(){
		age = this.age + 1;
		positionX = this.positionX + velocityX;
		positionY = this.positionY + velocityY;
	}
	
	public void timeStep(double gravity){
		age = this.age + 1;
		positionX = this.positionX + velocityX;
		positionY = this.positionY + velocityY;
		velocityY = this.velocityY + gravity;
	}
	
}
