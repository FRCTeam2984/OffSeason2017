package org.usfirst.frc.team2984.robot.util;

public class Motion {
	private double x;
	private double y;
	private double rotation;
	
	public Motion(double x, double y, double rotation) {
		this.x = x;
		this.y = y;
		this.rotation = rotation;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getRotation() {
		return this.rotation;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
	    if (!Motion.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    
	    final Motion other = (Motion) obj;
	    
	    double diffX = Math.abs(this.x - other.x);
	    double diffY = Math.abs(this.y - other.y);
	    double diffRotation = Math.abs(this.rotation - other.rotation);
	    
	    if (diffX > 0.0001 || diffY > 0.0001 || diffRotation > 0.0001) {
	    	return false;
	    }
	    
	    return true;
	}

	@Override
	public int hashCode() {
		return (int) this.x + (int) this.y + (int) this.rotation;
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + "," + this.rotation + ")";
	}
}
