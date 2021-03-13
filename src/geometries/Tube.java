package geometries;

import primitives.*;

/**
 * this class is used to represent a tube shape in 3D
 * 
 * @author David&Yishai
 *
 */
public class Tube {
	@Override
	public String toString() {
		return "axisRay= " + axisRay + ", radius=" + radius;
	}

	protected Ray axisRay;
	protected double radius;

	public Vector getNormal(Point3D a) {
		return null;
	}
}
